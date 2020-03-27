package amaro.backend.challenge.api.advice;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import amaro.backend.challenge.api.TagsVectorApiController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(assignableTypes = { TagsVectorApiController.class })
@SuppressWarnings("rawtypes")
public class TagsVectorApiControllerAdvice extends BaseAdvice {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity exceptionHandler(Exception e) {
		log.error("Error when processing products/tags", e);
		if (this.isBadRequestForMissingInputData(e)) {
			return this.missingInputData(e.getLocalizedMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(this.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
	}
	
	private boolean isBadRequestForMissingInputData(Exception e) {
		if (Objects.nonNull(e.getLocalizedMessage()) && e.getLocalizedMessage().contains(BAD_REQUEST_PREFIX_ERROR)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	private ResponseEntity missingInputData(final String message) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(this.errorResponse(message, HttpStatus.BAD_REQUEST.value()));
	}
	
}
