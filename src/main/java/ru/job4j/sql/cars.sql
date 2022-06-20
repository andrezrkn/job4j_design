create table body(
	id serial primary key,
	name text
);

create table engine(
	id serial primary key,
	name text
);

create table transmission(
	id serial primary key,
	name text
);

create table car(
	id serial primary key,
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body(name) values
('Volkswagen Polo'), ('Kia Rio'), ('Chevrolet Lachetti'), ('Lada Vesta'), ('Skoda Rapid');

insert into engine(name) values
('2JZ'), ('1S-U'), ('OM602'), ('AAH 2.8i'), ('TSI 2.0');

insert into transmission(name) values
('DSG'), ('ZF'), ('Aisin'), ('jatco'), ('4G Tronic');

insert into car(body_id, engine_id) values
(1, 1), (1, 3), (4, 3), (5, 5), (4, 4);

insert into car(body_id, transmission_id) values
(1, 1), (1, 5), (4, 1), (3, 5), (1, 4);

insert into car(body_id, engine_id, transmission_id) values
(1, 1, 1), (3, 4, 1), (5, 4, 3), (3, 1, 3), (4, 1, 5);

select c.id, b.name as kuzov, e.name as motor, t.name as korobka
from car c
full join body b
on c.body_id = b.id
full join engine e
on c.engine_id = e.id
full join transmission t
on c.transmission_id = t.id
where
(c.body_id = b.id and c.engine_id = e.id and (c.transmission_id is null)) or
(c.body_id = b.id and c.engine_id is null and c.transmission_id = t.id) or
(c.body_id = b.id and c.engine_id = e.id and c.transmission_id = t.id);

select b.name
from car c
right join body b
on c.body_id = b.id
where c.body_id is null;

select e.name
from car c
right join engine e
on c.engine_id = e.id
where c.engine_id is null;

select t.name
from car c
right join transmission t
on c.transmission_id = t.id
where c.transmission_id is null;