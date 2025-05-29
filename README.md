# Proyecto RepDis

Este proyecto sigue una **arquitectura hexagonal (ports & adapters)** adaptada a la gestión de talleres técnicos, permitiendo registrar clientes, dispositivos, órdenes de servicio, técnicos, diagnósticos, y más.

## 🧱 Estructura del Proyecto

```bash
repdis/
├── application/
│   └── src/main/java/
│           ├── pom.xml
│           ├── domain/                       # Entidades de dominio (modelos, enums)
│           │   ├── enums/ 
│           ├── com/repdis/application/
│           │   ├── services/                 # Casos de uso
│           │   ├── ports/
│           │   │   ├── driving/              # Interfaces de entrada (controladores, CLI, etc.)
│           │   │   └── driven/               # Interfaces de salida (bases de datos, servicios externos)
│           │   └── exceptions/               # Excepciones del dominio
│
├── domain/
│   └── src/main/java/
│       ├── com/repdis/domain/                # Entidades puras del dominio (Client, Device, Order, etc.)
│       │   └── enums/                        # Enumeraciones como OrderStatus, Difficulty
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
│       │    │    ├── adapters/               # Implementación de los puertos de salida (driven)
│       │    │    ├── config/                 # Configuración de persistencia
│       │    │    ├── entities/               # Entidades JPA que reflejan la estructura de la base de datos
│       │    │    ├── mappers/                # Conversión entre entidades JPA y modelos del dominio
│       │    │    └── repositories/           # Interfaces que extienden JpaRepository
│
├── driving/
│   ├── pom.xml
│   └── apirest/
│       │── src/main/java/
│       │    │── com/repdis/driving
│       │           ├── adapters/            # Controladores REST (implementan puertos driving)
│       │           ├── dto/                 # Data Transfer Objects: simplifican la entrada/salida de datos
│       │           ├── error/               # Gestión de excepciones HTTP (handlers personalizados)
│       │           └── mappers/             # Mapeo entre DTOs y modelos del dominio
│       └── support/                         # Documentación, Postman, Swagger...
│
├── docker/                                  # Por ahora esta carpeta no sirve para nada hasta que no tenga docker
│   ├── sql/                                 # Scripts de schema y data para inicializar MySQL
│   ├── start-dev.sh                         # Script para levantar entorno local
│   └── docker-compose.yml                   # Define los servicios MySQL y Adminer
│ 
├── frontend/                                # Frontend de la página web
│       ├── index.html                       # Página principal
│       ├── css/                             # Estilos
│       └── js/                              # Lógica JS para consumir el backend
```

---

## Descripción por módulo

###  `application/`
Contiene los casos de uso del sistema, orquestando la lógica entre controladores y repositorios. Define los puertos que el dominio expone (`driving`) y consume (`driven`), permitiendo una alta independencia de la infraestructura. Es el corazón de la lógica de negocio.

###  `domain/`
Contiene las entidades del modelo de dominio (como `Client`, `Device`, `Order`) y sus comportamientos. Son objetos puros sin dependencias externas. También contiene `enums` como `OrderStatus` o `Difficulty`, que controlan el flujo de negocio.

###  `driven/`
Implementa los puertos de salida. Por ejemplo, una interfaz `OrderRepository` en `application` es implementada aquí usando JPA. Además, define:

- Las entidades JPA que se mapean a la base de datos.
- Mappers que transforman entre modelo JPA y dominio.
- Configuraciones técnicas (como nombres de tablas, relaciones, etc.)

###  `driving/`
Contiene los adaptadores de entrada como controladores REST, que transforman HTTP en lógica de negocio. Aquí se validan los datos de entrada (vía DTOs) y se gestiona el output de forma controlada. También gestiona los errores de forma centralizada para ofrecer respuestas HTTP claras.

###  `boot/`
Arranca la aplicación con Spring Boot. También carga el perfil activo (`dev`, `prod`, etc.) y conecta todos los módulos.

---

## Base de datos

- **Tipo:** MySQL
- **Persistencia:** Spring Data JPA
- **DDL auto:** `update`, permitiendo migraciones automáticas durante desarrollo

### Esquema:

- `clients(id, name, phone, dni, email, address)`
- `devices(id, type, brand, model, serial_number, client_id)`
- `orders(id, entry_date, status, difficulty, cost, client_id, device_id, admin_id)`
- `admins(id, name)`

---

## Comunicación frontend-backend

- **Frontend:** JavaScript puro
- **Backend:** Spring Boot (REST API)
- Comunicación mediante `fetch` con rutas `REST`:
  - CRUD completo (`GET`, `POST`, `PUT`, `DELETE`) para `/clients`, `/devices`, `/orders`
  - Rutas personalizadas: `/clients/by-phone`, `/devices/by-serial`
- Interacción dinámica:
  - Tablas renderizadas desde JS
  - Validación visual (`input.invalid`)
  - Validación de negocio (teléfono repetido, n.º serie, etc.)

---

## Convención de mensajes de commit

Se sigue el estándar **[Conventional Commits](https://www.conventionalcommits.org/)**:

- `feat:` Nueva funcionalidad
- `fix:` Corrección de bug
- `docs:` Documentación
- `chore:` Tareas internas, estructura, etc.
- `test:` Test unitarios 

---

## GitFlow mejorado

- `main`: Rama estable que refleja producción
- `develop`: Rama de integración donde se combinan nuevas funcionalidades
- `feature/REP-XXX-descripcion`: Para funcionalidades nuevas. Se basan en `develop`
- `hotfix/REP-XXX-urgente`: Para corregir errores críticos detectados en producción