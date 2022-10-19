package io.everyonecodes.sweetmarket.communication.client;

import io.everyonecodes.sweetmarket.domain.storedomain.SweetProduct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Controller
public class StoreClient {
	
	private final RestTemplate resttemplate;
	private final String urlStorage;
	private final String urlProducts;
	
	public StoreClient(RestTemplate resttemplate, @Value("${sweetshop.store.products.url}") String urlProducts, @Value("${sweetshop.store.storage.url}") String urlStorage) {
		this.resttemplate = resttemplate;
		this.urlProducts = urlProducts;
		this.urlStorage = urlStorage;
	}
	
	public List<SweetProduct> getActualProductsFromStore() {
		return List.of(Objects.requireNonNull(resttemplate.getForObject(urlProducts, SweetProduct[].class)));
	}
}
