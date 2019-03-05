package ru.mirea.bsbd.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicle", schema = "public", catalog = "bsbd_homework")
public class VehicleEntity {
    private int vehicleId;
    private String kind;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleEntity that = (VehicleEntity) o;
        return vehicleId == that.vehicleId &&
                Objects.equals(kind, that.kind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, kind);
    }
}
