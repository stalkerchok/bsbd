package ru.mirea.bsbd.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "purchase", schema = "public", catalog = "bsbd_homework")
public class PurchaseEntity {
    private int purchaseId;
    private int productId;
    private int orderedQuantity;
    private int productReleased;

    @Id
    @Column(name = "purchase_id", nullable = false)
    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Basic
    @Column(name = "product_id", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "ordered_quantity", nullable = false)
    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    @Basic
    @Column(name = "product_released", nullable = false)
    public int getProductReleased() {
        return productReleased;
    }

    public void setProductReleased(int productReleased) {
        this.productReleased = productReleased;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseEntity that = (PurchaseEntity) o;
        return purchaseId == that.purchaseId &&
                productId == that.productId &&
                orderedQuantity == that.orderedQuantity &&
                productReleased == that.productReleased;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseId, productId, orderedQuantity, productReleased);
    }
}
