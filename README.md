
# Proyecto RepDis

Este proyecto sigue una **arquitectura hexagonal (ports & adapters)** adaptada a la gestión de talleres técnicos, permitiendo registrar clientes, dispositivos, órdenes de servicio, técnicos, diagnósticos, y más.

## 🧱 Estructura del Proyecto

```bash
repdis/
├── application/
│   └── src/main/java/
│           ├── pom.xml
│           ├── domain/                       # Entidades de dominio (modelos, enums)
│           ├── com/repdis/application/
│           │   ├── services/                 # Casos de uso
│           │   ├── ports/
│           │   │   ├── driving/              # Interfaces que expone el dominio
│           │   │   └── driven/               # Interfaces que consume el dominio
│           │   └── exceptions/               # Excepciones del dominio
│
├── boot/
│   ├── pom.xml
│   └── src/main/java/
│       ├── com/repdis/application/
│       │    ├── Application.java             # Clase principal que arranca la aplicación
│       │    └── resources/                   # application.properties y configuración general
│
├── driven/
│   ├── pom.xml
│   └── mysqlrepository/
│       │── src/main/java/
│       │    │── com/repdis/driven
│       │    │    ├── adapters/               # Implementaciones de puertos driven
│       │    │    ├── config/                 # Configuración de persistencia
│       │    │    ├── entities/               # Entidades JPA
│       │    │    ├── mappers/                # Conversión entre dominio y JPA
│       │    │    └── repositories/           # Interfaces JPA
│
├── driving/
│   ├── pom.xml
│   └── apirest/
│       │── src/main/java/
│       │    │── com/repdis/driving
│       │    │     ├── adapters/              # Controladores REST
│       │    │     ├── dto/                   # Objetos de transferencia de datos
│       │    │     ├── error/                 # Gestión de excepciones HTTP
│       │    │     └── mappers/               # Conversión entre DTO y dominio
│       └── support/                          # Documentación, Postman, Swagger...
│
├── docker/                                   # Por ahora esta carpeta no sirve para nada hasta que no tenga docker
│   ├── sql/                                  # Scripts de schema y data para inicializar MySQL
│   ├── start-dev.sh                          # Script para levantar entorno local
│   └── docker-compose.yml                    # Define los servicios MySQL y Adminer
│ 
├── frontend/                                 # Frontend de la página web
│       ├── index.html                        # Página principal
│       ├── css/                              # Estilos
│       └── js/                               # Lógica JS para consumir el backend
```

---

## 📌 Principios de diseño

- El **módulo `application`** contiene la lógica de negocio pura y desacoplada.
- Los **puertos (driving y driven)** definen interfaces para entrada/salida del sistema.
- `driven` implementa los puertos para acceder a infraestructura (DB).
- `driving` adapta el acceso REST (controladores, DTOs, etc).
- `boot` contiene la clase principal de arranque y configuración.

---

## ✅ Convención de mensajes de commit

Se sigue el estándar **[Conventional Commits](https://www.conventionalcommits.org/)**:

- `feat:` Nueva funcionalidad
- `fix:` Corrección de bug
- `docs:` Documentación
- `chore:` Tareas internas, estructura, etc.
- `test:` Test unitarios 

---

## 🚀 GitFlow simplificado

- `main`: versión estable
- `develop`: integración de nuevas features
- `feature/ID-descripcion`: ramas de desarrollo de funcionalidades
- `hotfix/ID-descripcion`: parches urgentes
