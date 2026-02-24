package com.brunofhome.testes.content.repository;

import com.brunofhome.testes.content.entity.Questao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuestaoRepository extends JpaRepository<Questao, UUID> {}
