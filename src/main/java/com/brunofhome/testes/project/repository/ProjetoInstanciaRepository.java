package com.brunofhome.testes.project.repository;

import com.brunofhome.testes.project.entity.ProjetoInstancia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjetoInstanciaRepository extends JpaRepository<ProjetoInstancia, UUID> {}
