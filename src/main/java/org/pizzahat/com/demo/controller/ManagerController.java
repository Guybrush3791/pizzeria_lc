package org.pizzahat.com.demo.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.pizzahat.com.demo.inter.PriceableInt;
import org.pizzahat.com.demo.pojo.Drink;
import org.pizzahat.com.demo.pojo.Pizza;
import org.pizzahat.com.demo.serv.DrinkServ;
import org.pizzahat.com.demo.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/available")
public class ManagerController {

	public static class ProductComp implements Comparator<PriceableInt> {
		
		@Override
		public int compare(PriceableInt o1, PriceableInt o2) {
			
			return o1.getPrice() - o2.getPrice();
		}
	}
	
	@Autowired
	private PizzaServ pizzaServ;
	
	@Autowired
	private DrinkServ drinkServ;
	
	@GetMapping
	public String getAllByName(Model model, 
			@RequestParam(name = "query", required = false) String query) {
		
		List<Pizza> pizzas = null;
		List<Drink> drinks = null;
		
		if (query == null || query.isEmpty()) {
			
			pizzas = pizzaServ.findAll(); 
			drinks = drinkServ.findAll();
		} else {
			
			pizzas = pizzaServ.findByName(query);
			drinks = drinkServ.findByName(query);
		}
		
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("drinks", drinks);
		model.addAttribute("query", query);
		
		return "available-search";
	}
	
	@GetMapping("/ordered/products")
	public String getOrderedProducts(Model model) {
		
		List<PriceableInt> products = new ArrayList<>();

		products.addAll(drinkServ.findAll());
		products.addAll(pizzaServ.findAll());
		
//		products.sort((p1, p2) -> p1.getPrice() - p2.getPrice());
		products.sort(new ProductComp());
		
		model.addAttribute("products", products);
		
		return "ordered-products";
	}
}
















