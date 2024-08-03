package com.eaglestalwart.MailTransfer.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Route {

    @Id
    @GeneratedValue
    private Long routeId;
    private String routeName;
    private String origin;
    private String destination;
    private String stops;
    @OneToMany(mappedBy = "route")
    private List<Mail> mailList;
    @OneToMany(mappedBy = "route")
    private List<Driver> drivers;


    @PrePersist
    public void generateRouteName(){
        this.routeName = this.origin+"-"+this.destination;
    }

    public Route() {
    }

    public Route( String routeName, String origin, String destination, String stops) {
        this.routeName = routeName;
        this.origin = origin;
        this.destination = destination;
        this.stops = stops;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return this.routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }
}
