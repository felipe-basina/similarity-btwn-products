package amaro.backend.challenge.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import amaro.backend.challenge.exception.ProductNotFoundException;
import amaro.backend.challenge.model.ProductWrapper;
import amaro.backend.challenge.model.SimilarProductFinderRequest;
import amaro.backend.challenge.model.SimilarProductFinderResponse;
import amaro.backend.challenge.model.SimilarProductFinderWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Qualifier(value = SimilarityService.SIMILARITY_SERVICE_QUALIFIER)
public class SimilarityService implements IProductService<SimilarProductFinderWrapper, SimilarProductFinderResponse> {

	public static final String SIMILARITY_SERVICE_QUALIFIER = "SimilarityService";
	
	private ProductSimilarityService productSimilarityService;
	
	private MessageSource messageSource;
	
	@Value(value = "${application.total.most.similar.products}")
	private int totalMostSimilarProducts;
	
	@Autowired
	public SimilarityService(ProductSimilarityService productSimilarityService, MessageSource messageSource) {
		this.productSimilarityService = productSimilarityService;
		this.messageSource = messageSource;
	}
	
	@Override
	public ProductWrapper<SimilarProductFinderWrapper, SimilarProductFinderResponse> process(
			ProductWrapper<SimilarProductFinderWrapper, SimilarProductFinderResponse> input)
			throws ProductNotFoundException {
		SimilarProductFinderWrapper similarWrapper = input.getInput().get();
		
		Optional<SimilarProductFinderRequest> referenceProductOptional = this.getReferenceProduct(similarWrapper);
		if (!referenceProductOptional.isPresent()) {
			throw new ProductNotFoundException(this.messageSource.getMessage("product.not.found.exception", 
					new Object[] { similarWrapper.getProductId() }, Locale.ENGLISH));
		}
		
		List<SimilarProductFinderResponse> similarResponses = new ArrayList<>();
		List<SimilarProductFinderRequest> products = this.getAllButReferenceProduct(similarWrapper);
		SimilarProductFinderRequest referenceProduct = referenceProductOptional.get();
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(this.forkJoinSize(products));
		try {
			forkJoinPool.submit(() -> products.parallelStream()
					.forEach(comparingProduct -> {
						similarResponses.add(this.productSimilarityService.getSimilarity(referenceProduct, comparingProduct));
					}))
				.get();
		} catch (Exception e) {
			log.error("Error processing products", e);
		} finally {
			forkJoinPool.shutdown();
		}
		
		Collections.sort(similarResponses);
		
		return input.addResponse(similarResponses.subList(0, this.totalMostSimilarProducts));
	}
	
	private Optional<SimilarProductFinderRequest> getReferenceProduct(SimilarProductFinderWrapper similarWrapper) {
		return similarWrapper.getProducts().stream()
				.filter(product -> similarWrapper.getProductId().compareTo(product.getId()) == 0)
			.findFirst();
	}
	
	private List<SimilarProductFinderRequest> getAllButReferenceProduct(SimilarProductFinderWrapper similarWrapper) {
		return similarWrapper.getProducts().stream()
				.filter(product -> similarWrapper.getProductId().compareTo(product.getId()) != 0)
			.collect(Collectors.toList());		
	}
	
	private int forkJoinSize(List<SimilarProductFinderRequest> products) {
		return products.size() > 1 ? products.size() / 2 : 1;
	}
	
}
