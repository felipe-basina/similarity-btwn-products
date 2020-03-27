package amaro.backend.challenge.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import amaro.backend.challenge.model.ProductRequest;
import amaro.backend.challenge.model.ProductResponse;
import amaro.backend.challenge.model.ProductWrapper;
import amaro.backend.challenge.service.ProductFacadeService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-03-26T01:08:13.280Z")

@Slf4j
@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class TagsVectorApiController implements TagsVectorApi {

	private ProductFacadeService productFacadeService;
	
	@Autowired
	public TagsVectorApiController(ProductFacadeService productFacadeService) {
		this.productFacadeService = productFacadeService;
	}
	
	public ResponseEntity createCharacteristicsTags(
			@ApiParam(value = "An array of available products", required = true) @Valid @RequestBody ProductRequest products) {
		log.info("Processing products");
		ProductWrapper<ProductRequest, ProductResponse> productWrapper = this.productFacadeService.addTagVector(products); 
		return ResponseEntity.status(HttpStatus.OK).body(productWrapper.getResponse());
	}

}
