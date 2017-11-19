package com.github.eljah.visualbusroutes.repository;

import com.github.eljah.visualbusroutes.domain.BusRouteToCheck;
import com.github.eljah.visualbusroutes.domain.BusStopToCheck;
import com.google.appengine.api.datastore.Key;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by eljah32 on 10/22/2017.
 */
public interface BusRouteToCheckRepository extends CustomRepository<BusRouteToCheck,Long> {
    List<BusRouteToCheck> findTop1ByChecked(String checked);
    List<Long> findTop1OsmIdByChecked(String checked);
    BusRouteToCheck findOneByChecked(String checked);
    BusRouteToCheck findOneByOsmId(Long id);
}
