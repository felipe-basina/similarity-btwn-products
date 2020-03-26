package amaro.backend.challenge.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SimilarProductFinderWrapper {

	private Long productId;
	
	private List<SimilarProductFinderRequest> similars;
	
}
