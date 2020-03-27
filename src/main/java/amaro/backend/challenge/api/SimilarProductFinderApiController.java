package amaro.backend.challenge.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import amaro.backend.challenge.ProductNotFoundException;
import amaro.backend.challenge.model.ApiErrorResponse;
import amaro.backend.challenge.model.ProductWrapper;
import amaro.backend.challenge.model.SimilarProductFinderRequest;
import amaro.backend.challenge.model.SimilarProductFinderResponse;
import amaro.backend.challenge.model.SimilarProductFinderWrapper;
import amaro.backend.challenge.service.ProductFacadeService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-03-26T01:08:13.280Z")

@Slf4j
@Validated
@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SimilarProductFinderApiController implements SimilarProductFinderApi {

	private ProductFacadeService productFacadeService;
	
	private MessageSource messageSource;
	
	@Autowired
	public SimilarProductFinderApiController(ProductFacadeService productFacadeService, MessageSource messageSource) {
		this.productFacadeService = productFacadeService;
		this.messageSource = messageSource;
	}
	
	public ResponseEntity getSimilarProducts(
			@ApiParam(value = "The product ID to fetch similar products", required = true) @PathVariable("productId") Integer productId,
			@ApiParam(value = "An array of available products with its charcateristics", required = true) @RequestBody List<SimilarProductFinderRequest> products)
			throws ProductNotFoundException {
		if (products.isEmpty()) {
			return this.badRequestResponse();
		}

		log.info("Finding similar products for ID {}", productId);
		
		SimilarProductFinderWrapper similarWrapper = this.createSimilarProductFinderWrapper(productId, products);
		ProductWrapper<SimilarProductFinderWrapper, SimilarProductFinderResponse> productWrapper = this.productFacadeService
				.findSimilarity(similarWrapper);
		return ResponseEntity.status(HttpStatus.OK).body(productWrapper.getResponse());
	}
	
	private ResponseEntity badRequestResponse() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiErrorResponse()
						.message(this.messageSource.getMessage("product.list.notempty.notnull", null, Locale.ENGLISH))
						.status(HttpStatus.BAD_REQUEST.value())
						.timestamp(LocalDateTime.now().toString()));
	}
		
	private SimilarProductFinderWrapper createSimilarProductFinderWrapper(Integer productId,
			List<SimilarProductFinderRequest> products) {
		return new SimilarProductFinderWrapper(productId.longValue(), products);
	}

}
