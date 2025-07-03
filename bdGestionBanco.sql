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
    estado BOOLEAN NOT NULL DEFAULT 1,
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
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
    estado BOOLEAN DEFAULT 1,
    id_cliente INT,
    FOREIGN KEY (tipo_cuenta) REFERENCES Tipos_cuenta(tipo_id),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente)
);

-- Tipos de movimiento
CREATE TABLE Tipo_movimiento (
    id_tipo_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    descripcion VARCHAR(255)
);

-- Movimientos
CREATE TABLE Movimientos (
    id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    id_cuenta INT,
    id_tipo_movimiento INT,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
    estado ENUM('pendiente','aprobado','rechazado') NOT NULL DEFAULT 'pendiente' ,
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
    pagada BOOLEAN DEFAULT 0,
    fecha_vencimiento DATE,
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



-- Tipos de usuario
INSERT INTO Tipos_usuario (descripcion_tipo) VALUES ('Administrador'), ('Cliente');

-- Usuarios


-- Tipos de cuenta
INSERT INTO Tipos_cuenta (descripcion) VALUES ('Caja de ahorro'), ('Cuenta corriente');

-- Tipos de movimiento
INSERT INTO Tipo_movimiento (nombre, descripcion) VALUES
('Alta de cuenta', 'Creación inicial de cuenta'),
('Transferencia', 'Transferencia a otra cuenta'),
('Préstamo', 'Depósito por préstamo'),
('Pago préstamo', 'Cuota de préstamo pagada');

-- Clientes

-- Cuentas


-- Movimientos


-- Préstamo


-- #DATA# (inserts generados por Cursor)



-- Completar Nacionalidades (15 total)
INSERT INTO Nacionalidad (nombre) VALUES 
('Estados Unidos'),
('España'),
('Italia'),
('Francia'),
('Alemania'),
('México'),
('Colombia'),
('Perú'),
('Venezuela'),
('Ecuador');

-- Provincias (15 total)
INSERT INTO Provincia (nombre) VALUES 
('Buenos Aires'),
('Córdoba'),
('Santa Fe'),
('Mendoza'),
('Tucumán'),
('Entre Ríos'),
('Salta'),
('Chaco'),
('Corrientes'),
('Misiones'),
('Santiago del Estero'),
('San Juan'),
('Jujuy'),
('La Rioja'),
('Catamarca');

-- Localidades (15 total, una por provincia)
INSERT INTO Localidad (nombre, id_provincia) VALUES 
('La Plata', 1),
('Córdoba Capital', 2),
('Rosario', 3),
('Mendoza Capital', 4),
('San Miguel de Tucumán', 5),
('Paraná', 6),
('Salta Capital', 7),
('Resistencia', 8),
('Corrientes Capital', 9),
('Posadas', 10),
('Santiago del Estero Capital', 11),
('San Juan Capital', 12),
('San Salvador de Jujuy', 13),
('La Rioja Capital', 14),
('San Fernando del Valle de Catamarca', 15);



-- Usuarios (15 total)
INSERT INTO Usuarios (nombre_usuario, pass_usuario, tipo_usuario) VALUES 
('admin', 'admin123', 1),
('cliente1', 'pass123', 2),
('cliente2', 'pass123', 2),
('cliente3', 'pass123', 2),
('cliente4', 'pass123', 2),
('cliente5', 'pass123', 2),
('cliente6', 'pass123', 2),
('cliente7', 'pass123', 2),
('cliente8', 'pass123', 2),
('cliente9', 'pass123', 2),
('cliente10', 'pass123', 2),
('cliente11', 'pass123', 2),
('cliente12', 'pass123', 2),
('cliente13', 'pass123', 2),
('cliente14', 'pass123', 2);



-- Clientes (15 total)
INSERT INTO Clientes (id_usuario, DNI, CUIL, nombre, apellido, id_sexo, id_nacionalidad, fecha_nacimiento, direccion, id_localidad, email, telefono) VALUES 
(2, '12345678', '20-12345678-9', 'Juan', 'Pérez', 1, 1, '1985-03-15', 'Av. San Martín 123', 1, 'juan.perez@email.com', '011-1234-5678'),
(3, '23456789', '20-23456789-0', 'María', 'González', 2, 1, '1990-07-22', 'Belgrano 456', 2, 'maria.gonzalez@email.com', '0351-234-5678'),
(4, '34567890', '20-34567890-1', 'Carlos', 'López', 1, 1, '1988-11-10', 'Rivadavia 789', 3, 'carlos.lopez@email.com', '0341-345-6789'),
(5, '45678901', '20-45678901-2', 'Ana', 'Martínez', 2, 1, '1992-05-18', 'Mitre 321', 4, 'ana.martinez@email.com', '0261-456-7890'),
(6, '56789012', '20-56789012-3', 'Roberto', 'Fernández', 1, 1, '1987-09-30', 'Sarmiento 654', 5, 'roberto.fernandez@email.com', '0381-567-8901'),
(7, '67890123', '20-67890123-4', 'Laura', 'Rodríguez', 2, 1, '1995-01-25', 'Independencia 987', 6, 'laura.rodriguez@email.com', '0343-678-9012'),
(8, '78901234', '20-78901234-5', 'Diego', 'Silva', 1, 1, '1983-12-08', 'Libertad 147', 7, 'diego.silva@email.com', '0387-789-0123'),
(9, '89012345', '20-89012345-6', 'Patricia', 'Torres', 2, 1, '1989-04-12', 'San Juan 258', 8, 'patricia.torres@email.com', '0362-890-1234'),
(10, '90123456', '20-90123456-7', 'Miguel', 'Ramírez', 1, 1, '1991-08-20', 'Corrientes 369', 9, 'miguel.ramirez@email.com', '0379-901-2345'),
(11, '01234567', '20-01234567-8', 'Sofía', 'Herrera', 2, 1, '1986-06-14', 'Entre Ríos 741', 10, 'sofia.herrera@email.com', '0376-012-3456'),
(12, '11111111', '20-11111111-1', 'Luis', 'Morales', 1, 1, '1993-02-28', 'Catamarca 852', 11, 'luis.morales@email.com', '0385-111-1111'),
(13, '22222222', '20-22222222-2', 'Carmen', 'Vargas', 2, 1, '1984-10-05', 'San Luis 963', 12, 'carmen.vargas@email.com', '0264-222-2222'),
(14, '33333333', '20-33333333-3', 'Fernando', 'Castro', 1, 1, '1994-07-17', 'Jujuy 159', 13, 'fernando.castro@email.com', '0388-333-3333'),
(15, '44444444', '20-44444444-4', 'Valeria', 'Rojas', 2, 1, '1982-12-03', 'La Rioja 753', 14, 'valeria.rojas@email.com', '0380-444-4444');

-- Cuentas (14 total, una por cliente, saldo inicial 10000)
INSERT INTO Cuentas (numero_cuenta, cbu, tipo_cuenta, fecha_creacion, saldo, id_cliente) VALUES 
('001-123456-7', '0110123456789012345678', 1, CURDATE(), 10000.00, 1),
('001-234567-8', '0110234567890123456789', 2, CURDATE(), 10000.00, 2),
('001-345678-9', '0110345678901234567890', 1, CURDATE(), 10000.00, 3),
('001-456789-0', '0110456789012345678901', 2, CURDATE(), 10000.00, 4),
('001-567890-1', '0110567890123456789012', 1, CURDATE(), 10000.00, 5),
('001-678901-2', '0110678901234567890123', 2, CURDATE(), 10000.00, 6),
('001-789012-3', '0110789012345678901234', 1, CURDATE(), 10000.00, 7),
('001-890123-4', '0110890123456789012345', 2, CURDATE(), 10000.00, 8),
('001-901234-5', '0110901234567890123456', 1, CURDATE(), 10000.00, 9),
('001-012345-6', '0110012345678901234567', 2, CURDATE(), 10000.00, 10),
('001-111111-1', '0110111111111111111111', 1, CURDATE(), 10000.00, 11),
('001-222222-2', '0110222222222222222222', 2, CURDATE(), 10000.00, 12),
('001-333333-3', '0110333333333333333333', 1, CURDATE(), 10000.00, 13),
('001-444444-4', '0110444444444444444444', 1, CURDATE(), 10000.00, 14);

-- Movimientos iniciales (14 total, uno por cuenta - Alta de cuenta)
INSERT INTO Movimientos (id_cuenta, id_tipo_movimiento, importe, saldo) VALUES 
(1, 1, 10000.00, 10000.00),
(2, 1, 10000.00, 10000.00),
(3, 1, 10000.00, 10000.00),
(4, 1, 10000.00, 10000.00),
(5, 1, 10000.00, 10000.00),
(6, 1, 10000.00, 10000.00),
(7, 1, 10000.00, 10000.00),
(8, 1, 10000.00, 10000.00),
(9, 1, 10000.00, 10000.00),
(10, 1, 10000.00, 10000.00),
(11, 1, 10000.00, 10000.00),
(12, 1, 10000.00, 10000.00),
(13, 1, 10000.00, 10000.00),
(14, 1, 10000.00, 10000.00);

-- Transferencias (14 total)
INSERT INTO Transferencias (id_cuenta_origen, id_cuenta_destino, importe, detalle) VALUES 
(1, 2, 500.00, 'Transferencia por servicios'),
(2, 3, 750.00, 'Pago de alquiler'),
(3, 4, 300.00, 'Transferencia familiar'),
(4, 5, 1200.00, 'Pago de deuda'),
(5, 6, 450.00, 'Transferencia comercial'),
(6, 7, 800.00, 'Pago de facturas'),
(7, 8, 600.00, 'Transferencia personal'),
(8, 9, 900.00, 'Pago de préstamo'),
(9, 10, 350.00, 'Transferencia de regalo'),
(10, 11, 1100.00, 'Pago de servicios'),
(11, 12, 650.00, 'Transferencia familiar'),
(12, 13, 400.00, 'Pago de alquiler'),
(13, 14, 950.00, 'Transferencia comercial'),
(14, 1, 700.00, 'Transferencia personal');

-- Movimientos por transferencias (28 total - 14 de origen y 14 de destino)
INSERT INTO Movimientos (id_cuenta, id_tipo_movimiento, importe, saldo) VALUES 
-- Transferencias de origen (negativas)
(1, 2, -500.00, 9500.00),
(2, 2, -750.00, 9250.00),
(3, 2, -300.00, 9700.00),
(4, 2, -1200.00, 8800.00),
(5, 2, -450.00, 9550.00),
(6, 2, -800.00, 9200.00),
(7, 2, -600.00, 9400.00),
(8, 2, -900.00, 9100.00),
(9, 2, -350.00, 9650.00),
(10, 2, -1100.00, 8900.00),
(11, 2, -650.00, 9350.00),
(12, 2, -400.00, 9600.00),
(13, 2, -950.00, 9050.00),
(14, 2, -700.00, 9300.00),
-- Transferencias de destino (positivas)
(2, 2, 500.00, 10500.00),
(3, 2, 750.00, 10750.00),
(4, 2, 300.00, 10300.00),
(5, 2, 1200.00, 11200.00),
(6, 2, 450.00, 10450.00),
(7, 2, 800.00, 10800.00),
(8, 2, 600.00, 10600.00),
(9, 2, 900.00, 10900.00),
(10, 2, 350.00, 10350.00),
(11, 2, 1100.00, 11100.00),
(12, 2, 650.00, 10650.00),
(13, 2, 400.00, 10400.00),
(14, 2, 950.00, 10950.00),
(1, 2, 700.00, 10200.00);

-- Préstamos (14 total)
INSERT INTO Prestamos (id_cliente, id_cuenta, fecha_alta, importe_pedido, plazo_meses, cantidad_cuotas, importe_cuota) VALUES 
(1, 1, CURDATE(), 5000.00, 6, 6, 833.33),
(2, 2, CURDATE(), 8000.00, 12, 12, 666.67),
(3, 3, CURDATE(), 3000.00, 6, 6, 500.00),
(4, 4, CURDATE(), 10000.00, 12, 12, 833.33),
(5, 5, CURDATE(), 6000.00, 8, 8, 750.00),
(6, 6, CURDATE(), 7500.00, 10, 10, 750.00),
(7, 7, CURDATE(), 4000.00, 6, 6, 666.67),
(8, 8, CURDATE(), 9000.00, 12, 12, 750.00),
(9, 9, CURDATE(), 3500.00, 6, 6, 583.33),
(10, 10, CURDATE(), 12000.00, 12, 12, 1000.00),
(11, 11, CURDATE(), 6500.00, 8, 8, 812.50),
(12, 12, CURDATE(), 4000.00, 6, 6, 666.67),
(13, 13, CURDATE(), 9500.00, 10, 10, 950.00),
(14, 14, CURDATE(), 7000.00, 8, 8, 875.00);

-- Movimientos por préstamos (14 total)
INSERT INTO Movimientos (id_cuenta, id_tipo_movimiento, importe, saldo) VALUES 
(1, 3, 5000.00, 15500.00),
(2, 3, 8000.00, 18500.00),
(3, 3, 3000.00, 13000.00),
(4, 3, 10000.00, 20000.00),
(5, 3, 6000.00, 16000.00),
(6, 3, 7500.00, 17500.00),
(7, 3, 4000.00, 14000.00),
(8, 3, 9000.00, 19000.00),
(9, 3, 3500.00, 13500.00),
(10, 3, 12000.00, 22000.00),
(11, 3, 6500.00, 16500.00),
(12, 3, 4000.00, 14000.00),
(13, 3, 9500.00, 19500.00),
(14, 3, 7000.00, 17000.00);

-- Cuotas (mínimo 30 total - 15 pagadas y 15 pendientes)
INSERT INTO Cuotas (id_prestamo, numero_cuota, monto, fecha_pago, fecha_vencimiento) VALUES 
-- Cuotas pagadas (15 total)
(1, 1, 833.33, CURDATE(), DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
(1, 2, 833.33, CURDATE(), DATE_SUB(CURDATE(), INTERVAL 2 MONTH)),
(2, 1, 666.67, CURDATE(), DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
(2, 2, 666.67, CURDATE(), DATE_SUB(CURDATE(), INTERVAL 2 MONTH)),
(3, 1, 500.00, CURDATE(), DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
(4, 1, 833.33, CURDATE(), DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
(5, 1, 750.00, CURDATE(), DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
(6, 1, 750.00, CURDATE(), DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
(7, 1, 666.67, CURDATE(), DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
(8, 1, 750.00, CURDATE(), DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
(9, 1, 583.33, CURDATE(), DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
(10, 1, 1000.00, CURDATE(), DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
(11, 1, 812.50, CURDATE(), DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
(12, 1, 666.67, CURDATE(), DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
(13, 1, 950.00, CURDATE(), DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
-- Cuotas pendientes (15 total)
(1, 3, 833.33, NULL, DATE_ADD(CURDATE(), INTERVAL 1 MONTH)),
(1, 4, 833.33, NULL, DATE_ADD(CURDATE(), INTERVAL 2 MONTH)),
(1, 5, 833.33, NULL, DATE_ADD(CURDATE(), INTERVAL 3 MONTH)),
(1, 6, 833.33, NULL, DATE_ADD(CURDATE(), INTERVAL 4 MONTH)),
(2, 3, 666.67, NULL, DATE_ADD(CURDATE(), INTERVAL 1 MONTH)),
(2, 4, 666.67, NULL, DATE_ADD(CURDATE(), INTERVAL 2 MONTH)),
(2, 5, 666.67, NULL, DATE_ADD(CURDATE(), INTERVAL 3 MONTH)),
(2, 6, 666.67, NULL, DATE_ADD(CURDATE(), INTERVAL 4 MONTH)),
(2, 7, 666.67, NULL, DATE_ADD(CURDATE(), INTERVAL 5 MONTH)),
(2, 8, 666.67, NULL, DATE_ADD(CURDATE(), INTERVAL 6 MONTH)),
(2, 9, 666.67, NULL, DATE_ADD(CURDATE(), INTERVAL 7 MONTH)),
(2, 10, 666.67, NULL, DATE_ADD(CURDATE(), INTERVAL 8 MONTH)),
(2, 11, 666.67, NULL, DATE_ADD(CURDATE(), INTERVAL 9 MONTH)),
(2, 12, 666.67, NULL, DATE_ADD(CURDATE(), INTERVAL 10 MONTH)),
(3, 2, 500.00, NULL, DATE_ADD(CURDATE(), INTERVAL 1 MONTH)),
(3, 3, 500.00, NULL, DATE_ADD(CURDATE(), INTERVAL 2 MONTH)),
(3, 4, 500.00, NULL, DATE_ADD(CURDATE(), INTERVAL 3 MONTH)),
(3, 5, 500.00, NULL, DATE_ADD(CURDATE(), INTERVAL 4 MONTH)),
(3, 6, 500.00, NULL, DATE_ADD(CURDATE(), INTERVAL 5 MONTH));

-- Movimientos por pagos de cuotas (14 total)
INSERT INTO Movimientos (id_cuenta, id_tipo_movimiento, importe, saldo) VALUES 
(1, 4, -833.33, 14666.67),
(1, 4, -833.33, 13833.34),
(2, 4, -666.67, 17833.33),
(2, 4, -666.67, 17166.66),
(3, 4, -500.00, 12500.00),
(4, 4, -833.33, 19166.67),
(5, 4, -750.00, 15250.00),
(6, 4, -750.00, 16750.00),
(7, 4, -666.67, 13333.33),
(8, 4, -750.00, 18250.00),
(9, 4, -583.33, 12916.67),
(10, 4, -1000.00, 21000.00),
(11, 4, -812.50, 15687.50),
(12, 4, -666.67, 13333.33);

-- #DATA# end



