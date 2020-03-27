package amaro.backend.challenge.api;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import amaro.backend.challenge.CommonsBase;
import amaro.backend.challenge.api.advice.BaseAdvice;
import amaro.backend.challenge.api.advice.TagsVectorApiControllerAdvice;
import amaro.backend.challenge.model.ProductRequest;
import amaro.backend.challenge.service.ProductFacadeService;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TagsVectorApiControllerTest extends CommonsBase {

	@Autowired
	private ProductFacadeService productFacadeService;
	
	private TagsVectorApiController tagsVectorApiController;
	
	private MockMvc mockMvc;
	
	private String endpoint = "/tagsVector";
	
	private static final int FIRST_PRODUCT_ID = 7516;
	private static final String FIRST_PRODUCT_NAME = "VESTIDO WRAP FLEUR";
	private static final int LAST_PRODUCT_ID = 8371;
	private static final String LAST_PRODUCT_NAME = "VESTIDO TRICOT CHEVRON";
	private static final int TOTAL_PRODUCTS = 25;
	private static final int TOTAL_TAG_VECTORS_ELEMENTS = 20;
	
	@Before
	public void setUp() throws Exception {
		this.tagsVectorApiController = new TagsVectorApiController(this.productFacadeService);
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.tagsVectorApiController)
				.setControllerAdvice(new TagsVectorApiControllerAdvice())
			.build();
	}

	@Test
	public void testCreateCharacteristicsTags() throws Exception {
		ProductRequest productRequest = this.createProductRequest();
		
		this.mockMvc.perform(post(this.endpoint)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(this.convertToJson(productRequest)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(TOTAL_PRODUCTS)))
				.andExpect(jsonPath("$.[0].id", is(FIRST_PRODUCT_ID)))
				.andExpect(jsonPath("$.[0].name", is(FIRST_PRODUCT_NAME)))
				.andExpect(jsonPath("$.[0].tags").exists())
				.andExpect(jsonPath("$.[0].tagsVector").exists())
				.andExpect(jsonPath("$.[0].tagsVector", hasSize(TOTAL_TAG_VECTORS_ELEMENTS)))
				.andExpect(jsonPath("$.[24].id", is(LAST_PRODUCT_ID)))
				.andExpect(jsonPath("$.[24].name", is(LAST_PRODUCT_NAME)))
				.andExpect(jsonPath("$.[24].tags").exists())
				.andExpect(jsonPath("$.[24].tagsVector").exists())
				.andExpect(jsonPath("$.[24].tagsVector", hasSize(TOTAL_TAG_VECTORS_ELEMENTS)))
			.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testCreateCharacteristicsTagsWithBadRequestNullProductList() throws Exception {
		ProductRequest productRequest = new ProductRequest();
		
		this.mockMvc.perform(post(this.endpoint)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(this.convertToJson(productRequest)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.timestamp").exists())
				.andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
				.andExpect(jsonPath("$.message", containsString(BaseAdvice.BAD_REQUEST_PREFIX_ERROR)))
			.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testCreateCharacteristicsTagsWithBadRequestEmptyProductList() throws Exception {
		ProductRequest productRequest = new ProductRequest();
		productRequest.products(Collections.emptyList());
		
		this.mockMvc.perform(post(this.endpoint)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(this.convertToJson(productRequest)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.timestamp").exists())
				.andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
				.andExpect(jsonPath("$.message", containsString(BaseAdvice.BAD_REQUEST_PREFIX_ERROR)))
			.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testCreateCharacteristicsTagsWithInternalServerError() throws Exception {
		ProductRequest productRequest = null;
		
		this.mockMvc.perform(post(this.endpoint)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(this.convertToJson(productRequest)))
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.timestamp").exists())
				.andExpect(jsonPath("$.status", is(HttpStatus.INTERNAL_SERVER_ERROR.value())))
				.andExpect(jsonPath("$.message").isNotEmpty())
			.andDo(MockMvcResultHandlers.print());
	}

}
