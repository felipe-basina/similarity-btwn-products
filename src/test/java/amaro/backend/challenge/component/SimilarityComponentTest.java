package amaro.backend.challenge.component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SimilarityComponentTest {

	@Autowired
	private SimilarityComponent similarityComponent;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalculateSimilarity_036() {
		List<Integer> productTagsVector = Arrays.asList(1, 0, 0, 1);
		List<Integer> comparingProductTagsVector = Arrays.asList(0, 0, 1, 0);

		BigDecimal _036 = BigDecimal.valueOf(0.36).setScale(SimilarityComponent.DEFAULT_SCALE, RoundingMode.FLOOR);

		BigDecimal similarity = this.similarityComponent.calculateSimilarity(productTagsVector, comparingProductTagsVector);
		Assert.assertEquals(_036, similarity);
	}

	@Test
	public void testCalculateSimilarity_050() {
		List<Integer> productTagsVector = Arrays.asList(0, 0, 0, 1);
		List<Integer> comparingProductTagsVector = Arrays.asList(0, 0, 0, 0);

		BigDecimal _050 = BigDecimal.valueOf(0.50).setScale(SimilarityComponent.DEFAULT_SCALE, RoundingMode.FLOOR);

		BigDecimal similarity = this.similarityComponent.calculateSimilarity(productTagsVector, comparingProductTagsVector);
		Assert.assertEquals(_050, similarity);
	}

	@Test
	public void testCalculateSimilarity_030() {
		List<Integer> productTagsVector = Arrays.asList(0, 1, 0, 1, 0);
		List<Integer> comparingProductTagsVector = Arrays.asList(1, 0, 1, 0, 1);

		BigDecimal _030 = BigDecimal.valueOf(0.30).setScale(SimilarityComponent.DEFAULT_SCALE, RoundingMode.FLOOR);

		BigDecimal similarity = this.similarityComponent.calculateSimilarity(productTagsVector, comparingProductTagsVector);
		Assert.assertEquals(_030, similarity);
	}

	@Test
	public void testCalculateSimilarity_033() {
		List<Integer> productTagsVector = Arrays.asList(0, 0, 0, 0, 1);
		List<Integer> comparingProductTagsVector = Arrays.asList(1, 1, 1, 1, 1);

		BigDecimal _033 = BigDecimal.valueOf(0.33).setScale(SimilarityComponent.DEFAULT_SCALE, RoundingMode.FLOOR);

		BigDecimal similarity = this.similarityComponent.calculateSimilarity(productTagsVector, comparingProductTagsVector);
		Assert.assertEquals(_033, similarity);
	}

	@Test
	public void testCalculateSimilarity_1() {
		List<Integer> productTagsVector = Arrays.asList(0, 0, 0, 0, 0);
		List<Integer> comparingProductTagsVector = Arrays.asList(0, 0, 0, 0, 0);

		BigDecimal _10 = BigDecimal.valueOf(1.00).setScale(SimilarityComponent.DEFAULT_SCALE, RoundingMode.FLOOR);

		BigDecimal similarity = this.similarityComponent.calculateSimilarity(productTagsVector, comparingProductTagsVector);
		Assert.assertEquals(_10, similarity);
	}

	@Test
	public void testCalculateSimilarity_1v2() {
		List<Integer> productTagsVector = Arrays.asList(1, 1, 1, 1, 1);
		List<Integer> comparingProductTagsVector = Arrays.asList(1, 1, 1, 1, 1);

		BigDecimal _10 = BigDecimal.valueOf(1.00).setScale(SimilarityComponent.DEFAULT_SCALE, RoundingMode.FLOOR);

		BigDecimal similarity = this.similarityComponent.calculateSimilarity(productTagsVector, comparingProductTagsVector);
		Assert.assertEquals(_10, similarity);
	}

}
