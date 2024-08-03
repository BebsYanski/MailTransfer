package com.eaglestalwart.MailTransfer.DTO;

import java.time.LocalDateTime;

public class MailDetailsDto {
    private Long id;
    private String trackingNumber;
    private double cost;
    private LocalDateTime mailCreationDate;
    private String deliveryStatus;
    private String content;
    private String senderName;
    private String senderContact;
    private String senderAgency;
    private String senderEmail;
    private String recipientName;
    private String recipientContact;
    private String recipientDestination;
    private String recipientEmail;
    private Long driverId;
    private String driverFirstName;
    private String driverLastName;
    private String latitude;
    private String longitude;

    public MailDetailsDto() {
    }

    public MailDetailsDto(Long id, String trackingNumber, double cost, LocalDateTime mailCreationDate, String deliveryStatus, String content, String senderName, String senderContact, String senderAgency, String senderEmail, String recipientName, String recipientContact, String recipientDestination, String recipientEmail, Long driverId, String driverFirstName, String driverLastName, String latitude, String longitude) {
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.cost = cost;
        this.mailCreationDate = mailCreationDate;
        this.deliveryStatus = deliveryStatus;
        this.content = content;
        this.senderName = senderName;
        this.senderContact = senderContact;
        this.senderAgency = senderAgency;
        this.senderEmail = senderEmail;
        this.recipientName = recipientName;
        this.recipientContact = recipientContact;
        this.recipientDestination = recipientDestination;
        this.recipientEmail = recipientEmail;
        this.driverId = driverId;
        this.driverFirstName = driverFirstName;
        this.driverLastName = driverLastName;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderContact() {
        return senderContact;
    }

    public void setSenderContact(String senderContact) {
        this.senderContact = senderContact;
    }

    public String getSenderAgency() {
        return senderAgency;
    }

    public void setSenderAgency(String senderAgency) {
        this.senderAgency = senderAgency;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientContact() {
        return recipientContact;
    }

    public void setRecipientContact(String recipientContact) {
        this.recipientContact = recipientContact;
    }

    public String getRecipientDestination() {
        return recipientDestination;
    }

    public void setRecipientDestination(String recipientDestination) {
        this.recipientDestination = recipientDestination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public void setDriverFirstName(String driverFirstName) {
        this.driverFirstName = driverFirstName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public void setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
    }

    public LocalDateTime getMailCreationDate() {
        return mailCreationDate;
    }

    public void setMailCreationDate(LocalDateTime mailCreationDate) {
        this.mailCreationDate = mailCreationDate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
