create database dbds20242;
use dbds20242;

create table tperson(
idPerson char(36) not null,
firstName varchar(70) not null,
surName varchar(40) not null,
dni char(8) not null,
password varchar(1000) not null,
gender bool not null,
birthDate date not null,
createdAt datetime not null,
updatedAt datetime not null,
primary key(idPerson)
) engine=innodb;