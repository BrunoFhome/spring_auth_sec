package com.brunofhome.testes.content.repository;

import com.brunofhome.testes.content.entity.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AulaRepository extends JpaRepository<Aula, UUID> {}
