/*==============================================================*/
/* DBMS name:      PostgreSQL                                   */
/* Created on:     31/07/2016                                   */
/*==============================================================*/

--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
--TABELAS DAS ESTRUTURAS DE LOG
﻿--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
--ATIVIDADES
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
CREATE TABLE public.LogAtividade (
	IdAtividade 		INT NOT NULL,
	NomeAtividade 		VARCHAR(255) NOT NULL,
	Status 			INT NOT NULL,
	constraint PK_Atividade primary key (IdAtividade)
);




--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
--PAGINA
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
CREATE TABLE public.LogPagina (
	IdPagina 		INT NOT NULL,
	NomePagina 		VARCHAR(255) NOT NULL,
	Status 			INT NOT NULL,
	constraint PK_Pagina 	PRIMARY KEY(IdPagina)
);
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
--EVENTOS DE PAGINA
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
CREATE SEQUENCE public.id_evento_seq
start 100;
CREATE TABLE public.LogEventoPagina (
	IdEvento 		BIGINT NOT NULL DEFAULT nextval('public.id_evento_seq'),
	IdAtividade 		INT NOT NULL,
	IdPagina 		INT NULL,
	IdObjeto 		INT NULL,
	NomeUsuario 		VARCHAR(255) not null,
	DataInsercao 		DATE NOT NULL,
	HoraInsercao 		TIME not null ,

	CONSTRAINT IdEvento_PK PRIMARY KEY (IdEvento),
	CONSTRAINT fk_IdPagina FOREIGN KEY (IdPagina) REFERENCES LogPagina (IdPagina),
	CONSTRAINT fk_IdAtividade FOREIGN KEY (IdAtividade) REFERENCES LogAtividade (IdAtividade)
);
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%




--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
--HISTORICO DE DADOS
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
CREATE SEQUENCE public.id_historico_seq
start 100;
CREATE TABLE public.LogPaginaHistoricoDados (
	IdHistorico 		BIGINT NOT NULL DEFAULT nextval('public.id_historico_seq'),
	IdPagina 		INT NOT NULL,
	IdObjeto 		INT NULL,
	NomeUsuario 		VARCHAR(255) NOT NULL,
	CampoAlterado 		VARCHAR(255) NOT NULL,
	ConteudoAnterior 	VARCHAR(255) NULL,
	ConteudoAtual 		VARCHAR(255) NULL,
	DataAlteracao 		DATE NOT NULL,
	HoraAlteracao           TIME not null ,

	CONSTRAINT IdHistorico_PK PRIMARY KEY (IdHistorico),
	CONSTRAINT fk_IdPagina 	FOREIGN KEY (IdPagina) REFERENCES LogPagina (IdPagina)
);
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
--ERROS
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
CREATE SEQUENCE public.id_erro_seq
start 100;
CREATE TABLE public.LogErro (
	IdErro 			BIGINT NOT NULL DEFAULT nextval('public.id_erro_seq'),
	IdPagina 		INT NOT NULL,
	IdObjeto 		INT NULL,
	NomeUsuario 		VARCHAR(255) not null,
	MsgErro 		VARCHAR(4000) not null,
	DataInclusao 		DATE NOT NULL,
	HoraInclusao            TIME not null ,

	CONSTRAINT IdErro_PK 	PRIMARY KEY (IdErro),
	CONSTRAINT fk_IdPagina 	FOREIGN KEY (IdPagina) REFERENCES LogPagina (IdPagina)
);
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


/* TABELAS DE ATIVIDADE E DE PAGINAS SAO TABELAS DE REFERENCIA*/

