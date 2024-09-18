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

-- Inserción de Plantas de ejemplo:
insert into planta (especie, color_hojas, fecha_plantacion, jardinero_id) VALUES ('Venus atrapamoscas', 'Verdes', '2023-01-10', 1);

-- Inserción de Plantas de ejemplo con prospectos:
INSERT INTO planta_prospecto (planta_id, prospecto_id) VALUES (1,1);