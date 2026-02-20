package com.brunofhome.testes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brunofhome.testes.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
