package com.brunofhome.testes.project.repository;

import com.brunofhome.testes.project.entity.AutoAvaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutoAvaliacaoRepository extends JpaRepository<AutoAvaliacao, UUID> {}
