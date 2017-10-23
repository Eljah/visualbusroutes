<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="gae" uri="/WEB-INF/googleKey.tld"%>

<%--
  Created by IntelliJ IDEA.
  User: pawel
  Date: 20.07.13
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title></title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
</head>
<body>
<form:form commandName="student">
	<form:hidden path="id" />
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
	<form:hidden path="address.id" />
	<div>
		<span>Country</span>
		<form:input path="address.country" />
	</div>
	<div>
		<span>City</span>
		<form:input path="address.city" />
	</div>
	<div>
		<span>Street</span>
		<form:input path="address.street" />
	</div>
	<div>
		<span>Building Number</span>
		<form:input path="address.buildingNumber" />
	</div>
	<script>
		var indexManager = (function() {
			var currentIdx = 0;
			return {
				setCurrentIndex: function(idx) {
					currentIdx = idx;
				},
				getNextIndex: function() {
					return currentIdx++;
				}
			}
		})();

		function addGrade(grade) {
			var idx = indexManager.getNextIndex();

			function getInputName(name) {
				return 'grades[' + idx + '].' + name;
			};
			function getId() {
				return 'grade' + idx;
			};
			function getValue(name) {
				return grade ? grade[name] : '';
			};

			var trContent = '<tr id="' + getId() + '">' +
					'<td>' +
					'	<input id="' + getId()+"_remove" + '" type="hidden" name="' + getInputName('removed') +'" value="false"/>' +
					'	<input type="hidden" name="' + getInputName('id') + '" value="' + getValue('id') + '"/>' +
					'	<input name="' + getInputName('courseName') + '" value="' + getValue('courseName') + '"/>' +
					'</td>' +
					'<td>' +
					'	<input name="' + getInputName('grade') + '" value="' + getValue('grade') + '"/>' +
					'</td>' +
					'<td>' +
					'	<a href="#" onclick="deleteGrade(\'' + getId() + '\')">' +
					'		Delete' +
					'	</a>' +
					'</td> ' +
					'</tr>';
			$('#gradeContainer tbody').append(trContent);
		}

		function deleteGrade(gradeContainer) {
			$('#' + gradeContainer).hide();
			$('#' + gradeContainer + "_remove").val("true");
		}
	</script>
	<div>
		<table id="gradeContainer">
			<thead>
				<tr>
					<th>Course</th>
					<th>Grade</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="grade" items="${student.grades}">
					<script>
						addGrade({
							"id":"<gae:key key="${grade.id}"/>",
							"courseName":"${grade.courseName}",
							"grade":${grade.grade}
						});
					</script>
				</c:forEach>
			</tbody>
		</table>
		<a href="#" onclick="addGrade(null)">Add</a>
	</div>
	<div>
		<input type="submit" value="Save raw" name="saveMethod"/>
		<input type="submit" value="Save repo" name="saveMethod"/>
	</div>
</form:form>
<%@ include file="../links.jsp" %>
</body>
</html>