DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS clients;

CREATE TABLE clients
(
    id                 bigint PRIMARY KEY,
    name               VARCHAR(50) NOT NULL,
    birth_year          bigint NOT NULL
);

CREATE TABLE cars
(
    id                 bigint PRIMARY KEY,
    brand              VARCHAR(50) NOT NULL,
    manufacture_year   bigint NOT NULL,
    owner              bigint REFERENCES clients(id)
);