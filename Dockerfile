FROM gradle:8-jdk21 as build
WORKDIR /build
COPY . .
RUN gradle clean build

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /build/app/build/libs/app.jar .
ENTRYPOINT [ "java" ]
CMD [ "-jar", "/app/app.jar" ]
