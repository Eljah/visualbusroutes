<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="gae" uri="/WEB-INF/googleKey.tld"%>

<%--
  Created by IntelliJ IDEA.
  User: pawel
  Date: 20.07.13
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title></title>
</head>
<body>
<table border="1">
	<tr>
		<th>ID</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Password</th>
		<th>Address</th>
		<th>Grades</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<c:forEach var="student" items="${students}">
		<tr>
			<td><gae:key key="${student.id}" /></td>
			<td>${student.firstName}</td>
			<td>${student.lastName}</td>
			<td>${student.password}</td>
			<td>${student.address.country} ${student.address.city} ${student.address.street} ${student.address.buildingNumber}</td>
			<td>
				<c:forEach var="grade" items="${student.grades}" varStatus="status">
					${grade.courseName} (${grade.grade})
					<c:if test="${!status.last}">, </c:if>
				</c:forEach>
			</td>
			<td>
				<a href="${pageContext.request.contextPath}/student/edit/<gae:key key="${student.id}"/>?type=raw">Edit raw</a><br/>
				<a href="${pageContext.request.contextPath}/student/edit/<gae:key key="${student.id}"/>?type=repo">Edit repo</a>
			</td>
			<td>
				<a href="${pageContext.request.contextPath}/student/delete/<gae:key key="${student.id}"/>?type=raw">Delete raw</a><br/>
				<a href="${pageContext.request.contextPath}/student/delete/<gae:key key="${student.id}"/>?type=repo">Delete repo</a>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="../links.jsp" %>
</body>
</html>