package org.pizzahat.com.demo.repo;

import org.pizzahat.com.demo.pojo.Promozione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromozioneRepo extends JpaRepository<Promozione, Integer> {

}
