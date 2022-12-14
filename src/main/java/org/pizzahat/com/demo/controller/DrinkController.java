package org.pizzahat.com.demo.controller;

import java.util.List;
import java.util.Optional;

import org.pizzahat.com.demo.pojo.Drink;
import org.pizzahat.com.demo.serv.DrinkServ;
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
@RequestMapping("/drink")
public class DrinkController {

	@Autowired
	private DrinkServ drinkServ;
	
	@GetMapping
	public String getHome(Model model) {
		
		List<Drink> drinks = drinkServ.findAll();
		model.addAttribute("drinks", drinks);
		
		return "drinks";
	}
	
	@GetMapping("/create")
	public String getCreateDrink(Model model) {
		
		Drink drink = new Drink();
		model.addAttribute("drink", drink);
		
		return "drink-create";
	}
	@PostMapping("/create")
	public String getEditDrink(@Valid Drink drink, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/drink/create";
		}
		
		try {
			
			drinkServ.save(drink);
		} catch (Exception e) {
			
			final String msg = e.getMessage();
			
			System.err.println(msg);
			redirectAttributes.addFlashAttribute("catchError", msg);
			
			return "redirect:/drink/create";
		}
		
		return "redirect:/drink";
	}
	
	@GetMapping("/update/{id}")
	public String getUpdateDrink(@PathVariable("id") int id, Model model) {
		
		Optional<Drink> optDrink = drinkServ.findById(id);
		Drink drink = optDrink.get();
		
		model.addAttribute("drink", drink);
		
		return "drink-update";
	}
	@PostMapping("/update") 
	public String getStoreDrink(@Valid Drink drink, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/drink/update/" + drink.getId();
		}
		
		try {
			
			drinkServ.save(drink);
		} catch (Exception e) {
			
			final String msg = e.getMessage();
			
			System.err.println(msg);
			redirectAttributes.addFlashAttribute("catchError", msg);
			
			return "redirect:/drink/update/" + drink.getId();
		}
		
		return "redirect:/drink";
	}
	
	@GetMapping("/delete/{id}")
	public String getDeleteDrink(@PathVariable("id") int id, 
			RedirectAttributes redirectAttributes) {
		
		try {

			Optional<Drink> optDrink = drinkServ.findById(id);
			Drink drink = optDrink.get();
			
			drinkServ.delete(drink); 
		} catch (Exception e) {
			
			final String msg = e.getMessage();
			
			System.err.println(msg);
			redirectAttributes.addFlashAttribute("catchError", msg);
		}
		
		return "redirect:/drink";
	}
	
	@GetMapping("/search")
	public String getSearchDrinkByName(Model model, 
			@RequestParam(name = "q", required = false) String query) {
		
//		List<Drink> drinks = null;
//		if (query == null) {
//			
//			drinks = drinkServ.findAll();
//			
//		} else {
//			
//			drinks = drinkServ.findByName(query);
//		}
		List<Drink> drinks = query == null 
							? drinkServ.findAll()
							: drinkServ.findByName(query); 
		
		model.addAttribute("drinks", drinks);
		model.addAttribute("query", query);
		
		return "drink-search";
	}
}












