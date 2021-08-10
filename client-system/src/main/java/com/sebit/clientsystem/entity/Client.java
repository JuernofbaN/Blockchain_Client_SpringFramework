package com.sebit.clientsystem.entity;

import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

@Entity
@Table(name = "clients")
public class Client {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "address", nullable = false)
    private String address;
    @Id
    @Column(name = "mail", nullable = false)
    private String mail;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "wallet", nullable = false)
    private String wallet;

    public Client(){

    }
    public Client(String name, String address, String mail, String password) {
        super();
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.password = password;
        this.wallet = getSHA256Hash(mail);
    }

    public Client(Client client) {
        this.name = client.name;
        this.address = client.address;
        this.mail = client.mail;
        this.password = client.password;
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

    public String getWallet() { return wallet; }

    public void setWallet(String wallet) { this.wallet = wallet; }
    /**
     * Returns a hexadecimal encoded SHA-256 hash for the input String.
     * It is used for getting Wallets.
     * @param data
     * @return
     */
    private String getSHA256Hash(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return DatatypeConverter.printHexBinary(hash); // make it printable
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
