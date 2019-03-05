package ru.mirea.bsbd.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "okpo", schema = "public", catalog = "bsbd_homework")
public class OkpoEntity {
    private int okpoId;
    private String denomination;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OkpoEntity that = (OkpoEntity) o;
        return okpoId == that.okpoId &&
                Objects.equals(denomination, that.denomination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(okpoId, denomination);
    }
}
