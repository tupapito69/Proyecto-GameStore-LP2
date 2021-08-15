<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page session="true"  %>
<%
	HttpSession misession= (HttpSession) request.getSession();
	if(misession.getAttribute("codCargo")==null){
	    response.sendRedirect("index.jsp");
	}else{
	    /* String nivel=misession.getAttribute("codCargo").toString();
	    if(!nivel.equals("A01")){
	        response.sendRedirect("index.jsp");
	    } */
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Home</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap-grid.min.css" rel="stylesheet" type="text/css">
<link href="css/PanelIntranetIndex.css" rel="stylesheet" type="text/css">
<link href="css/bootstrapSideBar.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/SweetAlert.js"></script>
</head>
<body>
	<%@include file="MenuTemplate.html" %>
	<main class="" style="min-height: 100vh;">
		<div class="container">
			<div class="mt-1 mt-md-5">
				<h2 class="text-center m-3" style="color: gray;">Bienvenido a <span style="color: red;">GAME</span><span style="color:black;">STORE </span></h2>
				<h3 class="text-center" style="font-weight: bold;"><%= misession.getAttribute("NameComplet") %></h3>
				<h1 class="text-center">
					<svg xmlns="http://www.w3.org/2000/svg" width="160" height="160" fill="green" class="bi bi-person-check-fill" viewBox="0 0 16 16">
					  <path fill-rule="evenodd" d="M15.854 5.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 0 1 .708-.708L12.5 7.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
					  <path d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
					</svg>
				</h1>
				<h5 class="text-center text-info">Selecciona las opciones del menu para empezar</h5>
			</div>
		</div>
	</main>
	<%@include file="FooterTemplate.html" %>
	
</body>
</html>