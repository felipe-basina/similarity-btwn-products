package amaro.backend.challenge.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductWrapperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateProductWrapperForProduct() {
		List<Product> products = this.createEmptyProductList();
		List<ProductResponse> productResponses = this.createProductResponseList();
		
		ProductWrapper<Product, ProductResponse> productWrapper = new ProductWrapper<>(products);
		productWrapper.addResponse(productResponses);
		
		Assert.assertTrue(productWrapper.getListInput().isEmpty());
		Assert.assertFalse(productWrapper.getResponse().isEmpty());
		Assert.assertEquals(productResponses.size(), productWrapper.getResponse().size());
	}
	
	@Test
	public void testCreateProductWrapperForSimilarProductFinderRequest() {
		SimilarProductFinderWrapper inputWrapper = this.createSimilarProductFinderWrapper();
		List<SimilarProductFinderResponse> similarResponses = this.createSimilarProductFinderResponseList();
		
		ProductWrapper<SimilarProductFinderWrapper, SimilarProductFinderResponse> similarWrapper = 
				new ProductWrapper<>(inputWrapper);
		similarWrapper.addResponse(similarResponses);
		
		Assert.assertNotNull(similarWrapper.getInput());
		Assert.assertTrue(similarWrapper.getInput().isPresent());
		
		SimilarProductFinderWrapper input = similarWrapper.getInput().get();
		
		Assert.assertEquals(inputWrapper.getProductId(), input.getProductId());
		Assert.assertTrue(input.getProducts().isEmpty());
		Assert.assertFalse(similarWrapper.getResponse().isEmpty());
		Assert.assertEquals(similarResponses.size(), similarWrapper.getResponse().size());
		Assert.assertFalse(similarWrapper.isListInput());
	}
	
	private List<Product> createEmptyProductList() {
		return Collections.emptyList();
	}
	
	private List<ProductResponse> createProductResponseList() {
		return Arrays.asList(this.createProductResponse());
	}
	
	private ProductResponse createProductResponse() {
		return new ProductResponse();
	}
	
	private SimilarProductFinderWrapper createSimilarProductFinderWrapper() {
		return new SimilarProductFinderWrapper(1L, this.createEmptySimilarProductFinderRequestList());
	}
	
	private List<SimilarProductFinderRequest> createEmptySimilarProductFinderRequestList() {
		return Collections.emptyList();
	}
	
	private List<SimilarProductFinderResponse> createSimilarProductFinderResponseList() {
		return Arrays.asList(this.createSimilarProductFinderResponse());
	}
	
	private SimilarProductFinderResponse createSimilarProductFinderResponse() {
		return new SimilarProductFinderResponse();
	}

}
