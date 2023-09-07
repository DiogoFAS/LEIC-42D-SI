drop procedure insereVeiculo(int, char(10), int, int, int, int, varchar(10));
create or replace procedure insereVeiculo(
    vlId int, matricula char(10), cilindrada int, vlLugares int, tara int, condutor int, tipo varchar(10))
    language plpgSQL as
$$
begin

    if exists(select * from veiculopesado vp, veiculoligeiro vl where vp.vlmatricula = matricula or vl.vlmatricula = matricula) then
        raise exception 'Matricula já existe.';
    end if;

    if exists(select * from veiculopesado vp, veiculoligeiro vl where vp.vlcondutor = condutor or vl.vlcondutor = condutor) then
        raise exception 'Condutor % já conduz 1 veiculo.', condutor;
    end if;

    if (tipo = 'ligeiro') then
        begin
            insert into veiculoligeiro values (vlid, matricula, cilindrada, vllugares, condutor);
        end;

    else
        begin
            insert into veiculoligeiro values (vlid, matricula, cilindrada, tara, condutor);
        end;
    end if;
end;
$$;


call insereVeiculo(4,'99-FS-22',32,4,null,5,'ligeiro');
