FROM eclipse-temurin:26-jre-ubi10-minimal AS builder

WORKDIR /builder
COPY target/chat*.jar chat.jar

RUN java -Djarmode=tools -jar chat.jar extract --layers --destination extracted


# Build the actual image using builder from above
FROM eclipse-temurin:26-jre-ubi10-minimal

RUN groupadd app && adduser -g app app

WORKDIR /app
RUN chown app:app /app
USER app

COPY --from=builder /builder/extracted/dependencies/ ./
COPY --from=builder /builder/extracted/spring-boot-loader/ ./
COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder /builder/extracted/application/ ./

COPY src/main/resources/tls/truststore.jks ./tls/truststore.jks

ENTRYPOINT ["java", \
    "-Djavax.net.ssl.trustStore=/app/tls/truststore.jks", \
    "-Djavax.net.ssl.trustStorePassword=", \
    "-jar", "chat.jar"]
