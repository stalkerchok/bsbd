package ru.mirea.bsbd.entity;

import javax.persistence.*;

@Entity
@Table(name = "vehicle", schema = "public", catalog = "bsbd_homework")
public class VehicleEntity {
    private int vehicleId;
    private String kind;
    private int actId;

    @Id
    @Column(name = "vehicle_id", nullable = false)
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Basic
    @Column(name = "kind", nullable = false, length = 100)
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
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

        VehicleEntity that = (VehicleEntity) o;

        if (vehicleId != that.vehicleId) return false;
        if (actId != that.actId) return false;
        if (kind != null ? !kind.equals(that.kind) : that.kind != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vehicleId;
        result = 31 * result + (kind != null ? kind.hashCode() : 0);
        result = 31 * result + actId;
        return result;
    }
}
