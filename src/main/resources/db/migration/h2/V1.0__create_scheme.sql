drop table ordering if exists;
drop table ordering_positions if exists;
drop table position if exists;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1000 increment by 1;

create table ordering (
   id bigint not null,
    billed boolean not null,
    order_id varchar(255) not null,
    customer_code varchar(255) not null,
    customer_name varchar(255) not null,
    customer_vat varchar(255) not null,
    shipping_city varchar(255) not null,
    shipping_state varchar(255) not null,
    shipping_street varchar(255) not null,
    shipping_zip varchar(6) not null,
    version integer,
    primary key (id)
);


create table position (
   id bigint not null,
    amount integer not null,
    code varchar(255) not null,
    name varchar(255) not null,
    price double not null,
    version integer,
    ordering_id bigint,
    primary key (id)
);


alter table position
   add constraint FKasougox8d191fnailqfi4jab4
   foreign key (ordering_id)
   references ordering;
