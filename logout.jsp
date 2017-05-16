<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pl">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Planszówki - wyloguj</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.jsp">Planszówki</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="index.jsp">Home</a></li>
			</ul>
			<c:choose>
				<c:when test="${logged == true}">
					<ul class="nav navbar-nav navbar-right">
						<li><p class="navbar-text">Zalogowany jako ${login}</p></li>
						<li><a href="#">Edytuj profil</a></li>
						<li><a href="#">Wyloguj</a></li>
					</ul>
				</c:when>
				<c:when test="${empty logged or logged == false}">
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
						<li><a href="rejestracja.jsp"><span class="glyphicon glyphicon-user"></span> Zarejestruj</a></li>
					</ul>
				</c:when>
			</c:choose>
		</div>
	</nav>			
	
	<div class="container">	
		<div class="row">	
			<div class="col-xs-12 col-sm-6 col-sm-push-3">
				<c:choose>
				    <c:when test="${not empty message}">
				        <div class="alert alert-danger" role="alert">
							${message} <a href="index.jsp">Powrót do serwisu.</a>
						</div>
				    </c:when>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>
