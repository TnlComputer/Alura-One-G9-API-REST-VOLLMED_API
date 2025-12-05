# 🏥 VOLL MED – API REST

API REST desarrollada en **Java + Spring Boot** para la gestión de médicos, pacientes y turnos médicos.
Proyecto basado en la arquitectura recomendada en el curso, con buenas prácticas, DTOs, validaciones y persistencia con MySQL.

---

## 🚀 Tecnologías utilizadas

* **Java 17**
* **Spring Boot 3.x**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate**
* **MySQL**
* **Lombok**
* **Validación con Jakarta Validation**
* **Maven**

---

## 📌 Funcionalidades principales

### ✔ Gestión de Médicos

* Registrar médico (POST)
* Listar médicos (GET)
* Actualizar médico (PUT)
* Eliminar (lógico) médico (DELETE)

### ✔ Gestión de Pacientes (si aplica)

* CRUD completo siguiendo el mismo patrón que Médicos

### ✔ Gestión de Turnos (si aplica)

* Registro de turnos con validaciones

---

## 📁 Estructura del Proyecto

```
src/main/java/com/vollmed/api/
    ├── controller/        # Controladores REST (MedicoController, etc.)
    ├── domain/
    │     ├── medico/      # Entidad, DTOs y repositorio
    │     ├── paciente/
    │     └── turno/
    ├── infra/             # Manejo de errores, seguridad, etc.
    └── VollmedApiApplication.java
```

---

## 🔧 Configuración

### 1. Base de datos MySQL

Crear una BD:

```sql
CREATE DATABASE vollmed;
```

En `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/vollmed
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🧪 Endpoints principales

### ➤ Registrar médico

```
POST /medicos
Content-Type: application/json
```

```json
{
  "nombre": "Jorge Martinez",
  "email": "jorge@test.com",
  "telefono": "123456",
  "documento": "44556677",
  "especialidad": "CARDIOLOGIA"
}
```

### ➤ Listar médicos

```
GET /medicos
```

### ➤ Actualizar médico

```
PUT /medicos
Content-Type: application/json
```

### ➤ Eliminar médico

```
DELETE /medicos/{id}
```

---

## 🧱 Arquitectura aplicada

El proyecto aplica:

* ✔ **DTOs de Entrada (Request) y Salida (Response)**
* ✔ **Validación con @Valid**
* ✔ **Mappers implícitos mediante constructores**
* ✔ **Soft Delete** (no se borra el registro, solo se marca como inactivo)
* ✔ **Registro del servidor en puerto configurado**
* ✔ Control de errores usando `@RestControllerAdvice`

---

## 🐳 Docker (opcional)

Archivo `Dockerfile`:

```dockerfile
FROM eclipse-temurin:17-jdk
COPY target/vollmed-api.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
```

---

## ▶ Ejecución

Compilar:

```
mvn clean install
```

Ejecutar:

```
mvn spring-boot:run
```

---

## 🙌 Autor

**Jorge Gustavo Martinez**
Analista Programador – Backend / Fullstack
Tecnologías: Java, Spring Boot, PHP/Laravel, Python, SQL, Docker

