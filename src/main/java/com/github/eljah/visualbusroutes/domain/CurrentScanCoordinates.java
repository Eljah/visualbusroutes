package com.github.eljah.visualbusroutes.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by eljah32 on 10/22/2017.
 */

@Entity
public class CurrentScanCoordinates  {
    @Id
    int id=1;
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
