package ru.mirea.bsbd.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "act", schema = "public", catalog = "bsbd_homework")
public class ActEntity {
    private int actId;
    private String name;
    private Date date;
    private int employee_id;
    private EmployeeEntity employeeEntity;


    @Id
    @Column(name = "act_id", nullable = false)
    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
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
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "employee_id", nullable = false, insertable = false, updatable = false)
    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", insertable = false, updatable = false)
    public EmployeeEntity getEmployeeEntity(){
        return this.employeeEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    /*@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", nullable = false)

    public int getEmployeeEntity(){
        return this.employeeEntity.getEmployeeId();
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity.setEmployeeId(employeeEntity.getEmployeeId());
    }

*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActEntity actEntity = (ActEntity) o;
        return actId == actEntity.actId &&
                Objects.equals(name, actEntity.name) &&
                Objects.equals(date, actEntity.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actId, name, date);
    }
}
