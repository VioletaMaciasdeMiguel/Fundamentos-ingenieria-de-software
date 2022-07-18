DROP DATABASE IF EXISTS fisSegundaEntrega;
CREATE DATABASE fisSegundaEntrega;
USE fisSegundaEntrega;

CREATE TABLE Alumno (
	correoETSISI VARCHAR(50) PRIMARY KEY,
	dni CHAR(9) UNIQUE NOT NULL,
	nombreAl VARCHAR(50) NOT NULL,
	apellidos VARCHAR(50) NOT NULL,
	matricula CHAR(12) UNIQUE NOT NULL,
	passw VARCHAR(255) NOT NULL
);

CREATE TABLE Profesor (
	correoETSISI VARCHAR(50) PRIMARY KEY,
	dni CHAR(9) UNIQUE NOT NULL,
	nombreProf VARCHAR(50) NOT NULL,
	apellidos VARCHAR(50) NOT NULL,
	codEmpleado VARCHAR(15),
    codDepartamento VARCHAR(15),
	passw VARCHAR(255) NOT NULL
);

CREATE TABLE Asignatura (
	abreviatura VARCHAR(10) PRIMARY KEY,
	nombre VARCHAR(100) UNIQUE NOT NULL,
	ects TINYINT UNSIGNED NOT NULL
);

CREATE TABLE Cursa (
	correoAlumno VARCHAR(50) NOT NULL,
	asignatura VARCHAR(10) NOT NULL,
	PRIMARY KEY (correoAlumno, asignatura),
	FOREIGN KEY (correoAlumno) REFERENCES Alumno(correoETSISI),
	FOREIGN KEY (asignatura) REFERENCES Asignatura(abreviatura)
);

CREATE TABLE Asociado (
	correoProf VARCHAR(50) NOT NULL,
    asignatura VARCHAR(10) NOT NULL,
    PRIMARY KEY (correoProf, asignatura),
    FOREIGN KEY (correoProf) REFERENCES Profesor(correoETSISI),
    FOREIGN KEY (asignatura) REFERENCES Asignatura(abreviatura)
);


-- Esta prueba tiene la clave almacenada en texto claro, la version final tendra el campo encriptado 
INSERT INTO Alumno(correoETSISI, dni, nombreAl, apellidos, matricula, passw) 
VALUES ('l.martinl@alumnos.upm.es', '12345678Z', 'Luis', 'Martin', 'bp0318', 'abc123.');
INSERT INTO Profesor(correoETSISI, dni, nombreProf, apellidos, passw, codDepartamento, codEmpleado)
VALUES ('profesor_fis@upm.es', '34567890V', 'Albert C.', 'Antístenes', 'abc123.', 'xxxx', 'zzzzz');
INSERT INTO Asignatura(nombre, abreviatura, ects) VALUES ('Fundamentos de Ingeniería del Software', 'FIS', 9);
INSERT INTO Asociado(correoProf, asignatura) VALUES ('profesor_fis@upm.es', 'FIS');
INSERT INTO Cursa(correoAlumno, asignatura) VALUES ('l.martinl@alumnos.upm.es', 'FIS');

INSERT INTO Alumno(correoETSISI, dni, nombreAl, apellidos, matricula, passw) 
VALUES ('test@alumnos.upm.es', '23456789D', 'Pirkko', 'Soumilainen', 'bp0xxx', '0123456789');
INSERT INTO Cursa(correoAlumno, asignatura) VALUES ('test@alumnos.upm.es', 'FIS');

INSERT INTO Asignatura(nombre, abreviatura, ects) VALUES ('Base de Datos Avanzadas', 'BDA', 6);
INSERT INTO Cursa(correoAlumno, asignatura) VALUES ('l.martinl@alumnos.upm.es', 'BDA');
INSERT INTO Asociado(correoProf, asignatura) VALUES ('profesor_fis@upm.es', 'BDA');