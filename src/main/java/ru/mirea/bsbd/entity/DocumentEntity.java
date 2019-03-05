package ru.mirea.bsbd.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "document", schema = "public", catalog = "bsbd_homework")
public class DocumentEntity {
    private int documentId;
    private String type;
    private Date date;
    private int documentsId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentEntity that = (DocumentEntity) o;
        return documentId == that.documentId &&
                documentsId == that.documentsId &&
                Objects.equals(type, that.type) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, type, date, documentsId);
    }
}
