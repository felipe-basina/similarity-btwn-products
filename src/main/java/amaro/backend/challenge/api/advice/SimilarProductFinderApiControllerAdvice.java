package amaro.backend.challenge.api.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import amaro.backend.challenge.ProductNotFoundException;
import amaro.backend.challenge.api.SimilarProductFinderApiController;

@RestControllerAdvice(assignableTypes = { SimilarProductFinderApiController.class })
@SuppressWarnings("rawtypes")
public class SimilarProductFinderApiControllerAdvice extends BaseAdvice {

	@ExceptionHandler(value = ProductNotFoundException.class)
	protected ResponseEntity productNotFoundExceptionHandler(ProductNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(this.errorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value()));
	}

}
