package io.everyonecodes.sweetmarket.logic;

import io.everyonecodes.sweetmarket.communication.client.StoreClient;
import io.everyonecodes.sweetmarket.domain.SweetItem;
import io.everyonecodes.sweetmarket.domain.storedomain.PartyBag;
import io.everyonecodes.sweetmarket.domain.storedomain.Sweet;
import io.everyonecodes.sweetmarket.domain.storedomain.SweetProduct;
import io.everyonecodes.sweetmarket.repositories.CustomerRepository;
import io.everyonecodes.sweetmarket.repositories.SweetItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarketService {

	private final SweetItemRepository sweetItemRepository;
	private final CustomerRepository customerRepository;
	private final StoreClient storeClient;
	
	public MarketService(SweetItemRepository sweetItemRepository, CustomerRepository customerRepository, StoreClient storeClient) {
		this.sweetItemRepository = sweetItemRepository;
		this.customerRepository = customerRepository;
		this.storeClient = storeClient;
	}
	
	// Admins only
	public List<SweetItem> getAllSweetItemsUpdatedFromStore() {
		List<SweetProduct> sweetProductList = storeClient.getActualProductsFromStore();
		
		List<SweetItem> currentSweetItems = sweetItemRepository.findAll();
//		var sweetItemsAvailable = currentSweetItems.stream().filter(sweetItem -> sweetItem.)
		
		List<SweetItem> sweetItems = new ArrayList<>();
		
		for(SweetProduct sweetProduct : sweetProductList) {
			
			if(sweetProduct.getClass().equals(PartyBag.class)) {
				PartyBag partyBag = new PartyBag(sweetProduct.getSize(), sweetProduct.getPrice(), sweetProduct.getInStock(), ((PartyBag) sweetProduct).getName(), ((PartyBag) sweetProduct).getSweets());
				
				SweetItem sweetItem = new SweetItem(partyBag.getId(), partyBag.getName(), partyBag.getSize(), partyBag.getPrice(), 1);
				
				if(!currentSweetItems.contains(sweetItem)) {
					sweetItems.add(sweetItem);
				}
			}
			
			if(sweetProduct.getClass().equals(Sweet.class)) {
				Sweet sweet = new Sweet(sweetProduct.getSize(), sweetProduct.getPrice(), sweetProduct.getInStock(), ((Sweet) sweetProduct).getType(), ((Sweet) sweetProduct).getFlavour(), ((Sweet) sweetProduct).getAppearance());
				
				SweetItem sweetItem = new SweetItem(sweet.getId(), sweet.getType(), sweet.getSize(), sweet.getPrice(), 1);
				
				if(!currentSweetItems.contains(sweetItem)) {
					sweetItems.add(sweetItem);
				}
			}
		}
		
		sweetItemRepository.saveAll(sweetItems);
		
		return sweetItems;
	}
	
	// all
	public List<SweetProduct> getAllProducts() {
		return storeClient.getActualProductsFromStore();
	}
	
	
	
	// customers only -- /products/cart/{productId}
	public SweetItem putItemInCart(String productId) {
		var item = sweetItemRepository.findByProductId(productId);
//		if()
		
		
		return new SweetItem();
	}
}
