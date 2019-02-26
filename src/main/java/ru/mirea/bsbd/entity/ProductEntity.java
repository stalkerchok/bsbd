package ru.mirea.bsbd.entity;

import javax.persistence.*;

@Entity
@Table(name = "product", schema = "public", catalog = "bsbd_homework")
public class ProductEntity {
    private int productId;
    private String productName;
    private double value;
    private String okei;
    private int okeiId;
    private int purchaseId;

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

    @Basic
    @Column(name = "okei_id", nullable = false)
    public int getOkeiId() {
        return okeiId;
    }

    public void setOkeiId(int okeiId) {
        this.okeiId = okeiId;
    }

    @Basic
    @Column(name = "purchase_id", nullable = false)
    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (productId != that.productId) return false;
        if (Double.compare(that.value, value) != 0) return false;
        if (okeiId != that.okeiId) return false;
        if (purchaseId != that.purchaseId) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (okei != null ? !okei.equals(that.okei) : that.okei != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = productId;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (okei != null ? okei.hashCode() : 0);
        result = 31 * result + okeiId;
        result = 31 * result + purchaseId;
        return result;
    }
}
