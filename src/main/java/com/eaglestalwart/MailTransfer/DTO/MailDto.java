package com.eaglestalwart.MailTransfer.DTO;

import java.time.LocalDateTime;

public class MailDto {
    private String content;
    private double cost;
    private String paymentMethod;
    private boolean priority;

    private String recipientName;
    private String recipientEmail;
    private String recipientContact;
    private String recipientDestination;

    private String senderName;
    private String senderEmail;
    private String senderContact;
    private String senderAgency;


    public MailDto() {
    }

    public MailDto(String content, double cost, String paymentMethod, boolean priority, String recipientName, String recipientEmail, String recipientContact, String recipientDestination, String senderName, String senderEmail, String senderContact, String senderAgency) {
        this.content = content;
        this.cost = cost;
        this.paymentMethod = paymentMethod;
        this.priority = priority;
        this.recipientName = recipientName;
        this.recipientEmail = recipientEmail;
        this.recipientContact = recipientContact;
        this.recipientDestination = recipientDestination;
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.senderContact = senderContact;
        this.senderAgency = senderAgency;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
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
}
