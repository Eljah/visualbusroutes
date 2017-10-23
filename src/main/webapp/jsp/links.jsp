<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%--
  Created by IntelliJ IDEA.
  User: pawel
  Date: 20.07.13
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a href="${pageContext.request.contextPath}/student/edit">Create</a><br/>
<a href="${pageContext.request.contextPath}/student/list?type=raw">List using raw entity manager</a><br/>
<a href="${pageContext.request.contextPath}/student/list?type=repo">List using spring data</a><br/><br/>

<a href="/_ah/admin">app engine admin</a>