package com.brunofhome.testes.content.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunofhome.testes.content.entity.Trilha;

public interface TrilhaRepository extends JpaRepository<Trilha, UUID> {
	
	
}
