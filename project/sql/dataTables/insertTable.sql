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
insert into Jogo (nome, id, URL) values ('Tetris', 1, 'http://Tetris64');
insert into Jogo (nome, id, URL) values ('Pacman', 2, 'http://Pacman');
insert into Jogo (nome, id, URL) values ('Snake', 3, 'http://Snake');
insert into Jogo (nome, id, URL) values ('SpaceInv', 4, 'http://SpaceInvaders');

--insert Cracha
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('TetrisRank1', 'Tetris', 1000, 'http://TetrisRank1.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('TetrisRank2', 'Tetris', 500, 'http://TetrisRank2.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('TetrisRank3', 'Tetris', 250, 'http://TetrisRank3.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('TetrisRank4', 'Tetris', 100, 'http://TetrisRank4.jpg');

insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('PacmanRank1', 'Pacman', 1000, 'http://PacmanRank1.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('PacmanRank2', 'Pacman', 500, 'http://PacmanRank2.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('PacmanRank3', 'Pacman', 250, 'http://PacmanRank3.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('PacmanRank4', 'Pacman', 100, 'http://PacmanRank4.jpg');

insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SnakeRank1', 'Snake', 1000, 'http://SnakeRank1.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SnakeRank2', 'Snake', 500, 'http://SnakeRank2.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SnakeRank3', 'Snake', 250, 'http://SnakeRank3.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SnakeRank4', 'Snake', 100, 'http://SnakeRank4.jpg');

insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SpaceInvadersRank1', 'SpaceInv', 1000, 'http://SpaceInvadersRank1.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SpaceInvadersRank2', 'SpaceInv', 500, 'http://SpaceInvadersRank2.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SpaceInvadersRank3', 'SpaceInv', 250, 'http://SpaceInvadersRank3.jpg');
insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SpaceInvadersRank4', 'SpaceInv', 100, 'http://SpaceInvadersRank4.jpg');

--insert Partida
insert into Partida (id, nomeJogo, dataFim) values (1, 'Tetris', '2024-04-30 19:40:00');
insert into Partida (id, nomeJogo, dataFim) values (2, 'Pacman', '2024-04-30 19:40:00');
insert into Partida (id, nomeJogo, dataFim) values (3, 'Snake', '2024-04-30 21:20:00');
insert into Partida (id, nomeJogo, dataFim) values (4, 'SpaceInv', '2024-04-30 12:30:00');
insert into Partida (id, nomeJogo, dataFim) values (5, 'SpaceInv', '2024-04-23 21:30:00');


--insert MultiJogador
insert into MultiJogador (idPartida, nomeJogo, estado, nomeRegiao) values (1, 'Tetris', 'Terminada', 'Chelas');
insert into MultiJogador (idPartida, nomeJogo, estado, nomeRegiao) values (4, 'SpaceInv', 'Terminada', 'Porto');

--insert Normal
insert into Normal (idPartida, nomeJogo, dificuldade, idJogador, pontuacao) values (2, 'Pacman', 4, 3, 350);
insert into Normal (idPartida, nomeJogo, dificuldade, idJogador, pontuacao) values (3, 'Snake', 3, 2, 450);
insert into Normal (idPartida, nomeJogo, dificuldade, idJogador, pontuacao) values (5, 'SpaceInv', 3, 3, 500);


--insert Jogar
insert into Jogar (idPartida, nomeJogo, idJogador, pontuacao) values (1, 'Tetris', 1, 450);
insert into Jogar (idPartida, nomeJogo, idJogador, pontuacao) values (1, 'Tetris', 6, 500);
insert into Jogar (idPartida, nomeJogo, idJogador, pontuacao) values (4, 'SpaceInv', 3, 700);
insert into Jogar (idPartida, nomeJogo, idJogador, pontuacao) values (4, 'SpaceInv', 5, 650);