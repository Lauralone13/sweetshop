package io.everyonecodes.sweet_store.logic;

import io.everyonecodes.sweet_store.dataconverter.FileToRandomSweetProductsCreator;
import io.everyonecodes.sweet_store.domain.PartyBag;
import io.everyonecodes.sweet_store.domain.Sweet;
import io.everyonecodes.sweet_store.domain.SweetProduct;
import io.everyonecodes.sweet_store.persistence.repository.SweetProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SweetProductService {
	
	private final SweetProductRepository sweetProductRepository;

	private final FileToRandomSweetProductsCreator fileToRandomSweetProductsCreator;
	
	public SweetProductService(SweetProductRepository sweetProductRepository, FileToRandomSweetProductsCreator fileToRandomSweetProductsCreator) {
		this.sweetProductRepository = sweetProductRepository;
		this.fileToRandomSweetProductsCreator = fileToRandomSweetProductsCreator;
	}
	
	public String getStorageInformationMessage() {
		
		List<SweetProduct> allSweetProducts = sweetProductRepository.findAll();
		
		List<SweetProduct> sweets = allSweetProducts.stream().filter(sweetProduct -> sweetProduct.getClass() == Sweet.class).toList();
		
		List<SweetProduct> partyBags = allSweetProducts.stream().filter(sweetProduct -> sweetProduct.getClass() == PartyBag.class).toList();
		
		int totalStorageSpace = allSweetProducts.size();
		int numberOfProductsInStorage = allSweetProducts.stream().filter(SweetProduct :: isInStock).toList().size();
		
		int numberOfSweets = sweets.size();
		int numberOfSweetsInStorage = sweets.stream().filter(SweetProduct :: isInStock).toList().size();
		
		int numberOfPartyBags = partyBags.size();
		int numberOfPartyBagsInStorage = partyBags.stream().filter(SweetProduct :: isInStock).toList().size();
		
		int freeStorageSpace = totalStorageSpace - (numberOfSweetsInStorage + numberOfPartyBagsInStorage);
		
		return "sweetshop-storage information:" +
				"\n-------------------------------" +
				"\ntotal storage space: " + totalStorageSpace +
				"\nproducts available: " + numberOfProductsInStorage +
				"\n\nsweets: " + numberOfSweets + ", available: " + numberOfSweetsInStorage +
				"\nparty bags: " + numberOfPartyBags + ", available: " + numberOfPartyBagsInStorage +
				"\n\nfree storage space: " + freeStorageSpace;
	}
	
	public void fillUpExistingSweetProducts() {
		List<SweetProduct> sweetProducts = sweetProductRepository.findAll();
		for(SweetProduct sweetProduct : sweetProducts) {
			sweetProduct.setInStock(true);
		}
	}
	
	public List<SweetProduct> findAllSweetProducts() {
		return sweetProductRepository.findAll();
	}
	
	public SweetProduct saveOneSweetProduct(SweetProduct sweetProduct) {
		return sweetProductRepository.save(sweetProduct);
	}
	
	public List<Sweet> addNumberOfRandomSweets(int number) {
		List<Sweet> sweets = fileToRandomSweetProductsCreator.createNewSweets(number);
		return sweetProductRepository.saveAll(sweets);
	}
	
	public List<PartyBag> addNumberOfRandomPartyBags(int number) {
		List<PartyBag> partyBags = fileToRandomSweetProductsCreator.createNewPartyBags(number);
		return sweetProductRepository.saveAll(partyBags);
	}
	
	public List<SweetProduct> deleteOldAndCreateBrandNewRandomSweetStorage() {
		sweetProductRepository.deleteAll();
		List<SweetProduct> sweetProducts = fileToRandomSweetProductsCreator.getRandomSweetShopProducts();
		sweetProductRepository.saveAll(sweetProducts);
		return sweetProducts;
	}
	
	public List<SweetProduct> addNewRandomSweetProductsToStorage() {
		return fileToRandomSweetProductsCreator.getRandomSweetShopProducts();
	}
	
	public Optional<SweetProduct> findSweetProductById(String id) {
		return sweetProductRepository.findById(id);
	}
	
	public SweetProduct showAndReplaceById(String id, SweetProduct sweetProduct) {
		Optional<SweetProduct> optionalSweetProduct = sweetProductRepository.findById(id);
		
		if(optionalSweetProduct.isEmpty()) {
			return null;
		}
		
		System.out.println(optionalSweetProduct);
		sweetProduct.setId(id);
		return sweetProductRepository.save(sweetProduct);
	}
	
	public List<Sweet> findSweetProductsByTypeContaining(String typeKeyword) {
		return sweetProductRepository.findByTypeContaining(typeKeyword);
	}
	
	public List<Sweet> findSweetProductsByFlavourContaining(String flavourKeyword) {
		return sweetProductRepository.findByFlavourContaining(flavourKeyword);
	}

	public void deleteOneSweetProductById(String id) {
		sweetProductRepository.deleteById(id);
	}
	
	public void deleteAllSweetProducts() {
		sweetProductRepository.deleteAll();
	}
}
