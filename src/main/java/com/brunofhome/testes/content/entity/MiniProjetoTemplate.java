package com.brunofhome.testes.content.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "mini_projeto_template")
public class MiniProjetoTemplate {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "modulo_id", nullable = false, unique = true)
  private Modulo modulo;

  @Column(nullable = false, length = 200)
  private String titulo;

  @Column(nullable = false, columnDefinition = "text")
  private String descricao;

  @Column(name = "checklist_json", nullable = false, columnDefinition = "jsonb")
  private String checklistJson = "[]";

  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @PrePersist
  void prePersist() {
    createdAt = OffsetDateTime.now();
  }

  public UUID getId() { return id; }
  public Modulo getModulo() { return modulo; }
  public String getTitulo() { return titulo; }
  public String getDescricao() { return descricao; }
  public String getChecklistJson() { return checklistJson; }
  public OffsetDateTime getCreatedAt() { return createdAt; }

  public void setModulo(Modulo modulo) { this.modulo = modulo; }
  public void setTitulo(String titulo) { this.titulo = titulo; }
  public void setDescricao(String descricao) { this.descricao = descricao; }
  public void setChecklistJson(String checklistJson) { this.checklistJson = checklistJson; }
}
