package com.carManagment.carManagment.Dto.Repair;

import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@RequiredArgsConstructor
public class CreateOrUpdateRepairDTO {

    private double price;
    @NotBlank
    private String description;
    private String carNumber;


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }


    @Override
    public String toString() {
        return "CreateOrUpdateRepairDTO{" +
                "price=" + price +
                ", description='" + description + '\'' +
                ", carNumber='" + carNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrUpdateRepairDTO that = (CreateOrUpdateRepairDTO) o;
        return Double.compare(price, that.price) == 0 && Objects.equals(description, that.description) && Objects.equals(carNumber, that.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, description, carNumber);
    }
}
