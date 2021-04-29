create table users (
    id serial primary key,
	first_name varchar(60) not null,
	last_name varchar(60) not null,
	age integer not null
);

create table companies (
    id serial primary key,
    name varchar(255) not null
);

insert into users (first_name, last_name, age) values
    ('Pavlo', 'Trotsiuk', 20),
    ('Mykyta', 'Kemarskiy', 19),
    ('Oleksandr', 'Filatov', 20);

insert into companies (name) values
    ('Rozetka'),
    ('Amazon'),
    ('Allo');
