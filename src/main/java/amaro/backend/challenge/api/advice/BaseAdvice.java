package amaro.backend.challenge.api.advice;

import java.time.LocalDateTime;

import amaro.backend.challenge.model.ApiErrorResponse;

public class BaseAdvice {

	public static final String BAD_REQUEST_PREFIX_ERROR = "Validation failed for argument";
	
	protected ApiErrorResponse errorResponse(final String message, final int httpStatus) {
		return new ApiErrorResponse()
				.message(message)
				.status(httpStatus)
				.timestamp(LocalDateTime.now().toString());
	}
	
}
