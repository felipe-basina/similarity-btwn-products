package amaro.backend.challenge.component;

import java.util.Map;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductTagComponentTest {

	@Autowired
	private ProductTagComponent productTagComponent;
	
	private final int totalOfTags = 20;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetTags() {
		Map<Integer, String> tagsMap = this.productTagComponent.getTags();
		Assert.assertFalse(tagsMap.isEmpty());
		Assert.assertEquals(this.totalOfTags, tagsMap.size());
	}

	@Test
	public void testContainsMetalTag() {
		this.productTagComponent.getTags();
		Assert.assertTrue(this.productTagComponent.containsTag("metal"));
	}

	@Test
	public void testContainsCottonTag() {
		this.productTagComponent.getTags();
		Assert.assertFalse(this.productTagComponent.containsTag("cotton"));
	}
	
	@Test
	public void testGetTagByIdx0() {
		this.productTagComponent.getTags();
		Optional<String> optionalTag = this.productTagComponent.getByIndex(0);
		Assert.assertTrue(optionalTag.isPresent());
		Assert.assertEquals("neutro", optionalTag.get());
	}
	
	@Test
	public void testGetTagByIdx20() {
		this.productTagComponent.getTags();
		Optional<String> optionalTag = this.productTagComponent.getByIndex(20);
		Assert.assertFalse(optionalTag.isPresent());
	}

}
