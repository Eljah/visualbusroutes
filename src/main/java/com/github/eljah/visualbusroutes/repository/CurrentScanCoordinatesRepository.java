package com.github.eljah.visualbusroutes.repository;

import com.github.eljah.visualbusroutes.domain.BusStop;
import com.github.eljah.visualbusroutes.domain.CurrentScanCoordinates;
import com.google.appengine.api.datastore.Key;

/**
 * Created by eljah32 on 10/22/2017.
 */
public interface CurrentScanCoordinatesRepository extends CustomRepository<CurrentScanCoordinates,Key> {
}
