# Demo Spring Security Project

Este proyecto es una aplicación de ejemplo construida con **Spring Boot** y utiliza **Spring Security** para manejar autenticación y autorización. Está diseñado como una base para entender cómo integrar seguridad en servicios RESTful.

## 🔒 Seguridad con Spring Security

La configuración de seguridad en este proyecto incluye:

- **JWT (JSON Web Tokens)** para la autenticación sin estado.
- Filtros personalizados (`JwtAuthenticationFilter`, `JwtValidationFilter`) para generar y validar tokens.
- **Roles y permisos** administrados mediante entidades `Role` y `User`.
- Expresiones de seguridad personalizadas y control de acceso en los controladores.
- Protección de endpoints mediante anotaciones `@PreAuthorize` y configuraciones en la clase de seguridad.

Además, se integran validadores y servicios específicos para manejar la lógica de seguridad.

## 🚀 Características principales

1. **Autenticación de usuarios** con email y contraseña.
2. **Generación de tokens JWT** tras el login exitoso.
3. **Verificación de tokens** en cada solicitud entrante.
4. **Gestión de roles**: ADMIN, USER, etc. y asignación durante el registro.
5. Intercepción de excepciones de seguridad con un controlador global (`HandlerExceptionController`).

## 🛠 Estructura del proyecto

- `controllers/` - controladores REST para autenticación, usuarios, cursos, etc.
- `dto/` - objetos de transferencia de datos y respuestas API.
- `entities/` - modelos JPA incluyendo `User`, `Role`, `Course`, etc.
- `repositories/` - interfaces Spring Data JPA para acceso a datos.
- `security/` - filtros, configuraciones, utilidades y clases relacionadas a seguridad.
- `services/` - lógica de negocio, incluidos `AuthService`, `UserService`, etc.
- `validators/` - validaciones personalizadas para entidades y DTOs.

## 🧪 Pruebas

El proyecto incluye pruebas unitarias y de integración localizadas en `src/test/java`.

## 📄 Requisitos

- Java 17+
- Maven 3.8+
- Base de datos configurada en `application.properties` (por ejemplo H2, PostgreSQL, MySQL)

## 📦 Ejecución

```bash
./mvnw clean spring-boot:run
```

Posteriormente, las peticiones a los endpoints protegidos requieren el encabezado `Authorization: Bearer <token>`.

## 📚 Recursos adicionales

- [Documentación de Spring Security](https://spring.io/projects/spring-security)
- [JWT Specification](https://jwt.io/introduction)

---

Este README pretende servir de guía rápida y referencia para explorar la integración de Spring Security en una aplicación Spring Boot.
