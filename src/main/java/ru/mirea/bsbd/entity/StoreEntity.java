package ru.mirea.bsbd.entity;

import javax.persistence.*;

@Entity
@Table(name = "store", schema = "public", catalog = "bsbd_homework")
public class StoreEntity {
    private int storeId;
    private int productId;
    private int storage;
    private int productName;
    private int amount;
    private int purchaseId;

    @Id
    @Column(name = "store_id", nullable = false)
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
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
    @Column(name = "storage", nullable = false)
    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    @Basic
    @Column(name = "product_name", nullable = false)
    public int getProductName() {
        return productName;
    }

    public void setProductName(int productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "amount", nullable = false)
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

        StoreEntity that = (StoreEntity) o;

        if (storeId != that.storeId) return false;
        if (productId != that.productId) return false;
        if (storage != that.storage) return false;
        if (productName != that.productName) return false;
        if (amount != that.amount) return false;
        if (purchaseId != that.purchaseId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = storeId;
        result = 31 * result + productId;
        result = 31 * result + storage;
        result = 31 * result + productName;
        result = 31 * result + amount;
        result = 31 * result + purchaseId;
        return result;
    }
}
