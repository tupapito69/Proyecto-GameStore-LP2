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
<title>Consultas</title>
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
		<div class="d-flex" id="wrapper">
	        <div class="bg-light border-right" id="sidebar-wrapper">
	            <div class="sidebar-heading">Realizar Acciones</div>
	            <div class="list-group list-group-flush">
	                <a class="list-group-item list-group-item-action bg-light" href="" id="consultasV">Consultas Ventas</a>
	                <a class="list-group-item list-group-item-action bg-light" href="" id="consultasC">Consultas Clientes</a>
	            </div>
	        </div>
	        <!-- Page Content-->
	        <div id="page-content-wrapper">
	        	<div class="container-fluid">
	        
	        		<button class="btn btn-primary m-3" id="menu-toggle">Realizar Acciones</button>
					<div id="divContentIni">
	        			<h2 class="text-center m-3">Bienvenido <span style="font-weight: bold;"><%= misession.getAttribute("NameComplet") %></span></h2>
						<h4 class="text-center m-3">Consultas</h4>
	        		</div>
	        		
	        		
	        		<!-- Consultar Ventas -->
						<div id="ConsultaVenta" class="d-none">
							<form autocomplete="off">
								<h3 class="text-center">Consultar Ventas</h3>
								<div class="mr-3 ml-3  mb-3">
									<div class="row">
										<div class="col-12 col-md-8">
											<label for="fechaDesde" class="form-label">DNI:</label> <input
												type="text" class="form-control" id="DNIv"
												placeholder="Ingrese DNI del Cliente" value="">
										</div>
										<div class="col-12 col-md-4">
											<button type="button"
												class="p-2 btn btn-success form-control mb-sm-2"
												style="height: 100%;" id="btn-ConsultarV"><i class="bi bi-search"></i> Consultar</button>
										</div>
									</div>
								</div>
							</form>
							<div class="row">
								<div class="col-md-6">
									<h3>Facturas</h3>
									<div class="table-responsive" style="max-height: 50vh;">
										<table class="table">
											<thead>
												<tr>
													<th scope="col">Codigo Venta</th>
													<th scope="col">Codigo Empleado</th>
													<th scope="col">Fecha</th>
													<th scope="col">Precio Total</th>
													<th scope="col">Cupon</th>
												</tr>
											</thead>
											<tbody id="bodyTabConsultasV">
											</tbody>
										</table>
									</div>
								</div>
								<div class="col-md-6">
									<h3>Productos <span id="ProducCodventa"></span></h3>
									<div class="table-responsive" style="max-height: 70vh;">
										<table class="table">
											<thead>
												<tr>
													<th scope="col">Codigo Producto</th>
													<th scope="col">Cantidad</th>
													<th scope="col">Precio Unidad</th>
													<th scope="col">Total</th>
												</tr>
											</thead>
											<tbody id="bodyTabConsultasV2">
											</tbody>
										</table>
									</div>
								</div>
							</div>
							
							<div class="d-flex justify-content-center mt-3">
								<div class="table-responsive col-md-6" style="max-height: 25vh;">
									<h3>Cupón</h3>
									<table class="table">
										<thead>
											<tr>
												<th scope="col">Codigo Cupon</th>
												<th scope="col">Importe Descuento</th>
											</tr>
										</thead>
										<tbody id="bodyTabConsultasV3">
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!-- Consultar Cliente -->
						<div id="ConsultaCliente" class="d-none">
							<form autocomplete="off">
								<h3 class="text-center">Consultar Clientes</h3>
								<div class="mr-3 ml-3  mb-3">
									<div class="row">
										<div class="col-12 col-md-8">
											<label for="fechaDesde" class="form-label">DNI:</label> <input
												type="text" class="form-control" id="DNIc"
												placeholder="Ingrese DNI del Cliente" value="">
										</div>
										<div class="col-12 col-md-4">
											<button type="button"
												class="p-2 btn btn-success form-control mb-sm-2"
												style="height: 100%;" id="btn-ConsultarC"><i class="bi bi-search"></i> Consultar</button>
										</div>
									</div>
								</div>
							</form>
							<div class="table-responsive" style="max-height: 35vh;">
								<table class="table">
									<thead>
										<tr>
											<th scope="col">DNI</th>
											<th scope="col">Nombre</th>
											<th scope="col">Apellido Paterno</th>
											<th scope="col">Apellido Materno</th>
											<th scope="col">Telefono</th>
										</tr>
									</thead>
									<tbody id="bodyTabConsultasC">
									</tbody>
								</table>
							</div>
						</div>
				</div>
			</div>
		</div>
	</main>
	<%@include file="FooterTemplate.html" %>
</body>
</html>