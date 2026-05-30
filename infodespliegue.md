# Guía de Despliegue - Proyecto Food Fast

## 🚀 Configuración Automática con Script de Python (Recomendado)

Antes de cualquier cosa, usa el script `configurar_proyecto.py` para configurar automáticamente todas las versiones y credenciales!

1. **Ejecuta el script:**
   ```bash
   python configurar_proyecto.py
   ```
2. **Sigue las instrucciones en pantalla para:
   - Introducir credenciales de PostgreSQL
   - Seleccionar versión de Java (17, 20 o 21)
3. **El script actualizará automáticamente:**
   - `application.properties`
   - Todos los `pom.xml` con versiones compatibles
   - `Dockerfile` y `docker-compose.yml`

---

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalados los siguientes componentes:

- **JDK 17 o superior** (se probó con Java 21)
- **Maven 3.9+**
- **PostgreSQL** (si se despliega sin Docker)

## Configuración de la Base de Datos

El proyecto utiliza PostgreSQL como base de datos. Credenciales configuradas:

- Usuario: `postgres`
- Contraseña: `postgres`
- Nombre de la base de datos: `food_fast`
- URL: `jdbc:postgresql://localhost:5432/food_fast`

---

## Opción 1: Despliegue Local (Recomendado, probado y funcional)

Esta es la forma más sencilla y probada de desplegar el proyecto.

### Pasos:

1. **Asegúrate de que PostgreSQL esté ejecutándose localmente** y crea la base de datos `food_fast` si no existe.

2. **Navega a la carpeta raíz del proyecto y compila:**
   ```bash
   cd msafoodfast-main/msa_food_fast-main
   mvn clean install -DskipTests
   ```
   (Nota: El módulo `atencion-controller` fallará en el build final, pero los módulos necesarios sí se instalan correctamente)

3. **Navega a la carpeta del controlador y ejecuta el servidor Quarkus:**
   ```bash
   cd atencion-sl/atencion-controller
   mvn quarkus:dev "-Dnet.bytebuddy.experimental=true"
   ```
   (La propiedad `-Dnet.bytebuddy.experimental=true` es necesaria si usas Java 21)

4. **Accede a la aplicación:**
   - Swagger UI: http://localhost:8080/q/swagger-ui/
   - Health Check: http://localhost:8080/q/health

---

## Opción 2: Despliegue con Docker Compose

Esta opción levanta tanto la base de datos PostgreSQL como la aplicación Quarkus en contenedores Docker.

### Pasos:

1. **Asegúrate de que Docker Desktop esté ejecutándose correctamente.**

2. **Navega al directorio raíz del proyecto:**
   ```bash
   cd msafoodfast-main/msa_food_fast-main
   ```

3. **Levanta los servicios con Docker Compose:**
   ```bash
   docker-compose up -d --build
   ```

4. **Espera a que los servicios estén listos.** La aplicación se iniciará automáticamente después de que la base de datos esté disponible.

5. **Accede a la aplicación:**
   - Swagger UI: http://localhost:8080/q/swagger-ui/
   - Health Check: http://localhost:8080/q/health

### Comandos útiles de Docker:

- Ver logs de los servicios:
  ```bash
  docker-compose logs -f
  ```

- Detener los servicios:
  ```bash
  docker-compose down
  ```

- Detener y eliminar los volúmenes (borra los datos de la BD):
  ```bash
  docker-compose down -v
  ```

---

## Verificación del Despliegue

### Comprobaciones importantes:

1. **Base de Datos:** Asegúrate de que PostgreSQL esté ejecutándose en el puerto 5432
2. **Aplicación:** Verifica que la aplicación esté escuchando en el puerto 8080
3. **Swagger UI:** Accede a http://localhost:8080/q/swagger-ui/ para confirmar que la API está disponible

---

## Problemas Comunes y Soluciones

### Error: Lombok incompatible con Java 21
- **Solución:** Actualiza la versión de Lombok en los `pom.xml` a `1.18.34` o superior. (Ya está hecho)

### Error: Java 21 no compatible con Quarkus 2.16
- **Solución:** Usa la propiedad `-Dnet.bytebuddy.experimental=true` al ejecutar `mvn quarkus:dev`, o usa Java 17/20.

### Error: No se puede conectar a la base de datos
- Verifica que PostgreSQL esté ejecutándose
- Comprueba las credenciales en `application.properties`
- Asegúrate de que la base de datos `food_fast` exista

---

## Archivos de Configuración Importantes

- `atencion-sl/atencion-controller/src/main/resources/application.properties`: Configuración de la aplicación
- `docker-compose.yml`: Configuración de Docker Compose
- `Dockerfile`: Configuración de la imagen Docker
- `pom.xml`: Configuración principal de Maven y dependencias

---

## Cambios Realizados para el Despliegue Correcto:

1. **Actualizada versión de Lombok**: De `1.18.26/1.18.32` a `1.18.34` en `atencion-sl/pom.xml` y `atencion-sl/atencion-controller/pom.xml`
2. **Configurada contraseña de PostgreSQL**: `postgres` en `application.properties`
3. **Eliminadas dependencias duplicadas**: En `atencion-sl/atencion-controller/pom.xml`
4. **Mantenida compatibilidad con Java 21**: Usando la propiedad `-Dnet.bytebuddy.experimental=true`
