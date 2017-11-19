package com.github.eljah.visualbusroutes.dao;

import com.github.eljah.visualbusroutes.domain.BusRouteToCheck;
import com.github.eljah.visualbusroutes.domain.BusStopToCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by eljah32 on 10/30/2017.
 */
@Repository
public class BusRoutesToCheckDao {

    @Autowired
    @Qualifier("transactionManager2")
    private PlatformTransactionManager txManager;
    @PersistenceContext
    private EntityManager entityManager;

    private TransactionTemplate transactionTemplate;

    @PostConstruct
    protected void postConstruct() {
        transactionTemplate = new TransactionTemplate(txManager);
    }

    public Long findOsmIdByChecked(String checked) {
        List values=entityManager.createQuery("select s.osmId from BusRouteToCheck s where s.checked =:checked")
                .setParameter("checked",checked).getResultList();
        int size=values.size();

        Long toBereturned= (Long) values.get(ThreadLocalRandom.current().nextInt(0, size));
        entityManager.clear();
        //entityManager.flush();
        entityManager.close();
        //entityManager.
        //entityManager.getTransaction().commit();
        //entityManager.
        return toBereturned;
    }

    public Long findOsmIdByCheckedAkwaysFirst(String checked) {
        Long toBereturned= (Long) entityManager.createQuery("select s.osmId from BusRouteToCheck s where s.checked =:checked")
                .setParameter("checked",checked).setMaxResults(1).getResultList().get(0);
        entityManager.clear();
        //entityManager.flush();
        entityManager.close();
        //entityManager.
        //entityManager.getTransaction().commit();
        //entityManager.
        return toBereturned;
    }

}
