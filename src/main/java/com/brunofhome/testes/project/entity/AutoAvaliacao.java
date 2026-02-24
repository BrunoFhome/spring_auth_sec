package com.brunofhome.testes.project.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "auto_avaliacao")
public class AutoAvaliacao {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "projeto_instancia_id", nullable = false, unique = true)
  private ProjetoInstancia projetoInstancia;

  @Column(name = "respostas_json", nullable = false, columnDefinition = "jsonb")
  private String respostasJson = "{}";

  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private OffsetDateTime updatedAt;

  @PrePersist
  void prePersist() {
    var now = OffsetDateTime.now();
    createdAt = now;
    updatedAt = now;
    if (respostasJson == null) respostasJson = "{}";
  }

  @PreUpdate
  void preUpdate() {
    updatedAt = OffsetDateTime.now();
  }

  public UUID getId() { return id; }
  public ProjetoInstancia getProjetoInstancia() { return projetoInstancia; }
  public String getRespostasJson() { return respostasJson; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
  public OffsetDateTime getUpdatedAt() { return updatedAt; }

  public void setProjetoInstancia(ProjetoInstancia projetoInstancia) { this.projetoInstancia = projetoInstancia; }
  public void setRespostasJson(String respostasJson) { this.respostasJson = respostasJson; }
}
