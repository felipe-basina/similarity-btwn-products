package amaro.backend.challenge.api.advice;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import amaro.backend.challenge.api.TagsVectorApiController;

@RestControllerAdvice(assignableTypes = { TagsVectorApiController.class })
public class TagsVectorApiControllerAdvice extends BaseAdvice {
	
}
