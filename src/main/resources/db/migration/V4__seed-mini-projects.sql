-- Seed minimalista para mini_projeto_template
INSERT INTO mini_projeto_template (modulo_id, titulo, descricao, checklist_json)
SELECT m.id, 'Mini Projeto Exemplo', 'Descrição do mini projeto', '[{"id":"check1","text":"Checklist exemplo"}]'::jsonb
FROM modulo m WHERE m.slug = 'exemplo-modulo'
ON CONFLICT (modulo_id) DO NOTHING;