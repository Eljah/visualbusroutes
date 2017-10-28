package com.github.eljah.visualbusroutes.repository;

import com.github.eljah.visualbusroutes.domain.BusRouteToCheck;
import com.github.eljah.visualbusroutes.domain.BusStop;
import com.github.eljah.visualbusroutes.domain.BusStopToCheck;
import com.google.appengine.api.datastore.Key;

import java.util.List;

/**
 * Created by eljah32 on 10/22/2017.
 */
public interface BusStopToCheckRepository extends CustomRepository<BusStopToCheck,Key> {
    List<BusStopToCheck> findTop1ByChecked(String checked);
}
