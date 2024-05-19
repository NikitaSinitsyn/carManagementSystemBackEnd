package com.carManagment.carManagment.Service;

import com.carManagment.carManagment.Dto.Employee.CreateOrUpdateEmployeeDTO;
import com.carManagment.carManagment.Dto.Employee.EmployeeDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO addEmployee(CreateOrUpdateEmployeeDTO createOrUpdateEmployeeDTO);

    void deleteEmployee(Long employeeId);

    EmployeeDTO updateEmployee(Long employeeId, CreateOrUpdateEmployeeDTO createOrUpdateEmployeeDTO);

    EmployeeDTO getEmployeeById(Long employeeId);

    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO updateEmployeeImage(Long employeeId, MultipartFile imageFile);
    byte[] getEmployeeImage(Long employeeId);
}