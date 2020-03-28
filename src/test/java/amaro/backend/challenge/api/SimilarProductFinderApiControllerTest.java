package amaro.backend.challenge.api;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import amaro.backend.challenge.CommonsBase;
import amaro.backend.challenge.api.advice.BaseAdvice;
import amaro.backend.challenge.api.advice.SimilarProductFinderApiControllerAdvice;
import amaro.backend.challenge.model.SimilarProductFinderRequest;
import amaro.backend.challenge.service.ProductFacadeService;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SimilarProductFinderApiControllerTest extends CommonsBase {

	@Autowired
	private ProductFacadeService productFacadeService;
	
	@Autowired
	private MessageSource messageSource;
	
	private SimilarProductFinderApiController similarProductFinderApiController;
	
	private MockMvc mockMvc;
	
	private String endpoint = "/similarProductFinder/";
	
	private static final int FIRST_PRODUCT_ID = 8358;
	private static final String FIRST_PRODUCT_NAME = "VESTIDO REGATA FEMININO COM GOLA";
	private static final BigDecimal FIRST_PRODUCT_SIMILARITY = BigDecimal.valueOf(0.33);
	private static final int SECOND_PRODUCT_ID = 7518;
	private static final String SECOND_PRODUCT_NAME = "VESTIDO CAMISETA FANCY";
	private static final BigDecimal SECOND_PRODUCT_SIMILARITY = BigDecimal.valueOf(0.33);
	private static final int THIRD_PRODUCT_ID = 8360;
	private static final String THIRD_PRODUCT_NAME = "VESTIDO FEMININO CANELADO";
	private static final BigDecimal THIRD_PRODUCT_SIMILARITY = BigDecimal.valueOf(0.30);
	
	@Before
	public void setUp() throws Exception {
		this.similarProductFinderApiController = new SimilarProductFinderApiController(this.productFacadeService,
				this.messageSource);
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.similarProductFinderApiController)
				.setControllerAdvice(new SimilarProductFinderApiControllerAdvice())
			.build();
	}

	@Test
	public void testGetSimilarProducts() throws Exception {
		List<SimilarProductFinderRequest> products = this.createSimilarProductFinderRequestList();
		
		this.mockMvc.perform(post(this.endpoint + VESTIDO_WRAP_FLEUR_ID)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(this.convertToJson(products)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(TOTAL_MOST_SIMILAR_PRODUCTS)))
				.andExpect(jsonPath("$.[0].id", is(FIRST_PRODUCT_ID)))
				.andExpect(jsonPath("$.[0].name", is(FIRST_PRODUCT_NAME)))
				.andExpect(jsonPath("$.[0].similarity", is(FIRST_PRODUCT_SIMILARITY.doubleValue())))
				.andExpect(jsonPath("$.[1].id", is(SECOND_PRODUCT_ID)))
				.andExpect(jsonPath("$.[1].name", is(SECOND_PRODUCT_NAME)))
				.andExpect(jsonPath("$.[1].similarity", is(SECOND_PRODUCT_SIMILARITY.doubleValue())))
				.andExpect(jsonPath("$.[2].id", is(THIRD_PRODUCT_ID)))
				.andExpect(jsonPath("$.[2].name", is(THIRD_PRODUCT_NAME)))
				.andExpect(jsonPath("$.[2].similarity", is(THIRD_PRODUCT_SIMILARITY.doubleValue())))
			.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testGetSimilarProductsWithBadRequestNullProductList() throws Exception {
		List<SimilarProductFinderRequest> products = null;
		
		this.mockMvc.perform(post(this.endpoint + VESTIDO_WRAP_FLEUR_ID)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(this.convertToJson(products)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.timestamp").exists())
				.andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
				.andExpect(jsonPath("$.message", containsString(BaseAdvice.BAD_REQUEST_PREFIXS_ERROR.get(1))))
			.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testGetSimilarProductsWithBadRequestEmptyProductList() throws Exception {
		List<SimilarProductFinderRequest> products = Collections.emptyList();
		
		final String message = this.messageSource.getMessage("product.list.notempty.notnull", null, Locale.ENGLISH);
		
		this.mockMvc.perform(post(this.endpoint + VESTIDO_WRAP_FLEUR_ID)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(this.convertToJson(products)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.timestamp").exists())
				.andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
				.andExpect(jsonPath("$.message", is(message)))
			.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testProductNotFoundException() throws Exception {
		List<SimilarProductFinderRequest> products = this.createSimilarProductFinderRequestList();
		
		final String message = this.messageSource.getMessage("product.not.found.exception", 
				new Object[] { INVALID_PRODUCT_ID }, Locale.ENGLISH);
		
		this.mockMvc.perform(post(this.endpoint + INVALID_PRODUCT_ID)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(this.convertToJson(products)))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.timestamp").exists())
				.andExpect(jsonPath("$.status", is(HttpStatus.NOT_FOUND.value())))
				.andExpect(jsonPath("$.message", is(message)))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testInternalServerError() throws Exception {
		List<SimilarProductFinderRequest> products = this.createSimilarProductFinderRequestList()
				.subList(0, 1);
		Assert.assertEquals(1, products.size());
		
		this.mockMvc.perform(post(this.endpoint + products.get(0).getId())
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(this.convertToJson(products)))
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.timestamp").exists())
				.andExpect(jsonPath("$.status", is(HttpStatus.INTERNAL_SERVER_ERROR.value())))
				.andExpect(jsonPath("$.message").exists())
			.andDo(MockMvcResultHandlers.print());
	}

}
