package com.github.eljah.visualbusroutes.repository;

import com.github.eljah.visualbusroutes.domain.BusRoute;
import com.github.eljah.visualbusroutes.domain.BusStop;
import com.google.appengine.api.datastore.Key;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by eljah32 on 10/22/2017.
 */
public interface BusStopRepository extends CustomRepository<BusStop,Long> {
    BusStop findByOsmId(Long id);
    //@Query()
    List<BusStop> findByOsmIdIn(List<Long> ids);
}
