package ru.mirea.bsbd.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "purchase", schema = "public", catalog = "bsbd_homework")
public class PurchaseEntity {
    @Expose
    private int purchaseId;
    @Expose
    private String productDenomination;
    @Expose
    private Integer productId;
    @Expose
    private String unit;
    @Expose
    private Integer okei;
    @Expose
    private Double ordered;
    @Expose
    private Double released;
    @Expose
    private Double value;
    @Expose
    private Double discount;
    @Expose
    private Double sumWithoutVat;
    @Expose
    private Double vat;
    @Expose
    private Double vatSum;
    @Expose
    private Double sumWithVat;



    private ActEntity actEntity;


    @Id
    @Column(name = "purchase_id", nullable = false)
    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Basic
    @Column(name = "product_denomination", nullable = true, length = 50)
    public String getProductDenomination() {
        return productDenomination;
    }

    public void setProductDenomination(String productDenomination) {
        this.productDenomination = productDenomination;
    }

    @Basic
    @Column(name = "product_id", nullable = true)
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
    @Column(name = "okei", nullable = true)
    public Integer getOkei() {
        return okei;
    }

    public void setOkei(Integer okei) {
        this.okei = okei;
    }

    @Basic
    @Column(name = "ordered", nullable = true, precision = 0)
    public Double getOrdered() {
        return ordered;
    }

    public void setOrdered(Double ordered) {
        this.ordered = ordered;
    }

    @Basic
    @Column(name = "released", nullable = true, precision = 0)
    public Double getReleased() {
        return released;
    }

    public void setReleased(Double released) {
        this.released = released;
    }

    @Basic
    @Column(name = "value", nullable = true, precision = 0)
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Basic
    @Column(name = "discount", nullable = true, precision = 0)
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "sum_without_vat", nullable = true, precision = 0)
    public Double getSumWithoutVat() {
        return sumWithoutVat;
    }

    public void setSumWithoutVat(Double sumWithoutVat) {
        this.sumWithoutVat = sumWithoutVat;
    }

    @Basic
    @Column(name = "vat", nullable = true, precision = 0)
    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    @Basic
    @Column(name = "vat_sum", nullable = true, precision = 0)
    public Double getVatSum() {
        return vatSum;
    }

    public void setVatSum(Double vatSum) {
        this.vatSum = vatSum;
    }

    @Basic
    @Column(name = "sum_with_vat", nullable = true, precision = 0)
    public Double getSumWithVat() {
        return sumWithVat;
    }

    public void setSumWithVat(Double sumWithVat) {
        this.sumWithVat = sumWithVat;
    }




    //link to act
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "act_id")

    public ActEntity getActEntity() {
        return actEntity;
    }

    public void setActEntity(ActEntity actEntity) {
        this.actEntity = actEntity;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseEntity that = (PurchaseEntity) o;
        return purchaseId == that.purchaseId &&
                Objects.equals(productDenomination, that.productDenomination) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(okei, that.okei) &&
                Objects.equals(ordered, that.ordered) &&
                Objects.equals(released, that.released) &&
                Objects.equals(value, that.value) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(sumWithoutVat, that.sumWithoutVat) &&
                Objects.equals(vat, that.vat) &&
                Objects.equals(vatSum, that.vatSum) &&
                Objects.equals(sumWithVat, that.sumWithVat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseId, productDenomination, productId, unit, okei, ordered, released, value, discount, sumWithoutVat, vat, vatSum, sumWithVat);
    }
}
