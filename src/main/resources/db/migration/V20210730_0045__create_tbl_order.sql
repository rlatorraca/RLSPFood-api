create table tbl_order (
    id bigint not null auto_increment,
    order_beforeTax decimal(10,2) not null,
    order_deliverFee decimal(10,2) not null,
    order_afterTax decimal(10,2) not null,

    order_createdDate datetime not null,
    order_modifiedDate datetime not null,
    order_cancelDate datetime not null,
    order_deliveryDate datetime not null,

    order_status varchar(15) not null,

    order_address_city_id bigint(20) not null,
    order_address_cep varchar(9) not null,
    order_address_address varchar(150) not null,
    order_address_number varchar(10) not null,
    order_address_complement varchar(150) not null,
    order_address_district varchar(100) not null,

    order_restaurant_id bigint not null,
    order_user_client_id bigint not null,
    order_paymenttype_id bigint not null,

    primary key (id),

    constraint fk_order_address_city foreign key (order_address_city_id) references tbl_city (id),
    constraint fk_order_restaurant foreign key (order_restaurant_id) references tbl_restaurant (id),
    constraint fk_order_user_client foreign key (order_user_client_id) references tbl_client (id),
    constraint fk_order_paymenttype foreign key (order_paymenttype_id) references tbl_paymenttype (id)

) engine=InnoDB default charset=utf8;