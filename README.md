# 🚀 KAPITAN KERNEL - Plataforma Modular de E-commerce y CMS (Spring Boot)

![Logo de Kapitan Kernel (Si existe, si no, usa un icono relevante)](./docs/logo.png)

Kapitan Kernel es un proyecto de software empresarial desarrollado en **Java Spring Boot 3.x** que unifica un sistema de gestión de contenido (**CMS** / Blog técnico) con una futura plataforma de **Comercio Electrónico (E-commerce)**. El objetivo es crear un negocio digital escalable, con énfasis en la automatización de contenidos para **SEO/Marketing Digital** y una seguridad robusta.

---

## 💻 Stack Tecnológico Clave

| Categoría | Tecnología | Notas Importantes |
| :--- | :--- | :--- |
| **Backend Core** | **Java Zulu 21**, **Spring Boot 3.x** | Versión LTS de Java y framework principal. |
| **Persistencia** | **Spring Data JPA**, Hibernate | Diseño de Base de Datos Relacional y Mapeo de Entidades. |
| **Web Frontend** | **Thymeleaf** | Motor de plantillas para renderizado del panel de administración. |
| **Seguridad** | **Spring Security** | Implementación de estrategia **Híbrida (JWT + Sesiones)**. |
| **DevOps** | **Docker**, **Maven** | Contenerización del proyecto para despliegue en VPS (Debian 12). |
| **Futuro/IA** | GNews API, Google AI API (Gemini) | Integración de servicios de noticias y funciones de generación de contenido asistida por IA. |

---

## 🏛️ Arquitectura y Diseño

El proyecto utiliza una arquitectura de **Monolito Modular** estructurado con **Módulos Maven** para garantizar la **Separación de Responsabilidades (SoC)** y la escalabilidad.
---

## 💾 Diseño de la Persistencia (MySQL / JPA)

El proyecto se sustenta sobre una Base de Datos relacional, cuyo diseño de esquema demuestra la complejidad y las relaciones de los diferentes módulos (Blog, Tienda, Noticias). Las entidades **JPA** (`@Entity`) mapean directamente este esquema, centralizado en el `moduloEntidades`.

* **Destaca:** Mapeo de la tabla `noticias_externas` para el API de GNews, las tablas `usuarios`/`tipos_usuarios` para **Spring Security (RBAC)**, y la estructura de `productos`/`pedidos` para el futuro **E-commerce**.

![Diagrama de Entidad-Relación (MySQL)](../KapitanKernel/docs/mysql_tablas.png)

---

## 📸 Evidencia Visual: Lógica de Negocio y Seguridad

Para validar la solidez del sistema y el alcance del proyecto **MVP (Blog)**, se muestran capturas clave del **Panel de Administración** (acceso protegido por Spring Security).

### A. Core del CMS: Gestión de Entradas y SEO
Se muestra la capacidad de crear y gestionar entradas del blog, incluyendo la `Meta Descripción (SEO)` y la selección de `Categorías`, confirmando el enfoque en el **Marketing Digital**.

![Gestión de Entradas del Blog (Posts) con Meta Descripción SEO](../KapitanKernel/docs/Captura%20de%20pantalla%202025-11-16%20203025.png)

### B. Gestión Avanzada de Usuarios y Roles (Spring Security)
Esta funcionalidad valida la implementación del **Control de Acceso Basado en Roles (RBAC)** y el uso de **Thymeleaf** para el frontend de administración.

![Gestión de Tipos de Usuario (Roles)](./docs/Captura%20de%20pantalla%202025-11-16%20202702.png)

### C. Automatización de Contenido (Módulo AI Contenido)
La interfaz para configurar la **Búsqueda Automática de Noticias** demuestra el manejo de tareas programadas y la lógica de búsqueda con **Palabras Clave Booleanas** (`OR`), un pilar de la integración API.

![Panel de Administración de Búsquedas IA con Configuración Programada](./docs/Captura%20de%20pantalla%202025-11-16%20203112.png)

