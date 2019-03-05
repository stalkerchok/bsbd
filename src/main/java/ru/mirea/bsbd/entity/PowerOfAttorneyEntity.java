package ru.mirea.bsbd.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "power_of_attorney", schema = "public", catalog = "bsbd_homework")
public class PowerOfAttorneyEntity {
    private int powerOfAttorneyId;
    private Date date;

    @Id
    @Column(name = "power_of_attorney_id", nullable = false)
    public int getPowerOfAttorneyId() {
        return powerOfAttorneyId;
    }

    public void setPowerOfAttorneyId(int powerOfAttorneyId) {
        this.powerOfAttorneyId = powerOfAttorneyId;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerOfAttorneyEntity that = (PowerOfAttorneyEntity) o;
        return powerOfAttorneyId == that.powerOfAttorneyId &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(powerOfAttorneyId, date);
    }
}
