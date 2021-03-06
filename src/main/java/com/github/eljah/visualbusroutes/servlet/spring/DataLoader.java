package com.github.eljah.visualbusroutes.servlet.spring;

import com.github.eljah.visualbusroutes.service.OSMService;
import com.google.appengine.api.datastore.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.google.appengine.repackaged.com.google.common.base.Strings.nullToEmpty;

/**
 * Created by eljah32 on 10/22/2017.
 */

@Controller
@RequestMapping("/load")
public class DataLoader {

    @Autowired
    OSMService osmService;


    @RequestMapping(value = "/findroutes", method = RequestMethod.GET)
    public String findroutes() {
        osmService.scanAndGoForward();
        return "/helloSpring";
    }


    @RequestMapping(value = "/routenames", method = RequestMethod.GET)
    public String routenames() {
        osmService.doOSMRoutesNameExtraction();
        return "/helloSpring";
    }

    @RequestMapping(value = "/stopnames/{id}", method = RequestMethod.GET)
    public String stopnamesId(@PathVariable Long id) {
        osmService.doOSMStopsNameExtractionId(id);
        return "/helloSpring";
    }

    @RequestMapping(value = "/stopnames", method = RequestMethod.GET)
    public String stopnames() {
        osmService.doOSMStopsNameExtraction();
        return "/helloSpring";
    }

    @RequestMapping(value = "/routestops", method = RequestMethod.GET)
    public String routestops() {
        Long busRouteToCheckId = osmService.obtainCheckedBusRouteOsmId("1");
        List<Long> busStopsNodeIds=osmService.busStopsNodeIds(busRouteToCheckId);
        Long count=osmService.getCountOfOsmIdsExisted(busStopsNodeIds);
        Long weUpdate=osmService.saveOSMRoutesStopsExtraction(busRouteToCheckId,count,busStopsNodeIds);
        //Long weUpdate=osmService.doOSMRoutesStopsExtraction(busRouteToCheckId);
        if (weUpdate!=null){osmService.setBusRouteStatus2(weUpdate);};
        return "/helloSpring";
    }


}
