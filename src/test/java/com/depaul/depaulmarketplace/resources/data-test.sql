--Product feature test data set up
insert into products(name) values ('product A');
insert into products(name) values ('product B');

-- insert some fake data to shopping cart
insert into carts(id, total) values (000000, 145.7);
insert into carts(id, total) values (111111, 27);
insert into carts(id, total) values (222222, 657.99);