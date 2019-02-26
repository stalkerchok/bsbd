package ru.mirea.bsbd.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "document", schema = "public", catalog = "bsbd_homework")
public class DocumentEntity {
    private int documentId;
    private String type;
    private Date date;
    private int documentsId;
    private int actId;

    @Id
    @Column(name = "document_id", nullable = false)
    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 1000)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name = "documents_id", nullable = false)
    public int getDocumentsId() {
        return documentsId;
    }

    public void setDocumentsId(int documentsId) {
        this.documentsId = documentsId;
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

        DocumentEntity that = (DocumentEntity) o;

        if (documentId != that.documentId) return false;
        if (documentsId != that.documentsId) return false;
        if (actId != that.actId) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = documentId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + documentsId;
        result = 31 * result + actId;
        return result;
    }
}
