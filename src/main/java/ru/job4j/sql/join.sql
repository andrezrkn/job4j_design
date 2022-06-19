create table engine(
	id serial primary key,
	hpower int,
	volume int
);

create table car(
	id serial primary key,
	VIN bigint,
	model text,
	color text,
	engine_id int references engine(id)
);

insert into engine(hpower, volume) values (100, 1);
insert into engine(hpower, volume) values (150, 2);
insert into engine(hpower, volume) values (250, 2);
insert into engine(hpower, volume) values (290, 2);
insert into engine(hpower, volume) values (150, 3);
insert into engine(hpower, volume) values (250, 3);
insert into engine(hpower, volume) values (300, 3);
insert into engine(hpower, volume) values (400, 4);
insert into engine(hpower, volume) values (450, 4);

insert into car(VIN, model, color, engine_id) values (18654306754306544, 'Volkswagen Golf', 'Blue', 2);
insert into car(VIN, model, color, engine_id) values (18654306754306544, 'Volkswagen Golf', 'Purple', 7);
insert into car(VIN, model, color, engine_id) values (18654306754265544, 'Fiat Abarth', 'Black', 3);
insert into car(VIN, model, color, engine_id) values (18654306754265544, 'Fiat Abarth', 'Red', 1);
insert into car(VIN, model, color, engine_id) values (18654316454306544, 'Ford Mondeo', 'Yellow', 8);
insert into car(VIN, model, color, engine_id) values (18612616754306544, 'Hyundai Creta', 'White', 7);
insert into car(VIN, model, color, engine_id) values (18655487777706544, 'Volkswagen Jetta', 'Green', 6);
insert into car(VIN, model, color, engine_id) values (18654306777706544, 'Volkswagen Passat', 'Red', 9);

select cr.VIN, e.volume
from engine as e
join car as cr
on cr.engine_id = e.id;

select cr.VIN, cr.model
from engine as e
join car as cr
on cr.engine_id = e.id;

select cr.VIN, cr.color, e.hpower
from engine as e
join car as cr
on cr.engine_id = e.id;