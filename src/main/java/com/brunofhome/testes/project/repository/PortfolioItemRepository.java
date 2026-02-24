package com.brunofhome.testes.project.repository;

import com.brunofhome.testes.project.entity.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, UUID> {}
