-- Borrado previo para evitar duplicados en desarrollo
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE diagnosticos;
TRUNCATE TABLE citas;
TRUNCATE TABLE ordenes_servicios;
TRUNCATE TABLE dispositivos;
TRUNCATE TABLE empresas;
TRUNCATE TABLE clientes;
TRUNCATE TABLE administradores;

SET FOREIGN_KEY_CHECKS = 1;

-- Administradores
INSERT INTO administradores (username, password_hash, name) VALUES
('admin1', 'hash1', 'Carlos Méndez'),
('admin2', 'hash2', 'Lucía Torres'),
('admin3', 'hash3', 'Pedro Salas');

-- Clientes
INSERT INTO clientes (name, dni, phone, email, address) VALUES
('Juan Pérez', '12345678A', '600123456', 'juan.perez@example.com', 'Calle Falsa 123'),
('Ana Gómez', '87654321B', '699987654', 'ana.gomez@example.com', 'Avenida Siempre Viva 742');

-- Dispositivos
INSERT INTO dispositivos (type, brand, model, serial_number, description, password, image_url, client_id) VALUES
('Laptop', 'Dell', 'XPS 13', 'SN1234', 'Pantalla rota', '1234', 'http://example.com/img1.jpg', 1),
('Teléfono', 'Samsung', 'Galaxy S21', 'SN5678', 'No enciende', '0000', 'http://example.com/img2.jpg', 2),
('Tablet', 'Apple', 'iPad Air', 'SN91011', 'No carga', NULL, 'http://example.com/img3.jpg', 2);

-- Empresas
INSERT INTO empresas (name, address, phone, email, iva_rate, welcome_message, completion_message, admin_id) VALUES
('Informática Total', 'Calle Real 45', '910000111', 'contacto@informaticatotal.com', 21.00, 'Gracias por confiar en nosotros', 'Reparación completada con éxito', 1),
('TechMóvil Express', 'Paseo Central 77', '920000222', 'soporte@techmovil.com', 18.00, 'Bienvenido a TechMóvil', 'Gracias por su visita', 2);

-- Órdenes de servicio (4) → ¡añadimos el client_id!
INSERT INTO ordenes_servicios (entry_date, status, difficulty, cost, description, device_id, admin_id, client_id) VALUES
('2025-05-01 10:00:00', 'CREATED', 'MEDIUM', 150.00, 'Cambio de pantalla y limpieza interna', 1, 1, 1),
('2025-05-03 11:30:00', 'IN_PROGRESS', 'HARD', 200.00, 'Sustitución de placa base', 2, 2, 2),
('2025-05-04 09:45:00', 'CREATED', 'EASY', 100.00, 'Reemplazo de batería', 3, 3, 2),
('2025-05-06 12:00:00', 'COMPLETED', 'HARD', 170.00, 'Reinstalación de sistema operativo', 2, 1, 2);

-- Diagnósticos
INSERT INTO diagnosticos (service_order_id, description, final_cost, admin_id) VALUES
(1, 'Pantalla nueva instalada correctamente', 180.00, 1),
(2, 'Nueva placa instalada, funcionando', 240.00, 2),
(3, 'Batería reemplazada', 110.00, 3),
(4, 'Sistema operativo reinstalado y actualizado', 130.00, 1);

-- Citas
INSERT INTO citas (client_id, admin_id, scheduled_at, description) VALUES
(1, 1, '2025-05-05 09:00:00', 'Diagnóstico de portátil'),
(2, 2, '2025-05-06 15:00:00', 'Revisión de móvil'),
(1, 1, '2025-05-08 11:00:00', 'Entrega de dispositivo');
