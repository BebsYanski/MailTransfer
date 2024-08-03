package com.eaglestalwart.MailTransfer.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Recipient{

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String contact;
    private String destination;
    @OneToMany(mappedBy = "recipient")
    private List<Mail> mailList;



    public Recipient() {
    }

    public Recipient(String name, String email, String contact, String destination) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Mail> getMailList() {
        return mailList;
    }

    public void setMailList(List<Mail> mailList) {
        this.mailList = mailList;
    }

    @Override
    public String toString() {
        return "Recipient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact=" + contact +
                ", destination='" + destination + '\'' +
                ", mailList=" + mailList +
                '}';
    }
}
