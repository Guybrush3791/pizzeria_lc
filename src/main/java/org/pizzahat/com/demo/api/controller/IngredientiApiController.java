package org.pizzahat.com.demo.api.controller;

import java.util.List;

import org.pizzahat.com.demo.pojo.Ingrediente;
import org.pizzahat.com.demo.pojo.Pizza;
import org.pizzahat.com.demo.serv.IngredienteService;
import org.pizzahat.com.demo.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/ingredienti")
@CrossOrigin("*")
public class IngredientiApiController {

	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private PizzaServ pizzaServ;
	
	@GetMapping("/all")
	public List<Ingrediente> getIngredienti() {
		
		return ingredienteService.findAll();
	}
	
	@GetMapping("/by/pizza/{id}")
	public List<Ingrediente> getIngredientiByPizzaId(
			@PathVariable("id") int id
	) {
		
		Pizza pizza = pizzaServ.findById(id).get();
		
		return pizza.getIngredienti();
	}
	
}
