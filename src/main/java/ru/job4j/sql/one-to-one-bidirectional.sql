create table person(
    id serial primary key,
    name1 varchar(255)
);

create table DNA(
    id serial primary key,
    code varchar(255)
);

create table person_DNA(
    id serial primary key,
    person_id int references person(id) unique,
    DNA_id int references DNA(id) unique
);