--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
--ATIVIDADES
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
insert into LogAtividade(IdAtividade,NomeAtividade,Status)
values (1,'Consulta',1);
insert into LogAtividade(IdAtividade,NomeAtividade,Status)
values (2,'Insercao',1);
insert into LogAtividade(IdAtividade,NomeAtividade,Status)
values (3,'Alteracao',1);
insert into LogAtividade(IdAtividade,NomeAtividade,Status)
values (4,'Remocao',1);
insert into LogAtividade(IdAtividade,NomeAtividade,Status)
values (5,'Solicitacao',1);
insert into LogAtividade(IdAtividade,NomeAtividade,Status)
values (6,'Login',1);
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
--PAGINAS
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
insert into LogPagina(IdPagina,NomePagina,Status)
values (1,'Cliente',1);
insert into LogPagina(IdPagina,NomePagina,Status)
values (2,'Conta',1);
insert into LogPagina(IdPagina,NomePagina,Status)
values (3,'Agencia',1);
insert into LogPagina(IdPagina,NomePagina,Status)
values (4,'Banco',1);
insert into LogPagina(IdPagina,NomePagina,Status)
values (5,'Transacao',1);
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
--EVENTOS DE PAGINA
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
insert into LogEventoPagina (IdAtividade,IdPagina,IdObjeto,NomeUsuario,DataInsercao,HoraInsercao)
values (5,null,null,'MARCODANTAS',current_date,current_time) ;

insert into LogEventoPagina (IdAtividade,IdPagina,IdObjeto,NomeUsuario,DataInsercao,HoraInsercao)
values (1,1,null,'MARCODANTAS',current_date,current_time) ;

insert into LogEventoPagina (IdAtividade,IdPagina,IdObjeto,NomeUsuario,DataInsercao,HoraInsercao)
values (2,1,5752,'MARCODANTAS',current_date,current_time) ;

insert into LogEventoPagina (IdAtividade,IdPagina,IdObjeto,NomeUsuario,DataInsercao,HoraInsercao)
values (3,1,5752,'MARCODANTAS',current_date,current_time) ;

insert into LogEventoPagina (IdAtividade,IdPagina,IdObjeto,NomeUsuario,DataInsercao,HoraInsercao)
values (1,5,null,'MARCODANTAS',current_date,current_time) ;


--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
--HISTORICO DE DADOS
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
INSERT INTO LogPaginaHistoricoDados (IdPagina,IdObjeto,NomeUsuario,CampoAlterado,ConteudoAnterior,ConteudoAtual,DataAlteracao,HoraAlteracao) 
values (1,5752,'MARCODANTAS','NOME COMPLETO', 'MARCO DANTAS' , 'MARCO AURELIO DE CARVALHO DANTAS' , current_date,current_time);

INSERT INTO LogPaginaHistoricoDados (IdPagina,IdObjeto,NomeUsuario,CampoAlterado,ConteudoAnterior,ConteudoAtual,DataAlteracao,HoraAlteracao) 
values (2,4321,'MICHELTEMER','LIMITE MENSAL', '100.00' , '150.00' , current_date,current_time);
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
--ERROS
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
insert into LogErro (IdPagina,IdObjeto,NomeUsuario,MsgErro,DataInclusao,HoraInclusao)
values (1,5752,'MARCODANTAS','ERRO XXXX - DATA TYPE MISMATCH BETWEEN FLOAT AND INT',current_date,current_time) ;

insert into LogErro (IdPagina,IdObjeto,NomeUsuario,MsgErro,DataInclusao,HoraInclusao)
values (1,NULL,'MARCODANTAS','ERRO YYYY - SERVIDOR INDISPONIVEL - IMPOSSIVEL CONTINUAR',current_date,current_time) ;
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
--TABELAS DA APLICACAO
--%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


create sequence agencia_sequence
start 100;

create sequence banco_sequence
start 100;

create sequence cidade_sequence
start 100;

create sequence cliente_sequence
start 100;

create sequence conta_sequence
start 100;

create sequence cxeletronico_sequence
start 100;

create sequence endereco_sequence
start 100;

create sequence estado_sequence
start 100;

create sequence transacao_sequence
start 100;

/*==============================================================*/
/* Table: agencia                                               */
/*==============================================================*/
create table agencia (
agencia_id           INT4                 not null,
banco_id             INT4                 not null,
endereco_id          INT4                 not null,
numero               INT4                 not null,
nome                 VARCHAR(255)         not null,
constraint PK_AGENCIA primary key (agencia_id),
constraint AK_AGENCIA_NUMERO_UNQ_AGENCIA unique (numero)
);

