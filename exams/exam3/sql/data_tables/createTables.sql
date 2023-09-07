create table Funcionarios(
    num int primary key,
    nome varchar(255) not null,
    idade int not null,
    tipo char check ( tipo in ('C', 'N') )
);

create table FuncChefes(
    num int primary key references Funcionarios,
    telefone char(9) not null
);

alter table Funcionarios add chefe int references FuncChefes check ( chefe <> num );
