package com.eaglestalwart.MailTransfer.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(

        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_tracking_id",
                        columnNames = "tracking_id"
                )
        }
)
public class Mail {

    @Id
    @GeneratedValue
    private Long id;
    private double weight;
    @Column(
            nullable = false,
            unique = true
    )
    private String trackingId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private boolean priority;

    private LocalDateTime dispatchDate;
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private String deliveryStatus = "PENDING";

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id", nullable = false)
    private Sender sender;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient_id", nullable = false)
    private Recipient recipient;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = true)
    private Driver driver;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dispatcher_id")
    private Dispatcher dispatcher;


    public Mail() {
    }

    public Mail(double weight, String trackingId, String content, boolean priority, double cost, String paymentMethod, LocalDateTime dispatchDate, LocalDateTime creationDate, String deliveryStatus) {
        this.weight = weight;
        this.content = content;
        this.priority = priority;
        this.cost = cost;
        this.paymentMethod = paymentMethod;
        this.dispatchDate = dispatchDate;
        this.deliveryStatus = deliveryStatus;
    }

    public Mail(String content, double cost, String paymentMethod, boolean priority) {
        this.content = content;
        this.cost = cost;
        this.paymentMethod = paymentMethod;
        this.priority = priority;
    }

    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }

    @PrePersist
    public void generateTrackingId(){
        if (trackingId == null){
        this.trackingId = new CustomIdGenerator().generateTrackingId();
        this.creationDate = LocalDateTime.now();

        }

    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }



    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
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

    public LocalDateTime getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(LocalDateTime dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "id=" + id +
                ", weight=" + weight +
                ", trackingId='" + trackingId + '\'' +
                ", priority=" + priority +
                ", cost=" + cost +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", dispatchDate=" + dispatchDate +
                ", creationDate=" + creationDate +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                ", location=" + location +
                ", route=" + route +
                ", sender=" + sender +
                ", recipient=" + recipient +
                ", driver=" + driver +
                ", dispatcher=" + dispatcher +
                '}';
    }
}
