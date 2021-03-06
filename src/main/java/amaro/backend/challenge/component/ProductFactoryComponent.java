package amaro.backend.challenge.component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import amaro.backend.challenge.model.ProductRequest;
import amaro.backend.challenge.model.ProductResponse;
import amaro.backend.challenge.model.SimilarProductFinderResponse;
import amaro.backend.challenge.model.SimilarProductFinderWrapper;
import amaro.backend.challenge.service.IProductService;
import amaro.backend.challenge.service.SimilarityService;
import amaro.backend.challenge.service.TagService;

@Component
@SuppressWarnings("rawtypes")
public class ProductFactoryComponent {

	@Autowired
	@Qualifier(value = TagService.TAG_SERVICE_QUALIFIER)
	private IProductService<ProductRequest, ProductResponse> tagService;
	
	@Autowired
	@Qualifier(value = SimilarityService.SIMILARITY_SERVICE_QUALIFIER)
	private IProductService<SimilarProductFinderWrapper, SimilarProductFinderResponse> similarityService;
	
	private Map<String, IProductService> implementations = new HashMap<>();
	
	private IProductService getImpl(final String qualifier) {
		if (this.implementations.isEmpty()) {
			this.implementations.put(TagService.TAG_SERVICE_QUALIFIER, this.tagService);
			this.implementations.put(SimilarityService.SIMILARITY_SERVICE_QUALIFIER, this.similarityService);
		}
		return this.implementations.get(qualifier);
	}
	
	public IProductService getImplementationFrom(final String qualifier) {
		return this.getImpl(qualifier);
	}
	
}
