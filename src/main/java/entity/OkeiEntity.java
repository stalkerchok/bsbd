package entity;

import javax.persistence.*;

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

        if (okeiId != that.okeiId) return false;
        if (denomination != null ? !denomination.equals(that.denomination) : that.denomination != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = okeiId;
        result = 31 * result + (denomination != null ? denomination.hashCode() : 0);
        return result;
    }
}
