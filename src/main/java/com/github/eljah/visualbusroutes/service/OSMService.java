package com.github.eljah.visualbusroutes.service;

import com.github.eljah.visualbusroutes.domain.*;
import com.github.eljah.visualbusroutes.repository.*;
import com.google.appengine.api.quota.QuotaService;
import com.google.appengine.api.quota.QuotaServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import de.westnordost.osmapi.OsmConnection;
import de.westnordost.osmapi.map.MapDataDao;
import de.westnordost.osmapi.map.data.*;
import de.westnordost.osmapi.map.handler.MapDataHandler;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by eljah32 on 10/22/2017.
 */
@Service
//@Transactional
public class OSMService {
    double LONGITUDE_START_CENTER = 49.121007;
    double LATITUDE_START_CENTER = 55.787365;


    double LONGITUDE_START = 48.833744;
    double LATITUDE_START = 55.693307;
    double LONGITUDE_STOP = 49.261698;
    double LATITUDE_STOP = 55.897801;

    double LONGITUDE_STEP = 0.005; //0.02
    double LATITUDE_STEP = 0.005; //0.02

    double LONGITUDE_CURRENT = 48.833744;
    double LATITUDE_CURRENT = 55.693307;


    @Autowired
    private BusRouteRepository busRouteRepository;
    @Autowired
    private BusStopRepository busStopRepository;
    @Autowired
    private BusRouteToCheckRepository busRouteToCheckRepository;
    @Autowired
    private BusStopToCheckRepository busStopToCheckRepository;
    @Autowired
    private CurrentScanCoordinatesRepository currentScanCoordinatesRepository;

    //@Autowired
    //private JpaContext jpaContext;

    QuotaService quotaService = QuotaServiceFactory.getQuotaService();
    ;

    public long getMegacycles() {
        return quotaService.getCpuTimeInMegaCycles();
    }

    public CurrentScanCoordinates getCurrentScanCoordinates() {
        CurrentScanCoordinates currentScanCoordinates;
        try {
            currentScanCoordinates = currentScanCoordinatesRepository.findAll().get(0);
        } catch (Exception e) {
            currentScanCoordinates = new CurrentScanCoordinates();
            currentScanCoordinates.setLatitude(LATITUDE_START_CENTER);
            currentScanCoordinates.setLongitude(LONGITUDE_START_CENTER);
            currentScanCoordinatesRepository.save(currentScanCoordinates);
        }
        LATITUDE_CURRENT = currentScanCoordinates.getLatitude();
        LONGITUDE_CURRENT = currentScanCoordinates.getLongitude();
        return currentScanCoordinates;
    }

    public void scanAndGoForward() {
        long befor = getMegacycles();
        CurrentScanCoordinates currentScanCoordinates = getCurrentScanCoordinates();
        doOSMRoutesAndStopsExtraction();
        double currentLongitude = currentScanCoordinates.getLongitude();
        double currentLatitude = currentScanCoordinates.getLatitude();
        if (currentLongitude < LONGITUDE_STOP) {
            currentScanCoordinates.setLongitude(currentLongitude + LONGITUDE_STEP);
        } else {
            if (currentLatitude < LATITUDE_STOP) {
                currentScanCoordinates.setLongitude(LONGITUDE_START);
                currentScanCoordinates.setLatitude(currentLatitude + LATITUDE_STEP);
            } else {
                currentScanCoordinates.setLongitude(LONGITUDE_START);
                currentScanCoordinates.setLatitude(LATITUDE_START);
            }
        }
        currentScanCoordinatesRepository.save(currentScanCoordinates);
        long after = getMegacycles();
        System.out.println("Megacycles quota lost" + (after - befor));
    }

    public void doOSMRoutesAndStopsExtraction() {

        OsmLatLon min = new OsmLatLon(LATITUDE_CURRENT, LONGITUDE_CURRENT);
        OsmLatLon max = new OsmLatLon(LATITUDE_CURRENT + LATITUDE_STEP, LONGITUDE_CURRENT + LONGITUDE_STEP);
        BoundingBox kazanBuses = new BoundingBox(min, max);
        try {
            getMapDao().getMap(kazanBuses, mapDataHandler);
        } catch (de.westnordost.osmapi.common.errors.OsmApiReadResponseException e) {
            System.out.println(e.toString());

        } catch (de.westnordost.osmapi.common.errors.OsmConnectionException e) {
            System.out.println(e.toString());
        }

    }

