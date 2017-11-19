package com.github.eljah.visualbusroutes.repository;

import com.github.eljah.visualbusroutes.domain.BusRoute;
import com.github.eljah.visualbusroutes.domain.BusRouteToCheck;
import com.google.appengine.api.datastore.Key;

import java.util.List;

/**
 * Created by eljah32 on 10/22/2017.
 */
public interface BusRouteRepository  extends CustomRepository<BusRoute, Long> {
    BusRoute findByOsmId(Long id);
    List<BusRoute> findTop1ByOsmId(Long id);
}
