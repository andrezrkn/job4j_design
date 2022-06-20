create table devices(
    id serial primary key,
    name varchar(255),
    price int
);

create table people1(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people1(id)
);

insert into people1(name) values ('Андрей'), ('Георгий'), ('Дмитрий'), ('Александр');
insert into devices(name, price) values ('computer', 50000), ('smartphone', 10000), ('earpods', 1000),
 ('charger', 2000), ('watch', 5000);
insert into devices_people(device_id, people_id) values (1, 1), (2, 1), (4, 1);
insert into devices_people(device_id, people_id) values (2, 2), (3, 2), (5, 2);
insert into devices_people(device_id, people_id) values (2, 3), (4, 3);
insert into devices_people(device_id, people_id) values (2, 4), (3, 4), (4, 4);

select avg(price) from devices;

select people1.name as n, avg(d.price)
from devices_people as dpp
join devices d
on dpp.device_id = d.id
join people1 as p
on p.id = dpp.people_id
group by n;

select p.name as n, avg(d.price)
from devices_people as dpp
join devices d
on dpp.device_id = d.id
join people1 as p
on p.id = dpp.people_id
group by n
having avg(d.price) > 5000;
