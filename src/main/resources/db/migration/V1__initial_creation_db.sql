create sequence address_seq start with 1 increment by 1;

create table address
(
    id                  integer primary key,
    city                varchar,
    postalcode          varchar,
    street_name         varchar,
    house_number        varchar,
    additional_info     varchar
);

create sequence customer_seq start with 1 increment by 1;

create table customer
(
    id              integer primary key,
    password        varchar,
    first_name      varchar,
    last_name       varchar,
    email_address   varchar,
    phone_number    varchar,
    address_id      integer references address,
    role            varchar
);

create sequence order_seq start with 1 increment by 1;

create table Customer_order
(
    id              integer primary key,
    total_price     numeric,
    customer_id     integer references customer
);

create sequence item_group_seq start with 1 increment by 1;

create table item_group
(
    id                  integer primary key,
    item_name           varchar,
    amount_ordered      varchar,
    shipping_date       date,
    item_group_price    numeric,
    order_id            integer references Customer_order
);

create sequence item_seq start with 1 increment by 1;

create table item
(
    id              integer primary key,
    name            varchar,
    description     varchar,
    price           numeric,
    amount_in_stock integer
);