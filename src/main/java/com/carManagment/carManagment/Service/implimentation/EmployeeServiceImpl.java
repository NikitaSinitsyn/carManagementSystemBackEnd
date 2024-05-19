package com.carManagment.carManagment.Service.implimentation;

import com.carManagment.carManagment.Dto.Employee.CreateOrUpdateEmployeeDTO;
import com.carManagment.carManagment.Dto.Employee.EmployeeDTO;
import com.carManagment.carManagment.Entitys.Car;
import com.carManagment.carManagment.Entitys.Employee;
import com.carManagment.carManagment.Exceptions.CarNotFoundException;
import com.carManagment.carManagment.Exceptions.EmployeeCreationException;
import com.carManagment.carManagment.Exceptions.EmployeeNotFoundException;
import com.carManagment.carManagment.Mappers.EmployeeMapper;
import com.carManagment.carManagment.Repository.CarRepository;
import com.carManagment.carManagment.Repository.EmployeeRepository;
import com.carManagment.carManagment.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final CarRepository carRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, CarRepository carRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.carRepository = carRepository;
    }

    @Override
    public EmployeeDTO addEmployee(CreateOrUpdateEmployeeDTO createOrUpdateEmployeeDTO) {
        // Создание нового объекта Employee
        Employee employee = new Employee();

        // Заполнение полей Employee из DTO
        employee.setName(createOrUpdateEmployeeDTO.getName());
        employee.setCode(createOrUpdateEmployeeDTO.getCode());
        employee.setCostCenter(createOrUpdateEmployeeDTO.getCostCenter());
        employee.setCCName(createOrUpdateEmployeeDTO.getCCName());
        employee.setPosition(createOrUpdateEmployeeDTO.getPosition());
        employee.setLicenseNumber(createOrUpdateEmployeeDTO.getLicenseNumber());
        employee.setLicenseExpirationDate(createOrUpdateEmployeeDTO.getLicenseExpirationDate());

        List<Car> cars = new ArrayList<>();

        if (createOrUpdateEmployeeDTO.getCarNumbers() != null && !createOrUpdateEmployeeDTO.getCarNumbers().isEmpty()) {
            createOrUpdateEmployeeDTO.getCarNumbers().stream()
                    .map(carRepository::findByNumber)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(cars::add);
        }

        employee.setCars(cars);



        try {
            Employee savedEmployee = employeeRepository.save(employee);

            for (Car car : cars) {
                // Устанавливаем сотрудника для машины
                car.setEmployee(savedEmployee);
                // Сохраняем машину с обновленными данными
                carRepository.save(car);
            }
            return employeeMapper.toDto(savedEmployee);
        } catch (Exception e) {
            // Обработка ошибок при сохранении
            throw new EmployeeCreationException("Failed to create employee", e);
        }
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        Employee employee = employeeOptional.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));

        // Удаление ссылки на сотрудника у машин
        List<Car> cars = employee.getCars();
        for (Car car : cars) {
            car.setEmployee(null); // Установка сотрудника машины в null
            carRepository.save(car); // Сохранение изменений в базе данных
        }

        // Удаление сотрудника
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, CreateOrUpdateEmployeeDTO createOrUpdateEmployeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee existingEmployee = optionalEmployee.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));

        existingEmployee.setName(createOrUpdateEmployeeDTO.getName());
        existingEmployee.setCode(createOrUpdateEmployeeDTO.getCode());
        existingEmployee.setCostCenter(createOrUpdateEmployeeDTO.getCostCenter());
        existingEmployee.setCCName(createOrUpdateEmployeeDTO.getCCName());
        existingEmployee.setPosition(createOrUpdateEmployeeDTO.getPosition());
        existingEmployee.setLicenseNumber(createOrUpdateEmployeeDTO.getLicenseNumber());
        existingEmployee.setLicenseExpirationDate(createOrUpdateEmployeeDTO.getLicenseExpirationDate());


        if (createOrUpdateEmployeeDTO.getCarNumbers() != null && !createOrUpdateEmployeeDTO.getCarNumbers().isEmpty()) {
            if (existingEmployee.getCars() != null && !existingEmployee.getCars().isEmpty()) {
                List<Car> existingEmployeeCars = existingEmployee.getCars().stream().peek((car) -> car.setEmployee(null)).toList();
                carRepository.saveAll(existingEmployeeCars);
            }

            List<Car> cars = createOrUpdateEmployeeDTO.getCarNumbers().stream()
                    .map(carNumber -> carRepository.findByNumber(carNumber)
                            .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + carNumber)))
                    .toList();
            cars.forEach((car) -> car.setEmployee(existingEmployee));

            existingEmployee.setCars(cars);
        } else {
            if (existingEmployee.getCars() != null && !existingEmployee.getCars().isEmpty()) {
                List<Car> cars = existingEmployee.getCars().stream().peek((car) -> car.setEmployee(null)).toList();
                carRepository.saveAll(cars);
                existingEmployee.getCars().clear();
                return employeeMapper.toDto(existingEmployee);
            } else if (existingEmployee.getCars() == null) {
                existingEmployee.setCars(new ArrayList<>());
                Employee updatedEmployee = employeeRepository.save(existingEmployee);
                return employeeMapper.toDto(updatedEmployee);
            }
        }

        // Сохранение обновленного сотрудника в репозиторий
        carRepository.saveAll(existingEmployee.getCars());
        return employeeMapper.toDto(existingEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));
        return employeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> allEmployees = employeeRepository.findAll();
        if (allEmployees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found");
        }
        return allEmployees.stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployeeImage(Long employeeId, MultipartFile imageFile) {
        // Находим сотрудника по его ID
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));

        try {
            // Проверяем, был ли предоставлен файл
            if (imageFile != null && !imageFile.isEmpty()) {
                // Получаем содержимое изображения в виде массива байтов
                byte[] imageData = imageFile.getBytes();

                // Преобразуем содержимое изображения в строку base64
                String base64Image = Base64.getEncoder().encodeToString(imageData);

                // Устанавливаем изображение сотрудника
                existingEmployee.setImage(base64Image);
            } else {
                // Если файл не предоставлен, устанавливаем пустое изображение или другое значение по умолчанию
                existingEmployee.setImage(null);
            }

            // Сохраняем обновленного сотрудника
            Employee updatedEmployee = employeeRepository.save(existingEmployee);

            // Преобразуем обновленного сотрудника в DTO и возвращаем его
            return employeeMapper.toDto(updatedEmployee);
        } catch (IOException ex) {
            // Если произошла ошибка ввода-вывода при чтении файла, бросаем исключение
            throw new EmployeeCreationException("Failed to read image file", ex);
        }
    }
    @Override
    public byte[] getEmployeeImage(Long employeeId) {
        // Получаем сущность Employee по её ID
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));

        // Получаем изображение сотрудника из сущности
        String base64Image = employee.getImage();

        // Если изображение представлено в формате base64, декодируем его и возвращаем
        if (base64Image != null && !base64Image.isEmpty()) {
            return Base64.getDecoder().decode(base64Image);
        } else {
            // Если изображение отсутствует, возвращаем null или другое значение по умолчанию
            return null;
        }
    }


}