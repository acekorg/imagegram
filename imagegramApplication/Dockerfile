FROM gcr.io/distroless/java11-debian11

ARG TAG
ARG JAR_VERSION

LABEL com.aleksandar.description="Imagegram application"
      com.aleksandar.tag=""${TAG}"

COPY "target/imagegramApplication-${JAR_VERSION}.jar" /workdir/app.jar

ENTRYPOINT ["java", "-jar", "/workdir/app.jar"]