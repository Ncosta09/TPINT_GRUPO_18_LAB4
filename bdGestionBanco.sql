create database bdGestionBanco;
use bdGestionBanco;

CREATE TABLE Tipos_usuario (
    tipo_id INT PRIMARY KEY,
    descripcion_tipo VARCHAR(50) NOT NULL
);

CREATE TABLE Usuarios (
    id_usuario INT PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL,
    pass_usuario VARCHAR(50) NOT NULL,
    tipo_usuario INT NOT NULL,
    estado INT NOT NULL,
    FOREIGN KEY (tipo_usuario) REFERENCES Tipos_usuario(tipo_id)
);

CREATE TABLE Clientes (
    id_cliente INT PRIMARY KEY,
    id_usuario INT NOT NULL,
    DNI VARCHAR(50) NOT NULL,
    CUIL VARCHAR(50) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    sexo VARCHAR(50) NOT NULL,
    nacionalidad VARCHAR(50),
    fecha_nacimiento DATE NOT NULL,
    direccion VARCHAR(50),
    localidad VARCHAR(50),
    provincia VARCHAR(50),
    email VARCHAR(50),
    telefono VARCHAR(50),
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

CREATE TABLE Tipos_cuenta (
    tipo_id INT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);

CREATE TABLE Cuentas (
    id_cuenta INT PRIMARY KEY,
    numero_cuenta VARCHAR(50),
    cbu VARCHAR(50),
    tipo_cuenta INT,
    fecha_creacion DATE,
    saldo DECIMAL(12,2),
    estado INT,
    id_cliente INT,
    FOREIGN KEY (tipo_cuenta) REFERENCES Tipos_cuenta(tipo_id),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente)
);

CREATE TABLE Tipo_movimiento (
    id_tipo_movimiento INT PRIMARY KEY,
    nombre VARCHAR(50),
    descripcion VARCHAR(255),
    estado INT
);

CREATE TABLE Movimientos (
    id_movimiento INT PRIMARY KEY,
    id_cuenta INT,
    id_tipo_movimiento INT,
    fecha DATE,
    importe DECIMAL(12,2),
    detalle VARCHAR(255),
    saldo DECIMAL(12,2),
    FOREIGN KEY (id_cuenta) REFERENCES Cuentas(id_cuenta),
    FOREIGN KEY (id_tipo_movimiento) REFERENCES Tipo_movimiento(id_tipo_movimiento)
);

CREATE TABLE Prestamos (
    id_prestamo INT PRIMARY KEY,
    id_cliente INT,
    id_cuenta INT,
    fecha_alta DATE,
    importe_pedido DECIMAL(12,2),
    plazo_meses INT,
    cantidad_cuotas INT,
    importe_cuota DECIMAL(12,2),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente),
    FOREIGN KEY (id_cuenta) REFERENCES Cuentas(id_cuenta)
);

CREATE TABLE Cuotas (
    id_cuota INT PRIMARY KEY,
    id_prestamo INT,
    numero_cuota INT,
    monto DECIMAL(12,2),
    fecha_pago DATE,
    FOREIGN KEY (id_prestamo) REFERENCES Prestamos(id_prestamo)
);

-- DATOS DE EJEMPLO

-- Tipos de usuario
INSERT INTO Tipos_usuario VALUES (1, 'Administrador'), (2, 'Cliente');

-- Tipos de cuenta
INSERT INTO Tipos_cuenta VALUES (1, 'Caja de ahorro'), (2, 'Cuenta corriente');

-- Tipos de movimiento
INSERT INTO Tipo_movimiento VALUES 
(1, 'Alta de cuenta', 'Creación inicial de cuenta', 1),
(2, 'Transferencia', 'Transferencia a otra cuenta', 1),
(3, 'Préstamo', 'Depósito por préstamo', 1),
(4, 'Pago préstamo', 'Cuota de préstamo pagada', 1);

-- Usuario administrador
INSERT INTO Usuarios VALUES (1, 'admin', 'admin', 1, 1);

-- Usuarios clientes
INSERT INTO Usuarios VALUES (2, 'cliente1', '1234', 2, 1);
INSERT INTO Usuarios VALUES (3, 'cliente2', '1234', 2, 1);

-- Clientes
INSERT INTO Clientes VALUES 
(1, 2, '12345678', '20-12345678-9', 'Juan', 'Pérez', 'Masculino', 'Argentina', '1990-01-01', 'Calle Falsa 123', 'San Martín', 'Buenos Aires', 'juan@example.com', '1111111111'),
(2, 3, '87654321', '20-87654321-9', 'Ana', 'Gómez', 'Femenino', 'Argentina', '1992-02-02', 'Calle Verdadera 456', 'Tigre', 'Buenos Aires', 'ana@example.com', '2222222222');

-- Cuentas
INSERT INTO Cuentas VALUES 
(1, 'CA-1001', '0110123456789012345671', 1, '2024-01-01', 10000.00, 1, 1),
(2, 'CC-1002', '0110123456789012345672', 2, '2024-01-02', 5000.00, 1, 2);

-- Movimientos
INSERT INTO Movimientos VALUES 
(1, 1, 1, '2024-01-01', 10000.00, 'Apertura de cuenta', 10000.00),
(2, 2, 1, '2024-01-02', 5000.00, 'Apertura de cuenta', 5000.00);

-- Préstamo
INSERT INTO Prestamos VALUES 
(1, 2, 2, '2024-02-01', 12000.00, 6, 6, 2000.00);

-- Cuotas del préstamo
INSERT INTO Cuotas VALUES 
(1, 1, 1, 2000.00, NULL),
(2, 1, 2, 2000.00, NULL),
(3, 1, 3, 2000.00, NULL),
(4, 1, 4, 2000.00, NULL),
(5, 1, 5, 2000.00, NULL),
(6, 1, 6, 2000.00, NULL);
