-- crear_bd.sql

USE master;

-- Verificar si la base de datos ya existe
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'Colegio')
BEGIN
    -- Crear la base de datos
    CREATE DATABASE Colegio;
END;
