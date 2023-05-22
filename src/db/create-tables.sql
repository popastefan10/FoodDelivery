CREATE TABLE customers
(
    id          SERIAL PRIMARY KEY,
    email       VARCHAR NOT NULL,
    phoneNumber VARCHAR NOT NULL,
    firstName   VARCHAR NOT NULL,
    lastName    VARCHAR NOT NULL,
    address     VARCHAR NOT NULL
);