CREATE DATABASE bdGestionBanco;
USE bdGestionBanco;

-- Tablas auxiliares
CREATE TABLE Sexo (
    id_sexo INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(20) NOT NULL
);

CREATE TABLE Nacionalidad (
    id_nacionalidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Provincia (
    id_provincia INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Localidad (
    id_localidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    id_provincia INT NOT NULL,
    FOREIGN KEY (id_provincia) REFERENCES Provincia(id_provincia)
);

-- Tipos de usuario
CREATE TABLE Tipos_usuario (
    tipo_id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion_tipo VARCHAR(50) NOT NULL
);

-- Usuarios
CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    pass_usuario VARCHAR(50) NOT NULL,
    tipo_usuario INT NOT NULL,
    estado BOOLEAN NOT NULL,
    FOREIGN KEY (tipo_usuario) REFERENCES Tipos_usuario(tipo_id)
);

-- Clientes
CREATE TABLE Clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    DNI VARCHAR(20) NOT NULL UNIQUE,
    CUIL VARCHAR(20) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    id_sexo INT NOT NULL,
    id_nacionalidad INT NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    direccion VARCHAR(100),
    id_localidad INT NOT NULL,
    email VARCHAR(50),
    telefono VARCHAR(50),
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY (id_sexo) REFERENCES Sexo(id_sexo),
    FOREIGN KEY (id_nacionalidad) REFERENCES Nacionalidad(id_nacionalidad),
    FOREIGN KEY (id_localidad) REFERENCES Localidad(id_localidad)
);

-- Tipos de cuenta
CREATE TABLE Tipos_cuenta (
    tipo_id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);

-- Cuentas
CREATE TABLE Cuentas (
    id_cuenta INT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(50) UNIQUE,
    cbu VARCHAR(50) UNIQUE,
    tipo_cuenta INT,
    fecha_creacion DATE,
    saldo DECIMAL(12,2),
    estado BOOLEAN,
    id_cliente INT,
    FOREIGN KEY (tipo_cuenta) REFERENCES Tipos_cuenta(tipo_id),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente)
);

-- Tipos de movimiento
CREATE TABLE Tipo_movimiento (
    id_tipo_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    descripcion VARCHAR(255),
    estado BOOLEAN
);

-- Movimientos
CREATE TABLE Movimientos (
    id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    id_cuenta INT,
    id_tipo_movimiento INT,
    fecha DATE,
    importe DECIMAL(12,2),
    saldo DECIMAL(12,2),
    FOREIGN KEY (id_cuenta) REFERENCES Cuentas(id_cuenta),
    FOREIGN KEY (id_tipo_movimiento) REFERENCES Tipo_movimiento(id_tipo_movimiento)
);

-- Transferencias
CREATE TABLE Transferencias (
    id_transferencia INT AUTO_INCREMENT PRIMARY KEY,
    id_cuenta_origen INT,
    id_cuenta_destino INT,
    fecha DATE,
    importe DECIMAL(12,2),
    detalle VARCHAR(255),
    FOREIGN KEY (id_cuenta_origen) REFERENCES Cuentas(id_cuenta),
    FOREIGN KEY (id_cuenta_destino) REFERENCES Cuentas(id_cuenta)
);

-- Prestamos
CREATE TABLE Prestamos (
    id_prestamo INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    id_cuenta INT,
    fecha_alta DATE,
    importe_pedido DECIMAL(12,2),
    plazo_meses INT,
    cantidad_cuotas INT,
    importe_cuota DECIMAL(12,2),
    estado ENUM('pendiente','aprobado','rechazado'),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente),
    FOREIGN KEY (id_cuenta) REFERENCES Cuentas(id_cuenta)
);

-- Cuotas
CREATE TABLE Cuotas (
    id_cuota INT AUTO_INCREMENT PRIMARY KEY,
    id_prestamo INT,
    numero_cuota INT,
    monto DECIMAL(12,2),
    fecha_pago DATE,
    pagada BOOLEAN,
    FOREIGN KEY (id_prestamo) REFERENCES Prestamos(id_prestamo)
);

-- Sexos
INSERT INTO Sexo (descripcion) VALUES ('Masculino');
INSERT INTO Sexo (descripcion) VALUES ('Femenino');
INSERT INTO Sexo (descripcion) VALUES ('Otro');

-- Nacionalidades
INSERT INTO Nacionalidad (nombre) VALUES ('Argentina');
INSERT INTO Nacionalidad (nombre) VALUES ('Brasil');
INSERT INTO Nacionalidad (nombre) VALUES ('Chile');
INSERT INTO Nacionalidad (nombre) VALUES ('Uruguay');
INSERT INTO Nacionalidad (nombre) VALUES ('Paraguay');
INSERT INTO Nacionalidad (nombre) VALUES ('Bolivia');
INSERT INTO Nacionalidad (nombre) VALUES ('Perú');
INSERT INTO Nacionalidad (nombre) VALUES ('Colombia');
INSERT INTO Nacionalidad (nombre) VALUES ('Venezuela');
INSERT INTO Nacionalidad (nombre) VALUES ('Ecuador');

