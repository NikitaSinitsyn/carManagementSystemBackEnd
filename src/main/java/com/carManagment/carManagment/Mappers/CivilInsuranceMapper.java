package com.carManagment.carManagment.Mappers;

import com.carManagment.carManagment.Dto.CivilInsurance.CivilInsuranceDTO;
import com.carManagment.carManagment.Entitys.Car;
import com.carManagment.carManagment.Entitys.CivilInsurance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CivilInsuranceMapper {

    @Mapping(target = "carId", source = "car.id")
    @Mapping(target = "position", source = "car.employee.position")
    @Mapping(target = "name", source = "car.employee.name")
    @Mapping(target = "carNumber", source = "car.number")
    CivilInsuranceDTO toDto(CivilInsurance civilInsurance);


    @Mapping(target = "car", source = "carId")
    CivilInsurance toEntity(CivilInsuranceDTO civilInsuranceDTO);

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
