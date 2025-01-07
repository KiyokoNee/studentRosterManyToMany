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
		<title>Class Students</title>
		<link rel="stylesheet" href="/css/style.css" />
	</head>
	<body>
		<h1>Students taking <c:out value="${lesson.name}"></c:out></h1>
		<a href="/dorms">Dashboard</a>
		<table id="studentDisplay">
			<thead>
				<tr>
					<th>Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="student" items="${classStudents}">
					<tr>
						<td><a href="/students/${student.id}"></a><c:out value="${student.name}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>