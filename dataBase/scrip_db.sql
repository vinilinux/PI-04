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

