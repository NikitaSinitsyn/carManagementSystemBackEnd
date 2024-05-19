package com.carManagment.carManagment.Dto.TechnicalInspectation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
public class TechnicalInspectionDTO {
    private Long id;
    private LocalDate datePassed;
    private LocalDate dateNextInspection;
    private BigDecimal price;
    private Long carId;
    private String carNumber;
    private String name;
    private String position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "TechnicalInspectionDTO{" +
                "id=" + id +
                ", datePassed=" + datePassed +
                ", dateNextInspection=" + dateNextInspection +
                ", price=" + price +
                ", carId=" + carId +
                ", carNumber='" + carNumber + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechnicalInspectionDTO that = (TechnicalInspectionDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(datePassed, that.datePassed) && Objects.equals(dateNextInspection, that.dateNextInspection) && Objects.equals(price, that.price) && Objects.equals(carId, that.carId) && Objects.equals(carNumber, that.carNumber) && Objects.equals(name, that.name) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datePassed, dateNextInspection, price, carId, carNumber, name, position);
    }
}
