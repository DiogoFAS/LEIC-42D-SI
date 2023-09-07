create or replace function totalTarefas(limiteInferior int)
returns table(num int, nome varchar, totalTarefas bigint)
language plpgSQL as
$$
begin

    return query select f.num, f.nome, count(id_tarefa) as totalTarefas
                 from funcionario f
                 left join funcionario_tarefa ft on f.num = ft.num_func
                 join tarefa t on ft.id_tarefa = t.id
                 where t.nome != 'Sem tarefa' and f.idade < limiteInferior
                 group by f.num;

end;
$$;

select * from totalTarefas(62);