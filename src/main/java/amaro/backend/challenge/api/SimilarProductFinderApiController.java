package amaro.backend.challenge.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import amaro.backend.challenge.model.SimilarProductFinderRequest;
import amaro.backend.challenge.model.SimilarProductFinderResponse;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-03-26T01:08:13.280Z")

@Slf4j
@Controller
public class SimilarProductFinderApiController implements SimilarProductFinderApi {

    public ResponseEntity<List<SimilarProductFinderResponse>> getSimilarProducts(@ApiParam(value = "The product ID to fetch similar products",required=true) @PathVariable("productId") Integer productId,@ApiParam(value = "An array of available products with its charcateristics" ,required=true )  @Valid @RequestBody List<SimilarProductFinderRequest> body) {
        return new ResponseEntity<List<SimilarProductFinderResponse>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
