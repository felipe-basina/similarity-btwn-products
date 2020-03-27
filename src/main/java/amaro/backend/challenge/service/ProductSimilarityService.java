package amaro.backend.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amaro.backend.challenge.component.SimilarityComponent;
import amaro.backend.challenge.model.SimilarProductFinderRequest;
import amaro.backend.challenge.model.SimilarProductFinderResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductSimilarityService {

	private SimilarityComponent similarityComponent;
	
	@Autowired
	public ProductSimilarityService(SimilarityComponent similarityComponent) {
		this.similarityComponent = similarityComponent;
	}
	
	public SimilarProductFinderResponse getSimilarity(SimilarProductFinderRequest referenceProduct,
			SimilarProductFinderRequest comparingProduct) {
		log.info("Comparing [{} | {}] x [{} | {}]", 
				referenceProduct.getId(), referenceProduct.getName(),
				comparingProduct.getId(), comparingProduct.getName());
		return new SimilarProductFinderResponse()
				.id(comparingProduct.getId())
				.name(comparingProduct.getName())
				.similarity(this.similarityComponent
						.calculateSimilarity(referenceProduct.getTagsVector(), comparingProduct.getTagsVector()));
	}
	
}
