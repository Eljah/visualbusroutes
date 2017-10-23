package com.github.eljah.visualbusroutes.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.appengine.repackaged.com.google.common.base.Strings.nullToEmpty;

/**
 * User: pawel
 * Date: 20.07.13
 * Time: 10:10
 */
public class RawServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String hello = req.getParameter("hello");

		resp.setContentType("text/plain");
		resp.getWriter().println("Raw HttpServlet");
		resp.getWriter().println("Hello " + nullToEmpty(hello));
	}
}
