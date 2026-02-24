package com.brunofhome.testes.content.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "questao")
public class Questao {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "aula_id", nullable = false, unique = true)
  private Aula aula;

  @Column(nullable = false, columnDefinition = "text")
  private String enunciado;

  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @OneToMany(mappedBy = "questao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Alternativa> alternativas = new ArrayList<>();

  @PrePersist
  void prePersist() {
    createdAt = OffsetDateTime.now();
  }

  public UUID getId() { return id; }
  public Aula getAula() { return aula; }
  public String getEnunciado() { return enunciado; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
  public List<Alternativa> getAlternativas() { return alternativas; }

  public void setAula(Aula aula) { this.aula = aula; }
  public void setEnunciado(String enunciado) { this.enunciado = enunciado; }
}
