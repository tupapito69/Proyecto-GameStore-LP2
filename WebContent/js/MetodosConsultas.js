/**
 * Script GameStore Copyright 2021
 */
/**************************************************/
// script para pintar la tabla de Consultas Ventas
var LoadTablaConsultasV=function(varDNI){
	$.ajax({
		url: 'Consultas', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {'opcion':'cvts','txtDNI':varDNI},
		success: function(data){
			if(data!=null){
				if(data.length!=0){
					EmptyFormConsultVen();
					crearTablaConsultasV1(data);
				}else{
					SweetAlertMessage('¡Error!','No hay datos con el DNI ingresado','error',false,2500);
					$('#DNIv').val('');
					EmptyFormConsultVen();
				}
			}else{console.log("error al cargar la tabla de consultas")}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
function crearTablaConsultasV1(a){
	var bodyTab="";
	$.each(a,function(item,c){
		bodyTab+='<tr><th scope="row">'+c.cod_ordenVenta+'</th>';
		bodyTab+='<td>'+c.cod_empleado+'</td>';
		bodyTab+='<td>'+c.fecha_ordenventa+'</td>';
		bodyTab+='<td>'+new Intl.NumberFormat("es-PE").format(c.importe_total)+'</td>';
		if(c.id_cupon!=null)
		{bodyTab+='<td>'+c.id_cupon+'</td>';}
		else{bodyTab+='<td></td>';}
		bodyTab+='<td><button class="btn" style="background: #fd7e14;" onclick="LoadTablaConsultasDetalleVenta(`'+c.cod_ordenVenta+'`)"><i class="bi bi-check2-circle"></i></button></td>';
		bodyTab+='</tr>';
	});
	$("#bodyTabConsultasV").empty();
    $("#bodyTabConsultasV").append(bodyTab);
}
var LoadTablaConsultasDetalleVenta=function(varCOD){
	$('#ProducCodventa').html('');
	$('#ProducCodventa').html(varCOD);
	$.ajax({
		url: 'Consultas', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {'opcion':'cvts2','CODV':varCOD},
		success: function(data){
			if(data.length !=0)
			{
				crearTablaConsultasV2(data);
				LoadTablaConsultasCuponVenta(varCOD);
			}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
var crearTablaConsultasV2=function(a){
	var bodyTab="";
	$.each(a,function(item,c){
		bodyTab+='<tr><th scope="row">'+c.cod_producto+'</th>';
		bodyTab+='<td>'+c.cantidad_venta+'</td>';
		bodyTab+='<td>'+c.precio_detalleventa+'</td>';
		bodyTab+='<td>'+new Intl.NumberFormat("es-PE").format((c.cantidad_venta*c.precio_detalleventa))+'</td>';
		bodyTab+='</tr>';
	});
	$("#bodyTabConsultasV2").empty();
    $("#bodyTabConsultasV2").append(bodyTab);
}
var LoadTablaConsultasCuponVenta=function(Venta){
	$.ajax({
		url: 'Consultas', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {'opcion':'cvtsC','CODV':Venta},
		success: function(data){
			if(data.length !=0)
			{
				crearTablaConsultasV3(data);
			}else{$("#bodyTabConsultasV3").empty();}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
var crearTablaConsultasV3=function(a){
	var bodyTab="";
	if(a.length !=0)
	{
		var item=0;
		$.each(a,function(item,c){
			bodyTab+='<tr><th scope="row">'+c.id_cupon+'</th>';
			bodyTab+='<td>'+c.importe_cupon+'</td>';
			bodyTab+='</tr>';
		});
	}
	$("#bodyTabConsultasV3").empty();
    $("#bodyTabConsultasV3").append(bodyTab);
}
var LoadTablaConsultasC=function(varDNI){
	$.ajax({
		url: 'Consultas', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {'opcion':'ccts','txtDNI':varDNI},
		success: function(data){
			if(data.length!=0){
				crearTablaConsultasC(data);
			}else{
				SweetAlertMessage('¡Error!','No existe cliente con el DNI ingresado','error',false,3000);
				$('#DNIc').val('');
				$("#bodyTabConsultasC").empty();
				return false;
			}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
function crearTablaConsultasC(a){
	var bodyTab="";
	$.each(a,function(item,c){
		bodyTab+='<tr><th scope="row">'+c.dni+'</th>';
		bodyTab+='<td>'+c.nomCli+'</td>';
		bodyTab+='<td>'+c.apeCli+'</td>';
		bodyTab+='<td>'+c.apeCli2+'</td>';
		bodyTab+='<td>'+c.telefono+'</td>';
		bodyTab+='</tr>';
	});
	$("#bodyTabConsultasC").empty();
    $("#bodyTabConsultasC").append(bodyTab);
}
var EmptyFormConsultVen=function(){
	$("#bodyTabConsultasV").empty();
	$("#bodyTabConsultasV2").empty();
	$("#bodyTabConsultasV3").empty();
	$("#ProducCodventa").empty();
}






