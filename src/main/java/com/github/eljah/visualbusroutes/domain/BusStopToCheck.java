package com.github.eljah.visualbusroutes.domain;

import javax.jdo.annotations.PersistenceCapable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by eljah32 on 10/22/2017.
 */
@Entity
public class BusStopToCheck {
    @Id
    Long osmId;
    String checked;

    public long getOsmId() {
        return osmId;
    }

    public void setOsmId(long osmId) {
        this.osmId = osmId;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

//    @ManyToOne
//    public BusRouteToCheck busRouteToCheck;
//
//    public BusRouteToCheck getBusRouteToCheck() {
//        return busRouteToCheck;
//    }
//
//    public void setBusRouteToCheck(BusRouteToCheck busRouteToCheck) {
//        this.busRouteToCheck = busRouteToCheck;
//    }
}
