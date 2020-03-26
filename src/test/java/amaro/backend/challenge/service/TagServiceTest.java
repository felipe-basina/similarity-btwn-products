package amaro.backend.challenge.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import amaro.backend.challenge.CommonsBase;
import amaro.backend.challenge.model.ProductRequest;
import amaro.backend.challenge.model.ProductResponse;
import amaro.backend.challenge.model.ProductWrapper;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TagServiceTest extends CommonsBase {

	@Autowired
	@Qualifier(value = TagService.TAG_SERVICE_QUALIFIER)
	private IProductService<ProductRequest, ProductResponse> tagService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testProcess() {
		ProductRequest productRequest = this.createProductRequest();
		
		ProductWrapper<ProductRequest, ProductResponse> productWrapper = 
				new ProductWrapper<ProductRequest, ProductResponse>(productRequest);
		
		this.tagService.process(productWrapper);
		
		Assert.assertFalse(productWrapper.getResponse().isEmpty());
		productWrapper.getResponse().forEach(product -> {
			Assert.assertFalse(product.getTagsVector().isEmpty());
		});
	}

}
