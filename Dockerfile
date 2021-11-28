FROM gradle:7.3.0-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon -x test
# use the next line(s) to debug the layout inside the build step of assemble process
RUN ls -Fahl
RUN ls build/libs -Fahl

#FROM openjdk:17.0.1-jdk-slim
FROM openjdk:17-alpine3.14
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/
COPY --from=build /home/gradle/src/build/libs/*.sh /app/
WORKDIR /app
ENTRYPOINT ["/bin/sh", "alternate-entrypoint.sh"]
#ENTRYPOINT ["java", "-jar","/app/fleet_vehicles_mgr-0.0.1-SNAPSHOT.jar", "io.fleet_vehicles_mgr.FleetVehiclesMgrApplication", "$@"]
