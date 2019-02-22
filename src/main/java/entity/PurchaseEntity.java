package entity;

import javax.persistence.*;

@Entity
@Table(name = "purchase", schema = "public", catalog = "bsbd_homework")
public class PurchaseEntity {
    private int purchaseId;
    private int productId;
    private int orderedQuantity;
    private int productReleased;
    private int actId;

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

    @Basic
    @Column(name = "act_id", nullable = false)
    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseEntity that = (PurchaseEntity) o;

        if (purchaseId != that.purchaseId) return false;
        if (productId != that.productId) return false;
        if (orderedQuantity != that.orderedQuantity) return false;
        if (productReleased != that.productReleased) return false;
        if (actId != that.actId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = purchaseId;
        result = 31 * result + productId;
        result = 31 * result + orderedQuantity;
        result = 31 * result + productReleased;
        result = 31 * result + actId;
        return result;
    }
}
