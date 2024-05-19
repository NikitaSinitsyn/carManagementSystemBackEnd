package com.carManagment.carManagment.Dto.CivilInsurance;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateCivilInsuranceDTO {

    private LocalDate purchaseDate;
    private LocalDate expirationDate;
    private BigDecimal price;
    private String carNumber;

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

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public String toString() {
        return "CreateOrUpdateCivilInsuranceDTO{" +
                "purchaseDate=" + purchaseDate +
                ", expirationDate=" + expirationDate +
                ", price=" + price +
                ", carNumber='" + carNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrUpdateCivilInsuranceDTO that = (CreateOrUpdateCivilInsuranceDTO) o;
        return Objects.equals(purchaseDate, that.purchaseDate) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(price, that.price) && Objects.equals(carNumber, that.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseDate, expirationDate, price, carNumber);
    }
}
