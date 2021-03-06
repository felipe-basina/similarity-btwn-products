package amaro.backend.challenge.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import amaro.backend.challenge.CommonsBase;
import amaro.backend.challenge.exception.ProductNotFoundException;
import amaro.backend.challenge.model.ProductWrapper;
import amaro.backend.challenge.model.SimilarProductFinderResponse;
import amaro.backend.challenge.model.SimilarProductFinderWrapper;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SimilarityServiceTest extends CommonsBase {

	@Autowired
	private SimilarityService similarityService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testProcess() {
		ProductWrapper<SimilarProductFinderWrapper, SimilarProductFinderResponse> input = this.createValidInput();
		input = this.similarityService.process(input);
		Assert.assertFalse(input.getResponse().isEmpty());
		Assert.assertEquals(TOTAL_MOST_SIMILAR_PRODUCTS, input.getResponse().size());
	}
	
	@Test(expected = ProductNotFoundException.class)
	public void testProductNotFoundException() {
		ProductWrapper<SimilarProductFinderWrapper, SimilarProductFinderResponse> input = this.createInvalidInput();
		this.similarityService.process(input);
	}

}
