package org.pizzahat.com.demo.serv;

import java.util.List;

import org.pizzahat.com.demo.pojo.Ingrediente;
import org.pizzahat.com.demo.repo.IngredienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepo ingredienteRepo;
	
	public void save(Ingrediente ingrediente) {
			
			ingredienteRepo.save(ingrediente);
		}
	public void delete(Ingrediente ingrediente) {
		
		ingredienteRepo.delete(ingrediente);
	}
	public Ingrediente findById(int id) {
		
		return ingredienteRepo.findById(id).get();
	}
	public List<Ingrediente> findAll() {
		
		return ingredienteRepo.findAll();
	}
}
