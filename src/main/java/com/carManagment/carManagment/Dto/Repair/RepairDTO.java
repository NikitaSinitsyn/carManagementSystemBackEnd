package com.carManagment.carManagment.Dto.Repair;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor

public class RepairDTO {
    private Long id;
    private double price;
    private String description;
    private Long carId;
    private String carNumber;
    private String name;
    private String position;

    public RepairDTO(Long id, double price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "RepairDTO{" +
                "id=" + id +
                ", price=" + price +
                ", description='" + description + '\'' +
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
        RepairDTO repairDTO = (RepairDTO) o;
        return Double.compare(price, repairDTO.price) == 0 && Objects.equals(id, repairDTO.id) && Objects.equals(description, repairDTO.description) && Objects.equals(carId, repairDTO.carId) && Objects.equals(carNumber, repairDTO.carNumber) && Objects.equals(name, repairDTO.name) && Objects.equals(position, repairDTO.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, description, carId, carNumber, name, position);
    }
}
