package org.pizzahat.com.demo.serv;

import java.util.List;
import java.util.Optional;

import org.pizzahat.com.demo.pojo.Pizza;
import org.pizzahat.com.demo.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaServ {

	@Autowired
	private PizzaRepo pizzaRepo;
	
	public List<Pizza> findAll() {
		
		return pizzaRepo.findAll();
	}
	public void save(Pizza pizza) {
		
		pizzaRepo.save(pizza);
	}
	public void delete(Pizza pizza) {
		
		pizzaRepo.delete(pizza);
	}
	public Optional<Pizza> findById(int id) {
		
		return pizzaRepo.findById(id);
	}
	public List<Pizza> findByName(String name) {
		
//		return pizzaRepo.findByNameContainingIgnoreCase(name);
		return pizzaRepo
				.findByNameIgnoreCaseContainingOrDescriptionContaining(name, name);
	}
}
