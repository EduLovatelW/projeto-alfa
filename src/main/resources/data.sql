INSERT INTO client (id, name) VALUES (1, 'Empresa A'), (2, 'Empresa B'), (3, 'Empresa C');
INSERT INTO module (id, name) VALUES (1, 'Financeiro'), (2, 'RH'), (3, 'Comercial');

INSERT INTO ticket (title, opening_date, closing_date, client_id, module_id)
VALUES
('Erro no sistema de login', '2025-11-01', NULL, 1, 1),
('Relatório não gera PDF', '2025-11-02', '2025-11-03', 2, 2),
('Falha ao enviar e-mail', '2025-11-05', NULL, 3, 3),
('Problema no dashboard', '2025-11-07', NULL, 1, 3);
