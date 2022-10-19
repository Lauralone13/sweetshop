package io.everyonecodes.sweetmarket.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
public class SweetItem {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	private String productId;
	private String nameOrType;
	private String size;
	private double singlePrice;
	private int amount;
	
	public SweetItem() {
	}
	
	public SweetItem(String productId, String nameOrType, String size, double singlePrice, int amount) {
		this.productId = productId;
		this.nameOrType = nameOrType;
		this.size = size;
		this.singlePrice = singlePrice;
		this.amount = amount;
	}
	
	public SweetItem(Long id, String productId, String nameOrType, String size, double singlePrice, int amount) {
		this.id = id;
		this.productId = productId;
		this.nameOrType = nameOrType;
		this.size = size;
		this.singlePrice = singlePrice;
		this.amount = amount;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getNameOrType() {
		return nameOrType;
	}
	
	public void setNameOrType(String nameOrType) {
		this.nameOrType = nameOrType;
	}
	
	public String getSize() {
		return size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public double getSinglePrice() {
		return singlePrice;
	}
	
	public void setSinglePrice(double singlePrice) {
		this.singlePrice = singlePrice;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		SweetItem that = (SweetItem) o;
		return Double.compare(that.singlePrice, singlePrice) == 0 && amount == that.amount && Objects.equals(id, that.id) && Objects.equals(productId, that.productId) && Objects.equals(nameOrType, that.nameOrType) && Objects.equals(size, that.size);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, productId, nameOrType, size, singlePrice, amount);
	}
	
	@Override
	public String toString() {
		return "SweetProductItem{" + "id=" + id + ", productId='" + productId + '\'' + ", nameOrType='" + nameOrType + '\'' + ", size='" + size + '\'' + ", singlePrice=" + singlePrice + ", amount=" + amount + '}';
	}
}
