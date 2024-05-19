package com.carManagment.carManagment.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Code cannot be blank")
    private String code;

    @NotBlank(message = "Cost Center cannot be blank")
    private String costCenter;

    @NotBlank(message = "CC Name cannot be blank")
    private String ccname;

    @NotBlank(message = "Position cannot be blank")
    private String position;

    @NotBlank(message = "License number cannot be blank")
    private String licenseNumber;

    @NotNull(message = "License expiration date cannot be null")
    private LocalDate licenseExpirationDate;

    @OneToMany(mappedBy = "employee")
    private List<Car> cars;
    @Lob
    private String image;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getCCName() {
        return ccname;
    }

    public void setCCName(String ccname) {
        this.ccname = ccname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public LocalDate getLicenseExpirationDate() {
        return licenseExpirationDate;
    }

    public void setLicenseExpirationDate(LocalDate licenseExpirationDate) {
        this.licenseExpirationDate = licenseExpirationDate;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        List<Long> displayCars = cars.stream().map((carEntity) -> carEntity.getId()).toList();

        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", costCenter='" + costCenter + '\'' +
                ", CCName='" + ccname + '\'' +
                ", position='" + position + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", licenseExpirationDate=" + licenseExpirationDate +
                ", cars=" + displayCars +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(code, employee.code) && Objects.equals(costCenter, employee.costCenter) && Objects.equals(ccname, employee.ccname) && Objects.equals(position, employee.position) && Objects.equals(licenseNumber, employee.licenseNumber) && Objects.equals(licenseExpirationDate, employee.licenseExpirationDate) && Objects.equals(cars, employee.cars) && Objects.equals(image, employee.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, costCenter, ccname, position, licenseNumber, licenseExpirationDate, cars, image);
    }
}
