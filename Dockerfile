FROM openjdk:11.0-jdk as build-image
RUN mkdir -p /app/source
WORKDIR /app/source
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src
COPY . /app/source
RUN ./mvnw clean package

FROM openjdk:11.0-jre
COPY --from=build-image /app/source/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]