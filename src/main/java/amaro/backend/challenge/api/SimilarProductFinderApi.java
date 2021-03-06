/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.12).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package amaro.backend.challenge.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import amaro.backend.challenge.model.ApiErrorResponse;
import amaro.backend.challenge.model.SimilarProductFinderRequest;
import amaro.backend.challenge.model.SimilarProductFinderResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-03-26T01:08:13.280Z")

@Api(value = "similarProductFinder", description = "the similarProductFinder API", tags = {"SimilarProductFinder"})
@RequestMapping
public interface SimilarProductFinderApi {

    @ApiOperation(value = "Returns a list with the three most similar products according to the product specified by its id as parameter", nickname = "getSimilarProducts", notes = "", response = SimilarProductFinderResponse.class, responseContainer = "List", tags={ "SimilarProductFinder", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = SimilarProductFinderResponse.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "product not found", response = ApiErrorResponse.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ApiErrorResponse.class) })
    @RequestMapping(value = "/similarProductFinder/{productId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<List<SimilarProductFinderResponse>> getSimilarProducts(@ApiParam(value = "The product ID to fetch similar products",required=true) @PathVariable("productId") Integer productId,@ApiParam(value = "An array of available products with its charcateristics" ,required=true )  @Valid @RequestBody List<SimilarProductFinderRequest> body);

}
