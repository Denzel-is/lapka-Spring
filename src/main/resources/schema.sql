-- schema.sql

-- Удалим таблицы, если уже существуют
DROP TABLE IF EXISTS payment_info CASCADE;
DROP TABLE IF EXISTS order_items CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS pizzas CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS user_roles CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Создаём таблицу users
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       full_name VARCHAR(255)
);

CREATE TABLE roles (
                       role_id SERIAL PRIMARY KEY,
                       role_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
                            user_role_id SERIAL PRIMARY KEY,
                            user_id INT NOT NULL,
                            role_id INT NOT NULL,
                            CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id),
                            CONSTRAINT fk_role FOREIGN KEY(role_id) REFERENCES roles(role_id)
);

CREATE TABLE categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE pizzas (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        description TEXT,
                        image_url TEXT,
                        price NUMERIC(10,2),
                        category_id INT,
                        CONSTRAINT fk_category FOREIGN KEY(category_id) REFERENCES categories(id)
);

CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        created_at TIMESTAMP,
                        user_id INT,
                        CONSTRAINT fk_user_order FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE order_items (
                             id SERIAL PRIMARY KEY,
                             pizza_id INT,
                             quantity INT,
                             order_id INT,
                             CONSTRAINT fk_pizza FOREIGN KEY(pizza_id) REFERENCES pizzas(id),
                             CONSTRAINT fk_order FOREIGN KEY(order_id) REFERENCES orders(id)
);

CREATE TABLE payment_info (
                              id SERIAL PRIMARY KEY,
                              card_number VARCHAR(255),
                              card_holder_name VARCHAR(255),
                              address VARCHAR(255),
                              city VARCHAR(255),
                              postal_code VARCHAR(20),
                              order_id INT UNIQUE,
                              CONSTRAINT fk_order_payment FOREIGN KEY(order_id) REFERENCES orders(id)
);
