create table apartment(
    id serial primary key,
    adress varchar(255)
);

create table person(
    id serial primary key,
    name1 varchar(255),
    apartment_id int references apartment(id)
)