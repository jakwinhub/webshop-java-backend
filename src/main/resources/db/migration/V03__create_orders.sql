create table order_entity_order_positions
(
    order_entity_id varchar(36) not null,
    id              varchar(36) not null,
    order_id        varchar(36) not null,
    product_id      varchar(36) not null,
    quantity        bigint      not null
);

create table orders
(
    id          varchar(36)  not null,
    customer_id varchar(36)  not null,
    order_time  timestamp(6) not null,
    status      varchar(255) not null,
    primary key (id)
);

alter table order_entity_order_positions
    add constraint order_positions_order_id_fk
        foreign key (order_entity_id)
            references orders(id);

alter table order_entity_order_positions
    add constraint order_positions_products_id_fk
        foreign key (product_id)
            references products(id);

alter table orders
    add constraint orders_customer_id_fk
        foreign key (customer_id)
            references customers(id);