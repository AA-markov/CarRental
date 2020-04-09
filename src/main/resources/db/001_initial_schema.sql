DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS clients;

CREATE TABLE clients
(
    id                 bigint AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(50) NOT NULL,
    birth_year         bigint NOT NULL
);

CREATE TABLE cars
(
    id                 bigint AUTO_INCREMENT PRIMARY KEY,
    brand              VARCHAR(50) NOT NULL,
    manufacture_year   bigint NOT NULL,
    owner              bigint REFERENCES clients(id)
);

ALTER TABLE clients ADD CONSTRAINT uniqueClient UNIQUE(name, birth_year);
ALTER TABLE cars ADD CONSTRAINT uniqueCar UNIQUE(brand, manufacture_year);