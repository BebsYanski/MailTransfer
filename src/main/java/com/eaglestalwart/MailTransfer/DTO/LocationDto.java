package com.eaglestalwart.MailTransfer.DTO;

import java.sql.Timestamp;
import java.util.Date;

public class LocationDto {
    private String longitude;
    private String latitude;
    private Timestamp timestamp;



    public LocationDto(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public LocationDto() {
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
}
