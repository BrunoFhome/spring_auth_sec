package com.brunofhome.testes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brunofhome.testes.dtos.UserDTO;
import com.brunofhome.testes.entities.User;
import com.brunofhome.testes.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		User user = repository.findById(id).orElseThrow(()-> new RuntimeException("Elemento nao encontrado"));
		return new UserDTO(user);
	}
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable){
		Page<User> client = repository.findAll(pageable);
		return client.map(x -> new UserDTO(x));
	}
	
	@Transactional
	public UserDTO insert(UserDTO dto) {
		User user = new User();
		copyDtoToEntity(dto, user);
		user = repository.save(user);
		return new UserDTO(user);
	}
	
	@Transactional
	public UserDTO update(UserDTO dto, Long id) {
		
		try {
			User entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UserDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new RuntimeException("Entidade nao encontrada");
		}
		
		
	}
	
	@Transactional
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new RuntimeException("Recurso nao encontrado");
		}
			repository.deleteById(id);
	}
	

	private void copyDtoToEntity(UserDTO dto, User user) {
		user.setName(dto.getName());
		user.setUsername(dto.getUsername());	
		user.setPassword(dto.getPassword());
		
	}

}
