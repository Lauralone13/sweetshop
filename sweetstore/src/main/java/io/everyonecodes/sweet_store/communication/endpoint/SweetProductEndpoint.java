package io.everyonecodes.sweet_store.communication.endpoint;

import io.everyonecodes.sweet_store.domain.Sweet;
import io.everyonecodes.sweet_store.domain.SweetProduct;
import io.everyonecodes.sweet_store.logic.SweetProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class SweetProductEndpoint {
	
	private final SweetProductService sweetProductService;
	
	public SweetProductEndpoint(SweetProductService sweetProductService) {
		this.sweetProductService = sweetProductService;
	}
	
	@GetMapping
	List<SweetProduct> getAll() {
		return sweetProductService.findAllSweetProducts();
	}
	
	@GetMapping("/{id}")
	SweetProduct getOneById(@PathVariable String id) {
		return sweetProductService.findSweetProductById(id).orElse(null);
	}
	
	@GetMapping("/type/{keyword}")
	List<Sweet> getProductsByType(String keyword) {
		return sweetProductService.findSweetProductsByTypeContaining(keyword);
	}
	
	@GetMapping("/flavour/{keyword}")
	List<Sweet> getProductsByFlavour(String keyword) {
		return sweetProductService.findSweetProductsByFlavourContaining(keyword);
	}
	
	// DTOs? post dto, transform it to product(or find already existing product and turn it back to dto, then return dto^^
}
