package com.carManagment.carManagment.Mappers;

import com.carManagment.carManagment.Dto.Casko.CASKODTO;
import com.carManagment.carManagment.Entitys.CASKO;
import com.carManagment.carManagment.Entitys.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CASKOMapper {


    @Mapping(target = "carId", source = "car.id")
    @Mapping(target = "position", source = "car.employee.position")
    @Mapping(target = "name", source = "car.employee.name")
    @Mapping(target = "carNumber", source = "car.number")
    CASKODTO toDto(CASKO casko);


    @Mapping(target = "car", source = "carId")
    CASKO toEntity(CASKODTO caskoDTO);

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
