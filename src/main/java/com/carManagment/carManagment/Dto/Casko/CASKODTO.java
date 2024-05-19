package com.carManagment.carManagment.Dto.Casko;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor

public class CASKODTO {
    private Long id;
    private LocalDate purchaseDate;
    private LocalDate expirationDate;
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

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
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
        return "CASKODTO{" +
                "id=" + id +
                ", purchaseDate=" + purchaseDate +
                ", expirationDate=" + expirationDate +
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
        CASKODTO caskodto = (CASKODTO) o;
        return Objects.equals(id, caskodto.id) && Objects.equals(purchaseDate, caskodto.purchaseDate) && Objects.equals(expirationDate, caskodto.expirationDate) && Objects.equals(price, caskodto.price) && Objects.equals(carId, caskodto.carId) && Objects.equals(carNumber, caskodto.carNumber) && Objects.equals(name, caskodto.name) && Objects.equals(position, caskodto.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchaseDate, expirationDate, price, carId, carNumber, name, position);
    }
}
