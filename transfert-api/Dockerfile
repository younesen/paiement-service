# Utiliser l'image officielle de Java 17
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR généré dans le conteneur
COPY target/transfert-api-0.0.1-SNAPSHOT.jar app.jar


# Exposer le port utilisé par Spring Boot
EXPOSE 9090

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
