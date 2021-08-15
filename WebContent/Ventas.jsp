<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page session="true" %>
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
<title>Ventas</title>
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
			<!-- Sidebar-->
	        <div class="bg-light border-right" id="sidebar-wrapper">
	            <div class="sidebar-heading">Realizar Acciones</div>
	            <div class="list-group list-group-flush">
	                <a class="list-group-item list-group-item-action bg-light" href="" id="btn-ActionVender">Vender</a>
	                <a class="list-group-item list-group-item-action bg-light" href="" id="btn-ActionRegistClient">Registrar Cliente</a>
	            </div>
	        </div>
	        <!-- Page Content-->
	        <div id="page-content-wrapper">
	        	<div class="container">
	        		<button class="btn btn-primary m-3" id="menu-toggle">Realizar Acciones</button>
	        		<div id="divContentIni">
	        			<h2 class="text-center m-3">Bienvenido <span style="font-weight: bold;"><%= misession.getAttribute("NameComplet") %></span></h2>
						<h4 class="text-center m-3">VENTAS</h4>
	        		</div>
					<div class="d-none" id="divRegsVenta">
						<form action="" method="post" autocomplete="off">
					    	<h3 class="text-center">Ventas</h3>
					    	<div class="mb-3">
					    		<div class="row">
									<div class="col-12 col-md-7 mb-sm-2">
										<label for="DNIClient" class="form-label">DNI:</label> 
										<input type="text" class="form-control" id="DNIClient" placeholder="Ingrese Código" value="">
									</div>
									<div class="col-12 col-md-5 mb-sm-2 btn-group d-flex align-items-end">
										<button type="button" class="btn btn-primary form-control" title="Buscar Cliente" id="btn-ValCli"><i class="bi bi-arrow-right-square"></i></button>
										<!-- <button type="button" class="btn btn-danger form-control" title="Habiliar Formulario" id="btn-Habi" disabled="disabled"><i class="bi bi-arrow-left-square"></i></button> -->							
									</div>
								</div>
							</div>
							<div class="mb-3">
								<div class="row">
									<div class="col-6">
										<label for="Cbocategories" class="form-label">Categoría:</label> 
										<select class="form-control" id="Cbocategories" name="catego">
											
										</select>
									</div>
									<div class="col-6">
										<label for="CboGames" class="form-label">Juego:</label> 
										<select class="form-control" id="CboGames" name="CboGames">
											<option>--Seleccione--</option>
										</select>
									</div>
									<div class="col-6">
										<label for="PriceGame" class="form-label">Precio:</label> 
										<input type="text" class="form-control" id="PriceGame" value="" readonly="readonly">
									</div>
									<div class="col-6">
										<label for="CantidadGame" class="form-label">Cantidad:</label>
										<input type="text" class="form-control" id="CantidadGame" placeholder="Ingrese una cantidad" value="" required="required">							
									</div>
								</div>
							</div>
							<div class="mb-3 d-flex justify-content-center">
								<button type="button" class="btn btn-warning" title="Agregar al Carrito" id="btn-AddCart" disabled="disabled"><i class="bi bi-cart-plus"></i> Añadir Producto</button>
							</div>
						</form>
						<div class="table-responsive" style="max-height: 50vh;">
							<table class="table">
								<thead>
									<tr>
										<th scope="col">PRODUCTO</th>
										<th scope="col">CANTIDAD</th>
										<th scope="col">PRECIO X UNIDAD</th>
										<th scope="col">TOTAL</th>
										<th scope="col"></th>
									</tr>
								</thead>
								<tbody id="TableProdCom">
									
								</tbody>
							</table>
						</div>
						<br/>
						<form action="" method="post" autocomplete="off">
							<div class="mb-3">
								<label for="TotalBruto" class="form-label">Total Bruto:</label> 
								<input type="text" class="form-control" id="TotalBruto" readonly="readonly" required="required">
							</div>
					    	<div class="mb-3">
					    		<div class="row">
									<div class="col-12 col-md-9 mb-sm-2 input-group">
										<label for="DesctCupon" class="form-label">Cod. Descuento:</label>
										<label class="switch ml-2 mr-2" >
										  <input type="checkbox" id="chkDescuento">
										  <span class="slider round"></span>
										</label>
										<!-- <input type="checkbox" class="form-control" id="chkDescuento"> -->
										<input type="text" class="form-control" id="DesctCupon" readonly="readonly" disabled="disabled" maxlength="10">							
									</div>
									<div class="col-12 col-md-3 mb-sm-2 btn-group d-flex align-items-end">
										<button type="button" class="btn btn-danger" id="btn-DescCup" title="Aplicar Descuento" disabled="disabled"><i class="bi bi-journal-check"></i> Aplicar</button>				
									</div>
								</div>
							</div>
							<div class="mb-3">
								<label for="DescuentoVenta" class="form-label">Descuento:</label>
								<input type="text" class="form-control" id="DescuentoVenta" name="txtDescuentoV" readonly="readonly">
							</div>
							<div class="mb-3">
								<label for="TotalNeto" class="form-label">Total Neto:</label> 
								<input type="text" class="form-control" id="TotalNeto" name="txtTotalNeto" readonly="readonly">
							</div>
							<div class="mb-3 d-flex justify-content-center">
								<button type="button" class="btn btn-lg btn-success" title="Generar Comprar" id="btn-reCp" disabled="disabled"><i class="bi bi-cash"></i> Procesar Compra</button>
							</div>
						</form>
					</div>
					<div class="d-none" id="divRegsClient"> 
						<form id="FomrRegsClient" autocomplete="off">
			        		<h3 class="text-center">Registrar Cliente</h3>
							<div class="mb-3">
								<div class="row">
									<div class="col-sm-12 col-md-6">
										<label for="txtDNIClient" class="form-label">DNI Cliente:</label> 
										<input type="text" class="form-control" id="txtDNIClient" placeholder="Ingrese un DNI">
									</div>
									<div class="col-sm-12 col-md-6">
										<label for="NombreClient" class="form-label">Nombre Completo:</label> 
										<input type="text" class="form-control" id="NombreClient" placeholder="Ingrese Nombre" value="" maxlength="20">
									</div>
									<div class="col-sm-12 col-md-6">
										<label for="apaternoClient" class="form-label">Apellido Paterno:</label> 
										<input type="text" class="form-control" id="apaternoClient" placeholder="Ingrese Apellido Paterno" value="" maxlength="20">
									</div>
									<div class="col-sm-12 col-md-6">
										<label for="amaternoClient" class="form-label">Apellido Materno:</label> 
										<input type="text" class="form-control" id="amaternoClient" placeholder="Ingrese Apellido Materno" value="" maxlength="20">
									</div>
									<div class="col-sm-12 col-md-6">
										<label for="telefonoClient" class="form-label">Teléfono:</label> 
										<input type="text" class="form-control" id="telefonoClient" placeholder="Ingrese Teléfono" value="" maxlength="11">
									</div>
								</div>
							</div>
							<div class="mb-3 d-flex justify-content-center">
								<button type="button" class="btn btn-lg btn-success" id="btn-reCl"><i class="bi bi-person-plus"></i> Registrar</button>
							</div>
						</form>
					</div>
				</div>
	        </div>
	    </div>
	</main>
	<%@include file="FooterTemplate.html" %>
</body>
</html>	        
	        