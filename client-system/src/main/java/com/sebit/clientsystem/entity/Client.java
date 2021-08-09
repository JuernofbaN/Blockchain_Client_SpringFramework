package com.sebit.clientsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "mail", nullable = false)
    private String mail;
    @Column(name = "password", nullable = false)
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Client(){

    }
    public Client(String name, String address, String mail, String password) {
        super();
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