/*==============================================================*/
/* Table: banco                                                 */
/*==============================================================*/
create table banco (
banco_id             INT4                 not null,
numero               INT4                 not null,
cnpj                 CHAR(14)             not null,
nome                 VARCHAR(255)         not null,
constraint PK_BANCO primary key (banco_id),
constraint AK_BANCO_NUMERO_UNQ_BANCO unique (numero)
);

/*==============================================================*/
/* Table: caixa_eletronico                                      */
/*==============================================================*/
create table caixa_eletronico (
caixa_eletronico_id  INT4                 not null,
endereco_id          INT4                 not null,
constraint PK_CAIXA_ELETRONICO primary key (caixa_eletronico_id)
);

/*==============================================================*/
/* Table: cidade                                                */
/*==============================================================*/
create table cidade (
cidade_id            INT4                 not null,
estado_id            INT4                 not null,
nome                 VARCHAR(255)         not null,
constraint PK_CIDADE primary key (cidade_id),
constraint AK_CIDADE_NOME_UNQ_CIDADE unique (nome)
);

/*==============================================================*/
/* Table: cliente                                               */
/*==============================================================*/
create table cliente (
cliente_id           INT4                 not null,
nome                 VARCHAR(255)         not null,
data_de_inicio_de_moradia DATE                 not null default CURRENT_DATE,
status               VARCHAR(1)           not null,
cnpj                 INT4                 null,
rg                   INT4                 null,
cpf                  INT4                 null,
tipo                 VARCHAR(1)           not null,
constraint PK_CLIENTE primary key (cliente_id)
);

/*==============================================================*/
/* Table: cliente_tem_conta                                     */
/*==============================================================*/
create table cliente_tem_conta (
cliente_id           INT4                 not null,
conta_id             INT4                 not null,
titularidade         VARCHAR(1)           not null,
constraint PK_CLIENTE_TEM_CONTA primary key (cliente_id, conta_id)
);

/*==============================================================*/
/* Table: conta                                                 */
/*==============================================================*/
create table conta (
conta_id             INT4                 not null,
agencia_id           INT4                 not null,
numero               INT4                 not null,
saldo                FLOAT4               not null default '0',
data_de_abertura     DATE                 not null,
tipo                 VARCHAR(1)           not null 
      constraint CKC_TIPO_CONTA check (tipo in ('C','P')),
constraint PK_CONTA primary key (conta_id),
constraint AK_CONTA_NUMERO_UNQ_CONTA unique (numero)
);

/*==============================================================*/
/* Table: conta_corrente                                        */
/*==============================================================*/
create table conta_corrente (
conta_id             INT4                 not null,
limite               INT4                 not null default 0,
constraint PK_CONTA_CORRENTE primary key (conta_id)
);

/*==============================================================*/
/* Table: conta_poupanca                                        */
/*==============================================================*/
create table conta_poupanca (
conta_id             INT4                 not null,
data_de_aniversario  DATE                 not null default CURRENT_DATE,
correcao_monetaria   INT4                 not null,
juros                INT4                 not null,
constraint PK_CONTA_POUPANCA primary key (conta_id)
);

/*==============================================================*/
/* Table: endereco                                              */
/*==============================================================*/
create table endereco (
endereco_id          INT4                 not null,
cidade_id            INT4                 not null,
cliente_id           INT4                 null,
logradouro           VARCHAR(255)         not null,
numero               INT4                 not null,
complemento          VARCHAR(255)         null,
bairro               VARCHAR(255)         not null,
cep                  INT4                 not null,
constraint PK_ENDERECO primary key (endereco_id)
);

/*==============================================================*/
/* Table: estado                                                */
/*==============================================================*/
create table estado (
estado_id            INT4                 not null,
nome                 VARCHAR(255)         not null,
constraint PK_ESTADO primary key (estado_id),
constraint AK_ESTADO_NOME_UNQ_ESTADO unique (nome)
);

