create table products
(
    id       serial
        constraint products_pk
            primary key,
    name     varchar not null,
    price    numeric,
    currency varchar,
    image    text,
    description text
);