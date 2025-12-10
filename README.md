🏥 VOLL MED – API REST

API REST desarrollada en Java + Spring Boot 3 para la gestión integral de médicos, pacientes y consultas médicas.
Incluye arquitectura limpia, DTOs, validaciones modulares, reglas de negocio encapsuladas y documentación interactiva con Swagger (SpringDoc OpenAPI).

🚀 Tecnologías utilizadas

Java 17

Spring Boot 3.x

Spring Web

Spring Data JPA / Hibernate

Jakarta Validation

MySQL

SpringDoc OpenAPI (Swagger UI)

Maven

Lombok (opcional)

📌 Funcionalidades principales
✔ Gestión de Médicos

Registrar médico (POST)

Listar con paginación (GET)

Actualizar médico (PUT)

Eliminación lógica (DELETE)

✔ Gestión de Pacientes

CRUD completo siguiendo el mismo patrón de médicos

✔ Gestión de Consultas

Reservar consulta con validaciones

Cancelar consulta aplicando reglas de negocio

Sistema extensible basado en interfaces y validadores

📁 Arquitectura del proyecto
src/main/java/med/voll/api/
    ├── controller/
    │     ├── MedicoController.java
    │     ├── PacienteController.java
    │     └── ConsultaController.java
    │
    ├── domain/
    │     ├── consulta/
    │     │     ├── Consulta.java
    │     │     ├── DatosReservaConsulta.java
    │     │     ├── DatosCancelamientoConsulta.java
    │     │     ├── ReservaDeConsultas.java
    │     │     └── validaciones/
    │     │           ├── reserva/
    │     │           └── cancelamiento/
    │     │
    │     ├── medico/
    │     └── paciente/
    │
    ├── infra/
    │     └── errors/ (manejador de excepciones)
    │
    └── VollMedApiApplication.java
```

🧠 Sistema de Validaciones
🔹 Validaciones al reservar consulta

Todos los validadores implementan:
```
public interface ValidadorDeConsultas {
    void validar(DatosReservaConsulta datos);
}
```

Y se ejecutan automáticamente:
```
@Autowired
private List<ValidadorDeConsultas> validadores;
```
🔹 Validaciones al cancelar consulta

INTERFAZ:
```
public interface ValidadorDeCancelamiento {
    void validar(DatosCancelamientoConsulta datos);
}
```

EJEMPLO:
```
@Component
public class ValidadorCancelamientoHorario implements ValidadorDeCancelamiento {
    @Override
    public void validar(DatosCancelamientoConsulta datos) {
        // lógica
    }
}
```

AUTO-INYECCIÓN:
```
@Autowired
private List<ValidadorDeCancelamiento> validadoresCancelamiento;
```
📚 Documentación interactiva – Swagger UI

Gracias a SpringDoc, la API expone su documentación en:

📄 Swagger UI:
```
http://localhost:8080/swagger-ui/index.html
```

📄 OpenAPI JSON:
```
http://localhost:8080/v3/api-docs
```

Dependencia incluida:
```
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```

🧪 Endpoints principales
🩺 Médicos
Registrar médico
```
POST /medicos
```

Listar médicos
```
GET /medicos
```

Actualizar médico
```
PUT /medicos
```

Eliminar médico
```
DELETE /medicos/{id}
```
👥 Pacientes

CRUD similar al de médicos.

📅 Consultas
Reservar consulta
```
POST /consultas
```

Ejemplo:

{
  "idPaciente": 1,
  "idMedico": null,
  "fecha": "2025-02-25T10:00:00",
  "especialidad": "CARDIOLOGIA"
}

Cancelar consulta
```
DELETE /consultas
```

Ejemplo:
```
{
  "idConsulta": 12,
  "motivo": "PACIENTE_DESISTIO"
}
```
🔧 Configuración — MySQL

Base de datos:
```
CREATE DATABASE vollmed_api;
```

application.properties:
```
spring.datasource.url=jdbc:mysql://localhost:3306/vollmed_api
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
🐳 Docker (opcional)

Dockerfile:

```
FROM eclipse-temurin:17-jdk
COPY target/vollmed-api.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

Build:
```
mvn clean package
docker build -t vollmed-api .
docker run -p 8080:8080 vollmed-api
````
▶ Ejecución

Compilar:
```
mvn clean install
```

Ejecutar:

```
mvn spring-boot:run
```
🙌 Autor

Jorge Gustavo Martinez
Analista Programador – Backend / Fullstack
Tecnologías: Java, Spring Boot, PHP/Laravel, Python, SQL, Docker