/*==============================================================*/
/* Table: transacao                                             */
/*==============================================================*/
create table transacao (
transacao_id         INT4                 not null,
caixa_eletronico_id  INT4                 null,
cliente_id           INT4                 not null,
conta_id             INT4                 not null,
data_de_realizacao   DATE                 not null default CURRENT_DATE,
hora_de_realizacao   TIME                 not null default CURRENT_TIME,
valor                FLOAT4               not null,
tipo                 VARCHAR(1)           not null 
      constraint CKC_TIPO_TRANSACA check (tipo in ('D','R','T')),
constraint PK_TRANSACAO primary key (transacao_id)
);

alter table agencia
   add constraint FK_AGENCIA_REFERENCE_BANCO foreign key (banco_id)
      references banco (banco_id)
      on delete restrict on update cascade;

alter table agencia
   add constraint FK_AGENCIA_REFERENCE_ENDERECO foreign key (endereco_id)
      references endereco (endereco_id)
      on delete restrict on update cascade;

alter table caixa_eletronico
   add constraint FK_CAIXA_EL_FK_CXELET_ENDERECO foreign key (endereco_id)
      references endereco (endereco_id)
      on delete restrict on update restrict;

alter table cidade
   add constraint FK_CIDADE_FK_CIDADE_ESTADO foreign key (estado_id)
      references estado (estado_id)
      on delete restrict on update cascade;

alter table cliente_tem_conta
   add constraint FK_CLIENTE__FK_CLIENT_CLIENTE foreign key (cliente_id)
      references cliente (cliente_id)
      on delete restrict on update cascade;

alter table cliente_tem_conta
   add constraint FK_CLIENTE__FK_CLIENT_CONTA foreign key (conta_id)
      references conta (conta_id)
      on delete restrict on update cascade;

alter table conta
   add constraint FK_CONTA_REFERENCE_AGENCIA foreign key (agencia_id)
      references agencia (agencia_id)
      on delete restrict on update cascade;

alter table conta_corrente
   add constraint FK_CONTA_CO_FK_CONTAC_CONTA foreign key (conta_id)
      references conta (conta_id)
      on delete cascade on update cascade;

alter table conta_poupanca
   add constraint FK_CONTAPOU_REFERENCE_CONTA foreign key (conta_id)
      references conta (conta_id)
      on delete cascade on update cascade;

alter table endereco
   add constraint FK_ENDERECO_FK_ENDERE_CIDADE foreign key (cidade_id)
      references cidade (cidade_id)
      on delete restrict on update cascade;

alter table endereco
   add constraint FK_ENDERECO_REFERENCE_CLIENTE foreign key (cliente_id)
      references cliente (cliente_id)
      on delete cascade on update cascade;

alter table transacao
   add constraint FK_TRANSACA_REFERENCE_CLIENTET foreign key (cliente_id, conta_id)
      references cliente_tem_conta (cliente_id, conta_id)
      on delete restrict on update cascade;

alter table transacao
   add constraint FK_TRANSACA_FK_TRANSA_CAIXA_EL foreign key (caixa_eletronico_id)
      references caixa_eletronico (caixa_eletronico_id)
      on delete restrict on update cascade;



insert into estado (estado_id, nome) values (1, 'Sao Paulo');
insert into estado (estado_id, nome) values (2, 'Rio de Janeiro');

insert into cidade (cidade_id, estado_id, nome) values (1, 1, 'Sao Carlos');
insert into cidade (cidade_id, estado_id, nome) values (2, 1, 'Aracatuba');
insert into cidade (cidade_id, estado_id, nome) values (3, 1, 'Votuporanga');
insert into cidade (cidade_id, estado_id, nome) values (4, 2, 'Rio de Janeiro');





insert into banco (banco_id, numero, cnpj, nome) values (1000, 100, 0, 'Nome do Banco 100');

insert into endereco (endereco_id, cidade_id, cliente_id, logradouro, numero, complemento, bairro, cep) values (1000, 1, null, 'Logradouro do Endereco 1', 1, 'Complemento do Endereco 1', 'Bairro do Endereco 1', 11111111);

insert into agencia (agencia_id, banco_id, endereco_id, numero, nome) values (1000, 1000, 1000, 100, 'Nome da Agencia 100');

