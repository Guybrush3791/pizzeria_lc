package org.pizzahat.com.demo.serv;

import java.util.List;
import java.util.Optional;

import org.pizzahat.com.demo.pojo.User;
import org.pizzahat.com.demo.repo.UserRepo;
import org.pizzahat.com.demo.security.DatabaseUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServ implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	public void save(User user) {
		
		userRepo.save(user);
	}
	public void delete(User user) {
		
		userRepo.delete(user);
	}
	public User findById(int id) {
		
		return userRepo.findById(id).get();
	}
	public List<User> findAll() {
		
		return userRepo.findAll();
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userOpt = userRepo.findByUsername(username);
		
		if (userOpt.isEmpty()) throw new UsernameNotFoundException("User not found");
		
		return new DatabaseUserDetail(userOpt.get());
	}
}
