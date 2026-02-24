package com.brunofhome.testes.content.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "alternativa")
public class Alternativa {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "questao_id", nullable = false)
  private Questao questao;

  @Column(nullable = false, columnDefinition = "text")
  private String texto;

  @Column(nullable = false)
  private boolean correta = false;

  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @PrePersist
  void prePersist() {
    createdAt = OffsetDateTime.now();
  }

  public UUID getId() { return id; }
  public Questao getQuestao() { return questao; }
  public String getTexto() { return texto; }
  public boolean isCorreta() { return correta; }
  public OffsetDateTime getCreatedAt() { return createdAt; }

  public void setQuestao(Questao questao) { this.questao = questao; }
  public void setTexto(String texto) { this.texto = texto; }
  public void setCorreta(boolean correta) { this.correta = correta; }
}
