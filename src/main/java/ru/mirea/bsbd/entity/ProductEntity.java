package ru.mirea.bsbd.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "public", catalog = "bsbd_homework")
public class ProductEntity {
    @Expose
    private int productId;
    @Expose
    private String productDenomination;
    @Expose
    private double amount;
    @Expose
    private String unit;
    @Expose
    private double value;
    @Expose
    private int okei;
    @Expose
    private double vat;
    @Expose
    private double discount;



    private InventoryBookEntity inventoryBookEntity;

    @Id
    @Column(name = "product_id", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "product_denomination", nullable = true, length = 100)
    public String getProductDenomination() {
        return productDenomination;
    }

    public void setProductDenomination(String productDenomination) {
        this.productDenomination = productDenomination;
    }

    @Basic
    @Column(name = "amount", nullable = true, precision = 0)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "unit", nullable = true, length = 50)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "value", nullable = true, precision = 0)
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Basic
    @Column(name = "okei", nullable = true)
    public int getOkei() {
        return okei;
    }

    public void setOkei(int okei) {
        this.okei = okei;
    }


    @Basic
    @Column(name = "vat", nullable = true)
    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }


    @Basic
    @Column(name = "discount", nullable = true)
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }




    //link to book
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")

    public InventoryBookEntity getInventoryBookEntity() {
        return inventoryBookEntity;
    }

    public void setInventoryBookEntity(InventoryBookEntity inventoryBookEntity) {
        this.inventoryBookEntity = inventoryBookEntity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return productId == that.productId &&
                Double.compare(that.amount, amount) == 0 &&
                Double.compare(that.value, value) == 0 &&
                okei == that.okei &&
                Objects.equals(productDenomination, that.productDenomination) &&
                Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productDenomination, amount, unit, value, okei);
    }
}
