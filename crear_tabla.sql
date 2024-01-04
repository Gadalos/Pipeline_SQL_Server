-- crear_tabla.sql

USE Colegio;

-- Verificar si la tabla ya existe
IF NOT EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'estudiantes')
BEGIN
    -- Crear la tabla de estudiantes
    CREATE TABLE estudiantes (
        id INT PRIMARY KEY,
        nombre VARCHAR(50),
        edad INT,
        grado VARCHAR(10),
        promedio FLOAT
    );
END;
