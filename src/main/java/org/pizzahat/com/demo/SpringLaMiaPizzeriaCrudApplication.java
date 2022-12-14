package org.pizzahat.com.demo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.pizzahat.com.demo.pojo.Drink;
import org.pizzahat.com.demo.pojo.Ingrediente;
import org.pizzahat.com.demo.pojo.Pizza;
import org.pizzahat.com.demo.pojo.Promozione;
import org.pizzahat.com.demo.serv.DrinkServ;
import org.pizzahat.com.demo.serv.IngredienteService;
import org.pizzahat.com.demo.serv.PizzaServ;
import org.pizzahat.com.demo.serv.PromozioneServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaServ pizzaServ;
	
	@Autowired
	private DrinkServ drinkServ;

	@Autowired
	private PromozioneServ promozioneServ;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// -- CREATE --------------------------------------------------------
		
		Promozione prom1 = new Promozione("Promozione Natalizia", LocalDate.parse("2022-12-01"), LocalDate.parse("2022-12-31"));
		Promozione prom2 = new Promozione("Promozione Pasqua", LocalDate.parse("2023-04-01"), LocalDate.parse("2023-05-01"));
		Promozione prom3 = new Promozione("Promozione Estiva", LocalDate.parse("2022-08-01"), LocalDate.parse("2022-09-01"));
		
		promozioneServ.save(prom1);
		promozioneServ.save(prom2);
		promozioneServ.save(prom3);
		
		Ingrediente ing1 = new Ingrediente("pomodoro");
		Ingrediente ing2 = new Ingrediente("mozzarella");
		Ingrediente ing3 = new Ingrediente("acciughe");
		Ingrediente ing4 = new Ingrediente("capperi");
		
		ingredienteService.save(ing1);
		ingredienteService.save(ing2);
		ingredienteService.save(ing3);
		ingredienteService.save(ing4);
		
		List<Ingrediente> ingP1 = Arrays.asList(new Ingrediente[] {
				ing1, ing2
		});		
		Pizza p1 = new Pizza("Margherita", null, 1000, prom1, ingP1);
		
		List<Ingrediente> ingP2 = Arrays.asList(new Ingrediente[] {
				ing2, ing4
		});
		Pizza p2 = new Pizza("Margherita piccante", "my p2 desc", 2200, prom2, ingP2);
		
		List<Ingrediente> ingP3 = Arrays.asList(new Ingrediente[] {
				ing3, ing4
		});
		Pizza p3 = new Pizza("Diavola", "my p3 desc", 400, ingP3);
		
		Pizza p4 = new Pizza("Diavola light", null, 1200, prom3);
	
		pizzaServ.save(p1);
		pizzaServ.save(p2);
		pizzaServ.save(p3);
		pizzaServ.save(p4);
		
//		List<Pizza> pizzas = pizzaServ.findAll();
//		System.out.println(pizzas);
		
		Drink d1 = new Drink("Gin tonic", "desc drink 1", 10000);
		Drink d2 = new Drink("Vodka tonic", "desc drink 2", 12000);
		Drink d3 = new Drink("Gin lemon", "desc drink 3", 1000);
		Drink d4 = new Drink("Vodka lemon", "desc drink 4", 20000);
		
		drinkServ.save(d1);
		drinkServ.save(d2);
		drinkServ.save(d3);
		drinkServ.save(d4);
		
		List<Drink> drinks = drinkServ.findAll();
		System.out.println(drinks);
		
		// -- READ --------------------------------------------------------
		
//		Pizza delPizza = pizzaServ.findById(1).get();
//		pizzaServ.delete(delPizza);
		
		List<Pizza> pizze = pizzaServ.findAll();
		for (Pizza p : pizze) {
			
			System.out.println(
						p + "\n------------\n" 
						+ p.getPromozione() 
						+ "\n------------\n\n"
					);
		}
		
//		Promozione delProm = promozioneServ.findById(2);
//		promozioneServ.delete(delProm);
		
		List<Promozione> promozioni = promozioneServ.findAllWPizze();
		for (Promozione p : promozioni) {
			
			System.out.println(p);
			if (p.getPizze() != null)
				for (Pizza pizza : p.getPizze()) {
					
					System.out.println("\t" + pizza);
				}
		}
	}
}
