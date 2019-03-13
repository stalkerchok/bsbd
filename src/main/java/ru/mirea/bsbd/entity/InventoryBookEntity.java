package ru.mirea.bsbd.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "inventory_book", schema = "public", catalog = "bsbd_homework")
public class InventoryBookEntity {
    @Expose
    private int bookId;
    @Expose
    private String organizationDenomination;
    @Expose
    private int storeId;

    @Expose
    private Set<ProductEntity> productEntities = new HashSet<>();


    private EmployeeEntity employeeEntity;

    @Id
    @Column(name = "book_id", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "organization_denomination", nullable = true, length = 100)
    public String getOrganizationDenomination() {
        return organizationDenomination;
    }

    public void setOrganizationDenomination(String organizationDenomination) {
        this.organizationDenomination = organizationDenomination;
    }

    @Basic
    @Column(name = "store_id", nullable = true)
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }




    //link to employee
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }




    //link to products
    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "inventoryBookEntity")

    public Set<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(Set<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }

    public void addProductEntities(ProductEntity productEntity){
        productEntity.setInventoryBookEntity(this);
        this.productEntities.add(productEntity);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryBookEntity that = (InventoryBookEntity) o;
        return bookId == that.bookId &&
                storeId == that.storeId &&
                Objects.equals(organizationDenomination, that.organizationDenomination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, organizationDenomination, storeId);
    }
}
