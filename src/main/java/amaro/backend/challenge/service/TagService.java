package amaro.backend.challenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import amaro.backend.challenge.model.Product;
import amaro.backend.challenge.model.ProductRequest;
import amaro.backend.challenge.model.ProductResponse;
import amaro.backend.challenge.model.ProductWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Qualifier(value = TagService.TAG_SERVICE_QUALIFIER)
public class TagService implements IProductService<ProductRequest, ProductResponse> {

	public static final String TAG_SERVICE_QUALIFIER = "TagService";
	
	private ProductTagService productTagService;
	
	@Autowired
	public TagService(ProductTagService productTagService) {
		this.productTagService = productTagService;
	}
	
	@Override
	public ProductWrapper<ProductRequest, ProductResponse> process(
			ProductWrapper<ProductRequest, ProductResponse> input) {
		List<ProductResponse> productResponses = new ArrayList<>();
		ProductRequest productRequest = input.getInput().get();
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(this.forkJoinSize(productRequest.getProducts()));
		try {
			forkJoinPool.submit(() -> productRequest.getProducts()
				.parallelStream()
					.forEach(product -> {
						productResponses.add(this.productTagService.addTagsVectorTokens(product));
					}))
				.get();
		} catch (Exception e) {
			log.error("Error processing products", e);
		} finally {
			forkJoinPool.shutdown();
		}
		
		return input.addResponse(productResponses);
	}
	
	private int forkJoinSize(List<Product> products) {
		return products.size() > 1 ? products.size() / 2 : 1;
	}

}
