package com.brunofhome.testes;

import com.brunofhome.testes.content.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SanityCheckRunner implements CommandLineRunner {

  private final TrilhaRepository trilhaRepository;
  private final ModuloRepository moduloRepository;
  private final AulaRepository aulaRepository;
  private final QuestaoRepository questaoRepository;
  private final MiniProjetoTemplateRepository miniProjetoTemplateRepository;

 
  public SanityCheckRunner(
      TrilhaRepository trilhaRepository,
      ModuloRepository moduloRepository,
      AulaRepository aulaRepository,
      QuestaoRepository questaoRepository,
      MiniProjetoTemplateRepository miniProjetoTemplateRepository
  ) {
    this.trilhaRepository = trilhaRepository;
    this.moduloRepository = moduloRepository;
    this.aulaRepository = aulaRepository;
    this.questaoRepository = questaoRepository;
    this.miniProjetoTemplateRepository = miniProjetoTemplateRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public void run(String... args) {
    System.out.println("=== LUPITO SANITY CHECK ===");
    System.out.println("Trilhas: " + trilhaRepository.count());
    System.out.println("Modulos: " + moduloRepository.count());
    System.out.println("Aulas: " + aulaRepository.count());
    System.out.println("Questoes: " + questaoRepository.count());
    System.out.println("MiniProjetosTemplate: " + miniProjetoTemplateRepository.count());
    System.out.println("=== SANITY CHECK DONE ===");
  }
}