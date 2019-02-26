package ru.mirea.bsbd.entity;

import javax.persistence.*;

@Entity
@Table(name = "organization", schema = "public", catalog = "bsbd_homework")
public class OrganizationEntity {
    private int organizationId;
    private int okpo;
    private String denomination;
    private String adress;
    private String telephoneNumder;
    private int actId;

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

    @Basic
    @Column(name = "act_id", nullable = false)
    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationEntity that = (OrganizationEntity) o;

        if (organizationId != that.organizationId) return false;
        if (okpo != that.okpo) return false;
        if (actId != that.actId) return false;
        if (denomination != null ? !denomination.equals(that.denomination) : that.denomination != null) return false;
        if (adress != null ? !adress.equals(that.adress) : that.adress != null) return false;
        if (telephoneNumder != null ? !telephoneNumder.equals(that.telephoneNumder) : that.telephoneNumder != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organizationId;
        result = 31 * result + okpo;
        result = 31 * result + (denomination != null ? denomination.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (telephoneNumder != null ? telephoneNumder.hashCode() : 0);
        result = 31 * result + actId;
        return result;
    }
}
