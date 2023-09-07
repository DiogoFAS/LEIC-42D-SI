-- chefes
INSERT INTO funcionarios (num, nome, idade, tipo, chefe) VALUES (1, 'jose', 69, 'C', NULL);
INSERT INTO funcchefes (num, telefone) VALUES (1, '123456789');

INSERT INTO funcionarios (num, nome, idade, tipo, chefe) VALUES (2, 'alberto', 55, 'C', NULL);
INSERT INTO funcchefes (num, telefone) VALUES (2, '987654321');

INSERT INTO funcionarios (num, nome, idade, tipo, chefe) VALUES (3, 'Maria', 45, 'C', NULL);
INSERT INTO funcchefes (num, telefone) VALUES (3, '555555555');

INSERT INTO funcionarios (num, nome, idade, tipo, chefe) VALUES (4, 'Ana', 32, 'C', NULL);
INSERT INTO funcchefes (num, telefone) VALUES (4, '999999999');

-- chefiados
INSERT INTO funcionarios (num, nome, idade, tipo, chefe) VALUES (5, 'Rafael', 25, 'N', 1);
INSERT INTO funcionarios (num, nome, idade, tipo, chefe) VALUES (6, 'Carolina', 30, 'N', 2);
INSERT INTO funcionarios (num, nome, idade, tipo, chefe) VALUES (7, 'Pedro', 40, 'N', 3);
INSERT INTO funcionarios (num, nome, idade, tipo, chefe) VALUES (8, 'Julia', 35, 'N', 4);
INSERT INTO funcionarios (num, nome, idade, tipo, chefe) VALUES (9, 'Gabriel', 28, 'N', 1);
INSERT INTO funcionarios (num, nome, idade, tipo, chefe) VALUES (10, 'Larissa', 33, 'N', 2);
INSERT INTO funcionarios (num, nome, idade, tipo, chefe) VALUES (11, 'Ricardo', 41, 'N', 4);
