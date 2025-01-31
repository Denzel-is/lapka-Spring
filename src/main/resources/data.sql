-- =========================
-- 1) Роли
-- =========================
INSERT INTO roles (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (role_name) VALUES ('ROLE_USER');

-- =========================
-- 2) Категории
-- =========================
INSERT INTO categories (id, name) VALUES (1, 'Пиццы');
INSERT INTO categories (id, name) VALUES (2, 'Гарниры');
INSERT INTO categories (id, name) VALUES (3, 'Напитки');
INSERT INTO categories (id, name) VALUES (4, 'Соусы');
INSERT INTO categories (id, name) VALUES (5, 'Комбо');
INSERT INTO categories (id, name) VALUES (6, 'Десерты');

-- =========================
-- 3) Товары (Пиццы, Гарниры, Напитки) + новые
-- =========================
-- Уже существующие 5 штук:
INSERT INTO pizzas (id, name, description, image_url, price, category_id)
VALUES
    (1, 'Маргарита', 'Томатный соус, сыр моцарелла',
     'https://i.postimg.cc/XJ0wSdjZ/1693270947-pushinka-top-p-pitstsa-margarita-kartinki-vkontakte-21.png',
     3500.00, 1),
    (2, 'Пепперони', 'Пикантная пепперони, сыр',
     'https://i.postimg.cc/k4fD1bBP/image.jpg',
     4000.00, 1),
    (3, 'Гавайская', 'Курица, ананас, сыр',
     'https://i.postimg.cc/28JLNhTz/scale-1200-1.jpg',
     4500.00, 1),
    (4, 'Картофель фри', 'Картошка, масло, соль',
     'https://i.postimg.cc/Hsj8NBwZ/portion-tomatoes-french-fries-wood-scaled.jpg',
     1000.00, 2),
    (5, 'Кола', 'Газированный напиток',
     'https://i.postimg.cc/0yTSDDJt/coca-cola-14072.jpg',
     800.00, 3);

-- Дополнительные (пиццы, напитки, гарниры)
INSERT INTO pizzas (id, name, description, image_url, price, category_id)
VALUES
    (6, 'Четыре сыра', 'Сыры: Моцарелла, Дор Блю, Пармезан, Эмменталь',
     'https://media.dodostatic.net/image/r:292x292/11ee7d5f837255b58b25a62c60ffdb38.avif',
     5000.00, 1),
    (7, 'Диабло', 'Острая пицца с колбасками чоризо и перцем чили',
     'https://media.dodostatic.net/image/r:292x292/01940234f31471ecaf81873fe8d16c7d.avif',
     5200.00, 1),
    (8, 'Вегетарианская', 'Томаты, перец, грибы, лук, оливки',
     'https://media.dodostatic.net/image/r:292x292/11ef01fee6dd7261a9c36187149758d0.avif',
     400.00, 1),
    (9,  '7UP', 'Лимон-лайм газированный напиток',
     'https://media.dodostatic.net/image/r:292x292/11ee7d5fe3c24295a8bb624018e41106.avif',
     800.00, 3),
    (10, 'Mirinda', 'Апельсиновый газированный напиток',
     'https://media.dodostatic.net/image/r:292x292/11ee7d5fe61e52fd98c08f0ccf44bff2.avif',
     800.00, 3),
    (11, 'Минеральная вода', 'Без газа, очищенная природная вода',
     'https://media.dodostatic.net/image/r:292x292/11ee796ef283421cb21fd87f1386b831.avif',
     700.00, 3),
    (12, 'Крылышки BBQ', 'Куриные крылья в соусе BBQ',
     'https://burgerking.kz/uploads/menuproducts/image/1628581766.png',
     2200.00, 2),
    (13, 'Луковые кольца', 'Хрустящие кольца в панировке',
     'https://burgerking.kz/uploads/menuproducts/image/1628581737.png',
     1500.00, 2),
    (14, 'Наггетсы', 'Куриные наггетсы в панировке',
     'https://burgerking.kz/uploads/menuproducts/image/1628581751.png',
     1800.00, 2),
    (15, 'Сырные палочки', 'Сырная закуска во фритюре',
     'data:image/jpeg;base64,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',
     160.00, 2);

