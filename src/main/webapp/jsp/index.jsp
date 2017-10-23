<%--
  Created by IntelliJ IDEA.
  User: pawel
  Date: 20.07.13
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title></title>
</head>
<body>
	<div>
		<h1>Students</h1>
		<%@ include file="links.jsp"%>
	</div>

	<div>
		<h1>Other</h1>
		<a href="${pageContext.request.contextPath}/raw">raw controller</a><br/>
		<a href="${pageContext.request.contextPath}/rawJsp">raw jsp file</a><br/>
	</div>
</body>
</html>