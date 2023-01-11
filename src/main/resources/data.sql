INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User1', 'user@yandex.ru', '{noop}user'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('User2', 'user2@gmail.com', '{noop}user2'),
       ('User3', 'user3@gmail.com', '{noop}user3'),
       ('User4', 'user4@gmail.com', '{noop}user4'),
       ('User5', 'user5@gmail.com', '{noop}user5');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2),
       ('USER', 3),
       ('USER', 4),
       ('USER', 5),
       ('USER', 6);
INSERT INTO RESTAURANT (NAME, UPDATE_DATE)
VALUES ('Astoria', '2023-01-12'),
       ('Prival', '2023-01-12'),
       ('Ohotnik', '2023-01-12');
INSERT INTO VOTES (DATE, RESTAURANT_ID, USER_ID)
VALUES ('2023-01-12', 1, 1);