package com.carManagment.carManagment.Mappers;

import com.carManagment.carManagment.Dto.TechnicalInspectation.TechnicalInspectionDTO;
import com.carManagment.carManagment.Entitys.Car;
import com.carManagment.carManagment.Entitys.TechnicalInspection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper
public interface TechnicalInspectionMapper {


    @Mapping(target = "position", source = "car.employee.position")
    @Mapping(target = "name", source = "car.employee.name")
    @Mapping(target = "carNumber", source = "car.number")
    @Mapping(target = "carId", source = "car.id")
    TechnicalInspectionDTO toDto(TechnicalInspection technicalInspection);


    @Mapping(target = "car", source = "carId")
    TechnicalInspection toEntity(TechnicalInspectionDTO technicalInspectionDTO);

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
