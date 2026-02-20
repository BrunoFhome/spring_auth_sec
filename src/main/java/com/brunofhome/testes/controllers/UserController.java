package com.brunofhome.testes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunofhome.testes.entities.User;
import com.brunofhome.testes.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
	}
	
	@PutMapping("/{id}")
    public User updateProduct(@PathVariable Long id, @RequestBody User userDetails) {
		User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

		user.setName(userDetails.getName());
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());

        return repository.save(user);
    }
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
		
	}
	
	
	
}
