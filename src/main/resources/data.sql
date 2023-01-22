INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', '{noop}user'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('User2', 'user2@yandex.ru', '{noop}user2'),
       ('User3', 'user3@yandex.ru', '{noop}user3'),
       ('User4', 'user4@yandex.ru', '{noop}user4'),
       ('User5', 'user5@yandex.ru', '{noop}user5'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2),
       ('USER', 3),
       ('USER', 4),
       ('USER', 5),
       ('USER', 6);

INSERT INTO RESTAURANT (NAME, DESCRIPTION)
VALUES ('Astoria', 'Russian kitchen.'),
       ('Prival', 'Asia kitchen.'),
       ('Ohotnik', 'European kitchen.');

INSERT INTO VOTES (DATE, RESTAURANT_ID, USER_ID)
VALUES (now(), 1, 1);

INSERT INTO DISHES (NAME, DATE, PRICE, RESTAURANT_ID)
VALUES ('Борщ Русский', now(), 100, 1),
       ('Суп Харчо', now(), 120, 1),
       ('Рис отварной', now(), 50, 1),
       ('Спагетти', now(), 130, 1),
       ('Гуляш говяжий', now(), 200, 1),
       ('Хлеб', now(), 15, 1),
       ('Чай с лимоном', now(), 60, 1),
       ('Суп гороховый', now(), 100, 2),
       ('Рис отварной', now(), 50, 2),
       ('Спагетти', now(), 130, 2),
       ('Гуляш Свиной', now(), 180, 2),
       ('Хлеб', now(), 15, 2),
       ('Капучино', now(), 100, 2),
       ('Суп "Весенний"', now(), 140, 3),
       ('Гречка', now(), 50, 3),
       ('Салат "Цезарь"', now(), 130, 3),
       ('Гуляш Свиной', now(), 180, 3),
       ('Хлеб', now(), 15, 3),
       ('Компот', now(), 60, 3);