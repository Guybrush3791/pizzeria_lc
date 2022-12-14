package org.pizzahat.com.demo.serv;

import java.util.List;

import org.hibernate.Hibernate;
import org.pizzahat.com.demo.pojo.Promozione;
import org.pizzahat.com.demo.repo.PromozioneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PromozioneServ {

	@Autowired
	private PromozioneRepo promozioneRepo;
	
	public void save(Promozione promozione) {
		
		promozioneRepo.save(promozione);
	}
	public void delete(Promozione promozione) {
		
		promozioneRepo.delete(promozione);
	}
	public List<Promozione> findAll() {
		
		return promozioneRepo.findAll();
	}
	@Transactional
	public List<Promozione> findAllWPizze() {
		
		List<Promozione> promozioni = promozioneRepo.findAll();
		
		for (Promozione p : promozioni) {
			
			Hibernate.initialize(p.getPizze());
		}
		
		return promozioni;
	}
	public Promozione findById(int id) {
		
		return promozioneRepo.findById(id).get();
	}
}
