package entity;

import javax.persistence.*;

@Entity
@Table(name = "acceptance", schema = "public", catalog = "bsbd_homework")
public class AcceptanceEntity {
    private int acceptanceId;
    private int actId;
    private String start;
    private String end1;
    private String renewed;
    private String suspended;
    private String adress;

    @Id
    @Column(name = "acceptance_id", nullable = false)
    public int getAcceptanceId() {
        return acceptanceId;
    }

    public void setAcceptanceId(int acceptanceId) {
        this.acceptanceId = acceptanceId;
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
    @Column(name = "start", nullable = false, length = 100)
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    @Basic
    @Column(name = "end_1", nullable = false, length = 100)
    public String getEnd1() {
        return end1;
    }

    public void setEnd1(String end1) {
        this.end1 = end1;
    }

    @Basic
    @Column(name = "renewed", nullable = false, length = 100)
    public String getRenewed() {
        return renewed;
    }

    public void setRenewed(String renewed) {
        this.renewed = renewed;
    }

    @Basic
    @Column(name = "suspended", nullable = false, length = 100)
    public String getSuspended() {
        return suspended;
    }

    public void setSuspended(String suspended) {
        this.suspended = suspended;
    }

    @Basic
    @Column(name = "adress", nullable = false, length = 100)
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AcceptanceEntity that = (AcceptanceEntity) o;

        if (acceptanceId != that.acceptanceId) return false;
        if (actId != that.actId) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (end1 != null ? !end1.equals(that.end1) : that.end1 != null) return false;
        if (renewed != null ? !renewed.equals(that.renewed) : that.renewed != null) return false;
        if (suspended != null ? !suspended.equals(that.suspended) : that.suspended != null) return false;
        if (adress != null ? !adress.equals(that.adress) : that.adress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = acceptanceId;
        result = 31 * result + actId;
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end1 != null ? end1.hashCode() : 0);
        result = 31 * result + (renewed != null ? renewed.hashCode() : 0);
        result = 31 * result + (suspended != null ? suspended.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        return result;
    }
}
