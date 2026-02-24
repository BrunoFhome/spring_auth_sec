CREATE EXTENSION IF NOT EXISTS pgcrypto;
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'progresso_modulo_status') THEN
    CREATE TYPE progresso_modulo_status AS ENUM ('BLOQUEADO','DESBLOQUEADO','CONCLUIDO');
  END IF;

  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'kanban_status') THEN
    CREATE TYPE kanban_status AS ENUM ('A_FAZER','EM_DESENVOLVIMENTO','EM_AVALIACAO','CONCLUIDO');
  END IF;
END$$;
CREATE TABLE IF NOT EXISTS trilha (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  slug VARCHAR(80) NOT NULL UNIQUE,
  titulo VARCHAR(200) NOT NULL,
  descricao TEXT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- =========================
-- USUARIO (fica pronto, mesmo sem auth por enquanto)
-- =========================
CREATE TABLE IF NOT EXISTS usuario (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  nome VARCHAR(120) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  senha_hash VARCHAR(255) NOT NULL,
  trilha_atual_ordem INT NOT NULL DEFAULT 1,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
CREATE TABLE IF NOT EXISTS modulo (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  trilha_id UUID NOT NULL REFERENCES trilha(id) ON DELETE CASCADE,
  slug VARCHAR(120) NOT NULL,
  titulo VARCHAR(200) NOT NULL,
  descricao TEXT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

);

CREATE INDEX IF NOT EXISTS idx_usuario_email ON usuario(email);

CREATE TABLE IF NOT EXISTS refresh_token (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  usuario_id UUID NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
  token_hash VARCHAR(255) NOT NULL UNIQUE,
  expires_at TIMESTAMPTZ NOT NULL,
  revoked_at TIMESTAMPTZ NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
CREATE TABLE IF NOT EXISTS aula (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  modulo_id UUID NOT NULL REFERENCES modulo(id) ON DELETE CASCADE,
  slug VARCHAR(140) NOT NULL,
  titulo VARCHAR(200) NOT NULL,
  teoria TEXT NOT NULL,
  exemplo_codigo TEXT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);


CREATE INDEX IF NOT EXISTS idx_refresh_token_usuario ON refresh_token(usuario_id);
CREATE INDEX IF NOT EXISTS idx_refresh_token_expires ON refresh_token(expires_at);

-- =========================
-- CONTEUDO
CREATE TABLE IF NOT EXISTS questao (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  aula_id UUID NOT NULL REFERENCES aula(id) ON DELETE CASCADE,
  enunciado TEXT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- =========================
CREATE TABLE IF NOT EXISTS trilha (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  ordem INT NOT NULL UNIQUE,
  slug VARCHAR(80) NOT NULL UNIQUE,
  titulo VARCHAR(200) NOT NULL,
  descricao TEXT NULL,
CREATE TABLE IF NOT EXISTS alternativa (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  questao_id UUID NOT NULL REFERENCES questao(id) ON DELETE CASCADE,
  texto TEXT NOT NULL,
  correta BOOLEAN NOT NULL DEFAULT FALSE,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS modulo (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  trilha_id UUID NOT NULL REFERENCES trilha(id) ON DELETE CASCADE,
  ordem INT NOT NULL,
  slug VARCHAR(120) NOT NULL,
CREATE TABLE IF NOT EXISTS mini_projeto_template (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  modulo_id UUID NOT NULL REFERENCES modulo(id) ON DELETE CASCADE,
  titulo VARCHAR(200) NOT NULL,
  descricao TEXT NOT NULL,
  checklist_json JSONB NOT NULL DEFAULT '[]'::jsonb,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

  titulo VARCHAR(200) NOT NULL,
  descricao TEXT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  CONSTRAINT uq_modulo_trilha_ordem UNIQUE (trilha_id, ordem),
  CONSTRAINT uq_modulo_trilha_slug UNIQUE (trilha_id, slug)
);

CREATE INDEX IF NOT EXISTS idx_modulo_trilha ON modulo(trilha_id);

CREATE TABLE IF NOT EXISTS aula (
CREATE TABLE IF NOT EXISTS projeto_instancia (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  mini_projeto_template_id UUID NOT NULL REFERENCES mini_projeto_template(id) ON DELETE CASCADE,
  status_kanban VARCHAR(40) NOT NULL DEFAULT 'A_FAZER',
  codigo_atual TEXT NOT NULL DEFAULT '',
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  modulo_id UUID NOT NULL REFERENCES modulo(id) ON DELETE CASCADE,
  ordem INT NOT NULL,
  slug VARCHAR(140) NOT NULL,
  titulo VARCHAR(200) NOT NULL,
  teoria TEXT NOT NULL,
  exemplo_codigo TEXT NOT NULL,
CREATE TABLE IF NOT EXISTS auto_avaliacao (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  projeto_instancia_id UUID NOT NULL REFERENCES projeto_instancia(id) ON DELETE CASCADE,
  respostas_json JSONB NOT NULL DEFAULT '{}'::jsonb,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  CONSTRAINT uq_aula_modulo_ordem UNIQUE (modulo_id, ordem),
  CONSTRAINT uq_aula_modulo_slug UNIQUE (modulo_id, slug)
);

CREATE INDEX IF NOT EXISTS idx_aula_modulo ON aula(modulo_id);

CREATE TABLE IF NOT EXISTS questao (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
CREATE TABLE IF NOT EXISTS portfolio_item (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  projeto_instancia_id UUID NOT NULL REFERENCES projeto_instancia(id) ON DELETE CASCADE,
  titulo VARCHAR(200) NOT NULL,
  descricao TEXT NULL,
  codigo_snapshot TEXT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
  aula_id UUID NOT NULL REFERENCES aula(id) ON DELETE CASCADE UNIQUE,
  enunciado TEXT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS alternativa (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  questao_id UUID NOT NULL REFERENCES questao(id) ON DELETE CASCADE,
  texto TEXT NOT NULL,
  correta BOOLEAN NOT NULL DEFAULT FALSE,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_alternativa_questao ON alternativa(questao_id);

CREATE TABLE IF NOT EXISTS mini_projeto_template (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  modulo_id UUID NOT NULL REFERENCES modulo(id) ON DELETE CASCADE UNIQUE,
  titulo VARCHAR(200) NOT NULL,
  descricao TEXT NOT NULL,
  checklist_json JSONB NOT NULL DEFAULT '[]'::jsonb,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- =========================
-- PROGRESSO (fica pronto, mesmo se você não usar agora)
-- =========================
CREATE TABLE IF NOT EXISTS progresso_modulo (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  usuario_id UUID NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
  modulo_id UUID NOT NULL REFERENCES modulo(id) ON DELETE CASCADE,
  status progresso_modulo_status NOT NULL DEFAULT 'BLOQUEADO',
  desbloqueado_em TIMESTAMPTZ NULL,
  concluido_em TIMESTAMPTZ NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  CONSTRAINT uq_progresso_modulo_usuario_modulo UNIQUE (usuario_id, modulo_id)
);

CREATE INDEX IF NOT EXISTS idx_progresso_modulo_usuario ON progresso_modulo(usuario_id);
CREATE INDEX IF NOT EXISTS idx_progresso_modulo_modulo ON progresso_modulo(modulo_id);

CREATE TABLE IF NOT EXISTS progresso_aula (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  usuario_id UUID NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
  aula_id UUID NOT NULL REFERENCES aula(id) ON DELETE CASCADE,
  acertou_questao BOOLEAN NOT NULL DEFAULT FALSE,
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  CONSTRAINT uq_progresso_aula_usuario_aula UNIQUE (usuario_id, aula_id)
);

CREATE INDEX IF NOT EXISTS idx_progresso_aula_usuario ON progresso_aula(usuario_id);
CREATE INDEX IF NOT EXISTS idx_progresso_aula_aula ON progresso_aula(aula_id);

-- =========================
-- KANBAN / PORTFOLIO
-- =========================
CREATE TABLE IF NOT EXISTS projeto_instancia (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  usuario_id UUID NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
  mini_projeto_template_id UUID NOT NULL REFERENCES mini_projeto_template(id) ON DELETE CASCADE,
  status_kanban kanban_status NOT NULL DEFAULT 'A_FAZER',
  codigo_atual TEXT NOT NULL DEFAULT '',
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  CONSTRAINT uq_projeto_usuario_template UNIQUE (usuario_id, mini_projeto_template_id)
);

CREATE INDEX IF NOT EXISTS idx_projeto_usuario ON projeto_instancia(usuario_id);
CREATE INDEX IF NOT EXISTS idx_projeto_status ON projeto_instancia(status_kanban);

CREATE TABLE IF NOT EXISTS auto_avaliacao (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  projeto_instancia_id UUID NOT NULL REFERENCES projeto_instancia(id) ON DELETE CASCADE UNIQUE,
  respostas_json JSONB NOT NULL DEFAULT '{}'::jsonb,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS portfolio_item (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  usuario_id UUID NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
  projeto_instancia_id UUID NOT NULL REFERENCES projeto_instancia(id) ON DELETE CASCADE UNIQUE,
  titulo VARCHAR(200) NOT NULL,
  descricao TEXT NULL,
  codigo_snapshot TEXT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_portfolio_usuario ON portfolio_item(usuario_id);