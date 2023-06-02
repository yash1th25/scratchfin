drop database userdb;
drop user postgresUser;
create user postgresUser with password 'password';
create database userdb with template=template0 owner=postgresUser;
\connect userdb;
alter default privileges grant all on tables to postgresUser;
alter default privileges grant all on sequences to postgresUser;

create table userdata(
id int PRIMARY KEY NOT NULL,
name varchar(50) not null
);

create sequence userdata_seq increment 1 start 1;
