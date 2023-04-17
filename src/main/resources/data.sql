--hydrate product table with sample data
insert into products(name) values ('product 1');
insert into products(name) values ('product 2');

-- insert some fake data to shopping cart
insert into carts(id, total) values (000000, 145.7);
insert into carts(id, total) values (111111, 27);
insert into carts(id, total) values (222222, 657.99);