    MapDataHandler mapDataHandler = new MapDataHandler() {
        public void handle(BoundingBox boundingBox) {
        }

        public void handle(Node node) {
            if (node.getTags() != null && node.getTags().get("highway") != null && node.getTags().get("highway").equals("bus_stop")) {
//                System.out.println(node.getTags().get("name"));
//                System.out.println(node.getTags().get("name:tt"));
//                System.out.println(node.getTags().get("name:ru"));
//                System.out.println(node.getTags().get("name:en"));
                BusStopToCheck busStopToCheck = new BusStopToCheck();
                busStopToCheck.setOsmId(node.getId());
                busStopToCheck.setChecked("0");
                busStopToCheckRepository.save(busStopToCheck);
            }
        }

        public void handle(Way way) {
        }

        public void handle(Relation relation) {
            if (relation.getTags().get("type").equals("route")) {
                if (relation.getTags().get("route").equals("bus")) {
                    System.out.println(relation.getTags().get("name"));
                    BusRouteToCheck busRouteToCheck = new BusRouteToCheck();
                    busRouteToCheck.setChecked("0");
                    busRouteToCheck.setOsmId(relation.getId());
                    busRouteToCheckRepository.save(busRouteToCheck);
                    //busRoutes.put(relation.getTags().get("name"), relation);
                }
            }
        }
    };

    @Transactional
    public void doOSMRoutesNameExtraction() {
        BusRouteToCheck busRouteToCheck = obtainCheckedBusRoute("0");
        Relation bus = getMapDao().getRelation(busRouteToCheck.getOsmId());

        BusRoute busRoute = new BusRoute();
        busRoute.setIdOsm(bus.getId());
        busRoute.setName(bus.getTags().get("name"));
        busRoute.setName_en(bus.getTags().get("name:en"));
        busRoute.setName_tt(bus.getTags().get("name:tt"));
        busRoute.setName_ru(bus.getTags().get("name:ru"));
        busRouteToCheck.setChecked("1");
        busRouteRepository.save(busRoute);
        busRouteToCheckRepository.save(busRouteToCheck);
    }

    BusRouteToCheck obtainCheckedBusRoute(String checkStatus) {
        List<BusRouteToCheck> busRouteToCheckList = busRouteToCheckRepository.findTop1ByChecked(checkStatus);

        if (busRouteToCheckList != null && busRouteToCheckList.size() > 0) {
            BusRouteToCheck busRouteToCheck = busRouteToCheckList.get(0);
            Long toRet = busRouteToCheck.getOsmId();
            return busRouteToCheck;
        } else {
            return null;
        }
    }

    @Transactional
    public void doOSMStopsNameExtraction() {
        BusStopToCheck busStopToCheck = obtainCheckedBusStop("0");
        Node stop = getMapDao().getNode(busStopToCheck.getOsmId());

        BusStop busStop = new BusStop();
        busStop.setIdOsm(stop.getId());
        busStop.setName(stop.getTags().get("name"));
        busStop.setName_en(stop.getTags().get("name:en"));
        busStop.setName_tt(stop.getTags().get("name:tt"));
        busStop.setName_ru(stop.getTags().get("name:ru"));
        busStopToCheck.setChecked("1");
        busStopRepository.save(busStop);
        busStopToCheckRepository.save(busStopToCheck);
    }

    @Transactional
    public void doOSMRoutesStopsExtraction() {
        BusRouteToCheck busRouteToCheck = obtainCheckedBusRoute("1");
        BusRoute busRoute = busRouteRepository.findByIdOsm(busRouteToCheck.getOsmId());
        Relation bus = getMapDao().getRelation(busRouteToCheck.getOsmId());
        List<BusStop> busStopList = new ArrayList<>();
        List<Long> busStopsNodeIds = new ArrayList<>();
        for (RelationMember member : bus.getMembers()) {
            if (member.getType().equals(Element.Type.WAY)) {
                Way line = getMapDao().getWay(member.getRef());
                for (Long id : line.getNodeIds()) {
                    //routeNodes.add(getMapDao().getNode(id));
                    Node node = getMapDao().getNode(id);
                    if (node.getTags() != null && node.getTags().get("highway") != null && node.getTags().get("highway").equals("bus_stop")) {
                        System.out.println("Bus stop osm id " + id);
                        busStopsNodeIds.add(id);
                    }
                }

            }

            //busRouteToCheckRepository.save(busRouteToCheck);
        }
        for (Long bs : busStopsNodeIds) {
            System.out.println("BS" + bs);
        }

        if (busStopsNodeIds.size() > 0) {

            busStopList = busStopRepository.findByIdOsmIn(busStopsNodeIds);
            for (BusStop bs : busStopList) {
                busRoute.getBusStopList().add(bs);
            }
            busRouteToCheck.setChecked("2");
            busRouteRepository.save(busRoute);
        }
    }

    BusStopToCheck obtainCheckedBusStop(String checkStatus) {
        List<BusStopToCheck> busStopToCheckList = busStopToCheckRepository.findTop1ByChecked(checkStatus);

        if (busStopToCheckList != null && busStopToCheckList.size() > 0) {
            BusStopToCheck busStopToCheck = busStopToCheckList.get(0);
            Long toRet = busStopToCheck.getOsmId();
            return busStopToCheck;
        } else {
            return null;
        }
    }


    public MapDataDao getMapDao() {
        OsmConnection osm = new OsmConnection(
                "http://api.openstreetmap.org/api/0.6/",
                "BusRouteDataExtractor 1.0", null);

        return new MapDataDao(osm);
    }

}
