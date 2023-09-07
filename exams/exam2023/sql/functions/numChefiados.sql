create or replace function numChefiados()
returns table(nomeCh varchar, nChefiados int)
language plpgSQL as
$$
begin
    return query select f.nome as nomeCh,
    (select count(*) :: int from funcionarios where chefe = f.num) as numChefiados
    from Funcionarios f where tipo = 'C';
end;
$$;
