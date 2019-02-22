package entity;

import javax.persistence.*;

@Entity
@Table(name = "inventory_book", schema = "public", catalog = "bsbd_homework")
public class InventoryBookEntity {
    private int bookId;
    private int productId;
    private String storage;
    private String date;
    private String productName;
    private String productCondition;
    private int employeeId;

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

    @Basic
    @Column(name = "employee_id", nullable = false)
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryBookEntity that = (InventoryBookEntity) o;

        if (bookId != that.bookId) return false;
        if (productId != that.productId) return false;
        if (employeeId != that.employeeId) return false;
        if (storage != null ? !storage.equals(that.storage) : that.storage != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (productCondition != null ? !productCondition.equals(that.productCondition) : that.productCondition != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + productId;
        result = 31 * result + (storage != null ? storage.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productCondition != null ? productCondition.hashCode() : 0);
        result = 31 * result + employeeId;
        return result;
    }
}
