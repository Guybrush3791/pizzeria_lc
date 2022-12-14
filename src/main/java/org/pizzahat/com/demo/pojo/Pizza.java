package org.pizzahat.com.demo.pojo;

import java.util.List;

import org.pizzahat.com.demo.inter.PriceableInt;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Pizza implements PriceableInt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "no null name")
	@Column(unique = true)
	private String name;
	
	@Lob
	private String description;
	@NotNull(message = "price can't be null")
	private Integer price;
	
	@ManyToOne
	private Promozione promozione;
	
	@ManyToMany
	private List<Ingrediente> ingredienti;
	
	public Pizza() { }
	public Pizza(String name, String description, int price) {
		
		setName(name);
		setDescription(description);
		setPrice(price);
	}
	public Pizza(String name, String description, int price, Promozione promozione) {
		
		this(name, description, price);
		
		setPromozione(promozione);
	}
	public Pizza(String name, String description, int price, List<Ingrediente> ingredienti) {
		
		this(name, description, price);
		
		setIngredienti(ingredienti);
	}
	public Pizza(String name, String description, int price, Promozione promozione, List<Ingrediente> ingredienti) {
		
		this(name, description, price, promozione);
		
		setIngredienti(ingredienti);
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
	public Promozione getPromozione() {
		return promozione;
	}
	public void setPromozione(Promozione promozione) {
		this.promozione = promozione;
	}
	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	public void addIngrediente(Ingrediente ingrediente) {
		
		boolean finded = false;
		for (Ingrediente i : getIngredienti()) {
			
			if (i.getId() == ingrediente.getId())
				finded = true;
		}
		
		if (!finded)
			getIngredienti().add(ingrediente);
	}
	
	@Override
	public String toString() {
		
		return "(" + getId() + ") " + getName() + " - " + getPrice()
			+ "\n" + getDescription();
	}
}
