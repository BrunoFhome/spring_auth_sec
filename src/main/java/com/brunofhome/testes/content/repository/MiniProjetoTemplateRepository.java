package com.brunofhome.testes.content.repository;

import com.brunofhome.testes.content.entity.MiniProjetoTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MiniProjetoTemplateRepository extends JpaRepository<MiniProjetoTemplate, UUID> {}
