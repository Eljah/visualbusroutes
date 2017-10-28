package com.github.eljah.visualbusroutes.servlet.spring;

import com.github.eljah.visualbusroutes.service.OSMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/stopnames", method = RequestMethod.GET)
    public String stopnames() {
        osmService.doOSMStopsNameExtraction();
        return "/helloSpring";
    }

    @RequestMapping(value = "/routestops", method = RequestMethod.GET)
    public String routestops() {
        osmService.doOSMRoutesStopsExtraction();
        return "/helloSpring";
    }


}
