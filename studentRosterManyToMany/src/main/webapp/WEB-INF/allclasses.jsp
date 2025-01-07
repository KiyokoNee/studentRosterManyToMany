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
		<title>All Classes</title>
		<link rel="stylesheet" href="css/style.css" />
	</head>
	<body>
		<h1>All Classes</h1>
		<a href="/dorms">Dashboard</a>
		<table id="dormDisplay">
			<thead>
				<tr>
					<th>Class</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="lesson" items="${classes}">
					<tr>
						<td><a href="/classes/${lesson.id}"><c:out value="${lesson.name}"></c:out></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>