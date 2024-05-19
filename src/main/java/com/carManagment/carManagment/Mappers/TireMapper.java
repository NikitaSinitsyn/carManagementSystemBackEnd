package com.carManagment.carManagment.Mappers;

import com.carManagment.carManagment.Dto.Tire.TireDTO;
import com.carManagment.carManagment.Entitys.Car;
import com.carManagment.carManagment.Entitys.Tire;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TireMapper {

    TireMapper INSTANCE = Mappers.getMapper(TireMapper.class);


    @Mapping(target = "position", source = "car.employee.position")
    @Mapping(target = "name", source = "car.employee.name")
    @Mapping(target = "carNumber", source = "car.number")
    @Mapping(target = "carId", source = "car.id")
    TireDTO toDto(Tire tire);


    @Mapping(target = "car", source = "carId")
    Tire toEntity(TireDTO tireDTO);

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
