package ru.mirea.bsbd.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "inventory_book", schema = "public", catalog = "bsbd_homework")
public class InventoryBookEntity {
    private int bookId;
    private int productId;
    private String storage;
    private String date;
    private String productName;
    private String productCondition;

    @Id
    @Column(name = "book_id", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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
    @Column(name = "storage", nullable = false, length = 100)
    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    @Basic
    @Column(name = "date", nullable = false, length = 100)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
    @Column(name = "product_condition", nullable = false, length = 100)
    public String getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(String productCondition) {
        this.productCondition = productCondition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryBookEntity that = (InventoryBookEntity) o;
        return bookId == that.bookId &&
                productId == that.productId &&
                Objects.equals(storage, that.storage) &&
                Objects.equals(date, that.date) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(productCondition, that.productCondition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, productId, storage, date, productName, productCondition);
    }
}
