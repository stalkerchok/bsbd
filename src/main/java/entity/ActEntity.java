package entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "act", schema = "public", catalog = "bsbd_homework")
public class ActEntity {
    private int actId;
    private String name;
    private Date date;
    private int clientId;
    private int employeeId;
    private int acceptanceId;

    @Id
    @Column(name = "act_id", nullable = false)
    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "client_id", nullable = false)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "employee_id", nullable = false)
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "acceptance_id", nullable = false)
    public int getAcceptanceId() {
        return acceptanceId;
    }

    public void setAcceptanceId(int acceptanceId) {
        this.acceptanceId = acceptanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActEntity actEntity = (ActEntity) o;

        if (actId != actEntity.actId) return false;
        if (clientId != actEntity.clientId) return false;
        if (employeeId != actEntity.employeeId) return false;
        if (acceptanceId != actEntity.acceptanceId) return false;
        if (name != null ? !name.equals(actEntity.name) : actEntity.name != null) return false;
        if (date != null ? !date.equals(actEntity.date) : actEntity.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + clientId;
        result = 31 * result + employeeId;
        result = 31 * result + acceptanceId;
        return result;
    }
}
