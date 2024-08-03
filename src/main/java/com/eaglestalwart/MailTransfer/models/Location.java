package com.eaglestalwart.MailTransfer.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue
    private Long id;
    private String longitude;
    private String latitude;
    private Timestamp timestamp;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Mail> mailList;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Driver> driver;

    public List<Driver> getDriver() {
        return driver;
    }

    public void setDriver(List<Driver> driver) {
        this.driver = driver;
    }

    public Location() {
    }


    public Location(String longitude, String latitude, Timestamp timestamp) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = timestamp;
    }

    public Location(Long id, String longitude, String latitude, Timestamp timestamp, List<Mail> mailList) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = timestamp;
        this.mailList = mailList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<Mail> getMailList() {
        return mailList;
    }

    public void setMailList(List<Mail> mailList) {
        this.mailList = mailList;
    }
}
