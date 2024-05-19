package com.carManagment.carManagment.Dto.TechnicalInspectation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateTechnicalInspectionDTO {
    private LocalDate datePassed;
    private LocalDate dateNextInspection;
    private BigDecimal price;
    private String carNumber;

    public LocalDate getDatePassed() {
        return datePassed;
    }

    public void setDatePassed(LocalDate datePassed) {
        this.datePassed = datePassed;
    }

    public LocalDate getDateNextInspection() {
        return dateNextInspection;
    }

    public void setDateNextInspection(LocalDate dateNextInspection) {
        this.dateNextInspection = dateNextInspection;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public String toString() {
        return "CreateOrUpdateTechnicalInspectionDTO{" +
                "datePassed=" + datePassed +
                ", dateNextInspection=" + dateNextInspection +
                ", price=" + price +
                ", carNumber='" + carNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrUpdateTechnicalInspectionDTO that = (CreateOrUpdateTechnicalInspectionDTO) o;
        return Objects.equals(datePassed, that.datePassed) && Objects.equals(dateNextInspection, that.dateNextInspection) && Objects.equals(price, that.price) && Objects.equals(carNumber, that.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datePassed, dateNextInspection, price, carNumber);
    }
}
