<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%--
  Created by IntelliJ IDEA.
  User: pawel
  Date: 20.07.13
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title></title>
</head>
<body>
	<form:form commandName="student">
		<div>
			<span>First name:</span>
			<form:input path="firstName"/>
		</div>
		<div>
			<span>Last name:</span>
			<form:input path="lastName" />
		</div>
		<div>
			<span>Password:</span>
			<form:password path="password" />
		</div>
		<div>
			<input type="submit" value="send student" />
		</div>
	</form:form>
</body>
</html>