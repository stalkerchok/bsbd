package ru.mirea.bsbd.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "client", schema = "public", catalog = "bsbd_homework")
public class ClientEntity {
    @Expose
    private int clientId;
    @Expose
    private String surname;
    @Expose
    private String name;
    @Expose
    private String patronymic;
    @Expose
    private String email;
    @Expose
    private String telephoneNumber;
    @Expose
    private String address;
    @Expose
    private Integer okpo;

    @Expose
    private Set<ActEntity> actEntities = new HashSet<>();

    @Id
    @Column(name = "client_id", nullable = false)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "surname", nullable = true, length = 100)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "patronymic", nullable = true, length = 100)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "telephone_number", nullable = true,  length = 50)
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 1000)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "okpo", nullable = true)
    public Integer getOkpo() {
        return okpo;
    }

    public void setOkpo(Integer okpo) {
        this.okpo = okpo;
    }


    @Basic
    @Column(name = "email", nullable = true, length = 100)
    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }



    //link to acts
    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "clientEntity")

    public Set<ActEntity> getActEntities() {
        return actEntities;
    }

    public void setActEntities(Set<ActEntity> actEntities) {
        this.actEntities = actEntities;
    }

    public void addActEntities(ActEntity actEntity) {
        actEntity.setClientEntity(this);
        this.actEntities.add(actEntity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return clientId == that.clientId &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(telephoneNumber, that.telephoneNumber) &&
                Objects.equals(address, that.address) &&
                Objects.equals(okpo, that.okpo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, surname, name, patronymic, telephoneNumber, address, okpo);
    }
}
