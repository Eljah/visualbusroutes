package com.github.eljah.visualbusroutes.servlet.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: pawel
 * Date: 20.07.13
 * Time: 15:38
 */
@Controller
public class MainServlet {
	@RequestMapping("/")
	public String getMainPage() {
		return "/index";
	}
}
