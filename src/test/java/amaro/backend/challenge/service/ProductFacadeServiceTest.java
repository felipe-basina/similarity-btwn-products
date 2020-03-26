package amaro.backend.challenge.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import amaro.backend.challenge.CommonsBase;
import amaro.backend.challenge.model.ProductRequest;
import amaro.backend.challenge.model.ProductResponse;
import amaro.backend.challenge.model.ProductWrapper;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductFacadeServiceTest extends CommonsBase {

	@Autowired
	private ProductFacadeService productFacadeService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddTagVector() {
		ProductRequest productRequest = this.createProductRequest();
		
		ProductWrapper<ProductRequest, ProductResponse> wrapper = this.productFacadeService
				.addTagVector(productRequest);
		
		Assert.assertFalse(wrapper.getResponse().isEmpty());
		wrapper.getResponse().forEach(product -> {
			Assert.assertFalse(product.getTagsVector().isEmpty()); 
		});
	}

}
