package amaro.backend.challenge.component;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import amaro.backend.challenge.service.IProductService;
import amaro.backend.challenge.service.TagService;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest
@SuppressWarnings("rawtypes")
public class ProductFactoryComponentTest {

	@Autowired
	private ProductFactoryComponent productFactoryComponent;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetImpementationFromTagServiceQualifier() {
		IProductService productService = this.productFactoryComponent
				.getImpementationFrom(TagService.TAG_SERVICE_QUALIFIER);
		Assert.assertTrue(productService instanceof TagService);
	}

}
