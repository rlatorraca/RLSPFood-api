create table tbl_orderitem (
    id bigint not null auto_increment,
    item_quantity smallint(6) not null,
    item_unitPrice decimal(10,2) not null,
    item_totalPrice decimal(10,2) not null,
    item_comments varchar(255) null,

    item_order_id bigint not null,
    item_product_id bigint not null,

    primary key(id),
    unique key uk_item_order_product (item_order_id, item_product_id),

    constraint fk_item_order_order foreign key (item_order_id) references tbl_order (id),
    constraint fk_item_order_product foreign key (item_product_id) references tbl_product (id)
) engine=InnoDB default charset=utf8;