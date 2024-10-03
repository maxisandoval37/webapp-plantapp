-- Inserción de Jardineros de ejemplo:
INSERT INTO jardinero (especialidad, email) VALUES ('Árboles', 'eduardo@email.com');
INSERT INTO jardinero (especialidad, email) VALUES ('Suculentas', 'leonardo@email.com');
INSERT INTO jardinero (especialidad, email) VALUES ('Helechos', 'ezequiel@email.com');
INSERT INTO jardinero (especialidad, email) VALUES ('Rosales', 'santiago@email.com');
INSERT INTO jardinero (especialidad, email) VALUES ('Hortalizas', 'camila@email.com');
INSERT INTO jardinero (especialidad, email) VALUES ('Frutas', 'maximiliano@email.com');

-- Inserción de Prospectos de ejemplo:
INSERT INTO prospecto (nombre, cantidad, unidad) VALUES ('Tierra', 5.0, 'KG');
INSERT INTO prospecto (nombre, cantidad, unidad) VALUES ('Perlitas', 1.0, 'KG');
INSERT INTO prospecto (nombre, cantidad, unidad) VALUES ('Fertilizante', 15.0, 'L');
INSERT INTO prospecto (nombre, cantidad, unidad) VALUES ('Agua destilada', 100.0, 'L');
INSERT INTO prospecto (nombre, cantidad, unidad) VALUES ('Humus de lombriz', 2.0, 'KG');
INSERT INTO prospecto (nombre, cantidad, unidad) VALUES ('Arena', 50.0, 'KG');
INSERT INTO Prospecto (nombre, cantidad, unidad) VALUES ('Maceta', 2.0, 'Cant');

-- Inserción de Plantas de ejemplo:
insert into planta (especie, color_hojas, fecha_plantacion, jardinero_id) VALUES ('Venus atrapamoscas', 'Verdes', '2023-01-09', 1);
INSERT INTO planta (especie, color_hojas, fecha_plantacion, jardinero_id) VALUES ('Suculenta', 'Rojas', '2023-05-10', 1);
INSERT INTO planta (especie, color_hojas, fecha_plantacion, jardinero_id) VALUES ('Tomate', 'Verdes', '2023-04-10', 3);
INSERT INTO planta (especie, color_hojas, fecha_plantacion, jardinero_id) VALUES ('Lengua de suegra', 'Verdes', '2023-01-05', 3);
INSERT INTO planta (especie, color_hojas, fecha_plantacion, jardinero_id) VALUES ('Flores', 'Violetas', '2022-01-12', 1);
INSERT INTO planta (especie, color_hojas, fecha_plantacion, jardinero_id) VALUES ('Lechuga', 'Verdes', '2021-11-11', 1);
INSERT INTO planta (especie, color_hojas, fecha_plantacion, jardinero_id) VALUES ('Rosas', 'Rosa', '2024-01-10', 4);

-- Inserción de Plantas de ejemplo con prospectos:
INSERT INTO planta_prospecto (planta_id, prospecto_id) VALUES (3,2);
INSERT INTO planta_prospecto (planta_id, prospecto_id) VALUES (1,3);
INSERT INTO planta_prospecto (planta_id, prospecto_id) VALUES (2,2);
INSERT INTO planta_prospecto (planta_id, prospecto_id) VALUES (2,4);

-- Inserción de Usuarios de ejemplo (contraseña user)
INSERT INTO usuario (username, contrasena, rol, nombre, apellido) VALUES ('admin', '{bcrypt}$2a$12$/c4G/jGZk0eJiT1oSWbV.OlaHJkskEUSFO7MYd4me31zmI7kzitf.', 'ROL_ADMIN', 'José', 'Pérez');
INSERT INTO usuario (username, contrasena, rol, nombre, apellido) VALUES ('user', '{bcrypt}$2a$12$/c4G/jGZk0eJiT1oSWbV.OlaHJkskEUSFO7MYd4me31zmI7kzitf.', 'ROL_LECTURA', 'Maria', 'Gómez');
INSERT INTO usuario (username, contrasena, rol, nombre, apellido) VALUES ('lectura', '{bcrypt}$2a$12$/c4G/jGZk0eJiT1oSWbV.OlaHJkskEUSFO7MYd4me31zmI7kzitf.', 'ROL_LECTURA', 'Carlos', 'López');

INSERT INTO usuario (username, contrasena, rol, nombre, apellido, jardinero_id) VALUES ('jardinero_eduardo', '{bcrypt}$2a$12$/c4G/jGZk0eJiT1oSWbV.OlaHJkskEUSFO7MYd4me31zmI7kzitf.', 'ROL_JARDINERO', 'Eduardo', 'Apellido', 1);
INSERT INTO usuario (username, contrasena, rol, nombre, apellido, jardinero_id) VALUES ('jardinero_leonardo', '{bcrypt}$2a$12$/c4G/jGZk0eJiT1oSWbV.OlaHJkskEUSFO7MYd4me31zmI7kzitf.', 'ROL_JARDINERO', 'Leonardo', 'Apellido', 2);
INSERT INTO usuario (username, contrasena, rol, nombre, apellido, jardinero_id) VALUES ('jardinero_ezequiel', '{bcrypt}$2a$12$/c4G/jGZk0eJiT1oSWbV.OlaHJkskEUSFO7MYd4me31zmI7kzitf.', 'ROL_JARDINERO', 'Ezequiel', 'Apellido', 3);
INSERT INTO usuario (username, contrasena, rol, nombre, apellido, jardinero_id) VALUES ('jardinero_santiago', '{bcrypt}$2a$12$/c4G/jGZk0eJiT1oSWbV.OlaHJkskEUSFO7MYd4me31zmI7kzitf.', 'ROL_JARDINERO', 'Santiago', 'Apellido', 4);
INSERT INTO usuario (username, contrasena, rol, nombre, apellido, jardinero_id) VALUES ('jardinero_camila', '{bcrypt}$2a$12$/c4G/jGZk0eJiT1oSWbV.OlaHJkskEUSFO7MYd4me31zmI7kzitf.', 'ROL_JARDINERO', 'Camila', 'Apellido', 5);
INSERT INTO usuario (username, contrasena, rol, nombre, apellido, jardinero_id) VALUES ('jardinero_maxi', '{bcrypt}$2a$12$/c4G/jGZk0eJiT1oSWbV.OlaHJkskEUSFO7MYd4me31zmI7kzitf.', 'ROL_JARDINERO', 'Maxi', 'Apellido', 6);