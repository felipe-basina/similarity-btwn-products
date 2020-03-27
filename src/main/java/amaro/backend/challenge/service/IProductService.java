package amaro.backend.challenge.service;

import amaro.backend.challenge.model.ProductWrapper;

public interface IProductService<T, R> {

	public ProductWrapper<T, R> process(ProductWrapper<T, R> input);
	
}
