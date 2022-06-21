create table departments(
	id serial primary key,
	name text
);

create table employees(
	id serial primary key,
	name text,
	departments_id int references departments(id)
)

create table teens(
	id serial primary key,
	name text,
	gender text
);

insert into departments(name) values
('headhunter'), ('job4j'), ('megafon'),
('linkedin'), ('microsoft'),
('oracle'), ('dell'), ('nokia');

insert into employees(name, departments_id) values
('Andre', 1), ('Veronika', 1), ('Alexander', 1),
('Kirill', 2), ('Eugeny', 2), ('Sergey', 2),
('Ekaterina', 4), ('Tom', 4), ('Jerry', 4);

insert into employees(name) values
('David'), ('Jessica'), ('Ovannes');

select d.name, e.name
from departments d
left join employees e
on d.id = e.departments_id;

select d.name, e.name
from employees e
left join departments d
on d.id = e.departments_id;

select d.name, e.name
from departments d
right join employees e
on d.id = e.departments_id;

select d.name, e.name
from employees e
right join departments d
on d.id = e.departments_id;

select d.name, e.name
from employees e
full join departments d
on d.id = e.departments_id;

select d.name, e.name
from employees e
cross join departments d;

select d.name
from departments d
left join employees e
on d.id = e.departments_id
where e.departments_id is null;

insert into teens(name, gender) values
('Andre', 'M'), ('Alexander', 'M'), ('Tom', 'M'),
('Ekaterina', 'F'), ('Elizaveta', 'F'), ('Aleksandra', 'F');

select m.name, f.name
from teens m
cross join teens as f
where m.gender = 'm' and f.gender = 'f'
