/**
 *  Script GameStore Copyright 2021
 */
var HborrarImputsTables = function(){
	$('#tabHVenta').empty();
	$('#fechaDesde').val('');
	$('#fechaHasta').val('');
	$('#imputHCliente').val('');
	$('#imputHEmpleado').val('');
}

var SendFormHistorialClient = function(){
	if (ValCampoVacio('imputHCliente'))
	{
		$.ajax({
			url: 'HistorialVenta', 
			type: 'POST',
			dataType: 'json',
			async: true,
			data: {'option':'hvpc','DNI':$('#imputHCliente').val().trim()},
			success: function(data){
				if (data.length == 0){SweetAlertMessage('ERROR','El cliente no existe','error',false,3000); return false;}
				else{crearTablaHistorial(data);}
			},
			error:function(e){console.log("error ajax "+e.getMessage)}
		});
	}
	else{
		SweetAlertMessage('ERROR','Ingrese el DNI de un Cliente','error',false,3000);
		return false;
	}
}
var SendFormHistorialEmple = function(){
	if (ValCampoVacio('imputHEmpleado')){
		$.ajax({
			url: 'HistorialVenta', 
			type: 'POST',
			dataType: 'json',
			async: true,
			data: {'option':'hvpe','idempleado':$('#imputHEmpleado').val().trim()},
			success: function(data){
				if (data.length == 0){SweetAlertMessage('ERROR','El empleado no existe','error',false,3000); return false;}
				else{crearTablaHistorial(data);}
			},
			error:function(e){console.log("error ajax "+e.getMessage)}
		});
	}else{SweetAlertMessage('ERROR','Ingrese el codigo del Empleado','error',false,3000);	return false;}
}
var SendFormHistorialFecha = function(){
	if (ValCampoVacio('fechaDesde')){
		if (ValCampoVacio('fechaHasta')){
			$.ajax({
			url: 'HistorialVenta', 
			type: 'POST',
			dataType: 'json',
			async: true,
			data: {'option':'hvpf','fecha1':$('#fechaDesde').val(),'fecha2':$('#fechaHasta').val()},
			success: function(data){
				if (data.length == 0){SweetAlertMessage('ERROR','Ingrese la fecha correctamente','error',false,3000);}
				else{crearTablaHistorial(data);}
			},
			error:function(e){console.log("error ajax "+e.getMessage)}
			});
		}else {SweetAlertMessage('ERROR','Ingrese la fecha final','error',false,3000);	return false;}
	}else{SweetAlertMessage('ERROR','Ingrese la fecha inicial','error',false,3000); return false;}
}
var crearTablaHistorial=function(a){
	var bodyTab="";
	$.each(a,function(item,e){
		bodyTab+='<tr><th scope="row">'+e.cod_ordenventa+'</th>';
		bodyTab+='<td>'+e.cod_empleado+'</td>';
		bodyTab+='<td>'+e.dni_cliente+'</td>';
		bodyTab+='<td>'+e.fecha+'</td>';
		bodyTab+='<td>'+new Intl.NumberFormat().format(e.importe_total)+'</td>';
		if(e.id_cupon!=null){bodyTab+='<td>'+e.id_cupon+'</td>';}
		else{bodyTab+='<td></td>';}
		bodyTab+='</tr>';
	});
	$('#tabHVenta').empty();
	$('#tabHVenta').append(bodyTab);
} 
