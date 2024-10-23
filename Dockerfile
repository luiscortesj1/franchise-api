# Usa la imagen de Maven para construir la aplicación
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y el directorio src para que Maven pueda resolver dependencias
COPY pom.xml .
COPY src ./src

# Compila la aplicación y empaqueta el JAR
RUN mvn clean package -DskipTests

# Usa la imagen de Java para ejecutar la aplicación
FROM eclipse-temurin:17-jdk

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR del contenedor de construcción
COPY --from=build /app/target/franchiseapi-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto que la aplicación usará
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
