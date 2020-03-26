package amaro.backend.challenge.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import amaro.backend.challenge.model.Product;
import amaro.backend.challenge.model.ProductResponse;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-03-26T01:08:13.280Z")

@Slf4j
@Controller
public class TagsVectorApiController implements TagsVectorApi {

	public ResponseEntity<List<ProductResponse>> createCharacteristicsTags(
			@ApiParam(value = "An array of available products", required = true) @Valid @RequestBody List<Product> body) {
		return new ResponseEntity<List<ProductResponse>>(HttpStatus.NOT_IMPLEMENTED);
	}

}
