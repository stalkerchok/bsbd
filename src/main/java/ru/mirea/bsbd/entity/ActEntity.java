package ru.mirea.bsbd.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "act", schema = "public", catalog = "bsbd_homework")
public class ActEntity {
    @Expose
    private int actId;
    @Expose
    private String structural_subdivision;
    @Expose
    private String denomination;
    @Expose
    private String date;
    @Expose
    private String status;

    @Expose
    private Set<OrganizationEntity> organizationEntities = new HashSet<>();

    @Expose
    private Set<PurchaseEntity> purchaseEntities = new HashSet<>();

    private ClientEntity clientEntity;

    private Set<EmployeeEntity> employeeEntities = new HashSet<>();

    //private EmployeeEntity employeeEntity;


    @Id
    @Column(name = "act_id", nullable = false)
    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    @Basic
    @Column(name = "denomination", nullable = true, length = 100)
    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    @Basic
    @Column(name = "date", nullable = true, length = 100)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "structural_subdivision", nullable = true, length = 100)
    public String getStructural_subdivision() {
        return structural_subdivision;
    }

    public void setStructural_subdivision(String structural_subdivision) {
        this.structural_subdivision = structural_subdivision;
    }


    @Basic
    @Column(name = "status", nullable = true, length = 50)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    /*
    //link to employee
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")

    public EmployeeEntity getEmployeeEntity(){
        return this.employeeEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }
    */

    //link to client
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")

    public ClientEntity getClientEntity(){
        return this.clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }


    //link to organizations
    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "actEntity")

    public Set<OrganizationEntity> getOrganizationEntities() {
        return organizationEntities;
    }

    public void setOrganizationEntities(Set<OrganizationEntity> organizationEntities) {
        this.organizationEntities = organizationEntities;
    }

    public void addOrganizationEntities(OrganizationEntity organizationEntity) {
        organizationEntity.setActEntity(this);
        this.organizationEntities.add(organizationEntity);
    }




    //link to purchases
    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "actEntity")

    public Set<PurchaseEntity> getPurchaseEntities() {
        return purchaseEntities;
    }

    public void setPurchaseEntities(Set<PurchaseEntity> purchaseEntities) {
        this.purchaseEntities = purchaseEntities;
    }

    public void addPurchaseEntities(PurchaseEntity purchaseEntity) {
        purchaseEntity.setActEntity(this);
        this.purchaseEntities.add(purchaseEntity);
    }




    //link to employees
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "act_employee",
            joinColumns = {@JoinColumn(name = "act_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )

    public Set<EmployeeEntity> getEmployeeEntities() {
        return employeeEntities;
    }

    public void setEmployeeEntities(Set<EmployeeEntity> employeeEntities) {
        this.employeeEntities = employeeEntities;
    }

    public void addEmpolyeeEntities(EmployeeEntity employeeEntity){
        this.employeeEntities.add(employeeEntity);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActEntity actEntity = (ActEntity) o;
        return actId == actEntity.actId &&
                Objects.equals(denomination, actEntity.denomination) &&
                Objects.equals(date, actEntity.date) &&
                Objects.equals(structural_subdivision, actEntity.structural_subdivision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actId, denomination, date, structural_subdivision);
    }
}
