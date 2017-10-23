package com.github.eljah.visualbusroutes.domain;


import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Unique;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eljah32 on 10/22/2017.
 */
@Entity
public class BusRouteToCheck extends BaseEntity {
    //@Column(unique = true)
    //@Id
    Long osmId;
    String checked;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "busRouteToCheck")
    List<BusStopToCheck> busStopToCheckList=new ArrayList<>();

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

    public List<BusStopToCheck> getBusStopToCheckList() {
        return busStopToCheckList;
    }

    public void setBusStopToCheckList(List<BusStopToCheck> busStopToCheckList) {
        this.busStopToCheckList = busStopToCheckList;
    }
}
