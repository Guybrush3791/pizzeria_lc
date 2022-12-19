package org.pizzahat.com.demo.api.controller;

import java.util.List;

import org.pizzahat.com.demo.pojo.Ingrediente;
import org.pizzahat.com.demo.pojo.Pizza;
import org.pizzahat.com.demo.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/1/pizza")
@CrossOrigin("*")
public class PizzaApiController {

	@Autowired
	private PizzaServ pizzaServ;
	
	@GetMapping("/all")
	public List<Pizza> getAllPizze() {
		
		List<Pizza> pizze = pizzaServ.findAll();
		
		return pizze;
	}
	
	@PostMapping("/create")
	public Pizza createPizza(
			@Valid @RequestBody Pizza pizza
	) {
		
		System.err.println(pizza);
		
		Pizza newPizza = pizzaServ.save(pizza);
		
		System.err.println(newPizza);
		
		return newPizza;
	}
	
	@PostMapping("/update/{id}")
	public Pizza updatePizza(
			@PathVariable("id") int id, 
			@Valid @RequestBody Pizza pizza
	) {
		
		Pizza oldPizza = pizzaServ.findById(id).get();
		pizza.setIngredienti(oldPizza.getIngredienti());
		
		Pizza newPizza = pizzaServ.save(pizza);
		
		return newPizza;
	}
	
	@GetMapping("/delete/{id}")
	public boolean deletePizza(
			@PathVariable("id") int id
	) {
		
		try {
			
			Pizza pizza = pizzaServ.findById(id).get();
			pizzaServ.delete(pizza);
		} catch(Exception e) { return false; }
		
		return true;
	}
}











