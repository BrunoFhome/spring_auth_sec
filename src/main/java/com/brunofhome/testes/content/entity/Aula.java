package com.brunofhome.testes.content.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(
    name = "aula",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_aula_modulo_ordem", columnNames = {"modulo_id", "ordem"}),
        @UniqueConstraint(name = "uq_aula_modulo_slug", columnNames = {"modulo_id", "slug"})
    }
)
public class Aula {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "modulo_id", nullable = false)
  private Modulo modulo;

  @Column(nullable = false)
  private Integer ordem;

  @Column(nullable = false, length = 140)
  private String slug;

  @Column(nullable = false, length = 200)
  private String titulo;

  @Column(nullable = false, columnDefinition = "text")
  private String teoria;

  @Column(name = "exemplo_codigo", nullable = false, columnDefinition = "text")
  private String exemploCodigo;

  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @OneToOne(mappedBy = "aula", fetch = FetchType.LAZY)
  private Questao questao;

  @PrePersist
  void prePersist() {
    createdAt = OffsetDateTime.now();
  }

  public UUID getId() { return id; }
  public Modulo getModulo() { return modulo; }
  public Integer getOrdem() { return ordem; }
  public String getSlug() { return slug; }
  public String getTitulo() { return titulo; }
  public String getTeoria() { return teoria; }
  public String getExemploCodigo() { return exemploCodigo; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
  public Questao getQuestao() { return questao; }

  public void setModulo(Modulo modulo) { this.modulo = modulo; }
  public void setOrdem(Integer ordem) { this.ordem = ordem; }
  public void setSlug(String slug) { this.slug = slug; }
  public void setTitulo(String titulo) { this.titulo = titulo; }
  public void setTeoria(String teoria) { this.teoria = teoria; }
  public void setExemploCodigo(String exemploCodigo) { this.exemploCodigo = exemploCodigo; }
}