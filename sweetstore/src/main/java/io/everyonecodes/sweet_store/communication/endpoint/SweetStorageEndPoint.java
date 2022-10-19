package io.everyonecodes.sweet_store.communication.endpoint;

import io.everyonecodes.sweet_store.domain.PartyBag;
import io.everyonecodes.sweet_store.domain.Sweet;
import io.everyonecodes.sweet_store.domain.SweetProduct;
import io.everyonecodes.sweet_store.logic.SweetProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storage")
public class SweetStorageEndPoint {

	private final SweetProductService sweetProductService;
	
	public SweetStorageEndPoint(SweetProductService sweetProductService) {
		this.sweetProductService = sweetProductService;
	}
	
	@GetMapping("/create")
	List<SweetProduct> createNewSweetStorage() {
		return sweetProductService.deleteOldAndCreateBrandNewRandomSweetStorage();
	}
	
	@GetMapping()
	String getSweetStorageInformation() {
		return sweetProductService.getStorageInformationMessage();
	}
	
	@PostMapping("/add")
	SweetProduct saveOne(@RequestBody SweetProduct sweetProduct) {
		return sweetProductService.saveOneSweetProduct(sweetProduct);
	}
	
	@GetMapping("/add/sweets/{number}")
	List<Sweet> addNewRandomSweets(@PathVariable int number) {
		return sweetProductService.addNumberOfRandomSweets(number);
	}
	
	@GetMapping("/add/partybags/{number}")
	List<PartyBag> addNewRandomPartyBags(@PathVariable int number) {
		return sweetProductService.addNumberOfRandomPartyBags(number);
	}
	
	@GetMapping("/add/products")
	List<SweetProduct> addNumberOfRandomSweetProducts() {
		// takes number of yaml file
		return sweetProductService.addNewRandomSweetProductsToStorage();
	}
	
	@PutMapping("/fillup")
	void putAllInStorage() {
		sweetProductService.fillUpExistingSweetProducts();
	}
	
	@PutMapping("/{id}")
	SweetProduct replaceSweetProductById(@PathVariable String id, @RequestBody SweetProduct sweetProduct) {
		return sweetProductService.showAndReplaceById(id, sweetProduct);
	}
	
	@DeleteMapping("/{id}")
	void deleteProductById(@PathVariable String id) {
		sweetProductService.deleteOneSweetProductById(id);
	}
	
	@DeleteMapping()
	void deleteWholeStorage() {
		sweetProductService.deleteAllSweetProducts();
	}
}
