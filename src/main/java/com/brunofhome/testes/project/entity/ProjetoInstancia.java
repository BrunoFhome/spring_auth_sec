package com.brunofhome.testes.project.entity;

import com.brunofhome.testes.common.enums.KanbanStatus;
import com.brunofhome.testes.content.entity.MiniProjetoTemplate;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(
    name = "projeto_instancia",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_projeto_usuario_template", columnNames = {"usuario_id", "mini_projeto_template_id"})
    }
)
public class ProjetoInstancia {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "usuario_id", nullable = false)
  private UUID usuarioId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "mini_projeto_template_id", nullable = false)
  private MiniProjetoTemplate miniProjetoTemplate;

  @Enumerated(EnumType.STRING)
  @Column(name = "status_kanban", nullable = false)
  private KanbanStatus statusKanban = KanbanStatus.A_FAZER;

  @Column(name = "codigo_atual", nullable = false, columnDefinition = "text")
  private String codigoAtual = "";

  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private OffsetDateTime updatedAt;

  @OneToOne(mappedBy = "projetoInstancia", fetch = FetchType.LAZY)
  private AutoAvaliacao autoAvaliacao;

  @OneToOne(mappedBy = "projetoInstancia", fetch = FetchType.LAZY)
  private PortfolioItem portfolioItem;

  @PrePersist
  void prePersist() {
    var now = OffsetDateTime.now();
    createdAt = now;
    updatedAt = now;
    if (statusKanban == null) statusKanban = KanbanStatus.A_FAZER;
    if (codigoAtual == null) codigoAtual = "";
  }

  @PreUpdate
  void preUpdate() {
    updatedAt = OffsetDateTime.now();
  }

  public UUID getId() { return id; }
  public UUID getUsuarioId() { return usuarioId; }
  public MiniProjetoTemplate getMiniProjetoTemplate() { return miniProjetoTemplate; }
  public KanbanStatus getStatusKanban() { return statusKanban; }
  public String getCodigoAtual() { return codigoAtual; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
  public OffsetDateTime getUpdatedAt() { return updatedAt; }
  public AutoAvaliacao getAutoAvaliacao() { return autoAvaliacao; }
  public PortfolioItem getPortfolioItem() { return portfolioItem; }

  public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
  public void setMiniProjetoTemplate(MiniProjetoTemplate miniProjetoTemplate) { this.miniProjetoTemplate = miniProjetoTemplate; }
  public void setStatusKanban(KanbanStatus statusKanban) { this.statusKanban = statusKanban; }
  public void setCodigoAtual(String codigoAtual) { this.codigoAtual = codigoAtual; }
}
