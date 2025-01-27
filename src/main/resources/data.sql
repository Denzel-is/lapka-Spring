-- data.sql

-- 1) Роли
INSERT INTO roles (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (role_name) VALUES ('ROLE_USER');

-- 2) Категории
INSERT INTO categories (id, name) VALUES (1, 'Пиццы');
INSERT INTO categories (id, name) VALUES (2, 'Гарниры');
INSERT INTO categories (id, name) VALUES (3, 'Напитки');

-- 3) Пиццы
INSERT INTO pizzas (id, name, description, image_url, price, category_id)
VALUES
    (1, 'Маргарита', 'Томатный соус, сыр моцарелла', 'https://example.com/margarita.jpg', 350.00, 1),
    (2, 'Пепперони', 'Пикантная пепперони, сыр', 'https://example.com/pepperoni.jpg', 400.00, 1),
    (3, 'Гавайская', 'Курица, ананас, сыр', 'https://example.com/hawaiian.jpg', 450.00, 1),
    (4, 'Картофель фри', 'Картошка, масло, соль', 'https://example.com/fries.jpg', 100.00, 2),
    (5, 'Кола', 'Газированный напиток', 'https://example.com/cola.jpg', 80.00, 3);

-- 4) Пользователь admin (без id)
INSERT INTO users (username, password, full_name)
VALUES ('admin',
        '$2a$10$e6Swfn0Io2h0TBBjkCR0XelMrld1EEtLNedxTefEBFoK2Fv5oNn22',
        'Администратор');

-- 5) Привязка роли к пользователю (через подзапрос)
INSERT INTO user_roles (user_id, role_id)
VALUES (
           (SELECT id FROM users WHERE username='admin'),
           (SELECT role_id FROM roles WHERE role_name='ROLE_ADMIN')
       );
