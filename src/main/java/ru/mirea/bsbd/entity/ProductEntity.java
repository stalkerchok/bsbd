package ru.mirea.bsbd.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "public", catalog = "bsbd_homework")
public class ProductEntity {
    private int productId;
    private String productName;
    private double value;
    private String okei;

    @Id
    @Column(name = "product_id", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "product_name", nullable = false, length = 100)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "value", nullable = false, precision = 0)
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Basic
    @Column(name = "okei", nullable = false, length = 100)
    public String getOkei() {
        return okei;
    }

    public void setOkei(String okei) {
        this.okei = okei;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return productId == that.productId &&
                Double.compare(that.value, value) == 0 &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(okei, that.okei);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, value, okei);
    }
}
