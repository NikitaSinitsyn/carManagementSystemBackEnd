package com.carManagment.carManagment.Mappers;

import com.carManagment.carManagment.Dto.Repair.RepairDTO;
import com.carManagment.carManagment.Entitys.Car;
import com.carManagment.carManagment.Entitys.Repair;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RepairMapper {

    @Mapping(target = "position", source = "car.employee.position")
    @Mapping(target = "name", source = "car.employee.name")
    @Mapping(target = "carNumber", source = "car.number")
    @Mapping(target = "carId", source = "car.id")
    RepairDTO toDto(Repair repair);


    @Mapping(target = "car", source = "carId")
    Repair toEntity(RepairDTO repairDTO);

    default Long mapCarToCarId(Car car) {
        return car != null ? car.getId() : null;
    }
    default Car map(Long value) {
        if (value == null) {
            return null;
        }
        Car car = new Car();
        car.setId(value);
        return car;
    }
}