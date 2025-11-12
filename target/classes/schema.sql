DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS module;

CREATE TABLE client (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE module (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE ticket (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    opening_date DATE,
    closing_date DATE,
    client_id BIGINT,
    module_id BIGINT,
    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (module_id) REFERENCES module(id)
);
