package com.github.eljah.visualbusroutes.servlet.spring;

import com.github.eljah.visualbusroutes.domain.FakeStudent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * User: pawel
 * Date: 20.07.13
 * Time: 10:35
 */
@Controller
@RequestMapping("/fakeForm")
public class DoNohingFormHandler {
	@RequestMapping(method = GET)
	public String getForm() {
		return "/fakeForm";
	}

	@RequestMapping(method = POST)
	public String showSuccess(FakeStudent fake, ModelMap model) {
		String msg = null;
		String destination = null;

		if(fake.isValid()) {
			msg = "Student successfully loaded!";
			destination = "/success";
		} else {
			msg = "Broken student!";
			destination = "/error";
		}

		model.put("msg", msg);
		model.put("object", fake);
		return destination;
	}

	@ModelAttribute(value = "student")
	public FakeStudent getStudent() {
		return new FakeStudent();
	}
}
