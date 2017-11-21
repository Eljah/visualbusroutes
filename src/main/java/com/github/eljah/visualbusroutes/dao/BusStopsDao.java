package com.github.eljah.visualbusroutes.dao;

import com.github.eljah.visualbusroutes.domain.BusStop;
import com.github.eljah.visualbusroutes.domain.Student;
import com.google.appengine.api.datastore.Key;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eljah32 on 10/29/2017.
 */
@Repository
public class BusStopsDao {

    //@Autowired
    @Qualifier("transactionManager2")
    private PlatformTransactionManager txManager2;
    @PersistenceContext
    private EntityManager entityManager;

    private TransactionTemplate transactionTemplate;

    @PostConstruct
    protected void postConstruct() {
        transactionTemplate = new TransactionTemplate(txManager2);
    }

    public Long countByOsmIds(List<Long> osmIds) {
        Long toBeReturned= (Long) entityManager.createQuery("select count(s) from BusStop s where s.osmId in (:id)")
                .setParameter("id",osmIds)
                .getSingleResult();
        entityManager.clear();
        //entityManager.flush();
        entityManager.close();
        //entityManager.getTransaction().commit();
        if (osmIds.contains(993540888l)) //very strange spetial case!!!!
        {toBeReturned=toBeReturned+1;}
        return toBeReturned;
    }

    public List<Long> getStopsMissing(List<Long> osmIds) {
        List<BusStop> toBeReturned=  entityManager.createQuery("select s from BusStop s where s.osmId in (:id)")
                .setParameter("id",osmIds)
                .getResultList();
        entityManager.clear();
        //entityManager.flush();
        entityManager.close();
        //entityManager.getTransaction().commit();

        List<Long> copy= new ArrayList<>(osmIds);

        for (BusStop busStop:toBeReturned)
        {
            if (copy.contains(busStop.getOsmId()))
            {
                copy.remove(busStop.getOsmId());
            }
        }
        return copy;
    }

}
