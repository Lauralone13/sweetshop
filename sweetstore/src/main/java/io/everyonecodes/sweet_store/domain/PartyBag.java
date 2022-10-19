package io.everyonecodes.sweet_store.domain;

import java.util.List;
import java.util.Objects;

public class PartyBag extends SweetProduct {
	
	private String name;
	private List<String> sweets;
	
	public PartyBag() {
	}
	
	public PartyBag(String size, double price, boolean inStock, String name, List<String> sweets) {
		super(size, price, inStock);
		this.name = name;
		this.sweets = sweets;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getSweets() {
		return sweets;
	}
	
	public void setSweets(List<String> sweets) {
		this.sweets = sweets;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		if(! super.equals(o)) {
			return false;
		}
		PartyBag partyBag = (PartyBag) o;
		return Objects.equals(name, partyBag.name) && Objects.equals(sweets, partyBag.sweets);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), name, sweets);
	}
	
	@Override
	public String toString() {
		return "PartyBag{" + "name='" + name + '\'' + ", sweets=" + sweets + '}';
	}
}
