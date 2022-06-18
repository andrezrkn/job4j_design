create table car(
	id serial primary key,
	VIN bigint,
	model text,
	color text
);

create table engine(
	id serial primary key,
	hpower int,
	volume int,
	car_id int references car(id)
);

insert into car(VIN, model, color) values (18654306754306544, 'Volkswagen Golf', 'Blue');
insert into car(VIN, model, color) values (18654306754265544, 'Fiat Abarth', 'Black');
insert into car(VIN, model, color) values (18654316454306544, 'Ford Mondeo', 'Yellow');
insert into car(VIN, model, color) values (18612616754306544, 'Hyundai Creta', 'White');
insert into car(VIN, model, color) values (18655487777706544, 'Volkswagen Jetta', 'Green');
insert into car(VIN, model, color) values (18654306777706544, 'Volkswagen Passat', 'Red');

insert into engine(hpower, volume, car_id) values (250, 3, 1);
insert into engine(hpower, volume, car_id) values (150, 2, 1);
insert into engine(hpower, volume, car_id) values (100, 1, 1);

insert into engine(hpower, volume, car_id) values (250, 2, 2);
insert into engine(hpower, volume, car_id) values (290, 2, 2);
insert into engine(hpower, volume, car_id) values (300, 3, 2);

insert into engine(hpower, volume, car_id) values (250, 3, 3);
insert into engine(hpower, volume, car_id) values (250, 3, 3);

insert into engine(hpower, volume, car_id) values (150, 3, 4);
insert into engine(hpower, volume, car_id) values (150, 3, 4);

insert into engine(hpower, volume, car_id) values (400, 4, 5);
insert into engine(hpower, volume, car_id) values (450, 4, 5);

select cr.VIN, e.volume
from engine as e
join car as cr
on cr.id = e.car_id;

select cr.VIN, cr.model
from engine as e
join car as cr
on cr.id = e.car_id;

select cr.VIN, cr.color, e.hpower
from engine as e
join car as cr
on cr.id = e.car_id;