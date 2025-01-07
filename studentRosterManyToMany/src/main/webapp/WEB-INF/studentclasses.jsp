<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><c:out value="${student.name}"></c:out></title>
		<link rel="stylesheet" href="/css/style.css" />
	</head>
	<body>
		<h1><c:out value="${student.name}"></c:out>'s Classes</h1>
		<a href="/dorms">Dashboard</a>
		<form:form action="/students/${student.id}/add" method="put" id="addClasses">
			<label>
				Classes
				<select id="classId" name="classId">
					<c:forEach var="lesson" items="${unselectedClasses}">
						<option value="${lesson.id}"><c:out value="${lesson.name}"></c:out></option>
					</c:forEach>
				</select>
			</label>
			<button type="submit">Add</button>
		</form:form>
		<table id="studentDisplay">
			<thead>
				<tr>
					<th>Class Name</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="lesson" items="${selectedClasses}">
					<tr>
						<td><a href="/classes/${lesson.id}"><c:out value="${lesson.name}"></c:out></a></td>
						<td><form:form action="/students/${student.id}/remove/${lesson.id}" method="put"><button class="link">Drop</button></form:form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>