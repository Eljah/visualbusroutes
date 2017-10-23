package com.github.eljah.visualbusroutes.domain;

import javax.persistence.Entity;

/**
 * Created by eljah32 on 10/16/2017.
 */

@Entity
public class Nosence extends BaseEntity {
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
