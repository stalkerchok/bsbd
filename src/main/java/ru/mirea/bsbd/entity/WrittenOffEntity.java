package ru.mirea.bsbd.entity;

import javax.persistence.*;

@Entity
@Table(name = "written_off", schema = "public", catalog = "bsbd_homework")
public class WrittenOffEntity {
    private int idWrittenOffProduct;
    private int amountWrittenOff;
    private int actId;

    @Id
    @Column(name = "id_written_off_product", nullable = false)
    public int getIdWrittenOffProduct() {
        return idWrittenOffProduct;
    }

    public void setIdWrittenOffProduct(int idWrittenOffProduct) {
        this.idWrittenOffProduct = idWrittenOffProduct;
    }

    @Basic
    @Column(name = "amount_written_off", nullable = false)
    public int getAmountWrittenOff() {
        return amountWrittenOff;
    }

    public void setAmountWrittenOff(int amountWrittenOff) {
        this.amountWrittenOff = amountWrittenOff;
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

        WrittenOffEntity that = (WrittenOffEntity) o;

        if (idWrittenOffProduct != that.idWrittenOffProduct) return false;
        if (amountWrittenOff != that.amountWrittenOff) return false;
        if (actId != that.actId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idWrittenOffProduct;
        result = 31 * result + amountWrittenOff;
        result = 31 * result + actId;
        return result;
    }
}
