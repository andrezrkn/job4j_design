create database users_rights_and_roles_schema;
create table role(
    id serial primary key,
    name varchar(255)
);
create table user(
  id serial primary key,
  name varchar(255)
);
create table rules(
    id serial primary key,
    rule varchar(255)
);
create table item(
    id serial primary key,
    topic varchar(255),
    text varchar(255)
);
create table comment(
  id serial primary key,
  text varchar(255)
);
create table category(
  id serial primary key,
  category1 varchar(255)
);
create table state(
    id serial primary key,
    state1 varchar(255)
);
create table attachs(
  id serial primary key,
  data varchar(255)
);