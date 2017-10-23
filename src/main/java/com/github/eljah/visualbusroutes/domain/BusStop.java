package com.github.eljah.visualbusroutes.domain;

import com.google.appengine.datanucleus.annotations.Unowned;

import javax.jdo.annotations.PersistenceCapable;
import javax.persistence.*;
import java.util.List;

/**
 * Created by eljah32 on 10/22/2017.
 */

@Entity
public class BusStop extends BaseEntity {

    //@Column(unique = true)
    public Long osmId;
    public String name;
    public String name_ru;
    public String name_tt;
    public String name_en;

    @Unowned
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "busRouteList")
    List<BusRoute> busRoutes;

    @Override
    public int hashCode()
    {
        return osmId.hashCode()+name.hashCode()+name_en.hashCode()+name_tt.hashCode()+name_ru.hashCode();
    }
}
