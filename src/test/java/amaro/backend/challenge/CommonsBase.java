package amaro.backend.challenge;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

import amaro.backend.challenge.model.Product;
import amaro.backend.challenge.model.ProductRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonsBase {

	private String productsFileName = "products.json";
	
	protected List<String> allTags = Arrays.asList("neutro", "veludo", "couro", "basics", "festa", "workwear", "inverno", "boho",
			"estampas", "balada", "colorido", "casual", "liso", "moderno", "passeio", "metal", "viagem", "delicado", "descolado", "elastano");
	
	protected Product createProduct() {
		return new Product()
				.id(58345L)
				.name("Saia Maxi Chiffon Saint")
				.tags(Arrays.asList("balada", "metal", "delicado", "descolado"));
	}
	
	protected ProductRequest createProductRequest() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(this.getFileContentAsText(), ProductRequest.class);
		} catch (Exception e) {
			log.error("Error converting products object", e);
		}
		return null;
	}
	
	private String getFileContentAsText() {
		try {
			InputStream inputStream = new ClassPathResource(this.productsFileName).getInputStream();
			return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
		} catch (IOException e) {
			log.error("Error reading file from classpath", e);
		}
		return null;
	}
	
}
