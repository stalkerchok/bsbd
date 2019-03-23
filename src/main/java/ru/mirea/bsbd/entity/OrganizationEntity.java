package ru.mirea.bsbd.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "organization", schema = "public", catalog = "bsbd_homework")
public class OrganizationEntity {
    @Expose
    private int organizationId;
    @Expose
    private String type;
    @Expose
    private String denomination;
    @Expose
    private String address;
    @Expose
    private String telephoneNumber;
    @Expose
    private Integer okpo;


    private ActEntity actEntity;

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "organization_organization_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence")
    @Column(name = "organization_id", nullable = false)
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name = "address", nullable = true, length = 1000)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    @Column(name = "okpo", nullable = true)
    public Integer getOkpo() {
        return okpo;
    }

    public void setOkpo(Integer okpo) {
        this.okpo = okpo;
    }




    //link to act
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_act_id")

    public ActEntity getActEntity() {
        return actEntity;
    }

    public void setActEntity(ActEntity actEntity) {
        this.actEntity = actEntity;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationEntity that = (OrganizationEntity) o;
        return organizationId == that.organizationId &&
                Objects.equals(type, that.type) &&
                Objects.equals(denomination, that.denomination) &&
                Objects.equals(address, that.address) &&
                Objects.equals(telephoneNumber, that.telephoneNumber) &&
                Objects.equals(okpo, that.okpo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationId, type, denomination, address, telephoneNumber, okpo);
    }
}
