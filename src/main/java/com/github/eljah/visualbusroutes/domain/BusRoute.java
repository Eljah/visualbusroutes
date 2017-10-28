package com.github.eljah.visualbusroutes.domain;

import com.google.appengine.datanucleus.annotations.Unowned;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Unique;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;

/**
 * Created by eljah32 on 10/22/2017.
 */

@Entity
public class BusRoute extends BaseEntity {
    //@Column(unique = true)
    public Long osmId;
    public String name;
    public String name_ru;
    public String name_tt;
    public String name_en;

    @Unowned
    List<BusRoute> busRouteList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_ru() {
        return name_ru;
    }

    public void setName_ru(String name_ru) {
        this.name_ru = name_ru;
    }

    public String getName_tt() {
        return name_tt;
    }

    public void setName_tt(String name_tt) {
        this.name_tt = name_tt;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public List<BusRoute> getBusRouteList() {
        return busRouteList;
    }

    public void setBusRouteList(List<BusRoute> busRouteList) {
        this.busRouteList = busRouteList;
    }

    public Long getOsmId() {
        return osmId;
    }

    public void setOsmId(Long osmId) {
        this.osmId = osmId;
    }


}
