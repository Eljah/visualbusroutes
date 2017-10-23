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
}
