package amaro.backend.challenge.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amaro.backend.challenge.component.ProductTagComponent;
import amaro.backend.challenge.model.Product;
import amaro.backend.challenge.model.ProductResponse;

@Service
public class ProductTagService {

	private ProductTagComponent productTagComponent;
	
	@Autowired
	public ProductTagService(ProductTagComponent productTagComponent) {
		this.productTagComponent = productTagComponent;
	}
	
	public ProductResponse addTagsVectorIndexes(Product product) {
		return new ProductResponse()
				.id(product.getId())
				.name(product.getName())
				.tags(product.getTags())
				.tagsVector(this.getTagIndexes(product.getTags()));
	}
	
	private List<Integer> getTagIndexes(List<String> tags) {
		Map<Integer, String> tagsMap = this.productTagComponent.getTags();
		return tagsMap.entrySet().stream()
				.map(tag -> tags.contains(tag.getValue()) ? 1 : 0)
			.collect(Collectors.toList());
	}
	
}
