package ru.mirea.bsbd.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "written_off", schema = "public", catalog = "bsbd_homework")
public class WrittenOffEntity {
    private int idWrittenOffProduct;
    private int amountWrittenOff;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrittenOffEntity that = (WrittenOffEntity) o;
        return idWrittenOffProduct == that.idWrittenOffProduct &&
                amountWrittenOff == that.amountWrittenOff;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWrittenOffProduct, amountWrittenOff);
    }
}
