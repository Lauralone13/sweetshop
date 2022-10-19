package io.everyonecodes.sweetmarket.domain.storedomain;

import java.util.Objects;

public class Sweet extends SweetProduct {
	
	private String type;
	private String flavour;
	private String appearance;

	public Sweet() {
	}
	
	public Sweet(String size, double price, boolean inStock, String type, String flavour,
	             String appearance) {
		super(size, price, inStock);
		this.type = type;
		this.flavour = flavour;
		this.appearance = appearance;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getFlavour() {
		return flavour;
	}
	
	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}
	
	public String getAppearance() {
		return appearance;
	}
	
	public void setAppearance(String appearance) {
		this.appearance = appearance;
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
		Sweet sweet = (Sweet) o;
		return Objects.equals(type, sweet.type) && Objects.equals(flavour, sweet.flavour) && Objects.equals(appearance
				, sweet.appearance);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), type, flavour, appearance);
	}
	
	@Override
	public String toString() {
		return "Sweet{" + "type='" + type + '\'' + ", flavour='" + flavour + '\'' + ", appearance='" + appearance + '\'' + '}';
	}
}
