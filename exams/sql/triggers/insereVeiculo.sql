create trigger insereVeiculo
    INSTEAD OF insert on Veiculo
    for each row
    execute procedure insereVeiculo();