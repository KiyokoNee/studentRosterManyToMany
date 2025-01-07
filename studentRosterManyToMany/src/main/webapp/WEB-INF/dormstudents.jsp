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
		<title>Dorm Students</title>
		<link rel="stylesheet" href="/css/style.css" />
	</head>
	<body>
		<a href="/dorms">Dashboard</a>
		<h1><c:out value="${dorm.name}"></c:out> Students</h1>
		<form:form action="/dorms/${dorm.id}/add" method="put" id="addStudents">
			<label>
				Students
				<select id="studentid" name="studentid">
					<c:forEach var="student" items="${allStudents}">
						<option value="${student.id}"><c:out value="${student.name}"></c:out> <c:if test="${student.dorm != null}">(<c:out value="${student.dorm.name}"></c:out>)</c:if></option>
					</c:forEach>
				</select>
			</label>
			<button type="submit">Add</button>
		</form:form>
		<table id="studentDisplay">
			<thead>
				<tr>
					<th>Student</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="student" items="${dormStudents}">
					<tr>
						<td><a href="/students/${student.id}"><c:out value="${student.name}"></c:out></a></td>
						<td><form:form action="/dorms/${dorm.id}/remove/${student.id}" method="put"><button class="link">Remove</button></form:form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>