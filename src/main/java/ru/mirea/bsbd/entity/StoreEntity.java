package ru.mirea.bsbd.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "store", schema = "public", catalog = "bsbd_homework")
public class StoreEntity {
    private int storeId;
    private int productId;
    private int storage;
    private int productName;
    private int amount;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreEntity that = (StoreEntity) o;
        return storeId == that.storeId &&
                productId == that.productId &&
                storage == that.storage &&
                productName == that.productName &&
                amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, productId, storage, productName, amount);
    }
}
