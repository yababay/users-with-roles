drop table if exists user cascade;
drop table if exists role cascade;
drop table if exists users_and_roles cascade;

create table role (id int not null auto_increment, name varchar(128) not null unique, description varchar(128), primary key(id));

create table user (
    id int not null auto_increment,
    name varchar(128) not null unique,
    password varchar(255) not null,
    email varchar(128) not null,
    first_name varchar(128),
    last_name varchar(128),
    primary key(id)
);

create table users_and_roles (user_id int not null, role_id int not null);
