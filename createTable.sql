
create table Regiao (
	nome varchar(10) primary key
);

create table Jogador (
	id serial primary key,
	estado varchar(10) check (estado = 'Ativo' or estado = 'Inativo' or estado = 'Banido'),
	userName varchar(20) unique,
	email varchar(30) unique,
	nomeRegiao varchar(10),
	foreign key (nomeRegiao) references Regiao(nome)
);

create table Jogo (
	nome varchar(20) primary key,
	id varchar(10),
	URL varchar(50)
);

create table Conversa (
	id serial unique, --Ele dá erro se eu não colocar unique.
	idJogador integer,
	nome varchar(20),
	primary key (id, idJogador),	
	foreign key (idJogador) references Jogador(id) 
);

create table Mensagem (
	id serial,
	idConversa integer,
	idJogador integer,
	texto varchar(200),
	dataDeEnvio timestamp default now(),
	primary key (id, idConversa, idJogador),
	foreign key (idConversa) references Conversa(id),
	foreign key (idJogador) references Jogador(id) 
);

create table Cracha (
	nome varchar(20) primary key, --Ele dá erro se eu não colocar unique.
	nomeJogo varchar(20), --Ele dá erro se eu não colocar unique.
	limiteDePontos integer,
	URL varchar(50),
	unique (nome, nomeJogo), --primary key (nome, nomeJogo)
	foreign key (nomeJogo) references Jogo(nome)
);

create table Partida (
	id integer primary key, --Ele dá erro se eu não colocar unique.
	nomeJogo varchar(20),
	dataInicio timestamp default now(),
	dataFim timestamp check (dataFim > dataInicio),
	unique (id, nomeJogo), --primary key (id, nomeJogo)
	foreign key (nomeJogo) references Jogo(nome)
);

create table MultiJogador (
	idPartida integer,
	nomeJogo varchar(20),
	estado varchar(30) check (estado = 'Por Iniciar' or estado = 'A aguardar jogadores' or estado = 'Em curso' or estado = 'Terminada'),
	nomeRegiao varchar(10),
	primary key (idPartida, nomeJogo),
	foreign key (idPartida) references Partida(id),
	foreign key (nomeRegiao) references Regiao(nome)
);

create table Normal (
	idPartida integer,
	nomeJogo varchar(20),
	dificuldade integer check ( dificuldade > 0 and dificuldade <= 5),
	idJogador integer,
	pontuacao integer,
	primary key (idPartida, nomeJogo),
	foreign key (idPartida) references Partida(id),
	foreign key (idJogador) references Jogador(id) 
);

create table Jogar (
	idPartida integer,
	idJogador integer,
	pontuacao integer,
	primary key (idPartida, idJogador),
	foreign key (idPartida) references Partida(id),
	foreign key (idJogador) references Jogador(id) 
);

create table Comprar (
	idJogador integer,
	nomeJogo varchar(20),
	preco integer,
	dataCompra timestamp,
	primary key (idJogador, nomeJogo),
	foreign key (idJogador) references Jogador(id),
	foreign key (nomeJogo) references Jogo(nome)
);

create table Tem (
	idJogador integer,
	nomeCracha varchar(20),
	nomeJogo varchar(20),
	primary key (idJogador, nomeCracha, nomeJogo),
	foreign key (idJogador) references Jogador(id),
	foreign key (nomeCracha) references Cracha(nome),
	foreign key (nomeJogo) references Jogo(nome) --foreign key (nomeJogo) references Cracha(nomeJogo)
);

create table Adicionar (
	idJogador integer,
	idJogadorAmigo integer,
	primary key (idJogador, idJogadorAmigo),
	foreign key (idJogador) references Jogador(id),
	foreign key (idJogadorAmigo) references Jogador(id)
);

create table EstatisticaJogador (
	idJogador integer primary key,
	nrPartidas integer,
	nrJogos integer,
	totalPontosJogos integer,
	foreign key (idJogador) references Jogador(id)
);

create table EstatisticaJogo (
	nomeJogo varchar(20) primary key,
	nrPartidas integer,
	nrJogadores integer,
	totalPontos integer,
	foreign key (nomejogo) references Jogo(nome)
);