insert into cliente (cliente_id, nome, data_de_inicio_de_moradia, status, cnpj, rg, cpf, tipo) values (1000, 'Nome do Cliente 1', '2015-01-01', 'A', 0, 0, 0, 'F');
insert into cliente (cliente_id, nome, data_de_inicio_de_moradia, status, cnpj, rg, cpf, tipo) values (2000, 'Nome do Cliente 2', '2015-01-02', 'A', 0, 0, 0, 'F');
insert into cliente (cliente_id, nome, data_de_inicio_de_moradia, status, cnpj, rg, cpf, tipo) values (3000, 'Nome do Cliente 3', '2015-01-03', 'A', 0, 0, 0, 'F');
insert into cliente (cliente_id, nome, data_de_inicio_de_moradia, status, cnpj, rg, cpf, tipo) values (4000, 'Nome do Cliente 4', '2015-01-04', 'A', 0, 0, 0, 'J');
insert into cliente (cliente_id, nome, data_de_inicio_de_moradia, status, cnpj, rg, cpf, tipo) values (5000, 'Nome do Cliente 5', '2015-01-05', 'A', 0, 0, 0, 'J');

insert into conta (conta_id, agencia_id, numero, saldo, data_de_abertura, tipo) values (1000, 1000, 11111, 1000.00, '2015-02-01', 'C');
insert into conta (conta_id, agencia_id, numero, saldo, data_de_abertura, tipo) values (2000, 1000, 22222, 2000.00, '2015-02-02', 'C');
insert into conta (conta_id, agencia_id, numero, saldo, data_de_abertura, tipo) values (3000, 1000, 33333, 3000.00, '2015-02-03', 'C');
insert into conta (conta_id, agencia_id, numero, saldo, data_de_abertura, tipo) values (4000, 1000, 44444, 4000.00, '2015-02-04', 'P');
insert into conta (conta_id, agencia_id, numero, saldo, data_de_abertura, tipo) values (5000, 1000, 55555, 5000.00, '2015-02-05', 'P');

insert into conta_corrente (conta_id, limite) values (1000, 1000);
insert into conta_corrente (conta_id, limite) values (2000, 2000);
insert into conta_corrente (conta_id, limite) values (3000, 3000);
insert into conta_poupanca (conta_id, data_de_aniversario, correcao_monetaria, juros) values (4000, '2015-03-04', 40, 40);
insert into conta_poupanca (conta_id, data_de_aniversario, correcao_monetaria, juros) values (5000, '2015-03-05', 50, 50);

insert into cliente_tem_conta (cliente_id, conta_id, titularidade) values (1000, 1000, 'S');
insert into cliente_tem_conta (cliente_id, conta_id, titularidade) values (2000, 2000, 'S');
insert into cliente_tem_conta (cliente_id, conta_id, titularidade) values (3000, 3000, 'S');
insert into cliente_tem_conta (cliente_id, conta_id, titularidade) values (4000, 4000, 'S');
insert into cliente_tem_conta (cliente_id, conta_id, titularidade) values (5000, 5000, 'S');



-- Table: usuario

-- DROP TABLE usuario;

CREATE TABLE usuario
(
  nomedeusuario character varying(30),
  senha character varying(32) NOT NULL,
  CONSTRAINT pk_usuario PRIMARY KEY (nomedeusuario)
);

-- Table: grupo

-- DROP TABLE grupo;

CREATE TABLE grupo
(
  nomedeusuario character varying(30),
  nomedogrupo character varying(30) NOT NULL,
  CONSTRAINT pk_grupo PRIMARY KEY (nomedeusuario, nomedogrupo),
  CONSTRAINT grupo_usuario_ref FOREIGN KEY (nomedeusuario)
      REFERENCES usuario (nomedeusuario) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);


insert into usuario values ('admin', '21232f297a57a5a743894a0e4a801fc3');
insert into grupo values ('admin', 'administradores');
insert into grupo values ('admin', 'clientes');

insert into usuario values ('cliente', '4983a0ab83ed86e0e7213c8783940193');
insert into grupo values ('cliente', 'clientes');

insert into usuario values ('marco', '21232f297a57a5a743894a0e4a801fc3');
insert into grupo values ('marco', 'clientes');



