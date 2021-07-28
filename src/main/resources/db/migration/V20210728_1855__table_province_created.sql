create table tbl_province(
     id bigint not null auto_increment,
     province_name varchar(100) not null,

     primary key (id)
) engine=innoDB default charset=utf8;