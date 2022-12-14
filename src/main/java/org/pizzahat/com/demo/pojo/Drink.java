package org.pizzahat.com.demo.pojo;

import org.pizzahat.com.demo.inter.PriceableInt;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Drink implements PriceableInt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "name can't be empty")
	@Column(unique = true, length = 64)
	private String name;
	
	@Lob
	private String description;
	
	@NotNull(message = "price can't be null")
	@Min(value = 1, message = "price can't be 0 or lower")
	private Integer price;
	
	public Drink() { }
	public Drink(String name, String description, Integer price) {
		
		setName(name);
		setDescription(description);
		setPrice(price);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		
		return "Drink: " + getId()
				+ "\n" + getName() + " - " + getPrice() 
				+ "\n" + getDescription();
	}
}
