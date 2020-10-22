create database users_rights_and_roles_schema;
create table role(
    id serial primary key,
    name varchar(255)
);
create table rules(
    id serial primary key,
    rule varchar(255)
);
create table role_rules_connection(
    id serial primary key,
    role_id int references role(id),
    rules_id int references rules(id)

);
create table user(
    id serial primary key,
    name varchar(255),
    role_id int references role(id)

);
create table item(
    id serial primary key,
    topic varchar(255),
    text varchar(255),
    user_id int references user(id),
    comment_id int references comment(id),
    attachs_id int references attachs(id)
);
create table comment(
  id serial primary key,
  text varchar(255)
);
create table category(
  id serial primary key,
  category_desc varchar(255),
  item_id int references item(id)
);
create table state(
    id serial primary key,
    state_desc varchar(255),
    item_id int references item(id)
);
create table attachs(
  id serial primary key,
  data varchar(255)
);