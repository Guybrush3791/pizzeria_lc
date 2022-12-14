package org.pizzahat.com.demo.repo;

import org.pizzahat.com.demo.pojo.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepo extends JpaRepository<Ingrediente, Integer> {

}
