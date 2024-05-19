package com.carManagment.carManagment.Dto.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private String code;
    private String costCenter;
    private String ccname;
    private String position;
    private String licenseNumber;
    private LocalDate licenseExpirationDate;
    private List<String> carNumbers;
    private List<String> brands;

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

    public List<String> getCarNumbers() {
        return carNumbers;
    }

    public void setCarNumbers(List<String> carNumbers) {
        this.carNumbers = carNumbers;
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", costCenter='" + costCenter + '\'' +
                ", CCName='" + ccname + '\'' +
                ", position='" + position + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", licenseExpirationDate=" + licenseExpirationDate +
                ", carNumbers=" + carNumbers +
                ", brands=" + brands +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(code, that.code) && Objects.equals(costCenter, that.costCenter) && Objects.equals(ccname, that.ccname) && Objects.equals(position, that.position) && Objects.equals(licenseNumber, that.licenseNumber) && Objects.equals(licenseExpirationDate, that.licenseExpirationDate) && Objects.equals(carNumbers, that.carNumbers) && Objects.equals(brands, that.brands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, costCenter, ccname, position, licenseNumber, licenseExpirationDate, carNumbers, brands);
    }
}
