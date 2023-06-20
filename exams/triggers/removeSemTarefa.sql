create or replace function removeSemTarefa() returns trigger
language plpgSQL as
$$
declare
    idTarefa int;
begin

	if (TG_OP <> 'INSERT') then
		raise exception 'gatilho inválido';
    end if;

    select t.id into idTarefa from tarefa t
    join funcionario_tarefa ft on t.id = ft.id_tarefa
    where t.nome = 'Sem tarefa' and ft.num_func = new.num_func;

    if idTarefa IS NOT NULL then

        delete from funcionario_tarefa where id_tarefa = idTarefa and num_func = new.num_func;

    end if;
    return null;
end;
$$;


create trigger removeSemTarefa
after insert on funcionario_tarefa
for each row execute procedure removeSemTarefa();

select * from funcionario_tarefa;

insert into funcionario_tarefa (num_func, id_tarefa) values (2, 1);
