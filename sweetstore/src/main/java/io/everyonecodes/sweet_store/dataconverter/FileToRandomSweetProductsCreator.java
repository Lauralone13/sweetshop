package io.everyonecodes.sweet_store.dataconverter;

import io.everyonecodes.sweet_store.domain.PartyBag;
import io.everyonecodes.sweet_store.domain.Sweet;
import io.everyonecodes.sweet_store.domain.SweetProduct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FileToRandomSweetProductsCreator {
	
	private final FileReader fileReader = new FileReader();
	private final Random random = new Random();
	
	private int amountOfProductsPlus20PercentPartyBags;
	// numberOfPartyBags == 20% of storeSize
	
	public FileToRandomSweetProductsCreator(@Value("${sweetshop.storage-size.numberOfProductsPlus20PercentPartyBags}") int amountOfProductsPlus20PercentPartyBags) {
		this.amountOfProductsPlus20PercentPartyBags = amountOfProductsPlus20PercentPartyBags;
	}
	
	public List<SweetProduct> getRandomSweetShopProducts() {
		List<SweetProduct> sweetShopProducts = new ArrayList<>();
		
		if(amountOfProductsPlus20PercentPartyBags < 2) {
			amountOfProductsPlus20PercentPartyBags = 2;
		}
		
		int amountOfSweets = (int) Math.floor(amountOfProductsPlus20PercentPartyBags * 0.8);
		amountOfSweets = makeSureThatNumberIsPositive(amountOfSweets);
		List<Sweet> sweets = createNewSweets(amountOfSweets);
		
		int amountOfPartyBags = (int) Math.ceil(amountOfProductsPlus20PercentPartyBags * 0.2);
		amountOfPartyBags = makeSureThatNumberIsPositive(amountOfPartyBags);
		List<PartyBag> partyBags = createNewPartyBags(amountOfPartyBags);
		
		sweetShopProducts.addAll(sweets);
		sweetShopProducts.addAll(partyBags);
		
		return sweetShopProducts;
	}
	
	private int makeSureThatNumberIsPositive(int number) {
		if(number < 1) {
			number = 1;
		}
		
		return number;
	}
	
	public List<PartyBag> createNewPartyBags(int amountOfPartyBags) {
		amountOfPartyBags = makeSureThatNumberIsPositive(amountOfPartyBags);
		
		List<PartyBag> partyBags = new ArrayList<>();
		
		while(partyBags.size() < amountOfPartyBags) {
			List<String> names = fileReader.read("files/partybag/partybags_names.txt");
			int randomIndexNumber = random.nextInt(names.size());
			String name = names.get(randomIndexNumber);
			
			Map.Entry<String, Double> sizeAndPrice = getSizeAndPrize("files/partybag/partybags_sizesandprices.txt");
			String size = sizeAndPrice.getKey();
			
			double price = sizeAndPrice.getValue();
			
			List<String> flavoursInPartyBag = findNumberOfFlavoursByPartyBagSize(size);
			
			boolean inStock = isInStock();
			
			
			PartyBag partyBag = new PartyBag(size, price, inStock, name,flavoursInPartyBag);
			partyBags.add(partyBag);
		}
		
		System.out.println("Party bags in storage:" + partyBags);
		
		return partyBags;
	}
	
	public List<Sweet> createNewSweets(int amountOfSweets) {
		
		List<Sweet> sweetsList = new ArrayList<>();
		
		while(sweetsList.size() < amountOfSweets) {
			
			String type = getSweetType();
			String flavour = getOneSweetFlavour();
			String appearance = getSweetAppearance();
			
			Map.Entry<String, Double> sizeAndPrice = getSizeAndPrize("files/sweet/sweets_sizesandprices.txt");
			String size = sizeAndPrice.getKey();
			double price = sizeAndPrice.getValue();
			boolean inStock = isInStock();
			
			Sweet sweet = new Sweet(size, price, inStock, type, flavour, appearance);
			
			sweetsList.add(sweet);
		}
		
		System.out.println("Sweets in storage:" + sweetsList);
		
		return sweetsList;
	}
	
	private String getSweetType() {
		
		List<String> types = fileReader.read("files/sweet/sweets_types.txt");
		
		int randomIndexNumber = random.nextInt(types.size() - 1);
		
		return types.get(randomIndexNumber);
	}
	
	private String getOneSweetFlavour() {
		List<String> flavours = fileReader.read("files/sweet/sweets_flavours.txt");
		int randomIndexNumber = random.nextInt(flavours.size() - 1);
		
		return flavours.get(randomIndexNumber);
	}
	
	private List<String> getNumberOfSweetFlavors(int number) {
		List<String> allFlavours = fileReader.read("files/sweet/sweets_flavours.txt");
		List<String> numberOfFlavors = new ArrayList<>();
		
		for(int i = 0; i < number; i++) {
			int randomIndexNumber = random.nextInt(allFlavours.size() - 1);
			numberOfFlavors.add(allFlavours.get(randomIndexNumber));
		}
		
		return numberOfFlavors;
	}
	
	private List<String> findNumberOfFlavoursByPartyBagSize(String size) {
		int smallBagFlavourAmount = 3;
		int mediumBagFlavourAmount = 6;
		int largeBagFlavourAmount = 9;
		int actualFlavoursAmount = 0;
		if(size.equals("small")) {
			actualFlavoursAmount = smallBagFlavourAmount;
		}
		
		if(size.equals("medium")) {
			actualFlavoursAmount = mediumBagFlavourAmount;
		}
		
		if(size.equals("large")) {
			actualFlavoursAmount = largeBagFlavourAmount;
		}
		
		return getNumberOfSweetFlavors(actualFlavoursAmount);
	}
	
	private String getSweetAppearance() {
		List<String> appearances = fileReader.read("files/sweet/sweets_appearances.txt");
		int randomIndexNumber = random.nextInt(appearances.size() - 1);
		
		return appearances.get(randomIndexNumber);
	}
	
	private Map.Entry<String, Double> getSizeAndPrize(String file) {
		List<String> smallPrices = new ArrayList<>();
		List<String> mediumPrices = new ArrayList<>();
		List<String> largePrices = new ArrayList<>();
		List<String> allSizesAndPrizes = fileReader.read(file);
		
		String small = "small:";
		String medium = "medium:";
		String large = "large:";
		
		int index = 0;
		for(String line : allSizesAndPrizes) {
			
			if(line.equals("")) {
				index++;
				continue;
			}
			
			if(line.equals(small)) {
				index++;
				smallPrices = findPricesInIndexRange(index, medium, allSizesAndPrizes);
			}
			
			if(line.equals(medium)) {
				index++;
				mediumPrices = findPricesInIndexRange(index, large, allSizesAndPrizes);
			}
			
			index++;
			
			if(line.equals(large)) {
				largePrices = findPricesInIndexRange(index, "", allSizesAndPrizes);
				break;
			}
		}
		
		List<List<String>> pricesLists = List.of(smallPrices, mediumPrices, largePrices);

		return calculateRandomSizeWithPriceStrings(pricesLists);
	}
	
	private Map.Entry<String, Double> calculateRandomSizeWithPriceStrings(List<List<String>> pricesLists) {
		
		int randomIndexNumberPricesLists = random.nextInt(pricesLists.size());
		List<String> randomPriceList = pricesLists.get(randomIndexNumberPricesLists);
		
		int randomIndexNumberPrizeList = random.nextInt(randomPriceList.size());
		String priceString = randomPriceList.get(randomIndexNumberPrizeList);
		
		double price = turnStringToDouble(priceString);
		String size = "not available";
		if(randomIndexNumberPricesLists == 0) {
			size = "small";
		}
		
		if(randomIndexNumberPricesLists == 1) {
			size = "medium";
		}
		
		if(randomIndexNumberPricesLists == 2) {
			size = "large";
		}
		
		String finalSize = size;
		Map.Entry<String, Double> sizeAndPrice = new Map.Entry<>() {
			@Override
			public String getKey() {
				return finalSize;
			}
			
			@Override
			public Double getValue() {
				return price;
			}
			
			@Override
			public Double setValue(Double value) {
				return value;
			}
		};
		
		return sizeAndPrice;
	}
	
	private Double turnStringToDouble(String stringDouble) {
		double price = 0.0;
		
		try {
			price = Double.parseDouble(stringDouble);
		} catch(InputMismatchException e) {
			// do nothing
		}
		
		return price;
	}
	
	private List<String> findPricesInIndexRange(int index, String stopWord, List<String> allSizesAndPrizes) {
		List<String> specificPriceStrings = new ArrayList<>();
		
		for(int i = index; i < allSizesAndPrizes.size(); i++) {
			String nextLine = allSizesAndPrizes.get(i);
			if(nextLine.equals("")) {
				continue;
			}
			
			if(nextLine.equals(stopWord)) {
				break;
			}
			
			specificPriceStrings.add(nextLine);
		}
		
		return specificPriceStrings;
	}
	
	
	private boolean isInStock() {
		return random.nextBoolean();
	}
}