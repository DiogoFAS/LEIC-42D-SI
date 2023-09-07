drop function numChefiados();
create or replace function numChefiados()
returns table(nome varchar,num bigint)
language plpgSQL as
$$
begin
    return query
        SELECT f2.nome, count(f2.nome)
        FROM Funcionarios f, FuncChefes fc join funcionarios f2 on f2.num = fc.num where f.chefe = fc.num
        group by f2.nome;
end;
$$;

select * from numChefiados();