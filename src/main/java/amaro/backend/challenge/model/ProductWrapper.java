package amaro.backend.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class ProductWrapper<T, R> {

	private T singleObject;
	
	private List<T> listObject = new ArrayList<>();
	
	private List<R> response = new ArrayList<>();

	public ProductWrapper(T singleObject) {
		this.singleObject = singleObject;
	}
	
	public ProductWrapper(List<T> listObject) {
		this.listObject.addAll(listObject);
	}
	
	public boolean isListInput() {
		return !this.listObject.isEmpty();
	}
	
	public T getInput() {
		return this.singleObject;
	}
	
	public List<T> getListInput() {
		return this.listObject;
	}
	
	public void addResponse(List<R> response) {
		this.response.addAll(response);
	}
	
	public List<R> getResponse() {
		return this.response;
	}
	
}
