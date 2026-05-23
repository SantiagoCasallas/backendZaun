# Backend Zaun

Backend REST desarrollado con **Java**, **Spring Boot** y **PostgreSQL** para la gestión de usuarios, amigos, grupos, mensajes, contenidos, ubicaciones y propiedades.

---

## Tecnologías utilizadas

- Java 21 o superior
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven Wrapper

---

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- Git
- JDK 21 o superior
- PostgreSQL
- Postman, opcional para probar endpoints

Verifica Java:

```bash
java -version
```

Debe aparecer Java 21 o superior.

---

## Clonar el repositorio

```bash
git clone https://github.com/SantiagoCasallas/backendZaun.git
cd backendZaun
```

---

## Configurar PostgreSQL

Crea una base de datos local en PostgreSQL:

```sql
CREATE DATABASE socialdb;
```

Luego ejecuta el script SQL del proyecto sobre esa base de datos.

Desde consola:

```bash
psql -U postgres -d socialdb -f script.sql
```

En Windows PowerShell, si el script está en una ruta específica:

```powershell
psql -U postgres -d socialdb -f "C:\ruta\script.sql"
```

También puedes ejecutarlo desde **pgAdmin**:

1. Crear la base de datos `socialdb`.
2. Abrir la base de datos.
3. Entrar a **Query Tool**.
4. Pegar el script SQL.
5. Ejecutar.

---

## Configurar variables de entorno

El proyecto usa variables de entorno para conectarse a PostgreSQL:

- `URL`
- `USER_NAME`
- `PASSWORD`

### Git Bash

```bash
export URL="jdbc:postgresql://localhost:5432/socialdb"
export USER_NAME="postgres"
export PASSWORD="tu_password"
```

### PowerShell

```powershell
$env:URL="jdbc:postgresql://localhost:5432/socialdb"
$env:USER_NAME="postgres"
$env:PASSWORD="tu_password"
```

Reemplaza `tu_password` por la contraseña real de tu usuario de PostgreSQL.

---

## Configuración principal

El archivo de configuración está en:

```txt
src/main/resources/application.properties
```

Configuración esperada:

```properties
spring.datasource.url=${URL:jdbc:postgresql://localhost:5432/socialdb}
spring.datasource.username=${USER_NAME:postgres}
spring.datasource.password=${PASSWORD:postgres}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=none

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

> Nota: `ddl-auto=none` se usa porque la base de datos se crea desde el script SQL, no desde Hibernate.

---

## Ejecutar el proyecto

### En Git Bash o Linux/Mac

```bash
./mvnw clean spring-boot:run
```

### En Windows PowerShell

```powershell
.\mvnw.cmd clean spring-boot:run
```

Si todo está bien, el backend se levantará en:

```txt
http://localhost:8080
```

---

## Cambiar el puerto

Si el puerto `8080` está ocupado, puedes cambiarlo agregando esta línea en `application.properties`:

```properties
server.port=8081
```

Luego el backend quedará disponible en:

```txt
http://localhost:8081
```

También puedes ejecutarlo temporalmente en otro puerto:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

En PowerShell:

```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

---

## Verificar conexión con la base de datos

Primero verifica que PostgreSQL responde:

```bash
psql -U postgres -d socialdb
```

Dentro de PostgreSQL:

```sql
\dt
```

Deberías ver tablas como:

```txt
usuario
ubicacion
mensaje
contenido
amigo
grupo
propiedad
```

También puedes consultar usuarios:

```sql
SELECT * FROM usuario;
```

---

## Probar el backend

Con el backend corriendo, prueba en navegador o Postman:

```http
GET http://localhost:8080/api/usuarios
```

Si cambiaste el puerto:

```http
GET http://localhost:8081/api/usuarios
```

También puedes usar `curl`:

```bash
curl http://localhost:8080/api/usuarios
```

---

## Crear un usuario desde Postman

Método:

```http
POST
```

URL:

```txt
http://localhost:8080/api/usuarios
```

Headers:

```txt
Content-Type: application/json
```

Body:

```json
{
  "usuConsecUser": null,
  "codUbica": "57",
  "nombre": "Santiago",
  "apellido": "Casallas",
  "username": "santi",
  "fechaRegistro": "2025-06-26",
  "email": "santiago@test.com",
  "celular": "3001234567",
  "imageUser": null,
  "temaUser": null,
  "huellaUser": null
}
```

No es necesario enviar `consecUser`, ya que la base de datos puede generarlo automáticamente.

---

## Probar login

Endpoint:

```http
POST http://localhost:8080/api/usuarios/login
```

Body:

```json
{
  "email": "santiago@test.com",
  "celular": "3001234567"
}
```

---

## Probar mensajes

Endpoint:

```http
POST http://localhost:8080/api/mensajes
```

Body de ejemplo:

```json
{
  "consecUser": "1",
  "usuConsecUser": "12",
  "codGrupo": null,
  "idTipoContenido": "1",
  "idTipoArchivo": null,
  "localizaContenido": "Hola desde PostgreSQL"
}
```

---

## Errores comunes

### Error: `JAVA_HOME environment variable is not defined correctly`

Debes configurar `JAVA_HOME`.

En Git Bash:

```bash
export JAVA_HOME="/c/Program Files/Java/jdk-25"
export PATH="$JAVA_HOME/bin:$PATH"
```

En PowerShell:

```powershell
$env:JAVA_HOME="C:\Program Files\Java\jdk-25"
$env:Path="$env:JAVA_HOME\bin;$env:Path"
```

Verifica:

```bash
java -version
```

---

### Error: `Port 8080 was already in use`

El puerto 8080 está ocupado.

Puedes cerrar el proceso:

```powershell
netstat -ano | findstr :8080
taskkill /PID NUMERO_DEL_PID /F
```

O cambiar el puerto en `application.properties`:

```properties
server.port=8081
```

---

### Error: `password authentication failed`

La contraseña de PostgreSQL es incorrecta.

Revisa la variable:

```bash
echo $PASSWORD
```

O en PowerShell:

```powershell
echo $env:PASSWORD
```

---

### Error: `database "socialdb" does not exist`

La base de datos no existe.

Créala:

```sql
CREATE DATABASE socialdb;
```

---

### Error: `relation "usuario" does not exist`

La conexión funciona, pero no se ejecutó el script SQL en esa base de datos.

Ejecuta el script nuevamente:

```bash
psql -U postgres -d socialdb -f script.sql
```

---

## Comandos útiles de Git

Ver estado del repositorio:

```bash
git status
```

Agregar cambios:

```bash
git add .
```

Crear commit:

```bash
git commit -m "Update backend configuration"
```

Subir cambios:

```bash
git push origin main
```

Actualizar el repositorio local:

```bash
git pull origin main
```

---

## Estructura general del proyecto

```txt
src/main/java/com/social/backend
│
├── controller
├── dto
├── model
├── repository
├── service
└── BackendApplication.java
```

### Flujo general

```txt
Cliente / Postman
      ↓
Controller
      ↓
Service
      ↓
Repository
      ↓
PostgreSQL
```

---

## Notas importantes

- No subas contraseñas reales al repositorio.
- Usa variables de entorno para configurar la conexión a la base de datos.
- La base de datos debe estar creada antes de ejecutar el backend.
- El script SQL debe ejecutarse antes de probar los endpoints.
- El backend corre por defecto en el puerto `8080`.
