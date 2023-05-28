--insert Regiao
insert into Regiao (nome) values ('Lisboa');
insert into Regiao (nome) values ('Santarem');
insert into Regiao (nome) values ('Porto');
insert into Regiao (nome) values ('Chelas');

--insert Jogador
insert into Jogador (id, estado, username, email, nomeRegiao) values (1, 'Ativo', 'Santos01', 'Santos01@gmail.com', 'Chelas');
insert into Jogador (id, estado, username, email, nomeRegiao) values (2, 'Ativo', 'Guerra38', 'Guerra38@gmail.com', 'Santarem');
insert into Jogador (id, estado, username, email, nomeRegiao) values (3, 'Ativo', 'André3', 'André3@gmail.com', 'Porto');
insert into Jogador (id, estado, username, email, nomeRegiao) values (4, 'Ativo', 'Joao23', 'Joao23@gmail.com', 'Lisboa');
insert into Jogador (id, estado, username, email, nomeRegiao) values (5, 'Ativo', 'Daniel99', 'Daniel99@gmail.com', 'Porto');
insert into Jogador (id, estado, username, email, nomeRegiao) values (6, 'Ativo', 'DaniloBoy', 'DaniloBoy@gmail.com', 'Chelas');

--insert Jogo
insert into Jogo (nome, id, URL) values ('Xadrez', 1, 'http:/Xadrez');
insert into Jogo (nome, id, URL) values ('Damas', 2, 'http://Damas');
insert into Jogo (nome, id, URL) values ('Snake', 3, 'http://Snake');
insert into Jogo (nome, id, URL) values ('BatalhaNaval', 4, 'http://BatalhaNaval');

--insert Cracha
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('XadrezRank1', 'Xadrez', 1000, 'http:/XadrezRank1.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('XadrezRank2', 'Xadrez', 500, 'http:/XadrezRank2.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('XadrezRank3', 'Xadrez', 250, 'http:/XadrezRank3.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('XadrezRank4', 'Xadrez', 100, 'http:/XadrezRank4.jpg');

insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('DamasRank1', 'Damas', 1000, 'http://DamasRank1.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('DamasRank2', 'Damas', 500, 'http://DamasRank2.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('DamasRank3', 'Damas', 250, 'http://DamasRank3.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('DamasRank4', 'Damas', 100, 'http://DamasRank4.jpg');

insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SnakeRank1', 'Snake', 1000, 'http://SnakeRank1.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SnakeRank2', 'Snake', 500, 'http://SnakeRank2.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SnakeRank3', 'Snake', 250, 'http://SnakeRank3.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SnakeRank4', 'Snake', 100, 'http://SnakeRank4.jpg');

insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('BatalhaNavalRank1', 'BatalhaNaval', 1000, 'http://BatalhaNavalRank1.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('BatalhaNavalRank2', 'BatalhaNaval', 500, 'http://BatalhaNavalRank2.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('BatalhaNavalRank3', 'BatalhaNaval', 250, 'http://BatalhaNavalRank3.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('BatalhaNavalRank4', 'BatalhaNaval', 100, 'http://BatalhaNavalRank4.jpg');

--insert Partida
insert into Partida (id, nomeJogo, dataFim) values (1, 'Xadrez', '2024-04-30 19:40:00');
insert into Partida (id, nomeJogo, dataFim) values (2, 'Damas', '2024-04-30 19:40:00');
insert into Partida (id, nomeJogo, dataFim) values (3, 'Snake', '2024-04-30 21:20:00');
insert into Partida (id, nomeJogo, dataFim) values (4, 'BatalhaNaval', '2024-04-30 12:30:00');
insert into Partida (id, nomeJogo, dataFim) values (5, 'BatalhaNaval', '2024-04-23 21:30:00');


--insert MultiJogador
insert into MultiJogador (idPartida, nomeJogo, estado, nomeRegiao) values (1, 'Xadrez', 'Terminada', 'Chelas');
insert into MultiJogador (idPartida, nomeJogo, estado, nomeRegiao) values (4, 'BatalhaNaval', 'Terminada', 'Porto');

--insert Normal
insert into Normal (idPartida, nomeJogo, dificuldade, idJogador, pontuacao) values (2, 'Damas', 4, 3, 350);
insert into Normal (idPartida, nomeJogo, dificuldade, idJogador, pontuacao) values (3, 'Snake', 3, 2, 450);
insert into Normal (idPartida, nomeJogo, dificuldade, idJogador, pontuacao) values (5, 'BatalhaNaval', 3, 3, 500);


--insert Jogar
insert into Jogar (idPartida, nomeJogo, idJogador, pontuacao) values (1, 'Xadrez', 1, 450);
insert into Jogar (idPartida, nomeJogo, idJogador, pontuacao) values (1, 'Xadrez', 6, 500);
insert into Jogar (idPartida, nomeJogo, idJogador, pontuacao) values (4, 'BatalhaNaval', 3, 700);
insert into Jogar (idPartida, nomeJogo, idJogador, pontuacao) values (4, 'BatalhaNaval', 5, 650);