create table if not exists Funcionario(
    num int primary key,
    nome varchar(255) not null,
    idade int not null
);

create table if not exists tarefa(
    id int primary key,
    nome varchar(255) not null
);

create table if not exists funcionario_Tarefa(
   num_func int references Funcionario,
   id_tarefa int references Tarefa,
   primary key(num_func, id_tarefa)
);