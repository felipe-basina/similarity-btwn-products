package amaro.backend.challenge.component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

@Component
public class SimilarityComponent {

	public static final int POW_2 = 2;
	public static final int DEFAULT_SCALE = 2;
	
	public BigDecimal calculateSimilarity(List<Integer> productTagsVector, List<Integer> comparingProductTagsVector) {
		BigDecimal distance = this.calculateEuclideanDistance(productTagsVector, comparingProductTagsVector);
		return BigDecimal.ONE
				.divide(BigDecimal.ONE.add(distance), DEFAULT_SCALE, RoundingMode.FLOOR);
	}
	
	private BigDecimal calculateEuclideanDistance(List<Integer> productTagsVector, List<Integer> comparingProductTagsVector) {
		return BigDecimal
				.valueOf(Math.sqrt(this.sumUpPow2Difference(productTagsVector, comparingProductTagsVector)));
	}
	
	private double sumUpPow2Difference(List<Integer> productTagsVector, List<Integer> comparingProductTagsVector) {
		return IntStream.range(0, productTagsVector.size())
				.mapToObj(index -> productTagsVector.get(index) - comparingProductTagsVector.get(index))
				.mapToDouble(difference -> Math.pow(Double.valueOf(difference), POW_2))
			.sum();
	}
	
}
