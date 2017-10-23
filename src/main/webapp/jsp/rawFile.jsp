<%@ page import="com.google.appengine.repackaged.com.google.common.base.Strings" %>
<%--
  Created by IntelliJ IDEA.
  User: pawel
  Date: 20.07.13
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title></title>
</head>
<body>
	<p>Raw JSP file</p>
	Hello <%= Strings.nullToEmpty(request.getParameter("hello")) %>
</body>
</html>