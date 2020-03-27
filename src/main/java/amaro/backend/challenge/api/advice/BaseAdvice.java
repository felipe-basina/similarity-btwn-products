package amaro.backend.challenge.api.advice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import amaro.backend.challenge.model.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("rawtypes")
public class BaseAdvice {

	public static final List<String> BAD_REQUEST_PREFIXS_ERROR = Arrays.asList("Validation failed for argument", "Required request body is missing");
	
	protected ApiErrorResponse errorResponse(final String message, final int httpStatus) {
		return new ApiErrorResponse()
				.message(message)
				.status(httpStatus)
				.timestamp(LocalDateTime.now().toString());
	}
	
	@ExceptionHandler(value = Exception.class)
	protected ResponseEntity exceptionHandler(Exception e) {
		log.error("Error when processing products/tags", e);
		if (this.isBadRequestForMissingInputData(e)) {
			return this.missingInputData(e.getLocalizedMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(this.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
	}
	
	protected boolean isBadRequestForMissingInputData(Exception e) {
		if (Objects.nonNull(e.getLocalizedMessage()) && this.containsBadRequestErrorMessage(e.getLocalizedMessage())) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	private boolean containsBadRequestErrorMessage(final String errorMessage) {
		return BAD_REQUEST_PREFIXS_ERROR.stream().anyMatch(prefix -> errorMessage.contains(prefix));
	}
	
	protected ResponseEntity missingInputData(final String message) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(this.errorResponse(message, HttpStatus.BAD_REQUEST.value()));
	}
	
}
