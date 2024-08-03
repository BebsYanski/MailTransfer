package com.eaglestalwart.MailTransfer.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Sender{

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String contact;
    private String agency;
    @OneToMany(mappedBy = "sender")
    private List<Mail> mailList;



    public Sender() {
    }

    public Sender(String name, String email, String contact, String agency) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.agency = agency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public List<Mail> getMailList() {
        return mailList;
    }

    public void setMailList(List<Mail> mailList) {
        this.mailList = mailList;
    }

    @Override
    public String toString() {
        return "Sender{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact=" + contact +
                ", agency='" + agency + '\'' +
                ", mailList=" + mailList +
                '}';
    }
}
