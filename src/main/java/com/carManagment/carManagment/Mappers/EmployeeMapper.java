package com.carManagment.carManagment.Mappers;

import com.carManagment.carManagment.Dto.Employee.EmployeeDTO;
import com.carManagment.carManagment.Entitys.Car;
import com.carManagment.carManagment.Entitys.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);


    @Mapping(target = "carNumbers", source = "cars", qualifiedByName = "mapCarsToCarNumbersList")
    @Mapping(target = "brands", source = "cars", qualifiedByName = "mapCarsToCarBrandsList")
    EmployeeDTO toDto(Employee employee);

    @Mapping(target = "cars", source = "carNumbers", qualifiedByName = "mapCarNumbersListToCars")
    Employee toEntity(EmployeeDTO employeeDTO);

    @Named("mapCarsToCarNumbersList")
    default List<String> mapCarsToCarNumbersList(List<Car> cars) {
        return cars.stream()
                .map(Car::getNumber)
                .collect(Collectors.toList());
    }

    @Named("mapCarsToCarBrandsList")
    default List<String> mapCarsToCarBrandsList(List<Car> cars) {
        return cars.stream()
                .map(Car::getBrand)
                .collect(Collectors.toList());
    }

    @Named("mapCarNumbersListToCars")
    default List<Car> mapCarNumbersListToCars(List<String> carNumbers) {
        return carNumbers.stream()
                .map(number -> {
                    Car car = new Car();
                    car.setNumber(number);
                    return car;
                })
                .collect(Collectors.toList());
    }

    @Named("mapCarBrandsListToCars")
    default List<Car> mapCarBrandsListToCars(List<String> carBrands) {
        return carBrands.stream()
                .map(brand -> {
                    Car car = new Car();
                    car.setBrand(brand);
                    return car;
                })
                .collect(Collectors.toList());
    }
}