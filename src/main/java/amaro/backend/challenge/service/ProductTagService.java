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
	
	public static final int FOUND_TAG_TOKEN = 1;
	public static final int NOT_FOUND_TAG_TOKEN = 0;
	
	@Autowired
	public ProductTagService(ProductTagComponent productTagComponent) {
		this.productTagComponent = productTagComponent;
	}
	
	public ProductResponse addTagsVectorTokens(Product product) {
		return new ProductResponse()
				.id(product.getId())
				.name(product.getName())
				.tags(product.getTags())
				.tagsVector(this.getTagToken(product.getTags()));
	}
	
	private List<Integer> getTagToken(List<String> tags) {
		Map<Integer, String> tagsMap = this.productTagComponent.getTags();
		return tagsMap.entrySet().stream()
				.map(tag -> tags.contains(tag.getValue()) ? FOUND_TAG_TOKEN : NOT_FOUND_TAG_TOKEN)
			.collect(Collectors.toList());
	}
	
}
