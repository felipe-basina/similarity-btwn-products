package amaro.backend.challenge.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import amaro.backend.challenge.CommonsBase;
import amaro.backend.challenge.component.SimilarityComponent;
import amaro.backend.challenge.model.SimilarProductFinderRequest;
import amaro.backend.challenge.model.SimilarProductFinderResponse;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductSimilarityServiceTest extends CommonsBase {

	@Autowired
	private ProductSimilarityService productSimilarityService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetSimilarity() {
		SimilarProductFinderRequest referenceProduct = this.createSimilarProductFinderRequest(110012L,
				"Reference Product", Arrays.asList(1, 0, 0, 1));
		SimilarProductFinderRequest comparingProduct = this.createSimilarProductFinderRequest(330445L,
				"Comparing Product", Arrays.asList(0, 0, 1, 0));
		
		SimilarProductFinderResponse similarProductFinderResponse = this.productSimilarityService
				.getSimilarity(referenceProduct, comparingProduct);
		
		BigDecimal _036 = BigDecimal.valueOf(0.36).setScale(SimilarityComponent.DEFAULT_SCALE, RoundingMode.FLOOR);
		
		Assert.assertNotNull(similarProductFinderResponse);
		Assert.assertEquals(comparingProduct.getId(), similarProductFinderResponse.getId());
		Assert.assertEquals(comparingProduct.getName(), similarProductFinderResponse.getName());
		Assert.assertEquals(_036, similarProductFinderResponse.getSimilarity());
	}

}
