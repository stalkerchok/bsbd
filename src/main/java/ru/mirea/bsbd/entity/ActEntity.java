package ru.mirea.bsbd.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "act", schema = "public", catalog = "bsbd_homework")
public class ActEntity {
    @Expose
    private int actId;
    @Expose
    private String name;
    @Expose
    private Date date;

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




    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")


    public EmployeeEntity getEmployeeEntity(){
        return this.employeeEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }



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
