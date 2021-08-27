set foreign_key_checks = 0;

delete from tbl_city;
delete from tbl_cuisine;
delete from tbl_group;
delete from tbl_paymenttype;
delete from tbl_permission;
delete from tbl_product;
delete from tbl_province;
delete from tbl_restaurant;
delete from tbl_client;
delete from tbl_client_group;
delete from tbl_group_permission;
delete from tbl_restaurant_paymenttype;

set foreign_key_checks = 1;

alter table tbl_city auto_increment = 1;
alter table tbl_cuisine auto_increment = 1;
alter table tbl_group auto_increment = 1;
alter table tbl_paymenttype auto_increment = 1;
alter table tbl_permission auto_increment = 1;
alter table tbl_product auto_increment = 1;
alter table tbl_province auto_increment = 1;
alter table tbl_restaurant auto_increment = 1;
alter table tbl_client auto_increment = 1;


insert into tbl_cuisine (id, cuisine_name) values (1, 'Fusion');
insert into tbl_cuisine (id, cuisine_name) values (2, 'Haule');
insert into tbl_cuisine (id, cuisine_name) values (3, 'Nouvelle');
insert into tbl_cuisine (id, cuisine_name) values (4, 'Vegan');
insert into tbl_cuisine (id, cuisine_name) values (5, 'Vegetarian');
insert into tbl_cuisine (id, cuisine_name) values (6, 'Argentina');
insert into tbl_cuisine (id, cuisine_name) values (7, 'Spanish');
insert into tbl_cuisine (id, cuisine_name) values (8, 'Brazilian');
insert into tbl_cuisine (id, cuisine_name) values (9, 'Portuguese');

insert into tbl_paymenttype (payment_type) values ('Cash');
insert into tbl_paymenttype  (payment_type) values ('Credit card');
insert into tbl_paymenttype  (payment_type) values ('Debit card');
insert into tbl_paymenttype (payment_type) values ('Pay Pal');
insert into tbl_paymenttype  (payment_type) values ('Bitcoin');
insert into tbl_paymenttype  (payment_type) values ('Etherium');

insert into tbl_province (province_name) values ('Alberta');
insert into tbl_province (province_name) values ('British Columbia');
insert into tbl_province (province_name) values ('Manitoba');
insert into tbl_province (province_name) values ('New Brunswick');
insert into tbl_province (province_name) values ('Newfoundland and Labrador');
insert into tbl_province (province_name) values ('Northwest Territories.');
insert into tbl_province (province_name) values ('Nova Scotia');
insert into tbl_province (province_name) values ('Nunavut');
insert into tbl_province (province_name) values ('Ontario');
insert into tbl_province (province_name) values ('Quebec');
insert into tbl_province (province_name) values ('Prince Edward Island');
insert into tbl_province (province_name) values ('Saskatchewan');
insert into tbl_province (province_name) values ('Yukon');

insert into tbl_city(id, city_name, province_id) values (1, 'Ottawa', 9);
insert into tbl_city(id, city_name, province_id) values (2, 'Toronto', 9);
insert into tbl_city(id, city_name, province_id) values (3, 'Montreal', 10);
insert into tbl_city(id, city_name, province_id) values (4, 'Ville du Quebec', 10);
insert into tbl_city(id, city_name, province_id) values (5, 'Vancouver', 2);
insert into tbl_city(id, city_name, province_id) values (6, 'Calgary', 1);

insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id, address_postalcode, address_street, address_number, address_complement, address_district, address_city_id, created_date, date_last_update, active) values ('Mirazur', 15.60, 1, 'A1BC2D', 'Street A', '1', 'suite 111', 'downtown', 1, utc_timestamp, utc_timestamp, true);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id, address_postalcode, address_street, address_number, address_complement, address_district, address_city_id, created_date, date_last_update, active  ) values ('Noma', 34.10, 1, 'B1BC2D', 'Street B', '2', 'suite 222', 'Bay B', 3, utc_timestamp, utc_timestamp, true);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id, address_postalcode, address_street, address_number, address_complement, address_district, address_city_id, created_date, date_last_update, active  ) values ('Asador Etxebarri', 42.20, 2, 'C1BC2D', 'Street C', '3', 'suite 333', 'Bay C', 3, utc_timestamp, utc_timestamp, true);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id, address_postalcode, address_street, address_number, address_complement, address_district, address_city_id, created_date, date_last_update, active  ) values ('Gaggan', 35.00, 2, 'D1BC2D', 'Street D', '4', 'suite 444', 'downtown', 4, utc_timestamp, utc_timestamp, true);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id, address_postalcode, address_street, address_number, address_complement, address_district, address_city_id, created_date, date_last_update, active  ) values ('Garanium', 45.67, 3, 'E1BC2D', 'Street E', '5', 'suite 555', 'downtown', 1, utc_timestamp, utc_timestamp, true);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id, address_postalcode, address_street, address_number, address_complement, address_district, address_city_id, created_date, date_last_update, active ) values ('Central', 56.90, 1, 'F1BC2D', 'Street F', '6', 'suite 777', 'Water A', 2, utc_timestamp, utc_timestamp, true);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id, address_postalcode, address_street, address_number, address_complement, address_district, address_city_id, created_date, date_last_update, active  ) values ('Mugaritx', 64.00, 1, 'G1BC2D', 'Street G', '7', 'suite 888', 'downtown', 3, utc_timestamp, utc_timestamp, true);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id, address_postalcode, address_street, address_number, address_complement, address_district, address_city_id, created_date, date_last_update, active  ) values ('Arpège', 74.50, 1, 'H1BC2D', 'Street H', '8', 'suite 999', 'Qatar Beach', 5, utc_timestamp, utc_timestamp, true);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id, address_postalcode, address_street, address_number, address_complement, address_district, address_city_id, created_date, date_last_update, active  ) values ('Disfrutar', 51.00, 2, 'I1BC2D', 'Street I', '9', 'suite 1010', 'downtown', 1, utc_timestamp, utc_timestamp, true);
insert into tbl_restaurant (name_restaurant, delivery_fee, cuisine_id, address_postalcode, address_street, address_number, address_complement, address_district, address_city_id, created_date, date_last_update, active  ) values ('Maido', 40.00, 1, 'J1BC2D', 'Street J', '10', 'suite 1222', 'downtown', 2, utc_timestamp, utc_timestamp, true);

insert into tbl_restaurant_paymenttype (restaurant_id, payment_type_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
insert into tbl_restaurant_paymenttype (restaurant_id, payment_type_id) values (4, 1), (4, 2), (4, 3), (4, 5), (5, 2), (5, 3);
insert into tbl_restaurant_paymenttype (restaurant_id, payment_type_id) values (6, 1), (6, 2), (7, 3), (7, 4), (7, 5), (8, 1);
insert into tbl_restaurant_paymenttype (restaurant_id, payment_type_id) values (8, 2), (8, 3), (8, 4), (8, 5), (8, 6);
insert into tbl_restaurant_paymenttype (restaurant_id, payment_type_id) values (9, 1), (9, 2), (9, 3), (9, 4), (9, 5);
insert into tbl_restaurant_paymenttype (restaurant_id, payment_type_id) values (10, 1), (10, 2), (10, 3), (10, 4), (10, 5), (10, 6);

insert into tbl_product (product_name, product_description, product_price, product_active, restaurant_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into tbl_product (product_name, product_description, product_price, product_active, restaurant_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into tbl_product (product_name, product_description, product_price, product_active, restaurant_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into tbl_product (product_name, product_description, product_price, product_active, restaurant_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into tbl_product (product_name, product_description, product_price, product_active, restaurant_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into tbl_product (product_name, product_description, product_price, product_active, restaurant_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into tbl_product (product_name, product_description, product_price, product_active, restaurant_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into tbl_product (product_name, product_description, product_price, product_active, restaurant_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into tbl_product (product_name, product_description, product_price, product_active, restaurant_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

insert into tbl_group (group_name) values ('Manager');
insert into tbl_group (group_name) values ('Sales Associate');
insert into tbl_group (group_name) values ('Administrative Assistant');
insert into tbl_group (group_name) values ('Register');
