package ru.mirea.bsbd.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee", schema = "public", catalog = "bsbd_homework")
public class EmployeeEntity {
    @Expose
    private int employeeId;
    @Expose
    private String surname;
    @Expose
    private String name;
    @Expose
    private String patronymic;
    @Expose
    private String telephoneNumber;
    @Expose
    private Integer officeNumber;

    @Expose
    private Set<ActEntity> actEntities = new HashSet<>();

    @Expose
    private Set<InventoryBookEntity> inventoryBookEntities = new HashSet<>();



    @Id
    @Column(name = "employee_id", nullable = false)
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "surname", nullable = true, length = 100)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "patronymic", nullable = true, length = 100)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "telephone_number", nullable = true, length = 50)
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Basic
    @Column(name = "office_number", nullable = true)
    public Integer getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(Integer officeNumber) {
        this.officeNumber = officeNumber;
    }



    //link to acts
    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "employeeEntity")

    public Set<ActEntity> getActEntities() {
        return actEntities;
    }

    public void setActEntities(Set<ActEntity> actEntities) {
        this.actEntities = actEntities;
    }

    public void addActEntities(ActEntity actEntity) {
        actEntity.setEmployeeEntity(this);
        this.actEntities.add(actEntity);
    }




    //link to inventory books
    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "employeeEntity")

    public Set<InventoryBookEntity> getInventoryBookEntities() {
        return inventoryBookEntities;
    }

    public void setInventoryBookEntities(Set<InventoryBookEntity> inventoryBookEntities) {
        this.inventoryBookEntities = inventoryBookEntities;
    }

    public void addInventoryBookEntities(InventoryBookEntity inventoryBookEntity) {
        inventoryBookEntity.setEmployeeEntity(this);
        this.inventoryBookEntities.add(inventoryBookEntity);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return employeeId == that.employeeId &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(telephoneNumber, that.telephoneNumber) &&
                Objects.equals(officeNumber, that.officeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, surname, name, patronymic, telephoneNumber, officeNumber);
    }
}
