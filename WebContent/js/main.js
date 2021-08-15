/**
 * Script GameStore Copyright 2021
 */
//constantes para hacer las validaciones de las cajas de texto
const expreEmail=/\w+@\w+\.[a-z]/;
const expreTelf=/^9[0-9]{8}$/;
const expreDNI=/^[0-9]{8}$/;
const expreOrV=/^OV[0-9]+$/;
const numbersDecimal=/^\d*(\.\d{1})?\d{0,1}$/
const numbersInt=/^[0-9]+$/;
const NombProds=/^[A-Za-z0-9\s]+$/;
/**************************************************/
// script para cargar el contenido en mantenimiento
var LoadContentMant=function(param){
	$.ajax({
		url: 'PanelIntranet', 
		type: 'POST',
		async: true,
		data: {'op':param},
		success: function(d){
			$('#divContentIni').html('');
			switch(d){
				case "p": $('#divCrudProd').removeClass('d-none');$('#divHistVent').addClass('d-none');
					$('#divModifClient').addClass('d-none');$('#divNotaCredt').addClass('d-none');
					$('#divGCuponDescuento').addClass('d-none');$('#divCrudEmple').addClass('d-none');break;
				case "h": $('#divCrudProd').addClass('d-none');$('#divHistVent').removeClass('d-none');
					$('#divModifClient').addClass('d-none');$('#divNotaCredt').addClass('d-none');
					$('#divGCuponDescuento').addClass('d-none');$('#divCrudEmple').addClass('d-none');break;
				case "m": $('#divCrudProd').addClass('d-none');$('#divHistVent').addClass('d-none');
					$('#divModifClient').removeClass('d-none');$('#divNotaCredt').addClass('d-none');
					$('#divGCuponDescuento').addClass('d-none');$('#divCrudEmple').addClass('d-none');break;
				case "n": $('#divCrudProd').addClass('d-none');$('#divHistVent').addClass('d-none');
					$('#divModifClient').addClass('d-none');$('#divNotaCredt').removeClass('d-none');
					$('#divGCuponDescuento').addClass('d-none');$('#divCrudEmple').addClass('d-none');break;
				case "c": $('#divCrudProd').addClass('d-none');$('#divHistVent').addClass('d-none');
					$('#divModifClient').addClass('d-none');$('#divNotaCredt').addClass('d-none');
					$('#divGCuponDescuento').removeClass('d-none');$('#divCrudEmple').addClass('d-none');break;
				case "e": 
					$('#divCrudProd').addClass('d-none');$('#divHistVent').addClass('d-none');
					$('#divModifClient').addClass('d-none');$('#divNotaCredt').addClass('d-none');
					$('#divGCuponDescuento').addClass('d-none');$('#divCrudEmple').removeClass('d-none');break;
				default:
					SweetAlertMessage("Error","No Hay Contenido a Mostrar","error",false,2000);break;
			}
		}, 
		error: function(e){console.log("error ajax :: "+e.getMessage)}
	});
}
/**************************************************/
// script para cargar el contenido en consultas
var LoadContentConsul=function(param){
	$.ajax({
		url: 'PanelIntranet', 
		type: 'POST',
		async: true,
		data: {'op':param},
		success: function(d){
			$('#divContentIni').html('');
			switch(d){
				case "cv": 
				$('#ConsultaVenta').removeClass('d-none');
				$('#ConsultaCliente').addClass('d-none');break;
				case "cc": 
				$('#ConsultaVenta').addClass('d-none');
				$('#ConsultaCliente').removeClass('d-none');break;
				default:
					SweetAlertMessage("Error","No Hay Contenido a Mostrar","error",false,2000);break;
			}
		}, 
		error: function(e){console.log("error ajax :: "+e.getMessage)}
	});
}
/**************************************************/
// script para cargar el contenido en Ventas
var LoadContentVentas=function(param){
	$.ajax({
		url: 'PanelIntranet', 
		type: 'POST',
		async: true,
		data: {'op':param},
		success: function(d){
			$('#divContentIni').html('');
			switch(d){
				case "rvt": 
				$('#divRegsVenta').removeClass('d-none');
				$('#divRegsClient').addClass('d-none');break;
				case "rcl": 
				$('#divRegsVenta').addClass('d-none');
				$('#divRegsClient').removeClass('d-none');break;
				default:
					SweetAlertMessage("Error","No Hay Contenido a Mostrar","error",false,2000);break;
			}
		}, 
		error: function(e){console.log("error ajax :: "+e.getMessage)}
	});
}