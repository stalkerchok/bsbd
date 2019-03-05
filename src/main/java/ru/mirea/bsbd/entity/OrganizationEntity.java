package ru.mirea.bsbd.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "organization", schema = "public", catalog = "bsbd_homework")
public class OrganizationEntity {
    private int organizationId;
    private int okpo;
    private String denomination;
    private String adress;
    private String telephoneNumder;

    @Id
    @Column(name = "organization_id", nullable = false)
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @Basic
    @Column(name = "okpo", nullable = false)
    public int getOkpo() {
        return okpo;
    }

    public void setOkpo(int okpo) {
        this.okpo = okpo;
    }

    @Basic
    @Column(name = "denomination", nullable = false, length = 1000)
    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    @Basic
    @Column(name = "adress", nullable = false, length = 1000)
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Basic
    @Column(name = "telephone_numder", nullable = false, length = 12)
    public String getTelephoneNumder() {
        return telephoneNumder;
    }

    public void setTelephoneNumder(String telephoneNumder) {
        this.telephoneNumder = telephoneNumder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationEntity that = (OrganizationEntity) o;
        return organizationId == that.organizationId &&
                okpo == that.okpo &&
                Objects.equals(denomination, that.denomination) &&
                Objects.equals(adress, that.adress) &&
                Objects.equals(telephoneNumder, that.telephoneNumder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationId, okpo, denomination, adress, telephoneNumder);
    }
}
