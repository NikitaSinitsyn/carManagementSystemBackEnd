package com.carManagment.carManagment.Controller;

import com.carManagment.carManagment.Dto.Employee.CreateOrUpdateEmployeeDTO;
import com.carManagment.carManagment.Dto.Employee.EmployeeDTO;
import com.carManagment.carManagment.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody CreateOrUpdateEmployeeDTO createOrUpdateEmployeeDTO) {
        EmployeeDTO addedEmployee = employeeService.addEmployee(createOrUpdateEmployeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedEmployee);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long employeeId, @RequestBody CreateOrUpdateEmployeeDTO createOrUpdateEmployeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeId, createOrUpdateEmployeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        EmployeeDTO employee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @GetMapping("/All")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<EmployeeDTO> updateEmployeeImage(
            @PathVariable("id") Long employeeId,
            @RequestParam("imageFile") MultipartFile imageFile) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployeeImage(employeeId, imageFile);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getEmployeeImage(@PathVariable("id") Long employeeId) {
        byte[] imageBytes = employeeService.getEmployeeImage(employeeId);
        // Проверяем, есть ли изображение
        if (imageBytes != null && imageBytes.length > 0) {
            // Если есть, возвращаем его в ответе с соответствующим заголовком
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Устанавливаем тип содержимого как изображение JPEG
            headers.setContentLength(imageBytes.length); // Устанавливаем длину содержимого
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            // Если изображение отсутствует, возвращаем код ответа 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