---

---
### Módulos Principales:
1.  **`blog-base`**: Módulo principal del CMS (funcionalidad base completada - MVP).
2.  **`moduloEntidades`**: Define los modelos de datos **JPA** compartidos.
3.  **`moduloAiContenido`**: Gestión de la **sincronización de noticias programada** y la futura integración con APIs de IA.
4.  **`moduloTienda` / `moduloFinanzas`**: Módulos planificados para E-commerce, gestión de pedidos y pasarelas de pago.

---

## 🔒 Características de Seguridad

Se implementó una configuración avanzada de **Spring Security** con una **Doble Cadena de Filtros** para atender a diferentes clientes:

| Cliente | Estrategia de Autenticación | Detalles Técnicos |
| :--- | :--- | :--- |
| **API RESTful** (`/api/**`) | **Stateless** | Utiliza **JSON Web Tokens (JWT)** para ser consumida por aplicaciones móviles o SPA. |
| **Web Frontend** (`/admin/**`) | **Stateful** | Autenticación basada en **Sesiones HTTP** y Control de Acceso basado en Roles (**RBAC**). |

* **Cifrado**: Todas las contraseñas se almacenan mediante **BCryptPasswordEncoder**.
* **Diseño Limpio**: Uso de **Inyección de Dependencias (DI) por Constructor** para asegurar la inmutabilidad y facilitar los *testing*.

---

## ⚙️ Configuración y Ejecución

### Requisitos Previos
* [JDK 21](https://www.oracle.com/java/technologies/downloads/) (Zulu, OpenJDK, etc.)
* Docker (Opcional, para el despliegue)

### 1. Ejecución con Maven (Desarrollo)
```bash
# Navega al directorio raíz del proyecto
mvn clean install
cd blog-base
mvn spring-boot:run

Categoría,Tecnología,Notas Importantes
Backend Core,"Java Zulu 21, Spring Boot 3.x",Versión LTS de Java y framework principal.
Persistencia,"Spring Data JPA, Hibernate",Diseño de Base de Datos Relacional y Mapeo de Entidades.
Web Frontend,Thymeleaf,Motor de plantillas para renderizado del panel de administración.
Seguridad,Spring Security,Implementación de estrategia Híbrida (JWT + Sesiones).
DevOps,"Docker, Maven",Contenerización del proyecto para despliegue en VPS (Debian 12).
Futuro/IA,"GNews API, Google AI API (Gemini)",Integración de servicios de noticias y funciones de generación de contenido asistida por IA.

Cliente,Estrategia de Autenticación,Detalles Técnicos
API RESTful (/api/**),Stateless,Utiliza JSON Web Tokens (JWT) para ser consumida por aplicaciones móviles o SPA.
Web Frontend (/admin/**),Stateful,Autenticación basada en Sesiones HTTP y Control de Acceso basado en Roles (RBAC).

Ruta,Controlador,Seguridad
/,MainControlador,Pública
/login,MainControlador,Pública
/blog,BlogControlador,Pública (solo posts publicados)
/admin,MainControlador,Protegida por Sesión (.authenticated())
/admin/tipos-usuarios,MainControlador,Protegida por Sesión + Roles (RBAC)

Ruta,Controlador,Propósito,Seguridad
/api/auth/authenticate,AuthController,Emisión de JWT (Login de API).,Pública (.permitAll())
/api/usuarios/cambiar-password,UsuarioControlador,Permite que el usuario autenticado modifique su contraseña. Demuestra la inyección de contexto de JWT (Authentication authentication).,Protegida por JWT (.authenticated())
"/api/posts, /api/categorias",Varios Controladores,CRUD de recursos del CMS.,Protegida por JWT (.authenticated())
/api/v1/admin/busquedas/**,BusquedaController,Configuración de búsquedas automáticas de noticias (Automatización del negocio).,Protegida por JWT + Roles (RBAC)
