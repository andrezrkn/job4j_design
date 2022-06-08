create table person(
    id serial primary key,
    name1 varchar(255)
);

create table DNA(
    id serial primary key,
    code varchar(255),
    person_id int references person(id) unique
);