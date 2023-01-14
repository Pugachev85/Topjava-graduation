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
INSERT INTO RESTAURANT (NAME, UPDATE_DATE)
VALUES ('Astoria', now()),
       ('Prival', now()),
       ('Ohotnik', now());
INSERT INTO VOTES (DATE, RESTAURANT_ID, USER_ID)
VALUES (now(), 1, 1);

INSERT INTO DISHES (NAME, PRICE, RESTAURANT_ID)
VALUES ('Борщ Русский', 100, 1),
       ('Суп Харчо', 120, 1),
       ('Рис отварной', 50, 1),
       ('Спагетти', 130, 1),
       ('Гуляш говяжий', 200, 1),
       ('Хлеб', 15, 1),
       ('Чай с лимоном', 60, 1),
       ('Суп гороховый', 100, 2),
       ('Рис отварной', 50, 2),
       ('Спагетти', 130, 2),
       ('Гуляш Свиной', 180, 2),
       ('Хлеб', 15, 2),
       ('Капучино', 100, 2),
       ('Суп "Весенний"', 140, 3),
       ('Гречка', 50, 3),
       ('Салат "Цезарь"', 130, 3),
       ('Гуляш Свиной', 180, 3),
       ('Хлеб', 15, 3),
       ('Компот', 60, 3);