FROM gradle:6.6-jdk11 AS final
ARG ENV
WORKDIR /gradle
COPY . .
RUN gradle build --no-daemon
#
# FROM openjdk:11-jdk AS final
# WORKDIR /app
# COPY --from=build /gradle/dist/BaseSpringBoot*.jar /app/springbootapi.jar
# ENTRYPOINT ["java","-jar","springbootapi.jar"]