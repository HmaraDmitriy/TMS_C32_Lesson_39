CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(50),
                       age INTEGER,
                       email VARCHAR(50),
                       sex VARCHAR(10),
                       is_deleted BOOLEAN,
                       created TIMESTAMP DEFAULT now() NOT NULL,
                       updated TIMESTAMP
);

ALTER TABLE users OWNER TO postgres;

CREATE TABLE security (
                          id BIGSERIAL PRIMARY KEY,
                          user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
                          login VARCHAR(50) UNIQUE NOT NULL,
                          password VARCHAR(50) NOT NULL,
                          role VARCHAR(20) NOT NULL,
                          created TIMESTAMP DEFAULT now() NOT NULL,
                          updated TIMESTAMP
);

ALTER TABLE security OWNER TO postgres;

CREATE TABLE product (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(50) NOT NULL,
                         price DOUBLE PRECISION NOT NULL CHECK (price > 0),
                         created TIMESTAMP DEFAULT now() NOT NULL,
                         updated TIMESTAMP
);

ALTER TABLE product OWNER TO postgres;

CREATE TABLE users_product (
                               id BIGSERIAL PRIMARY KEY,
                               user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
                               product_id BIGINT REFERENCES product(id) ON DELETE CASCADE,
                               created TIMESTAMP DEFAULT now() NOT NULL,
                               updated TIMESTAMP
);

ALTER TABLE users_product OWNER TO postgres;


INSERT INTO users (name, age, email, sex, is_deleted) VALUES
                                                          ('Dima', 30, 'dima.com', 'Male', false),
                                                          ('Anna', 25, 'Anna.com', 'Female', false),
                                                          ('Oleg', 40, 'Oleg.com', 'Male', false);

INSERT INTO security (user_id, login, password, role) VALUES
                                                          (1, 'dima', 'qwerty', 'ADMIN'),
                                                          (2, 'anna', 'qwerty', 'USER'),
                                                          (3, 'oleg', 'qwerty', 'USER');

INSERT INTO product (name, price) VALUES
                                      ('Laptop', 7500.00),
                                      ('Smartphone', 4500.00),
                                      ('Tablet', 3000.00);

INSERT INTO users_product (user_id, product_id) VALUES
                                                    (1, 1),
                                                    (1, 2),
                                                    (2, 3),
                                                    (3, 1);

INSERT INTO security (user_id, login, password, role) VALUES
                                                          (1, 'dima', 'qwerty', 'ADMIN'),
                                                          (2, 'anna', 'qwerty', 'USER'),
                                                          (3, 'oleg', 'qwerty', 'USER');

SELECT * FROM security;