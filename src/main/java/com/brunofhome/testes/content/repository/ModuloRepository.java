package com.brunofhome.testes.content.repository;

import com.brunofhome.testes.content.entity.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuloRepository extends JpaRepository<Modulo, UUID> {}
