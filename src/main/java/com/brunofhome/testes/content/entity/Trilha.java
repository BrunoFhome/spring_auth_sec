package com.brunofhome.testes.content.entity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "trilha")
public class Trilha {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, unique = true)
  private Integer ordem;

  @Column(nullable = false, unique = true, length = 80)
  private String slug;

  @Column(nullable = false, length = 200)
  private String titulo;

  @Column(columnDefinition = "text")
  private String descricao;

  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @OneToMany(mappedBy = "trilha", fetch = FetchType.LAZY)
  @OrderBy("ordem ASC")
  private List<Modulo> modulos = new ArrayList<>();

  @PrePersist
  void prePersist() {
    createdAt = OffsetDateTime.now();
  }

  public UUID getId() { return id; }
  public Integer getOrdem() { return ordem; }
  public String getSlug() { return slug; }
  public String getTitulo() { return titulo; }
  public String getDescricao() { return descricao; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
  public List<Modulo> getModulos() { return modulos; }

  public void setOrdem(Integer ordem) { this.ordem = ordem; }
  public void setSlug(String slug) { this.slug = slug; }
  public void setTitulo(String titulo) { this.titulo = titulo; }
  public void setDescricao(String descricao) { this.descricao = descricao; }
}