CREATE TABLE type1(
	id serial primary key,
	name text
);

CREATE TABLE product1(
	id serial primary key,
	name text,
	expired_date date,
	price int,
	type_id int references type(id)
);

insert into type1(name) values ('сыр'), ('молоко'), ('шоколад'), ('крупа'), ('напитки');
insert into product1(name, expired_date, price, type_id) values
('сыр плавленый','2022-05-10', 50, 1),
('сыр твёрдый', '2024-01-01', 250, 1),
('сыр с плесенью', '2030-01-30', 500, 1),
('сыр полутвёрдый', '2021-01-01', 300, 1),
('сыр моцарелла', '2022-11-29', 700, 1),
('сыр пармезан', '2024-11-29', 1000, 1),
('сыр чанах', '2022-8-29', 500, 1),
('сыр горгонзола', '2024-11-29', 1000, 1),
('сыр бри', '2023-11-29', 900, 1),
('сыр пекорино', '2022-11-29', 900, 1),
('сыр маасдам', '2022-11-29', 700, 1),
('сыр моцарелла', '2022-11-29', 700, 1),

('молоко коровье', '2022-07-03', 100, 2),
('молоко козье', '2022-07-01', 120, 2),
('кефир', '2022-11-20', 100 , 2),
('тан', '2022-11-21', 100 , 2),
('айран', '2022-11-20', 100 , 2),
('ряженка', '2021-11-26', 70 , 2),
('мацун', '2022-10-15', 60 , 2),
('мороженое пломбир', '2022-11-20', 100 , 2),
('мороженое фруктовое', '2022-12-20', 120 , 2),
('мороженое фисташковое', '2022-11-20', 150 , 2),

('шоколад молочный', '2023-11-20', 100 , 3),
('шоколад горький', '2021-11-20', 150 , 3),
('шоколад белый', '2022-11-25', 110, 3),
('шоколад воздушный', '2022-12-25', 120, 3),

('кускус', '2022-11-25', 400, 4),
('манная крупа', '2022-11-25', 50, 4),
('рис коричневый', '2022-10-25', 300, 4),
('рис черный', '2022-03-25', 200, 4),
('рис белый', '2022-11-25', 100, 4),
('гречневая крупа', '2022-06-25', 110, 4),
('перловая крупа', '2022-11-25', 110, 4),

('вода негаз', '2022-11-25', 50, 5),
('вода газ', '2022-11-25', 600, 5),
('фанта', '2022-11-25', 130, 5),
('кола', '2022-11-25', 130, 5),
('спрайт', '2022-11-25', 130, 5);


select *
from product1
where type_id =
(select t.id
from type1 as t
where t.name = 'сыр');

select *
from product1
where name like '%мороженое%';

select *
from product1
where expired_date <= current_date;

select *
from product1
where price = (select max(price) from product1);

select t.name, count(p.type_id)
from type1 as t
join product1 p
on p.type_id = t.id
group by t.name;

select *
from product1
where (type_id =
(select t.id
from type1 as t
where t.name = 'сыр'))
or (type_id =
(select t.id
from type1 as t
where t.name = 'молоко'));

select t.name, count(p.type_id)
from type1 as t
join product1 p
on p.type_id = t.id
group by t.name
having count(p.type_id) < 10;

select *
from product1 p
join type1 t
on p.type_id = t.id;






