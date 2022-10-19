package io.everyonecodes.sweet_store.domain;

import java.util.Objects;

public abstract class SweetProduct {
	
	private String id;
	private String size;
	private double price;
	private boolean inStock;
	
	public SweetProduct() {
	}
	
	public SweetProduct(String size, double price, boolean inStock) {
		this.size = size;
		this.price = price;
		this.inStock = inStock;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSize() {
		return size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean isInStock() {
		return inStock;
	}
	
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		SweetProduct that = (SweetProduct) o;
		return Double.compare(that.price, price) == 0 && inStock == that.inStock && Objects.equals(id, that.id) && Objects.equals(size, that.size);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, size, price, inStock);
	}
	
	@Override
	public String toString() {
		return "SweetProduct{" + "id='" + id + '\'' + ", size='" + size + '\'' + ", price=" + price + ", inStock=" + inStock + '}';
	}
}
