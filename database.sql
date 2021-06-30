CREATE DATABASE IF NOT EXISTS loja_hibernate;

CREATE USER IF NOT EXISTS 'loja'@'localhost'IDENTIFIED WITH caching_sha2_password BY 'Loj@V1rtu4l';
GRANT ALL ON loja_hibernate.* TO 'loja'@'localhost';