-- Provincias y localidades (1 por provincia)
INSERT INTO Provincia (nombre) VALUES ('Buenos Aires');
INSERT INTO Localidad (nombre, id_provincia) VALUES ('Localidad 1', 1);
INSERT INTO Provincia (nombre) VALUES ('Córdoba');
INSERT INTO Localidad (nombre, id_provincia) VALUES ('Localidad 2', 2);
INSERT INTO Provincia (nombre) VALUES ('Santa Fe');
INSERT INTO Localidad (nombre, id_provincia) VALUES ('Localidad 3', 3);
INSERT INTO Provincia (nombre) VALUES ('Mendoza');
INSERT INTO Localidad (nombre, id_provincia) VALUES ('Localidad 4', 4);
INSERT INTO Provincia (nombre) VALUES ('Tucumán');
INSERT INTO Localidad (nombre, id_provincia) VALUES ('Localidad 5', 5);
INSERT INTO Provincia (nombre) VALUES ('Salta');
INSERT INTO Localidad (nombre, id_provincia) VALUES ('Localidad 6', 6);
INSERT INTO Provincia (nombre) VALUES ('Misiones');
INSERT INTO Localidad (nombre, id_provincia) VALUES ('Localidad 7', 7);
INSERT INTO Provincia (nombre) VALUES ('Neuquén');
INSERT INTO Localidad (nombre, id_provincia) VALUES ('Localidad 8', 8);
INSERT INTO Provincia (nombre) VALUES ('Chubut');
INSERT INTO Localidad (nombre, id_provincia) VALUES ('Localidad 9', 9);
INSERT INTO Provincia (nombre) VALUES ('Jujuy');
INSERT INTO Localidad (nombre, id_provincia) VALUES ('Localidad 10', 10);

-- Tipos de usuario
INSERT INTO Tipos_usuario (descripcion_tipo) VALUES ('Administrador'), ('Cliente');

-- Usuarios
INSERT INTO Usuarios (nombre_usuario, pass_usuario, tipo_usuario, estado) VALUES 
('admin', 'admin', 1, TRUE),
('cliente1', '1234', 2, TRUE),
('cliente2', '1234', 2, TRUE);

-- Tipos de cuenta
INSERT INTO Tipos_cuenta (descripcion) VALUES ('Caja de ahorro'), ('Cuenta corriente');

-- Tipos de movimiento
INSERT INTO Tipo_movimiento (nombre, descripcion, estado) VALUES
('Alta de cuenta', 'Creación inicial de cuenta', TRUE),
('Transferencia', 'Transferencia a otra cuenta', TRUE),
('Préstamo', 'Depósito por préstamo', TRUE),
('Pago préstamo', 'Cuota de préstamo pagada', TRUE);

-- Clientes
INSERT INTO Clientes (
    id_usuario, DNI, CUIL, nombre, apellido, id_sexo, id_nacionalidad,
    fecha_nacimiento, direccion, id_localidad, email, telefono
) VALUES 
(2, '12345678', '20-12345678-9', 'Juan', 'Pérez', 1, 1, '1990-01-01', 'Calle Falsa 123',1, 'juan@example.com', '1111111111'),
(3, '87654321', '20-87654321-9', 'Ana', 'Gómez', 2, 2, '1992-02-02', 'Calle Verdadera 456',2, 'ana@example.com', '2222222222');

-- Cuentas
INSERT INTO Cuentas (
    numero_cuenta, cbu, tipo_cuenta, fecha_creacion, saldo, estado, id_cliente
) VALUES 
('CA-1001', '0110123456789012345671', 1, CURDATE(), 10000.00, TRUE, 1),
('CC-1002', '0110123456789012345672', 2, CURDATE(), 5000.00, TRUE, 2);

-- Movimientos
INSERT INTO Movimientos (
    id_cuenta, id_tipo_movimiento, fecha, importe, saldo
) VALUES 
(1, 1, CURDATE(), 10000.00, 10000.00),
(2, 1, CURDATE(), 5000.00, 5000.00);

-- Préstamo
INSERT INTO Prestamos (
    id_cliente, id_cuenta, fecha_alta, importe_pedido, plazo_meses, cantidad_cuotas, importe_cuota, estado
) VALUES 
(2, 2, CURDATE(), 12000.00, 6, 6, 2000.00, 'pendiente');

-- Cuotas
INSERT INTO Cuotas (
    id_prestamo, numero_cuota, monto, fecha_pago, pagada
) VALUES
(1, 1, 2000.00, NULL, FALSE),
(1, 2, 2000.00, NULL, FALSE),
(1, 3, 2000.00, NULL, FALSE),
(1, 4, 2000.00, NULL, FALSE),
(1, 5, 2000.00, NULL, FALSE),
(1, 6, 2000.00, NULL, FALSE);
