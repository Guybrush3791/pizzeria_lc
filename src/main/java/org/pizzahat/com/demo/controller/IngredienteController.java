package org.pizzahat.com.demo.controller;

import java.util.List;

import org.pizzahat.com.demo.pojo.Ingrediente;
import org.pizzahat.com.demo.pojo.Pizza;
import org.pizzahat.com.demo.serv.IngredienteService;
import org.pizzahat.com.demo.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ing")
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private PizzaServ pizzaServ;
	
	@GetMapping
	public String getIngredienti(Model model) {
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		return "ingredienti";
	}
	@GetMapping("/create")
	public String createIngrediente(Model model) {
		
		Ingrediente ingrediente = new Ingrediente();
		model.addAttribute("ingrediente", ingrediente);
		
		List<Pizza> pizze = pizzaServ.findAll();
		model.addAttribute("pizze", pizze);
		
		return "ingrediente-create";
	}
	@PostMapping("/create")
	public String storeIngrediente(@Valid Ingrediente ingrediente) {
		
		for (Pizza p : ingrediente.getPizze()) {
			
			p.getIngredienti().add(ingrediente);
		}
		
		ingredienteService.save(ingrediente);
		
		return "redirect:/ing";
	}
	
	@GetMapping("/update/{id}")
	public String updateIngrediente(
			@PathVariable("id") int id,
			Model model
		) {
		
		Ingrediente ingrediente = ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		
		List<Pizza> pizze = pizzaServ.findAll();
		model.addAttribute("pizze", pizze);
		
		return "ingrediente-update";
	}
	@PostMapping("/update/{id}")
	public String editIngrediente(
			@PathVariable("id") int id,
			@Valid Ingrediente ingrediente
		) {
		
		Ingrediente oldIng = ingredienteService.findById(id);
		
		// --- -> sync -------------------------------------
		for (Pizza p : oldIng.getPizze()) {
			
			p.getIngredienti().remove(oldIng);
		}
		
		for (Pizza p : ingrediente.getPizze()) {
			
			p.addIngrediente(ingrediente);
		}
		// -------------------------------------------------
		
		ingredienteService.save(ingrediente);
		
		return "redirect:/ing/update/" + id;
	}
}









