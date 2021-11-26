package io.fleet_vehicles_mgr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
//@EnableMongock
@EnableMongoRepositories("io.fleet_vehicles_mgr.repository")
public class DatabaseConfiguration {
}
