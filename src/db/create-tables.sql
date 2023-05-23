DROP TABLE IF EXISTS customers;

CREATE TABLE customers
(
    id             SERIAL PRIMARY KEY,
    email          VARCHAR NOT NULL,
    "phone-number" VARCHAR NOT NULL,
    "first-name"   VARCHAR NOT NULL,
    "last-name"    VARCHAR NOT NULL,
    "address"      VARCHAR NOT NULL
);

DROP TABLE IF EXISTS drivers;

CREATE TABLE drivers
(
    id             SERIAL PRIMARY KEY,
    email          VARCHAR NOT NULL,
    "phone-number" VARCHAR NOT NULL,
    "first-name"   VARCHAR NOT NULL,
    "last-name"    VARCHAR NOT NULL,
    rating         REAL    NOT NULL
);

DROP TABLE IF EXISTS restaurants;

CREATE TABLE restaurants
(
    id             SERIAL PRIMARY KEY,
    email          VARCHAR NOT NULL,
    "phone-number" VARCHAR NOT NULL,
    name           VARCHAR NOT NULL,
    address        VARCHAR NOT NULL,
    rating         REAL    NOT NULL
);

DROP TABLE IF EXISTS products;

CREATE TABLE products
(
    id                 SERIAL PRIMARY KEY,
    "restaurant-id"    INT REFERENCES restaurants (id) NOT NULL,
    "name"             VARCHAR NOT NULL,
    quantity           REAL    NOT NULL,
    "measurement-unit" VARCHAR NOT NULL,
    price              REAL    NOT NULL
);