<%@page import="interfaces.OrdenVentaDAO"%>
<%@page import="beans.OrdenVentaDTO"%>
<%@page import="beans.EmpleadoDTO"%>
<%@page import="interfaces.EmpleadoDAO"%>
<%@page import="beans.CargoDTO"%>
<%@page import="interfaces.CargoDAO"%>
<%@page import="beans.CategoriaDTO"%>
<%@page import="interfaces.CategoriaDAO"%>
<%@page import="beans.ClienteDTO"%>
<%@page import="interfaces.ClienteDAO"%>
<%@page import="interfaces.ProductoDAO"%>
<%@page import="dao.DAOFactory"%>
<%@page import="beans.ProductoDTO"%>
<%@page import="java.util.ArrayList"%>
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
<title>Panel Intranet</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap-grid.min.css" rel="stylesheet" type="text/css">
<link href="css/PanelIntranetIndex.css" rel="stylesheet" type="text/css">
<link href="css/bootstrapSideBar.css" rel="stylesheet" type="text/css">
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="text/javascript" src="js/SweetAlert.js"></script>
</head>
<body>
	<%@include file="MenuTemplate.html" %>
	<main class="" style="min-height: 100vh;">
		<div class="d-flex" id="wrapper">
			<!-- Sidebar-->
	        <div class="bg-light border-right" id="sidebar-wrapper">
	            <div class="sidebar-heading">Realizar Acciones</div>
	            <div class="list-group list-group-flush">
	                <a class="list-group-item list-group-item-action bg-light" href="" id="btn-ActionProduct">Producto</a>
	                <a class="list-group-item list-group-item-action bg-light" href="" id="btn-ActionHistVent">Historial de Ventas</a>
	                <a class="list-group-item list-group-item-action bg-light" href="" id="btn-ActionClient">Modificar Cliente</a>
	                <a class="list-group-item list-group-item-action bg-light" href="" id="btn-ActionNotaCredt">Generar Nota de Credito</a>
	                <a class="list-group-item list-group-item-action bg-light" href="" id="btn-ActionGeneCupon">Crear Cupón</a>
	                <a class="list-group-item list-group-item-action bg-light" href="" id="btn-ActionEmple">Registrar Empleado</a>
	            </div>
	        </div>
	        <!-- Page Content-->
	        <div id="page-content-wrapper">
	        	<div class="container">
	        		<button class="btn btn-primary m-3" id="menu-toggle">Realizar Acciones</button>
	        		<div id="divContentIni">
	        			<h2 class="text-center m-3">Bienvenido <span style="font-weight: bold;"><%= misession.getAttribute("NameComplet") %></span></h2>
						<h4 class="text-center m-3">MANTENIMIENTOS</h4>
	        		</div>
					<div class="d-none" id="divCrudProd">
						<form action="" method="post" autocomplete="off">
					    	<h3 class="text-center">Productos</h3>
					    	<div class="mb-3 d-none">
					    		<div class="row">
									<div class="col-12 col-md-9 mb-sm-2">
										<label for="codigoProd" class="form-label">Código del Producto:</label> 
										<input type="text" class="form-control" id="codigoProd" placeholder="" value="0" readonly="readonly" hidden="">
									</div>
									<div class="col-12 col-md-3">
										<button type="button" class="btn btn-success form-control" style="height: 100%;">Buscar</button>							
									</div>
								</div>
							</div>
							<div class="mb-3">
								<label for="NombreProd" class="form-label">Nombre Producto:</label> 
								<input type="text" class="form-control" id="NombreProd" placeholder="Ingrese nomobre del producto" value="" maxlength="20">
							</div>
							<div class="mb-3">
								<div class="row">
									<div class="col-6">
										<label for="PrecioProd" class="form-label">Precio:</label> 
										<input type="text" class="form-control" id="PrecioProd" placeholder="Ingrese precio del producto" value="">
									</div>
									<div class="col-6">
										<label for="StockProd" class="form-label">Stock:</label>
										<input type="text" class="form-control" id="StockProd" placeholder="Ingrese stock del producto" value="">							
									</div>
									<div class="col-6">
										<label for="categoriaProd" class="form-label">Categoría:</label> 
										<select class="form-control" id="categoriaProd" name="catego">
											<%
												DAOFactory fabricaCategorias=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
												CategoriaDAO daoCategoria=fabricaCategorias.getCategoriaDAO();
												ArrayList<CategoriaDTO> lstCategorias=daoCategoria.listadoCategorias();
												if(lstCategorias!=null){
													for(CategoriaDTO catg: lstCategorias){%>
														<option value="<%= catg.getCod_cat()%>"><%= catg.getNom_categoria()%></option>
													<%}
												}
											
											%>
										</select>
									</div>
								</div>
							</div>
							<div class="mb-3">
								<div  class="d-flex justify-content-center">
									<button type="button" class="btn btn-success mr-3" id="buttonAgregar">
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
  										<path d="M8 0a1 1 0 0 1 1 1v6h6a1 1 0 1 1 0 2H9v6a1 1 0 1 1-2 0V9H1a1 1 0 0 1 0-2h6V1a1 1 0 0 1 1-1z"/>
										</svg> Agregar
									</button>
									<button type="button" class="btn btn-warning" id="buttonModificar"><i class="bi bi-pencil"></i> Modificar</button>
								</div>
							</div>
						</form>
						<div class="table-responsive" style="max-height: 35%;">
							<table class="table">
								<thead>
									<tr>
										<th scope="col">NOMBRES</th>
										<th scope="col">PRECIO</th>
										<th scope="col">STOCK</th>
										<th scope="col">CATEGORIA</th>
										<th scope="col"></th>
										<th scope="col"></th>
									</tr>
								</thead>
								<tbody id="BodyTabProductos">
								</tbody>
							</table>
						</div>
					</div>
					<div class="d-none" id="divHistVent">
						<form action="" method="post" autocomplete="off">
							<h3 class="text-center">Historial de Ventas</h3>
							<div class="d-flex justify-content-center mt-md-3 mb-md-3 mt-2 mb-2">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio"
										name="inlineRadioOptions" id="inlineRadio1" value="option1">
									<label class="form-check-label" for="inlineRadio1">Historial por Cliente</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio"
										name="inlineRadioOptions" id="inlineRadio2" value="option2">
									<label class="form-check-label" for="inlineRadio2">Historial por Fecha</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio"
										name="inlineRadioOptions" id="inlineRadio3" value="option3">
									<label class="form-check-label" for="inlineRadio3">Historial por Empleado</label>
								</div>
							</div>
							<div class="mb-3">
								<div class="row no-gutters">
									<div class="col-12 col-md-12 d-none" id="divHcliente">
										<h4>Historial por Cliente</h4>
										<div class="mr-3 ml-3  mb-3">
											<div class="row">
												<div class="col-12 col-md-8">
													<label for="imputHCliente" class="form-label">DNI:</label> <input
														type="text" class="form-control" id="imputHCliente"
														placeholder="Ingrese DNI del Cliente" value="">
												</div>
												<div class="col-12 col-md-4">
													<button type="button"
														class="p-2 btn btn-success form-control mb-sm-2"
														style="height: 100%;" id="buttonHCliente"><i class="bi bi-search"></i> Consultar</button>
												</div>
											</div>
										</div>
									</div>
									<div class="col-12 col-md-12 d-none" id="divHfecha">
										<h4>Historial por Fecha</h4>
										<div class="mr-3 ml-3  mb-3">
											<div class="row">
													<div class="col-12 col-md-4 mb-sm-2">
														<label for="fechaDesde" class="form-label">Desde:</label> 
														<input type="date" class="form-control" id="fechaDesde" placeholder="dd/mm/yyyy" value="">
													</div>
													<div class="col-12 col-md-4 mb-sm-2">
														<label for="fechaHasta" class="form-label">Hasta:</label>
														<input type="date" class="form-control" id="fechaHasta" placeholder="dd/mm/yyyy" value="">							
													</div>
													<div class="col-12 col-md-4">
														<button type="button" class="p-2 btn btn-success form-control mb-sm-2" id="buttonHFecha" style="height: 100%;"><i class="bi bi-search"></i> Consultar</button>
													</div>
											</div>
										</div>
									</div>
									<div class="col-12 col-md-12 d-none" id="divHempleado">
										<h4>Historial por Empleado</h4>
										<div class="mr-3 ml-3  mb-3">
											<div class="row">
												<div class="col-12 col-md-8">
													<label for="imputHEmpleado" class="form-label">Codigo del Empleado:</label> <input
														type="text" class="form-control" id="imputHEmpleado"
														placeholder="Ingrese Codigo del Empleado" value="">
												</div>
												<div class="col-12 col-md-4">
													<button type="button"
														class="p-2 btn btn-success form-control mb-sm-2"
														style="height: 100%;" id="buttonHEmpleado"><i class="bi bi-search"></i> Consultar</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
						<div class="table-responsive" style="max-height: 35%;">
							<table class="table">
								<thead>
									<tr>
										<th scope="col">N° Venta</th>
										<th scope="col">Cod. Empleado</th>
										<th scope="col">DNI</th>
										<th scope="col">Fecha</th>
										<th scope="col">Importe Total</th>
										<th scope="col">Cupón</th>
									</tr>
								</thead>
								<tbody id="tabHVenta">
								</tbody>
							</table>
						</div>
					</div>
					<div class="d-none" id="divModifClient">
						<form action="" method="post" autocomplete="off">
		        			<h3 class="text-center">Modificar Cliente</h3>
		        			<div class="mb-3">
		        				<div class="row">
									<div class="col-12 col-md-12 mb-sm-2">
										<label for="dniClient" class="form-label">DNI:</label> 
										<input type="text" class="form-control" id="dniClient" value="" readonly="readonly">
									</div>
								</div>
							</div>
							<div class="mb-3">
								<div class="row">
									<div class="col-sm-12 col-md-6">
										<label for="NombreClient" class="form-label">Nombre Completo:</label> 
										<input type="text" class="form-control" id="NombreClient" placeholder="Ingrese Nombre" value="" maxlength="20">
									</div>
									<div class="col-sm-12 col-md-6">
										<label for="apertenoClient" class="form-label">Apellido Paterno:</label> 
										<input type="text" class="form-control" id="apertenoClient" placeholder="Ingrese Apellido Paterno" value="" maxlength="20">
									</div>
									<div class="col-sm-12 col-md-6">
										<label for="amertenoClient" class="form-label">Apellido Materno:</label> 
										<input type="text" class="form-control" id="amertenoClient" placeholder="Ingrese Apellido Materno" value="" maxlength="20">
									</div>
									<div class="col-sm-12 col-md-6">
										<label for="telefonoClient" class="form-label">Teléfono:</label> 
										<input type="text" class="form-control" id="telefonoClient" placeholder="Ingrese Teléfono" value="" maxlength="11">
									</div>
								</div>
							</div>
							<div class="mb-3">
								<button type="button" class="btn btn-warning" id="btn-ModCliente"><i class="bi bi-pencil"></i> Modificar</button>
							</div>
						</form>
						<div class="table-responsive" style="max-height: 35%;">
							<table class="table">
								<thead>
									<tr>
										<th scope="col">DNI</th>
										<th scope="col">Nombres</th>
										<th scope="col">Ape. Paterno</th>
										<th scope="col">Ape. Materno</th>
										<th scope="col">Teléfono</th>
										<th scope="col"></th>
									</tr>
								</thead>
								<tbody id="bodyTabClient">
								</tbody>
							</table>
						</div>
					</div>
					<div class="d-none" id="divNotaCredt">
						<form action="" method="post" autocomplete="off">
							<h3 class="text-center">Generar Nota de Crédito</h3>
							<div class="mb-3">
		        				<div class="row">
									<div class="col-12 col-md-9 mb-sm-2">
										<label for="codOrdenV" class="form-label">Código de Orden de Venta:</label> 
										<input type="text" class="form-control" id="codOrdenV" placeholder="Ingrese Código" value="" onkeyup="ConvertUpercase(this)">
									</div>
									<div class="col-12 col-md-3">
										<button type="submit" class="btn btn-success form-control" style="height: 100%;" id="btn-ShOrdenVenta"><i class="bi bi-search"></i> Buscar</button>							
									</div>
								</div>
							</div>
						</form>
						<div class="mb-3">
							<label for="dniClientOV" class="form-label">DNI:</label> 
							<input type="text" class="form-control" id="dniClientOV" value="" readonly="readonly">
						</div>
						<div class="table-responsive" style="max-height: 40vh;">
							<table class="table">
								<thead>
									<tr>
										<th scope="col">Pod. Producto</th>
										<th scope="col">Cantidad</th>
										<th scope="col">Precio X Unidad</th>
										<th scope="col">Importe Total</th>
										<th scope="col"></th>
									</tr>
								</thead>
								<tbody id="bodyTabProdOrdC">
								</tbody>
							</table>
						</div>
						<form action="" method="post" autocomplete="off">
							<div class="mb-3">
								<label for="codProd" class="form-label">Código de Producto:</label> 
								<input type="text" class="form-control" id="codProd" placeholder="" value="" readonly="readonly">
							</div>
							<div class="mb-3">
								<div class="row">
									<div class="col-6">
										<label for="Cantidad" class="form-label">Cantidad:</label> 
										<input type="text" class="form-control" id="Cantidad" placeholder="Ingrese Cantidad" value="1" readonly="readonly">
									</div>
									<div class="col-6">
										<label for="Precio" class="form-label">Precio:</label>
										<input type="text" class="form-control" id="Precio" placeholder="" value="" readonly="readonly">							
									</div>
								</div>
							</div>
							<div class="mb-3 d-flex justify-content-end">
								<button type="submit" class="btn btn-info" id="btn-DevolverProd" disabled="disabled">Devolver</button>							
							</div>
						</form>
						<form action="" method="post" autocomplete="off">
							<div class="table-responsive mb-3" style="max-height: 40vh;">
								<table class="table">
									<thead>
										<tr>
											<th scope="col">Pod. Producto</th>
											<th scope="col">Cantidad</th>
											<th scope="col">Precio X Unidad</th>
											<th scope="col">Importe Total</th>
											<th scope="col"></th>
										</tr>
									</thead>
									<tbody id="bodyTabProdOrdDevol">
									</tbody>
								</table>
							</div>
						</form>
						<form action="" method="post" autocomplete="off">
							<div class="mb-3">
								<label for="importeDevol" class="form-label">Importe a Devolver:</label> 
								<input type="text" class="form-control" id="importeDevol" placeholder="" value="" readonly="readonly">
							</div>
							<div class="mb-3 d-flex justify-content-center">
								<button type="button" class="btn btn-success" style="width: 50%;" id="btn-PrcesarOC" disabled="disabled">Procesar</button>
							</div>
						</form>
					</div>
					<div class="d-none" id="divGCuponDescuento"> 
						<form id="FomrGCuponDesc" style="max-width: 50vh; margin: auto;" autocomplete="off">
							<h3 class="text-center">Crear Cupón de Descuento</h3>
							<div class="mb-3">
								<label for="CodCupon" class="form-label">Codigo de Cupón:</label> 
								<input type="text" class="form-control" id="CodCupon" placeholder="Ingrese Codigo para el cupón" value="" onkeyup="ConvertUpercase(this)" maxlength="10">
							</div>
							<div class="mb-3">
								<label for="CantUsosCupon" class="form-label">Cantidad de usos:</label> 
								<input type="text" class="form-control" id="CantUsosCupon" placeholder="Ingrese una cantidad de usos" value="">
							</div>
							<div class="mb-3">
								<label for="importeCupon" class="form-label">Valor del cupón (importe):</label> 
								<input type="text" class="form-control" id="importeCupon" placeholder="Ingrese Apellido Materno" value="">
							</div>
							<div class="mb-3 d-flex justify-content-center">
								<button type="button" class="btn btn-info" id="btn-GnCupon"><i class="bi bi-arrow-repeat"></i> Generar Cupón de Descuento</button>
							</div>
						</form>
					</div>
					
					<div class="d-none" id="divCrudEmple"> 
						<form id="FomrCrudEmple" autocomplete="off">
			        		<h3 class="text-center">Registrar Empleado</h3>
			        		<div class="mb-3 d-none" style="visibility: hidden;">
								<label for="codEmple" class="form-label">Codigo:</label> 
								<input type="text" class="form-control" id="codEmple" placeholder="" value="0" readonly="readonly" hidden="">
							</div>
							<div class="mb-3">
								<div class="row">
									<div class="col-sm-12 col-md-6">
										<label for="NombreEmple" class="form-label">Nombre Completo:</label> 
										<input type="text" class="form-control" id="NombreEmple" placeholder="Ingrese Nombre" value="" maxlength="20">
									</div>
									<div class="col-sm-12 col-md-6">
										<label for="apaternoEmple" class="form-label">Apellido Paterno:</label> 
										<input type="text" class="form-control" id="apaternoEmple" placeholder="Ingrese Apellido Paterno" value="" maxlength="20">
									</div>
									<div class="col-sm-12 col-md-6">
										<label for="amaternoEmple" class="form-label">Apellido Materno:</label> 
										<input type="text" class="form-control" id="amaternoEmple" placeholder="Ingrese Apellido Materno" value="" maxlength="20">
									</div>
									<div class="col-sm-12 col-md-6">
										<label for="emailEmple" class="form-label">Correo Electrónico:</label> 
										<input type="text" class="form-control" id="emailEmple" placeholder="Ingrese Email" value="" maxlength="60">
									</div>
									<div class="col-sm-12 col-md-6">
										<label for="telefonoEmple" class="form-label">Teléfono:</label> 
										<input type="text" class="form-control" id="telefonoEmple" placeholder="Ingrese Teléfono" value="" maxlength="11">
									</div>
									<div class="col-sm-12 col-md-6">
										<label for="supervisor" class="form-label">Supervisor:</label> 
										<select class="form-control" id="supervisor" name="supervisor">
											
										</select>
									</div>
									<div class="col-sm-12 col-md-6">
										<label for="cargo" class="form-label">Cargo:</label> 
										<select class="form-control" id="cargo" name="cargo">
											
										</select>
									</div>
								</div>
							</div>
							<div class="mb-3">
					    		<div class="row">
									<div class="col-12 col-md-6 mb-sm-2">
										<label for="UserEmple" class="form-label col-4">Usuario:</label> 
										<button type="button" class="btn btn-info" id="btn-Gcod"><i class="bi bi-arrow-repeat"></i> Generar Usuario</button>
									</div>
									<div class="col-12 col-md-6">
										<input type="text" class="form-control" id="UserEmple" placeholder="" value="" readonly="readonly">						
									</div>
								</div>
							</div>
							<div class="mb-3">
								<div class="row">
									<div class="col-12 col-md-6 d-flex justify-content-center">
										<button type="button" class="btn btn-success mb-sm-2" style="width: 80%;" id="btn-reE"><i class="bi bi-person-plus"></i> Registrar</button>
									</div>
									<div class="col-12 col-md-6 d-flex justify-content-center">
										<button type="button" class="btn btn-warning" style="width: 80%;" id="btn-mdE"><i class="bi bi-pencil"></i> Modificar</button>						
									</div>
								</div>
							</div>
						</form>
						<div class="table-responsive" style="max-height: 65vh;">
							<table class="table">
								<thead>
									<tr>
										<th scope="col">Nombres</th>
										<th scope="col">Ape. Paterno</th>
										<th scope="col">Ape. Materno</th>
										<th scope="col">Email</th>
										<th scope="col">Teléfono</th>
										<th scope="col">Cod. Supervisor</th>
										<th scope="col">Cargo</th>
										<th scope="col"></th>
									</tr>
								</thead>
								<tbody id="bodyTabEmple">
									
								</tbody>
							</table>
						</div>
						<br />
						<div id="botones" class="btn-group btn-group-xs" role="group" arial-label="grupo"></div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<%@include file="FooterTemplate.html" %>
</body>
</html>