create table person(
    id serial primary key,
    name1 varchar(255)
);

create table hairstyle(
    id serial primary key,
    name_hairstyle varchar(255)
);

create table person_hairstyle(
    id serial primary key,
    person_id int references person(id),
    hairstyle_id int references hairstyle(id)
);