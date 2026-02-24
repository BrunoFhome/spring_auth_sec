package com.brunofhome.testes.project.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "portfolio_item")
public class PortfolioItem {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "usuario_id", nullable = false)
  private UUID usuarioId;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "projeto_instancia_id", nullable = false, unique = true)
  private ProjetoInstancia projetoInstancia;

  @Column(nullable = false, length = 200)
  private String titulo;

  @Column(columnDefinition = "text")
  private String descricao;

  @Column(name = "codigo_snapshot", nullable = false, columnDefinition = "text")
  private String codigoSnapshot;

  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @PrePersist
  void prePersist() {
    createdAt = OffsetDateTime.now();
  }

  public UUID getId() { return id; }
  public UUID getUsuarioId() { return usuarioId; }
  public ProjetoInstancia getProjetoInstancia() { return projetoInstancia; }
  public String getTitulo() { return titulo; }
  public String getDescricao() { return descricao; }
  public String getCodigoSnapshot() { return codigoSnapshot; }
  public OffsetDateTime getCreatedAt() { return createdAt; }

  public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
  public void setProjetoInstancia(ProjetoInstancia projetoInstancia) { this.projetoInstancia = projetoInstancia; }
  public void setTitulo(String titulo) { this.titulo = titulo; }
  public void setDescricao(String descricao) { this.descricao = descricao; }
  public void setCodigoSnapshot(String codigoSnapshot) { this.codigoSnapshot = codigoSnapshot; }
}
