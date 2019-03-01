package ru.mirea.bsbd.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee", schema = "public", catalog = "bsbd_homework")
public class EmployeeEntity {
    private int employeeId;
    private String surname;
    private String name;
    private String patronymic;
    private int telephoneNumber;
    private int officeNumber;
    private String position;

    //link to act
    private Set<ActEntity> actEntity = new HashSet<>();


    @Id
    @Column(name = "employee_id", nullable = false, insertable = false, updatable = false)
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
    @Column(name = "telephone_number", nullable = true)
    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Basic
    @Column(name = "office_number", nullable = true)
    public int getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
    }

    @Basic
    @Column(name = "position", nullable = true, length = 100)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }




    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "employeeEntity")
    public Set<ActEntity> getActEntity(){
        return this.actEntity;
    }
    public void setActEntity(Set<ActEntity> actEntity) {
        this.actEntity = actEntity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return employeeId == that.employeeId &&
                telephoneNumber == that.telephoneNumber &&
                officeNumber == that.officeNumber &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, surname, name, patronymic, telephoneNumber, officeNumber, position);
    }
}
