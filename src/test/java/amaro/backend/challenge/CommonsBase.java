package amaro.backend.challenge;

import java.util.Arrays;
import java.util.List;

import amaro.backend.challenge.model.Product;

public class CommonsBase {

	protected List<String> allTags = Arrays.asList("neutro", "veludo", "couro", "basics", "festa", "workwear", "inverno", "boho",
			"estampas", "balada", "colorido", "casual", "liso", "moderno", "passeio", "metal", "viagem", "delicado", "descolado", "elastano");
	
	protected Product createProduct() {
		return new Product()
				.id(58345L)
				.name("Saia Maxi Chiffon Saint")
				.tags(Arrays.asList("balada", "metal", "delicado", "descolado"));
	}
	
}
