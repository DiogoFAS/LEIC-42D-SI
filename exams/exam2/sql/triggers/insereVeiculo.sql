create trigger insereVeiculo
    INSTEAD OF insert
    on Veiculo
    for each row
execute procedure insereVeiculo(
        new.id,
        new.matricula,
        new.cilindrada,
        new.lugares,
        new.tara,
        new.condutor,
        new.tipo);