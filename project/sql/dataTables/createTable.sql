
create table if not exists Regiao (
	nome varchar(10) primary key
);

create table if not exists Jogador (
	id serial primary key,
	estado varchar(10) check (estado = 'Ativo' or estado = 'Inativo' or estado = 'Banido'),
	userName varchar(20) unique,
	email varchar(30) unique,
	nomeRegiao varchar(10),
	foreign key (nomeRegiao) references Regiao(nome)
);

create table if not exists Jogo (
	nome varchar(20) primary key,
	id varchar(10),
	URL varchar(50)
);

create table if not exists Conversa (
	id serial,
	idJogador integer,
	nome varchar(20),
	primary key (id, idJogador),
	foreign key (idJogador) references Jogador(id)
);

create table if not exists Mensagem (
	id serial,
	idConversa integer,
	idJogador integer,
	texto varchar(200),
	dataDeEnvio timestamp default now(),
	primary key (id, idConversa, idJogador),
	foreign key (idConversa, idJogador) references Conversa(id, idJogador)
);

create table if not exists Cracha (
	nome varchar(20),
	nomeJogo varchar(20),
	limiteDePontos integer,
	URL varchar(50),
	primary key (nome, nomeJogo),
	foreign key (nomeJogo) references Jogo(nome)
);

create table if not exists Partida (
	id integer,
	nomeJogo varchar(20),
	dataInicio timestamp default now(),
	dataFim timestamp check (dataFim > dataInicio),
	primary key (id, nomeJogo),
	foreign key (nomeJogo) references Jogo(nome)
);

create table if not exists MultiJogador (
	idPartida integer,
	nomeJogo varchar(20),
	estado varchar(30) check (estado = 'Por Iniciar' or estado = 'A aguardar jogadores' or estado = 'Em curso' or estado = 'Terminada'),
	nomeRegiao varchar(10),
	primary key (idPartida, nomeJogo),
	foreign key (idPartida, nomeJogo) references Partida(id, nomeJogo),
	foreign key (nomeRegiao) references Regiao(nome)
);

create table if not exists Normal (
	idPartida integer,
	nomeJogo varchar(20),
	dificuldade integer check ( dificuldade > 0 and dificuldade <= 5),
	idJogador integer,
	pontuacao integer,
	primary key (idPartida, nomeJogo),
	foreign key (idPartida, nomeJogo) references Partida(id, nomeJogo),
	foreign key (idJogador) references Jogador(id)
);

create table if not exists Jogar (
	idPartida integer,
	nomeJogo varchar(20),
	idJogador integer,
	pontuacao integer,
	primary key (idPartida, nomeJogo, idJogador),
	foreign key (idPartida, nomeJogo) references Partida(id, nomeJogo),
	foreign key (idJogador) references Jogador(id)
);

create table if not exists Comprar (
	idJogador integer,
	nomeJogo varchar(20),
	preco integer,
	dataCompra timestamp,
	primary key (idJogador, nomeJogo),
	foreign key (idJogador) references Jogador(id),
	foreign key (nomeJogo) references Jogo(nome)
);

create table if not exists Tem (
	idJogador integer,
	nomeCracha varchar(20),
	nomeJogo varchar(20),
	primary key (idJogador, nomeCracha, nomeJogo),
	foreign key (idJogador) references Jogador(id),
	foreign key (nomeCracha, nomeJogo) references Cracha(nome, nomeJogo)
);

create table if not exists Adicionar (
	idJogador integer,
	idJogadorAmigo integer,
	primary key (idJogador, idJogadorAmigo),
	foreign key (idJogador) references Jogador(id),
	foreign key (idJogadorAmigo) references Jogador(id)
);

create table if not exists EstatisticaJogador (
	idJogador integer primary key,
	nrPartidas integer,
	nrJogos integer,
	totalPontosJogos integer,
	foreign key (idJogador) references Jogador(id)
);

create table if not exists EstatisticaJogo (
	nomeJogo varchar(20) primary key,
	nrPartidas integer,
	nrJogadores integer,
	totalPontos integer,
	foreign key (nomejogo) references Jogo(nome)
);
