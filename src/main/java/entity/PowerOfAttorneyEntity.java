package entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "power_of_attorney", schema = "public", catalog = "bsbd_homework")
public class PowerOfAttorneyEntity {
    private int powerOfAttorneyId;
    private Date date;
    private int actId;
    private int clientId;

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

    @Basic
    @Column(name = "act_id", nullable = false)
    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    @Basic
    @Column(name = "client_id", nullable = false)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PowerOfAttorneyEntity that = (PowerOfAttorneyEntity) o;

        if (powerOfAttorneyId != that.powerOfAttorneyId) return false;
        if (actId != that.actId) return false;
        if (clientId != that.clientId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = powerOfAttorneyId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + actId;
        result = 31 * result + clientId;
        return result;
    }
}
