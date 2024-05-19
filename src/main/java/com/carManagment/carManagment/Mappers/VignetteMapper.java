package com.carManagment.carManagment.Mappers;

import com.carManagment.carManagment.Dto.Vignette.VignetteDTO;
import com.carManagment.carManagment.Entitys.Car;
import com.carManagment.carManagment.Entitys.Vignette;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper

public interface VignetteMapper {



    @Mapping(target = "carId", source = "car.id")
    @Mapping(target = "position", source = "car.employee.position")
    @Mapping(target = "name", source = "car.employee.name")
    @Mapping(target = "carNumber", source = "car.number")
    VignetteDTO toDto(Vignette vignette);



    @Mapping(target = "car", source = "carId")
    Vignette toEntity(VignetteDTO vignetteDTO);

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