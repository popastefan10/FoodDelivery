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