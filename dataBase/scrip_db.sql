CREATE DATABASE if not exists PI4;

CREATE TABLE USER_BACKOFFICE (
    ID_USER int PRIMARY KEY auto_increment,
    NAME VARCHAR(50) NOT NULL,
    EMAIL VARCHAR(50) NOT NULL,
    PASSWORD VARCHAR(70) NOT NULL,
    CPF VARCHAR(11) NOT NULL,
    STATUS VARCHAR(25),
    GROUP_USER VARCHAR(25)
);

CREATE TABLE TBL_PRODUCT (
    ID_PRODUCT INT PRIMARY KEY AUTO_INCREMENT NOT NULL ,
    NAME_PRODUCT VARCHAR(200) NOT NULL ,
    RATING_PRODUCT DOUBLE ,
    DESCRIPTION_PRODUCT VARCHAR(2000) NOT NULL ,
    PRICE_PRODUCT DOUBLE NOT NULL ,
    AMOUNT_PRODUCT INT NOT NULL, 
    STATUS VARCHAR(25)
);

CREATE TABLE TBL_PRODUCT_IMAGE (
    IMAGE_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL ,
    IMAGE_PRODUCT_PATH VARCHAR(2000) NOT NULL ,
    IMAGE_DEFAULT varchar(3),
    ID_PRODUCT INT,
    FOREIGN KEY (ID_PRODUCT) REFERENCES TBL_PRODUCT (ID_PRODUCT)
);

INSERT INTO TBL_PRODUCT (NAME_PRODUCT, RATING_PRODUCT, DESCRIPTION_PRODUCT, PRICE_PRODUCT, AMOUNT_PRODUCT, STATUS)
VALUES
    ('Produto 1', 4.5, 'Descrição do Produto 1', 19.99, 100, 'Ativo'),
    ('Produto 2', 4.2, 'Descrição do Produto 2', 24.99, 50, 'Ativo'),
    ('Produto 3', 4.8, 'Descrição do Produto 3', 29.99, 75, 'Ativo'),
    ('Produto 4', 3.9, 'Descrição do Produto 4', 14.99, 120, 'Ativo'),
    ('Produto 5', 4.7, 'Descrição do Produto 5', 39.99, 90, 'Ativo'),
    ('Produto 6', 4.4, 'Descrição do Produto 6', 49.99, 60, 'Ativo'),
    ('Produto 7', 4.6, 'Descrição do Produto 7', 34.99, 85, 'Ativo'),
    ('Produto 8', 4.0, 'Descrição do Produto 8', 19.99, 110, 'Ativo'),
    ('Produto 9', 4.3, 'Descrição do Produto 9', 54.99, 70, 'Ativo'),
    ('Produto 10', 4.9, 'Descrição do Produto 10', 64.99, 45, 'Ativo'),
    ('Produto 11', 4.5, 'Descrição do Produto 11', 19.99, 100, 'Ativo'),
    ('Produto 12', 4.2, 'Descrição do Produto 12', 24.99, 50, 'Ativo'),
    ('Produto 13', 4.8, 'Descrição do Produto 13', 29.99, 75, 'Ativo'),
    ('Produto 14', 3.9, 'Descrição do Produto 14', 14.99, 120, 'Ativo'),
    ('Produto 15', 4.7, 'Descrição do Produto 15', 39.99, 90, 'Ativo'),
    ('Produto 16', 4.4, 'Descrição do Produto 16', 49.99, 60, 'Ativo'),
    ('Produto 17', 4.6, 'Descrição do Produto 17', 34.99, 85, 'Ativo'),
    ('Produto 18', 4.0, 'Descrição do Produto 18', 19.99, 110, 'Ativo'),
    ('Produto 19', 4.3, 'Descrição do Produto 19', 54.99, 70, 'Ativo'),
    ('Produto 20', 4.9, 'Descrição do Produto 20', 64.99, 45, 'Ativo');

INSERT INTO TBL_PRODUCT_IMAGE (IMAGE_PRODUCT_PATH, IMAGE_DEFAULT, ID_PRODUCT)
VALUES
    ('/Images/produto1.jpg', 'Sim', 1),
    ('/Images/produto2.jpg', 'Sim', 2),
    ('/Images/produto3.jpg', 'Sim', 3),
    ('/Images/produto4.jpg', 'Sim', 4),
    ('/Images/produto5.jpg', 'Sim', 5),
    ('/Images/produto6.jpg', 'Sim', 6),
    ('/Images/produto7.jpg', 'Sim', 7),
    ('/Images/produto8.jpg', 'Sim', 8),
    ('/Images/produto9.jpg', 'Sim', 9),
    ('/Images/produto10.jpg', 'Sim', 10),
    ('/Images/produto11.jpg', 'Sim', 11),
    ('/Images/produto12.jpg', 'Sim', 12),
    ('/Images/produto13.jpg', 'Sim', 13),
    ('/Images/produto14.jpg', 'Sim', 14),
    ('/Images/produto15.jpg', 'Sim', 15),
    ('/Images/produto16.jpg', 'Sim', 16),
    ('/Images/produto17.jpg', 'Sim', 17),
    ('/Images/produto18.jpg', 'Sim', 18),
    ('/Images/produto19.jpg', 'Sim', 19),
    ('/Images/produto20.jpg', 'Sim', 20);