-- Новые категории (Соусы=4, Комбо=5, Десерты=6)
-- Соусы - 7 позиций
INSERT INTO pizzas (id, name, description, image_url, price, category_id)
VALUES
    (16, 'Кетчуп', 'Классический томатный соус',
     'https://media.dodostatic.net/image/r:292x292/01939191feb47159b227df79939b66b8.avif',
     50.00, 4),
    (17, 'Сырный соус', 'Нежный соус на основе сыра',
     'https://media.dodostatic.net/image/r:292x292/01938e5fd676737bb2f04327649f4a9d.avif',
     60.00, 4),
    (18, 'Чесночный соус', 'Соус на основе чеснока и майонеза',
     'https://media.dodostatic.net/image/r:292x292/01939192ec2f7483af45c3d2b6df43da.avif',
     55.00, 4),
    (19, 'Барбекю соус', 'Копчёный соус BBQ',
     'https://media.dodostatic.net/image/r:292x292/01938e5f4b307891beefe5652941482d.avif',
     60.00, 4),
    (20, 'Горчичный соус', 'Пикантный соус с горчицей',
     'https://media.dodostatic.net/image/r:292x292/01938e5e5528753595bd6a4bc6268e90.avif',
     40.00, 4),
    (21, 'Сливочно-грибной соус', 'Нежный сливочный соус с грибами',
     'https://media.dodostatic.net/image/r:292x292/0193919291d47588ad8cb713305ba008.avif',
     65.00, 4),
    (22, 'Острый соус', 'Пряный соус с перцем чили',
     'https://media.dodostatic.net/image/r:292x292/019391923e427414804b46bae678f917.avif',
     60.00, 4);

-- Комбо - 7 позиций
INSERT INTO pizzas (id, name, description, image_url, price, category_id)
VALUES
    (23, 'Комбо Solo', 'Пицца Песто и Кола 0,5 л',
     'https://media.dodostatic.net/image/r:292x292/01938bd15819725eb61b8322f2ef393f.avif',
     4000.00, 5),
    (24, 'Комбо Double Brozza', 'Пицца Пепперони + Пицца Песто',
     'https://media.dodostatic.net/image/r:292x292/11ee7e215d8c4fed8a0e004fec01e713.avif',
     5300.00, 5),
    (25, 'Комбо Triple Brozza', 'Пицца Гавайская + Пицца Пепперони + Пицца Диабло ',
     'https://media.dodostatic.net/image/r:292x292/11ee7e215ea3a03c84f00c3554992cab.avif',
     5000.00, 5),
    (26, 'Комбо Друг В Беде', '2 Случайные Пиццы и Кола 1л',
     'https://media.dodostatic.net/image/r:292x292/11ef01524b2b3134bc40ad7a2d2fe5bc.avif',
     9000.00, 5),
    (27, 'Комбо Бедный Студент', 'Нагетсы,Деревенская Картошка и сырный соус',
     'https://media.dodostatic.net/image/r:292x292/11eec64006e56b30a5f1840368a0f0ca.avif',
     7500.00, 5),
    (28, 'Комбо Бразер энд Систер', '2 Маффина',
     'https://media.dodostatic.net/image/r:292x292/11ee7e215ae6b0428bea269c5d7e18aa.avif',
     2500.00, 5),
    (29, 'Бро И Брух', 'Два напитка ',
     'https://media.dodostatic.net/image/r:292x292/11ef014202e04875b62191676cecc546.avif',
     3000.00, 5);

-- Десерты - 7 позиций
INSERT INTO pizzas (id, name, description, image_url, price, category_id)
VALUES
    (30, 'Тирамису', 'Классический итальянский десерт',
     'https://i.postimg.cc/cJS5G3hN/image.png'
     ,     1800.00, 6),
    (31, 'Чизкейк', 'Нежный чизкейк с крем-сыром',
    ' https://i.postimg.cc/pTVGpt3G/image.png'
     ,     2000.00, 6),
    (32, 'Шоколадный маффин', 'Маффин с насыщенным шоколадным вкусом',
'https://i.postimg.cc/sxqw6Gxq/image.png'
     ,     900.00, 6),
    (33, 'Яблочный пирог', 'Сладкий пирог с яблоками и корицей',
'https://i.postimg.cc/c4tDKh5S/image.png'
     ,     1600.00, 6),
    (34, 'Панна котта', 'Лёгкий сливочный десерт',
'https://i.postimg.cc/s2sKNrfj/image.png'
     ,     1500.00, 6),
    (35, 'Мороженое', 'Шарики пломбира',
'https://i.postimg.cc/2j1pw4XH/image.png'
     ,     700.00, 6),
    (36, 'Шоколадный фондан', 'Фондан с горячей шоколадной начинкой',

     'https://i.postimg.cc/52MZbD9G/image.png',     190.00, 6);

-- =========================
-- 4) Пользователь admin (без id)
-- =========================
INSERT INTO users (username, password, full_name)
VALUES (
           'admin',
           -- Пароль "admin", закодированный bcrypt (пример)
           'admin',
           'Администратор'
       );

-- =========================
-- 5) Привязка роли к пользователю (через подзапрос)
-- =========================
INSERT INTO user_roles (user_id, role_id)
VALUES (
           (SELECT id FROM users WHERE username='admin'),
           (SELECT role_id FROM roles WHERE role_name='ROLE_ADMIN')
       );
