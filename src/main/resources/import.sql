insert into tbl_cuisine (id, cuisine_name) values (1, 'Fusion');
insert into tbl_cuisine (id, cuisine_name) values (2, 'Haule');
insert into tbl_cuisine (id, cuisine_name) values (3, 'Nouvelle');
insert into tbl_cuisine (id, cuisine_name) values (4, 'Vegan');
insert into tbl_cuisine (id, cuisine_name) values (5, 'Vegetarian');
insert into tbl_cuisine (id, cuisine_name) values (6, 'Argentina');
insert into tbl_cuisine (id, cuisine_name) values (7, 'Spanish');
insert into tbl_cuisine (id, cuisine_name) values (8, 'Brazilian');

insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id) values ('Mirazur', 15.60, 1);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id) values ('Noma', 34.10, 1);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id) values ('Asador Etxebarri', 42.20, 2);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id) values ('Gaggan', 35.00, 2);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id) values ('Garanium', 45.67, 3);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id) values ('Central', 56.90, 1);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id) values ('Mugaritx', 64.00, 1);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id) values ('Arpège', 74.50, 1);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id) values ('Disfrutar', 51.00, 2);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id) values ('Maido', 40.00, 1);

insert into tbl_payment_type (payment_type) values ('Cash');
insert into tbl_payment_type  (payment_type) values ('Credit card');
insert into tbl_payment_type  (payment_type) values ('Debit card');
insert into tbl_payment_type (payment_type) values ('Pay Pal');
insert into tbl_payment_type  (payment_type) values ('Bitcoin');
insert into tbl_payment_type  (payment_type) values ('Etherium');

insert into tbl_province (province_name) values ('Alberta');
insert into tbl_province (province_name) values ('British Columbia');
insert into tbl_province (province_name) values ('Manitoba');
insert into tbl_province (province_name) values ('New Brunswick');
insert into tbl_province (province_name) values ('Newfoundland and Labrador');
insert into tbl_province (province_name) values ('Northwest Territories.');
insert into tbl_province (province_name) values ('Nova Scotia');
insert into tbl_province (province_name) values ('Nunavut');
insert into tbl_province (province_name) values ('Ontario');
insert into tbl_province (province_name) values ('Prince Edward Island');
insert into tbl_province (province_name) values ('Saskatchewan');
insert into tbl_province (province_name) values ('Yukon');

insert into tbl_restaurant_payment_type (restaurant_id, payment_type_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
insert into tbl_restaurant_payment_type (restaurant_id, payment_type_id) values (4, 1), (4, 2), (4, 3), (4, 5), (5, 2), (5, 3);
insert into tbl_restaurant_payment_type (restaurant_id, payment_type_id) values (6, 1), (6, 2), (7, 3), (7, 4), (7, 5), (8, 1);
insert into tbl_restaurant_payment_type (restaurant_id, payment_type_id) values (8, 2), (8, 6), (8, 3), (8, 4), (8, 5);