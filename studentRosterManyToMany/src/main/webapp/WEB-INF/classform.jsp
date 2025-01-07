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
		<title>New Dorm</title>
		<link rel="stylesheet" href="/css/style.css">
	</head>
	<body>
		<div class="header">
			<h1>New Class</h1>
			<a href="/dorms">Dashboard</a>
		</div>
		<form:form action="/classes/new" method="post" modelAttribute="newClass">
			<div class="entries">	
				<span class="errors"><form:errors path="name" /></span>
				<div>
					<form:label path="name">Name: </form:label>
					<form:input path="name" />
				</div>
				<button class="formButton" type="submit">Add</button>
			</div>
		</form:form>
	</body>
</html>