CREATE TABLE IF NOT EXISTS administradores (
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS clientes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    dni VARCHAR(20) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    address VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS dispositivos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    type VARCHAR(50) NOT NULL,
    brand VARCHAR(50),
    model VARCHAR(50),
    serial_number VARCHAR(100) UNIQUE NOT NULL,
    description TEXT,
    password VARCHAR(100),
    image_url TEXT,
    client_id BIGINT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clientes(id)
);

CREATE TABLE IF NOT EXISTS empresas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20),
    email VARCHAR(100),
    iva_rate DECIMAL(5, 2),
    welcome_message TEXT,
    completion_message TEXT,
    admin_id BIGINT NOT NULL,
    FOREIGN KEY (admin_id) REFERENCES administradores(id)
);

CREATE TABLE IF NOT EXISTS ordenes_servicios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    entry_date DATETIME NOT NULL,
    status VARCHAR(20) DEFAULT 'CREATED',
    difficulty VARCHAR(20) DEFAULT 'MEDIUM',
    cost DECIMAL(10, 2),
    description TEXT,
    device_id BIGINT NOT NULL,
    admin_id BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    FOREIGN KEY (device_id) REFERENCES dispositivos(id),
    FOREIGN KEY (admin_id) REFERENCES administradores(id),
    FOREIGN KEY (client_id) REFERENCES clientes(id)
);

CREATE TABLE IF NOT EXISTS diagnosticos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    service_order_id BIGINT NOT NULL,
    description TEXT,
    final_cost DECIMAL(10, 2),
    admin_id BIGINT NOT NULL,
    FOREIGN KEY (service_order_id) REFERENCES ordenes_servicios(id),
    FOREIGN KEY (admin_id) REFERENCES administradores(id)
);

CREATE TABLE IF NOT EXISTS citas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    client_id BIGINT NOT NULL,
    admin_id BIGINT NOT NULL,
    scheduled_at DATETIME,
    description TEXT,
    FOREIGN KEY (client_id) REFERENCES clientes(id),
    FOREIGN KEY (admin_id) REFERENCES administradores(id)
);