package entity;

import javax.persistence.*;

@Entity
@Table(name = "okpo", schema = "public", catalog = "bsbd_homework")
public class OkpoEntity {
    private int okpoId;
    private String denomination;
    private int organizationId;
    private int vehicleId;

    @Id
    @Column(name = "okpo_id", nullable = false)
    public int getOkpoId() {
        return okpoId;
    }

    public void setOkpoId(int okpoId) {
        this.okpoId = okpoId;
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
    @Column(name = "organization_id", nullable = false)
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @Basic
    @Column(name = "vehicle_id", nullable = false)
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OkpoEntity that = (OkpoEntity) o;

        if (okpoId != that.okpoId) return false;
        if (organizationId != that.organizationId) return false;
        if (vehicleId != that.vehicleId) return false;
        if (denomination != null ? !denomination.equals(that.denomination) : that.denomination != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = okpoId;
        result = 31 * result + (denomination != null ? denomination.hashCode() : 0);
        result = 31 * result + organizationId;
        result = 31 * result + vehicleId;
        return result;
    }
}
