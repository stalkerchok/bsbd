package ru.mirea.bsbd.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "client", schema = "public", catalog = "bsbd_homework")
public class ClientEntity {
    private int clientId;
    private String surname;
    private String name;
    private String patronymic;
    private int telephoneNumber;
    private String adress;
    private int okpo;

    @Id
    @Column(name = "client_id", nullable = false)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 100)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
    @Column(name = "patronymic", nullable = false, length = 100)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "telephone_number", nullable = false)
    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Basic
    @Column(name = "adress", nullable = false, length = 1000)
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Basic
    @Column(name = "okpo", nullable = false)
    public int getOkpo() {
        return okpo;
    }

    public void setOkpo(int okpo) {
        this.okpo = okpo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return clientId == that.clientId &&
                telephoneNumber == that.telephoneNumber &&
                okpo == that.okpo &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(adress, that.adress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, surname, name, patronymic, telephoneNumber, adress, okpo);
    }
}
