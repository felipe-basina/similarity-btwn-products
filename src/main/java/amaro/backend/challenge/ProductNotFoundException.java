package amaro.backend.challenge;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -529237327408192180L;

	public ProductNotFoundException(String message) {
		super(message);
	}
	
}
