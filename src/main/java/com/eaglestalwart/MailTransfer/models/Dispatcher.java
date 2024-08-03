package com.eaglestalwart.MailTransfer.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(

        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_email",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "unique_phone",
                        columnNames = "phone_number"
                ),
                @UniqueConstraint(
                        name = "unique_firstname_lastname",
                        columnNames = {"first_name","last_name"}
                )
        }
)
public class Dispatcher {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String role = "Dispatcher";

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Long phoneNumber;

    @Column(nullable = false)
    private String agency;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "dispatcher")
    private List<Mail> mailList;

    public Dispatcher() {
    }

    public Dispatcher(String firstName, String lastName, String email, Long phoneNumber, String agency, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.agency = agency;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Mail> getMailList() {
        return mailList;
    }

    public void setMailList(List<Mail> mailList) {
        this.mailList = mailList;
    }

    @Override
    public String toString() {
        return "Dispatcher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", agency='" + agency + '\'' +
                ", password='" + password + '\'' +
                ", mailList=" + mailList +
                '}';
    }
}
