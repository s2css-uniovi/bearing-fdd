create database RegisteredUsers;
use RegisteredUsers;

CREATE TABLE IF NOT EXISTS Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(255),
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    email VARCHAR(255),
    passw VARCHAR(255),
    roles varchar(255), 
    maxdataset int
);

INSERT INTO Usuarios (usuario, nombre, apellido, email, passw, roles, maxdataset) VALUES
('admin', 'admin', 'admin', 'admin', '$2a$10$k1whoVFoMuwNZ4bUh0FZz.vIoUXPAWu0HDSvKAQ/pWf1xedHCybja', 'ADMIN', 9999),
('user', 'user', 'user', 'user', '$2a$10$2fIME4ZPmW.o76Mo/t3xGeH2Ga/KBu0QXwhSGA/GjBHp7/ArUrw2e', 'USER', 5);


select * from Usuarios;