package io.everyonecodes.sweetmarket.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String username;
	private String password;
	private Set<String> authorities;
	private Cart cart;
	
	public Customer(String username, String password, Cart cart) {
		this.username = username;
		this.password = password;
		this.cart = cart;
	}
	
	public Customer(Long id, String username, String password, Set<String> authorities, Cart cart) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.cart = cart;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<String> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}
	
	public Cart getCart() {
		return cart;
	}
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}
}
