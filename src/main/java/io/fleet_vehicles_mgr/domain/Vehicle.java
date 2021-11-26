package io.fleet_vehicles_mgr.domain;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "The Fleet domain entities.")
@Document(collection = "vehicles")
public class Vehicle {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Field("name")
    String name;

    @Field("vin")
    String VIN;

    @Indexed
    @Field("license_plate_number")
    String licensePlateNumber;

    @Field("color")
    String color;

    @Field("number_of_doors")
    Integer numberOfDoors;

    @Field("number_of_wheels")
    Integer numberOfWheels;

}
