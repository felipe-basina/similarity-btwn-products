package amaro.backend.challenge.service;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import amaro.backend.challenge.CommonsBase;
import amaro.backend.challenge.model.Product;
import amaro.backend.challenge.model.ProductResponse;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductTagServiceTest extends CommonsBase {

	@Autowired
	private ProductTagService productTagService;
	
	private static final int BALADA_INDEX = 9;
	private static final int METAL_INDEX = 15;
	private static final int DELICADO_INDEX = 17;
	private static final int DESCOLADO_INDEX = 18;
	private static final int TOTAL_TAGS = 20;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddTagsVectorTokens() {
		Product product = this.createProduct();
		
		ProductResponse productResponse = this.productTagService.addTagsVectorTokens(product);
		
		Assert.assertNotNull(productResponse);
		Assert.assertNotNull(productResponse.getTagsVector());
		Assert.assertFalse(productResponse.getTagsVector().isEmpty());
		Assert.assertTrue(TOTAL_TAGS == productResponse.getTagsVector().size());
		Assert.assertTrue(ProductTagService.FOUND_TAG_TOKEN == productResponse.getTagsVector().get(BALADA_INDEX));
		Assert.assertTrue(ProductTagService.FOUND_TAG_TOKEN == productResponse.getTagsVector().get(METAL_INDEX));
		Assert.assertTrue(ProductTagService.FOUND_TAG_TOKEN == productResponse.getTagsVector().get(DELICADO_INDEX));
		Assert.assertTrue(ProductTagService.FOUND_TAG_TOKEN == productResponse.getTagsVector().get(DESCOLADO_INDEX));
		Assert.assertEquals(4l,
				productResponse.getTagsVector().stream().filter(index -> index == ProductTagService.FOUND_TAG_TOKEN).count());
		Assert.assertEquals(16l,
				productResponse.getTagsVector().stream().filter(index -> index == ProductTagService.NOT_FOUND_TAG_TOKEN).count());
	}

	@Test
	public void testAddTagsVectorTokensWithNoTags() {
		Product product = this.createProduct();
		product.tags(Collections.emptyList());
		
		ProductResponse productResponse = this.productTagService.addTagsVectorTokens(product);
		
		Assert.assertNotNull(productResponse);
		Assert.assertNotNull(productResponse.getTagsVector());
		Assert.assertFalse(productResponse.getTagsVector().isEmpty());
		Assert.assertTrue(TOTAL_TAGS == productResponse.getTagsVector().size());
		Assert.assertEquals(0l,
				productResponse.getTagsVector().stream().filter(index -> index == ProductTagService.FOUND_TAG_TOKEN).count());
		Assert.assertEquals(TOTAL_TAGS,
				productResponse.getTagsVector().stream().filter(index -> index == ProductTagService.NOT_FOUND_TAG_TOKEN).count());
	}

	@Test
	public void testAddTagsVectorTokensWithAllTags() {
		Product product = this.createProduct();
		product.tags(this.allTags);
		
		ProductResponse productResponse = this.productTagService.addTagsVectorTokens(product);
		
		Assert.assertNotNull(productResponse);
		Assert.assertNotNull(productResponse.getTagsVector());
		Assert.assertFalse(productResponse.getTagsVector().isEmpty());
		Assert.assertTrue(TOTAL_TAGS == productResponse.getTagsVector().size());
		Assert.assertEquals(TOTAL_TAGS,
				productResponse.getTagsVector().stream().filter(index -> index == ProductTagService.FOUND_TAG_TOKEN).count());
		Assert.assertEquals(0l,
				productResponse.getTagsVector().stream().filter(index -> index == ProductTagService.NOT_FOUND_TAG_TOKEN).count());
	}

}
