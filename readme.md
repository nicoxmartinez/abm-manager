# ABM Manager API REST

API REST desarrollada para la gestión y control de un sistema de ventas.

---

## Tecnologías y Herramientas

* **Java:** 21 (OpenJDK)
* **Framework:** Spring Boot 3.x / 4.x
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** PostgreSQL (v15 Alpine)
* **Contenedorización:** Docker
* **Gestor de Dependencias:** Maven

---

## ⚙️ Configurar y Levantar la Base de Datos con Docker

Incluye un Dockerfile personalizado para levantar PostgreSQL de manera aislada ubicado en la raíz del proyecto.

###### comando para construir la imagen localmente:
```bash
docker build -t postgres-sales .
```