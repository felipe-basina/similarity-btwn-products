package amaro.backend.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amaro.backend.challenge.component.ProductFactoryComponent;
import amaro.backend.challenge.model.ProductRequest;
import amaro.backend.challenge.model.ProductResponse;
import amaro.backend.challenge.model.ProductWrapper;

@Service
@SuppressWarnings("unchecked")
public class ProductFacadeService {

	private ProductFactoryComponent productFactoryComponent;
	
	@Autowired
	public ProductFacadeService(ProductFactoryComponent productFactoryComponent) {
		this.productFactoryComponent = productFactoryComponent;
	}
	
	public ProductWrapper<ProductRequest, ProductResponse> addTagVector(ProductRequest productRequest) {
		ProductWrapper<ProductRequest, ProductResponse> input = new ProductWrapper<>(productRequest);
		return this.productFactoryComponent
				.getImplementationFrom(TagService.TAG_SERVICE_QUALIFIER)
			.process(input);
	}
	
}
