# Dockerfile (en la raíz de tu proyecto principal)

# 1. IMAGEN BASE: Usamos la versión slim de Java 21 JRE de Zulu
FROM azul/zulu-openjdk-centos:21-jre

# 2. DEFINIR VOLÚMENES Y METADATOS
VOLUME /tmp

# 3. ARGUMENTO DE COMPILACIÓN
# ⭐ CLAVE: Reemplaza 'nombre-de-tu-artefacto.jar' por el nombre real de tu JAR
# Ejemplo si usas el módulo blog-base:
ARG JAR_FILE=blog-base-0.0.1-SNAPSHOT.jar
# Si el JAR ejecutable está en la carpeta target de la raíz, podría ser:
# ARG JAR_FILE=target/KapitanKernel.jar

# 4. COPIAR EL JAR AL CONTENEDOR
COPY ${JAR_FILE} app.jar

# 5. EXPOSICIÓN DEL PUERTO
EXPOSE 8080

# 6. COMANDO DE EJECUCIÓN
# Comando de arranque para la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app.jar"]
