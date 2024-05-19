package com.carManagment.carManagment.Mappers;

import com.carManagment.carManagment.Dto.Car.CarDTO;
import com.carManagment.carManagment.Dto.Tire.TireDTO;
import com.carManagment.carManagment.Entitys.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(uses = {CASKOMapper.class, CivilInsuranceMapper.class, EmployeeMapper.class, RepairMapper.class, TechnicalInspectionMapper.class, TireMapper.class, VignetteMapper.class})
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);


    @Mapping(target = "winterTires", source = "winterTireDTOList", qualifiedByName = "mapWinterTireDTOListToTireList")
    @Mapping(target = "summerTires", source = "summerTireDTOList", qualifiedByName = "mapSummerTireDTOListToTireList")
    @Mapping(target = "repairs", source = "repairDTOList")
    @Mapping(target = "vignette", source = "vignetteDTO")
    @Mapping(target = "technicalInspection", source = "technicalInspectionDTO")
    @Mapping(target = "employee", source = "employeeDTO")
    @Mapping(target = "civilInsurance", source = "civilInsuranceDTO")
    @Mapping(target = "casko", source = "caskoDTO")
    @Mapping(target = "sap", source = "sap")
    Car carDTOToCar(CarDTO carDTO);

    @Mapping(target = "winterTireDTOList", source = "winterTires")
    @Mapping(target = "summerTireDTOList", source = "summerTires")
    @Mapping(target = "repairDTOList", source = "repairs")
    @Mapping(target = "vignetteDTO", source = "vignette")
    @Mapping(target = "technicalInspectionDTO", source = "technicalInspection")
    @Mapping(target = "employeeDTO", source = "employee")
    @Mapping(target = "civilInsuranceDTO", source = "civilInsurance")
    @Mapping(target = "caskoDTO", source = "casko")
    @Mapping(target = "sap", source = "sap")
    CarDTO toDto(Car car);

    @Named("mapWinterTireDTOListToTireList")
    static List<Tire> mapWinterTireDTOListToTireList(List<TireDTO> tireDTOList) {
        if (tireDTOList == null) {
            return null;
        }

        //
        return tireDTOList.stream()
                .map(tireDTO -> {
                    Tire tire = new Tire();
                    tire.setSeason(Season.WINTER); // Установка типа шины (зимняя)
                    // Дополнительные маппинги для каждого TireDTO, если необходимо
                    return tire;
                })
                .collect(Collectors.toList());
    }

    @Named("mapSummerTireDTOListToTireList")
    static List<Tire> mapSummerTireDTOListToTireList(List<TireDTO> tireDTOList) {
        if (tireDTOList == null) {
            return null;
        }
        return tireDTOList.stream()
                .map(tireDTO -> {
                    Tire tire = new Tire();
                    tire.setSeason(Season.SUMMER); // Установка типа шины (летняя)
                    // Дополнительные маппинги для каждого TireDTO, если необходимо
                    return tire;
                })
                .collect(Collectors.toList());
    }



    default List<Tire> mapTireIdsToTires(List<Long> tireIds) {
        if (tireIds == null) {
            return null;
        }
        return tireIds.stream()
                .map(id -> {
                    Tire tire = new Tire();
                    tire.setId(id);
                    return tire;
                })
                .collect(Collectors.toList());
    }

    default List<Repair> mapRepairIdsToRepairs(List<Long> repairIds) {
        if (repairIds == null) {
            return null;
        }
        return repairIds.stream()
                .map(id -> {
                    Repair repair = new Repair();
                    repair.setId(id);
                    return repair;
                })
                .collect(Collectors.toList());
    }

    default List<Long> mapTiresToTireIds(List<Tire> tires) {
        if (tires == null) {
            return null;
        }
        return tires.stream()
                .map(Tire::getId)
                .collect(Collectors.toList());
    }

    default List<Long> mapRepairsToRepairIds(List<Repair> repairs) {
        if (repairs == null) {
            return null;
        }
        return repairs.stream()
                .map(Repair::getId)
                .collect(Collectors.toList());
    }
}