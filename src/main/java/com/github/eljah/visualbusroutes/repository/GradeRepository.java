package com.github.eljah.visualbusroutes.repository;

import com.github.eljah.visualbusroutes.domain.Grade;
import com.google.appengine.api.datastore.Key;

import java.util.List;

/**
 * Created by eljah32 on 10/18/2017.
 */
public interface GradeRepository extends CustomRepository<Grade, Key> {
        List<Grade> findTop1ByCourseName(String country);

}
