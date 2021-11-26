#!/bin/sh

echo "The application will wait for db at ${MONGO_HOST}:${MONGO_PORT}"
./wait-for-it ${MONGO_HOST}:${MONGO_PORT} -t ${MONGO_WAIT_TIMEOUT}
java -jar fleet_vehicles_mgr-0.0.1-SNAPSHOT.jar "io.fleet_vehicles_mgr.FleetVehiclesMgrApplication" "$@"