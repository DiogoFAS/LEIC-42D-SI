create or replace procedure inicializaFuncionariosSemTarefas()
language plpgSQL as
$$
declare
idTarefa int;
funcionario record;
begin

    if not exists(select * from tarefa where nome = 'Sem tarefa') then
select max(id)+1 into idTarefa from tarefa;
insert into Tarefa (id, nome) values (idTarefa, 'Sem tarefa');
end if;

for funcionario in
select num from Funcionario f
                    left join funcionario_Tarefa ft on f.num = ft.num_func
where id_tarefa is null loop

insert into funcionario_tarefa (num_func, id_tarefa) VALUES (funcionario.num, idTarefa);

end loop;
end;
$$;

call inicializaFuncionariosSemTarefas();

select * from funcionario_tarefa