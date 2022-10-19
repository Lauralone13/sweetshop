package io.everyonecodes.sweetmarket.domain;

import java.util.List;

public class Cart {
	
//	private Customer ??
	private List<SweetItem> sweetItems;
	
	public Cart(List<SweetItem> sweetItems) {
		this.sweetItems = sweetItems;
	}
	
	public List<SweetItem> getSweetProductItems() {
		return sweetItems;
	}
	
	public void setSweetProductItems(List<SweetItem> sweetItems) {
		this.sweetItems = sweetItems;
	}
}
