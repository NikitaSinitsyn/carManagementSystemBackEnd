package com.carManagment.carManagment.Mappers;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public CarMapper carMapper() {
        return Mappers.getMapper(CarMapper.class);
    }
    @Bean
    public CASKOMapper caskoMapper() {
        return Mappers.getMapper(CASKOMapper.class);
    }
    @Bean
    public CivilInsuranceMapper civilInsuranceMapper(){
        return Mappers.getMapper(CivilInsuranceMapper.class);
    }
    @Bean
    public EmployeeMapper employeeMapper(){
        return Mappers.getMapper(EmployeeMapper.class);
    }
    @Bean
    public RepairMapper repairMapper(){
        return Mappers.getMapper(RepairMapper.class);
    }

    @Bean
    public TechnicalInspectionMapper technicalInspectionMapper(){
        return Mappers.getMapper(TechnicalInspectionMapper.class);
    }

    @Bean
    public TireMapper tireMapper(){
        return Mappers.getMapper(TireMapper.class);
    }

    @Bean
    public VignetteMapper vignetteMapper(){
        return Mappers.getMapper(VignetteMapper.class);
    }
}
