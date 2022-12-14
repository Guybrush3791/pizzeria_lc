package org.pizzahat.com.demo.pojo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;
	
	@ManyToMany(mappedBy = "ingredienti")
	private List<Pizza> pizze;
	
	public Ingrediente() { }
	public Ingrediente(String name) {
		
		setName(name);
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
	public List<Pizza> getPizze() {
		return pizze;
	}
	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}
	
	@Override
	public String toString() {
		
		return "(" + getId() + ") " + getName();
	}
}
