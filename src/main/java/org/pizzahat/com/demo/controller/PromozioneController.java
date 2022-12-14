package org.pizzahat.com.demo.controller;

import java.util.List;

import org.pizzahat.com.demo.pojo.Pizza;
import org.pizzahat.com.demo.pojo.Promozione;
import org.pizzahat.com.demo.serv.PizzaServ;
import org.pizzahat.com.demo.serv.PromozioneServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/promozione")
public class PromozioneController {

	@Autowired
	private PromozioneServ promozioneServ;

	@Autowired
	private PizzaServ pizzaServ;
	
	@GetMapping
	public String getPromozioni(Model model) {
		
		List<Promozione> promozioni = promozioneServ.findAllWPizze();
		model.addAttribute("promozioni", promozioni);
		
		return "promozioni";
	}
	
	@GetMapping("create")
	public String createPromozione(Model model) { 
		
		Promozione promozione = new Promozione();
		model.addAttribute("promozione", promozione);
		
		List<Pizza> pizze = pizzaServ.findAll();
		model.addAttribute("pizze", pizze);
		
		return "promozione-create";
	}
	@PostMapping("create")
	public String storePromozione(
		@Valid Promozione promozione, 
		BindingResult bindingResult, 
		RedirectAttributes redirectAttributes
	) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/promozione/create";
		}
		 
		try {
			
			List<Pizza> pizze = promozione.getPizze();
			for (Pizza pizza : pizze) {
				
				pizza.setPromozione(promozione);
			}
			
			promozioneServ.save(promozione);
		} catch(Exception e) {
			
			redirectAttributes.addFlashAttribute("catchError", e.getMessage());
			
			return "redirect:/promozione/create";
		}
		
		return "redirect:/promozione";
	}
}













