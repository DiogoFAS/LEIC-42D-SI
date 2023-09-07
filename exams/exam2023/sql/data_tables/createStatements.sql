begin transaction;
create table Funcionarios (
                              num int primary key,
                              nome varchar(255) not null,
                              idade int not null,
                              tipo char check (tipo in ('C','N')), -- 'C' - chefe, 'N' não chefe
                              chefe int --references FuncChefes check (chefe <> num)
);
create table FuncChefes (
                            num int primary key references Funcionarios,
                            telefone char(9) not null
);
alter table Funcionarios add constraint c foreign key (chefe) references FuncChefes;
commit;


