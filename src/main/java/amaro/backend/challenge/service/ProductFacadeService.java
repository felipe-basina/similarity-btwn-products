package amaro.backend.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amaro.backend.challenge.component.ProductFactoryComponent;
import amaro.backend.challenge.exception.ProductNotFoundException;
import amaro.backend.challenge.model.ProductRequest;
import amaro.backend.challenge.model.ProductResponse;
import amaro.backend.challenge.model.ProductWrapper;
import amaro.backend.challenge.model.SimilarProductFinderResponse;
import amaro.backend.challenge.model.SimilarProductFinderWrapper;

@Service
@SuppressWarnings("unchecked")
public class ProductFacadeService {

	private ProductFactoryComponent productFactoryComponent;
	
	@Autowired
	public ProductFacadeService(ProductFactoryComponent productFactoryComponent) {
		this.productFactoryComponent = productFactoryComponent;
	}
	
	public ProductWrapper<ProductRequest, ProductResponse> addTagVector(ProductRequest productRequest) {
		ProductWrapper<ProductRequest, ProductResponse> input = new ProductWrapper<>(productRequest);
		return this.productFactoryComponent
				.getImplementationFrom(TagService.TAG_SERVICE_QUALIFIER)
			.process(input);
	}
	
	public ProductWrapper<SimilarProductFinderWrapper, SimilarProductFinderResponse> findSimilarity(SimilarProductFinderWrapper similarWrapper) 
		throws ProductNotFoundException {
		ProductWrapper<SimilarProductFinderWrapper, SimilarProductFinderResponse> input = 
				new ProductWrapper<>(similarWrapper);
		return this.productFactoryComponent
				.getImplementationFrom(SimilarityService.SIMILARITY_SERVICE_QUALIFIER)
			.process(input);
	}
	
}
