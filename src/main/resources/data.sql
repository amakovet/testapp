--hydrate product table with products
insert into products(name, category, price, inventory) values ('product 1', 'electronics', '12.99', '10');
insert into products(name, category, price, inventory) values ('product 2', 'books', '1.99', '120');
insert into products(name, category, price, inventory) values ('product 23', 'toys', '122.99', '1');



-- insert some fake data to shopping cart
insert into carts(id, total) values (000000, 145.7);
insert into carts(id, total) values (111111, 27);
insert into carts(id, total) values (222222, 657.99);