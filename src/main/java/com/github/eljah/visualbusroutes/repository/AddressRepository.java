package com.github.eljah.visualbusroutes.repository;

import com.github.eljah.visualbusroutes.domain.Address;
import com.google.appengine.api.datastore.Key;

import java.util.List;

/**
 * Created by eljah32 on 10/16/2017.
 */
public interface AddressRepository  extends CustomRepository<Address, Key> {
    List<Address> findTop1ByCountry(String country);
}
