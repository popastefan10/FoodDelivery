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
    "name"             VARCHAR                         NOT NULL,
    quantity           REAL                            NOT NULL,
    "measurement-unit" VARCHAR                         NOT NULL,
    price              REAL                            NOT NULL
);

DROP TYPE IF EXISTS "order-status";
CREATE TYPE "order-status" AS ENUM (
    'PLACED',
    'IN_PREPARATION',
    'READY_FOR_PICKUP',
    'IN_DELIVERY',
    'DELIVERED',
    'CANCELED'
    );

DROP TABLE IF EXISTS orders;

CREATE TABLE orders
(
    id              SERIAL PRIMARY KEY,
    "customer-id"   INT REFERENCES customers (id)   NOT NULL,
    "restaurant-id" INT REFERENCES restaurants (id) NOT NULL,
    "driver-id"     INT REFERENCES drivers (id)     NOT NULL,
    status          "order-status"                  NOT NULL
);

DROP TABLE IF EXISTS "order-items";

CREATE TABLE "order-items"
(
    id           SERIAL PRIMARY KEY,
    "order-id"   INT REFERENCES orders (id)   NOT NULL,
    "product-id" INT REFERENCES products (id) NOT NULL,
    quantity     INT                          NOT NULL
);