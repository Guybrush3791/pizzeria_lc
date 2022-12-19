package org.pizzahat.com.demo.controller;

import java.util.List;
import java.util.Optional;

import org.pizzahat.com.demo.pojo.Ingrediente;
import org.pizzahat.com.demo.pojo.Pizza;
import org.pizzahat.com.demo.pojo.Promozione;
import org.pizzahat.com.demo.serv.IngredienteService;
import org.pizzahat.com.demo.serv.PizzaServ;
import org.pizzahat.com.demo.serv.PromozioneServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {
 
	@Autowired
	private PizzaServ pizzaServ;
	
	@Autowired
	private PromozioneServ promozioneServ;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping
	public String getHome(Model model) {
		
		List<Pizza> pizzas = pizzaServ.findAll();
		model.addAttribute("pizzas", pizzas);
		
		return "home";
	}
	
	@GetMapping("/pizza/admin/create")
	public String getPizzaCreate(Model model) {
		
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		
		List<Promozione> promozioni = promozioneServ.findAll();
		model.addAttribute("promozioni", promozioni);
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		return "pizza-create";
	}
	@PostMapping("/pizza/admin/create")
	public String storePizza(@Valid Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/pizza/create";
		}
		
		System.err.println(
				pizza 
				+ "\nPromozione:\n\t" + pizza.getPromozione()
				+ "\nIngredenti:"
		);
		for (Ingrediente i : pizza.getIngredienti()) {
			
			System.err.println("\t" + i);
		}
		
		try {
			
			pizzaServ.save(pizza);
		} catch(Exception e) {
			
			redirectAttributes.addFlashAttribute("catchError", e.getMessage());
			
			return "redirect:/pizza/create";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/pizza/admin/update/{id}")
	public String getPizzaUpdate(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaServ.findById(id);
		Pizza pizza = optPizza.get();
		model.addAttribute("pizza", pizza);
		
		List<Promozione> promozioni = promozioneServ.findAll();
		model.addAttribute("promozioni", promozioni);
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		return "pizza-update";
	}
	@PostMapping("/pizza/admin/update")
	public String updatePizza(@Valid Pizza pizza) {
		
		pizzaServ.save(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/pizza/admin/delete/{id}")
	public String deletePizza(@PathVariable int id) {
		
		Optional<Pizza> optPizza = pizzaServ.findById(id);
		Pizza pizza = optPizza.get();
		
		pizzaServ.delete(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/pizza/user/search")
	public String searchPizza(Model model,  
			@RequestParam(name = "query", required = false) String query) {
		
//		List<Pizza> pizzas = null;
//		if (query == null || query.isEmpty()) {
//			
//			pizzas = pizzaServ.findAll();
//			model.addAttribute("pizzas", pizzas);
//		} else {
//			
//			// ricerca
//		}
		
		List<Pizza> pizzas = (query == null || query.isEmpty())
							? pizzaServ.findAll()
							: pizzaServ.findByName(query);
		
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("query", query);
		
		return "pizza-search";
	}
}








