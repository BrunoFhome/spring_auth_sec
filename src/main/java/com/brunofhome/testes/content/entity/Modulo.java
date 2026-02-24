package com.brunofhome.testes.content.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
    name = "modulo",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_modulo_trilha_ordem", columnNames = {"trilha_id", "ordem"}),
        @UniqueConstraint(name = "uq_modulo_trilha_slug", columnNames = {"trilha_id", "slug"})
    }
)
public class Modulo {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "trilha_id", nullable = false)
  private Trilha trilha;

  @Column(nullable = false)
  private Integer ordem;

  @Column(nullable = false, length = 120)
  private String slug;

  @Column(nullable = false, length = 200)
  private String titulo;

  @Column(columnDefinition = "text")
  private String descricao;

  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @OneToMany(mappedBy = "modulo", fetch = FetchType.LAZY)
  @OrderBy("ordem ASC")
  private List<Aula> aulas = new ArrayList<>();

  @OneToOne(mappedBy = "modulo", fetch = FetchType.LAZY)
  private MiniProjetoTemplate miniProjetoTemplate;

  @PrePersist
  void prePersist() {
    createdAt = OffsetDateTime.now();
  }

  public UUID getId() { return id; }
  public Trilha getTrilha() { return trilha; }
  public Integer getOrdem() { return ordem; }
  public String getSlug() { return slug; }
  public String getTitulo() { return titulo; }
  public String getDescricao() { return descricao; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
  public List<Aula> getAulas() { return aulas; }
  public MiniProjetoTemplate getMiniProjetoTemplate() { return miniProjetoTemplate; }

  public void setTrilha(Trilha trilha) { this.trilha = trilha; }
  public void setOrdem(Integer ordem) { this.ordem = ordem; }
  public void setSlug(String slug) { this.slug = slug; }
  public void setTitulo(String titulo) { this.titulo = titulo; }
  public void setDescricao(String descricao) { this.descricao = descricao; }
}
