-- ========================================
-- SCRIPT DE BASE DE DATOS FOOD FAST
-- ========================================

-- Tabla: cliente
CREATE TABLE IF NOT EXISTS cliente (
    id BIGSERIAL PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    direccion TEXT,
    correo VARCHAR(100) UNIQUE,
    fecha_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Tabla: producto
CREATE TABLE IF NOT EXISTS producto (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio NUMERIC(10,2) NOT NULL,
    categoria VARCHAR(50),
    disponible BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla: mesa
CREATE TABLE IF NOT EXISTS mesa (
    id BIGSERIAL PRIMARY KEY,
    numero_mesa INTEGER NOT NULL UNIQUE,
    capacidad INTEGER NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'LIBRE'
);

-- Tabla: pedido
CREATE TABLE IF NOT EXISTS pedido (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    fecha_pedido TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    total NUMERIC(10,2) NOT NULL,
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE
);

-- Tabla: detalle_pedido
CREATE TABLE IF NOT EXISTS detalle_pedido (
    id BIGSERIAL PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad INTEGER NOT NULL,
    precio_unitario NUMERIC(10,2) NOT NULL,
    subtotal NUMERIC(10,2) NOT NULL,
    CONSTRAINT fk_detalle_pedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id) ON DELETE CASCADE,
    CONSTRAINT fk_detalle_pedido_producto FOREIGN KEY (producto_id) REFERENCES producto(id) ON DELETE CASCADE
);

-- Índices
CREATE INDEX IF NOT EXISTS idx_pedido_cliente_id ON pedido(cliente_id);
CREATE INDEX IF NOT EXISTS idx_detalle_pedido_pedido_id ON detalle_pedido(pedido_id);
CREATE INDEX IF NOT EXISTS idx_detalle_pedido_producto_id ON detalle_pedido(producto_id);
CREATE INDEX IF NOT EXISTS idx_producto_disponible ON producto(disponible);
CREATE INDEX IF NOT EXISTS idx_mesa_estado ON mesa(estado);
CREATE INDEX IF NOT EXISTS idx_pedido_estado ON pedido(estado);

-- ========================================
-- DATOS DE PRUEBA
-- ========================================

-- Insertar clientes
INSERT INTO cliente (nombres, apellidos, telefono, direccion, correo) VALUES
('Juan', 'Pérez', '777-1234', 'Calle 1 #123', 'juan.perez@email.com'),
('María', 'García', '777-5678', 'Av. Principal #456', 'maria.garcia@email.com'),
('Carlos', 'López', '777-9012', 'Barrio Centro #789', 'carlos.lopez@email.com'),
('Ana', 'Martínez', '777-3456', 'Zona Sur #012', 'ana.martinez@email.com'),
('Luis', 'Rodríguez', '777-7890', 'Calle 5 #345', 'luis.rodriguez@email.com');

-- Insertar productos
INSERT INTO producto (nombre, descripcion, precio, categoria, disponible) VALUES
('Hamburguesa Clásica', 'Hamburguesa con carne, lechuga, tomate y queso', 25.00, 'Comida Rápida', true),
('Hamburguesa Doble', 'Hamburguesa doble con papas fritas', 35.00, 'Comida Rápida', true),
('Pizza Pepperoni', 'Pizza mediana con pepperoni', 60.00, 'Pizza', true),
('Pizza Hawaiana', 'Pizza mediana con piña y jamón', 65.00, 'Pizza', true),
('Salchipapa', 'Salchicha y papas fritas con salsa', 20.00, 'Comida Rápida', true),
('Pollo Broster', 'Pollo frito crujiente', 45.00, 'Pollo', true),
('Pollo Broster Combo', 'Pollo frito con papas y refresco', 60.00, 'Pollo', true),
('Refresco Coca-Cola', 'Lata de 355ml', 8.00, 'Bebidas', true),
('Refresco Sprite', 'Lata de 355ml', 8.00, 'Bebidas', true),
('Jugo de Naranja', 'Jugo natural de naranja', 12.00, 'Bebidas', true);

-- Insertar mesas
INSERT INTO mesa (numero_mesa, capacidad, estado) VALUES
(1, 2, 'LIBRE'),
(2, 4, 'LIBRE'),
(3, 4, 'OCUPADA'),
(4, 6, 'LIBRE'),
(5, 2, 'RESERVADA');

-- Insertar pedidos
INSERT INTO pedido (cliente_id, fecha_pedido, estado, total) VALUES
(1, CURRENT_TIMESTAMP, 'PENDIENTE', 53.00),
(2, CURRENT_TIMESTAMP - INTERVAL '1 hour', 'PREPARANDO', 120.00),
(3, CURRENT_TIMESTAMP - INTERVAL '2 hours', 'ENTREGADO', 45.00),
(4, CURRENT_TIMESTAMP - INTERVAL '3 hours', 'CANCELADO', 20.00),
(5, CURRENT_TIMESTAMP - INTERVAL '4 hours', 'ENTREGADO', 77.00);

-- Insertar detalles de pedido
INSERT INTO detalle_pedido (pedido_id, producto_id, cantidad, precio_unitario, subtotal) VALUES
-- Pedido 1
(1, 1, 1, 25.00, 25.00),
(1, 8, 1, 8.00, 8.00),
(1, 9, 1, 8.00, 8.00),
(1, 10, 1, 12.00, 12.00),
-- Pedido 2
(2, 3, 1, 60.00, 60.00),
(2, 4, 1, 65.00, 65.00),
-- Pedido 3
(3, 6, 1, 45.00, 45.00),
-- Pedido 4
(4, 5, 1, 20.00, 20.00),
-- Pedido 5
(5, 2, 1, 35.00, 35.00),
(5, 7, 1, 60.00, 60.00);