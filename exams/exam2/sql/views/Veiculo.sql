drop view Veiculo;
create view Veiculo as
SELECT vlId         AS id,
       vlMatricula  AS matricula,
       vlCilindrada AS cilindrada,
       vlLugares    AS lugares,
       null         AS tara,
       vlCondutor   AS condutor,
       'ligeiro'    AS tipo
FROM veiculoLigeiro
UNION ALL
SELECT vlId        AS id,
       vlMatricula AS matricula,
       null        AS cilindrada,
       null        AS lugares,
       vlTara      AS tara,
       vlCondutor  AS condutor,
       'pesado'    AS tipo
FROM veiculoPesado;