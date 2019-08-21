alter table if exists position
   drop constraint if exists FKasougox8d191fnailqfi4jab4;

drop table if exists ordering cascade;

drop table if exists position cascade;

drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start 1000 increment 1;

create table ordering (
   id int8 not null,
    billed boolean not null,
    customer_code varchar(255) not null,
    customer_name varchar(255) not null,
    customer_vat varchar(255) not null,
    order_id varchar(255) not null,
    shipping_city varchar(255) not null,
    shipping_state varchar(255) not null,
    shipping_street varchar(255) not null,
    shipping_zip varchar(6) not null,
    version int4,
    primary key (id)
);

create table position (
   id int8 not null,
    amount int4 not null,
    code varchar(255) not null,
    name varchar(255) not null,
    price float8 not null,
    version int4,
    ordering_id int8,
    primary key (id)
);

alter table if exists position
   add constraint FKasougox8d191fnailqfi4jab4
   foreign key (ordering_id)
   references ordering
