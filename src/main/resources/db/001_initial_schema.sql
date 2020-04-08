DROP TABLE cars;
DROP TABLE clients;

CREATE TABLE cars
(
    id                 bigint PRIMARY KEY,
    brand              text NOT NULL,
    manufactureYear    bigint NOT NULL,
    name               text NOT NULL
);

CREATE TABLE clients
(
    id                 bigint PRIMARY KEY,
    name               text NOT NULL,
    birthYear          bigint NOT NULL
);

ALTER TABLE cars ADD CONSTRAINT fk_client FOREIGN KEY (name) REFERENCES clients(name);
