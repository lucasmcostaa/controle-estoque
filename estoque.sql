/**
*Projeto de umm sistema para gestão de estoque
@author Lucas Marcelino
@version 1.0
*/

create database dbestoque;
use dbestoque;

create table usuarios(
id int primary key auto_increment,
usuario varchar(50) not null, 
login varchar(20) not null,
senha varchar(250) not null
);


describe usuarios;

-- CREATE (inserir 5 usuários

insert into usuarios  (usuario,login,senha)
values ('Lucas Marcelino','lucas','123@senac');

insert into usuarios (usuario,login,senha)
values ('Guilherme Morgado','guilherme','123@senac');

insert into usuarios (usuario,login,senha)
values ('Otavio Alves','otavio','123@senac');

insert into usuarios (usuario,login,senha)
values ('Matheus Apolinario','matheus','123@senac');

insert into usuarios (usuario,login,senha)
values ('Pedro Henrique','pedro','123@senac');

-- a linha abaixo seleciona todos os registros da tabela
select * from usuarios;

select * from usuarios where id = 1;


select * from usuario;

select * from usuarios where id = 1;

update usuarios set usuario = 'Lucas Marcelino' where id = 1;

delete from usuario where id = 30;




/************** CRUD **************/

-- CREATE (inserir 5 usuários) OK
-- READ1 (selecionar todos os usuários)
-- READ2 (selecionar um usuários específico por id)
-- UPDATE (alterar todos os dados de um usuário específico
-- DELETE (excluir um usuário específico)
-- Gerar a documentação - Modelo ER (engenharia reversa)
