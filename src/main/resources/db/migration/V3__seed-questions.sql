-- Seed minimalista para aula, questao e alternativa
INSERT INTO aula (modulo_id, slug, titulo, teoria, exemplo_codigo)
SELECT m.id, 'exemplo-aula', 'Aula Exemplo', 'Teoria exemplo', 'Código exemplo'
FROM modulo m WHERE m.slug = 'exemplo-modulo'
ON CONFLICT (modulo_id, slug) DO NOTHING;

INSERT INTO questao (aula_id, enunciado)
SELECT a.id, 'Enunciado de exemplo'
FROM aula a WHERE a.slug = 'exemplo-aula'
ON CONFLICT (aula_id) DO NOTHING;

INSERT INTO alternativa (questao_id, texto, correta)
SELECT q.id, 'Alternativa correta', TRUE FROM questao q WHERE q.enunciado = 'Enunciado de exemplo'
ON CONFLICT (questao_id, texto) DO NOTHING;