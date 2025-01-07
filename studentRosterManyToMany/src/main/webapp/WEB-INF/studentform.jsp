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
		<title>New Student</title>
		<link rel="stylesheet" href="/css/style.css" />
	</head>
	<body>
		<div class="header">
			<a href="/dorms">Dashboard</a>
			<h1>New Student</h1>
		</div>
		<form:form action="/students/new" method="post" modelAttribute="student">
			<div class="entries">
				<span class="errors"><form:errors path="dorm" /></span>
				<div class="row">
					<form:label path="dorm">Dorm:</form:label>
					<form:select path="dorm">
						<c:forEach var="dorm" items="${dorms}">
							<form:option value="${dorm.id}" path="dorm">
								<c:out value="${dorm.name}" />
							</form:option>
						</c:forEach>
					</form:select>
				</div>	
				<span class="errors"><form:errors path="name" /></span>
				<div>
					<form:label path="name">Name: </form:label>
					<form:input path="name" />
				</div>
				<button class="formButton" type="submit">Create Student</button>
			</div>
		</form:form>
	</body>
</html>