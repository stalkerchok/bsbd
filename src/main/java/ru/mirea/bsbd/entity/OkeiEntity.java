package ru.mirea.bsbd.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "okei", schema = "public", catalog = "bsbd_homework")
public class OkeiEntity {
    private int okeiId;
    private String denomination;

    @Id
    @Column(name = "okei_id", nullable = false)
    public int getOkeiId() {
        return okeiId;
    }

    public void setOkeiId(int okeiId) {
        this.okeiId = okeiId;
    }

    @Basic
    @Column(name = "denomination", nullable = false, length = 100)
    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OkeiEntity that = (OkeiEntity) o;
        return okeiId == that.okeiId &&
                Objects.equals(denomination, that.denomination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(okeiId, denomination);
    }
}
