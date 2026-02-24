-- Seed minimalista para trilha e modulo
INSERT INTO trilha (slug, titulo, descricao)
VALUES ('exemplo-trilha', 'Trilha Exemplo', 'Descrição de exemplo')
ON CONFLICT (slug) DO NOTHING;

INSERT INTO modulo (trilha_id, slug, titulo, descricao)
SELECT t.id, 'exemplo-modulo', 'Módulo Exemplo', 'Descrição do módulo exemplo'
FROM trilha t WHERE t.slug = 'exemplo-trilha'
ON CONFLICT (trilha_id, slug) DO NOTHING;