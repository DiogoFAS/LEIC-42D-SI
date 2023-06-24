insert into funcionario (num, nome, idade) VALUES (1, 'Jose', 52);
insert into funcionario (num, nome, idade) VALUES (2, 'Pedro', 32);
insert into funcionario (num, nome, idade) VALUES (3, 'Joao', 44);

insert into tarefa (id, nome) VALUES (1, 'limpar');
insert into tarefa (id, nome) VALUES (1, 'arrumar');

insert into funcionario_tarefa (num_func, id_tarefa) VALUES (1, 1);
insert into funcionario_tarefa (num_func, id_tarefa) VALUES (2, 1);