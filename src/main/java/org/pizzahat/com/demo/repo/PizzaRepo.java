package org.pizzahat.com.demo.repo;

import java.util.List;

import org.pizzahat.com.demo.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Integer> {

	public List<Pizza> findByNameContainingIgnoreCase(String name);
	public List<Pizza> findByNameIgnoreCaseContainingOrDescriptionContaining(String name, String description);
}
