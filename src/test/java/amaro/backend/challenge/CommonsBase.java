package amaro.backend.challenge;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import amaro.backend.challenge.model.Product;
import amaro.backend.challenge.model.ProductRequest;
import amaro.backend.challenge.model.ProductWrapper;
import amaro.backend.challenge.model.SimilarProductFinderRequest;
import amaro.backend.challenge.model.SimilarProductFinderResponse;
import amaro.backend.challenge.model.SimilarProductFinderWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonsBase {

	protected String productsFileName = "products.json";
	protected String productsWithTagsVectorFileName = "products-with-tags-vector.json";
	
	protected List<String> allTags = Arrays.asList("neutro", "veludo", "couro", "basics", "festa", "workwear", "inverno", "boho",
			"estampas", "balada", "colorido", "casual", "liso", "moderno", "passeio", "metal", "viagem", "delicado", "descolado", "elastano");
	
	protected static final long VESTIDO_WRAP_FLEUR_ID = 7516l;
	protected static final long INVALID_PRODUCT_ID = 101l;
	protected static final int TOTAL_MOST_SIMILAR_PRODUCTS = 3;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	protected Product createProduct() {
		return new Product()
				.id(58345L)
				.name("Saia Maxi Chiffon Saint")
				.tags(Arrays.asList("balada", "metal", "delicado", "descolado"));
	}
	
	protected ProductRequest createProductRequest() {
		try {
			return objectMapper.readValue(this.getFileContentAsText(this.productsFileName), ProductRequest.class);
		} catch (Exception e) {
			log.error("Error converting products object", e);
		}
		return null;
	}
	
	protected SimilarProductFinderWrapper createSimilarProductFinderWrapper(final Long productId) {
		return new SimilarProductFinderWrapper(productId, this.createSimilarProductFinderRequestList());
	}
	
	protected List<SimilarProductFinderRequest> createSimilarProductFinderRequestList() {
		try {
			SimilarProductFinderRequest[] products = objectMapper
					.readValue(this.getFileContentAsText(this.productsWithTagsVectorFileName), 
							SimilarProductFinderRequest[].class);
			return Arrays.asList(products);
		} catch (Exception e) {
			log.error("Error converting products object", e);
		}
		return null;
	}
	
	private String getFileContentAsText(final String fileName) {
		try {
			InputStream inputStream = new ClassPathResource(fileName).getInputStream();
			return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
		} catch (IOException e) {
			log.error("Error reading file {} from classpath", fileName, e);
		}
		return null;
	}
	
	protected String convertToJson(Object object) {
		try {
			return this.objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("Error converting object to string", e);
		}
		return null;
	}
	
	protected SimilarProductFinderRequest createSimilarProductFinderRequest(Long id, String name, List<Integer> tagsVector) {
		return new SimilarProductFinderRequest()
				.id(id)
				.name(name)
				.tagsVector(tagsVector);
	}
	
	protected ProductWrapper<SimilarProductFinderWrapper, SimilarProductFinderResponse> createValidInput() {
		return this.createInput(VESTIDO_WRAP_FLEUR_ID);
	}
	
	protected ProductWrapper<SimilarProductFinderWrapper, SimilarProductFinderResponse> createInvalidInput() {
		return this.createInput(INVALID_PRODUCT_ID);
	}
	
	protected ProductWrapper<SimilarProductFinderWrapper, SimilarProductFinderResponse> createInput(
			final Long productId) {
		SimilarProductFinderWrapper similarWrapper = this.createSimilarProductFinderWrapper(productId);
		return new ProductWrapper<SimilarProductFinderWrapper, SimilarProductFinderResponse>(similarWrapper);
	}
	
}
