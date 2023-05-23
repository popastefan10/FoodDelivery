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