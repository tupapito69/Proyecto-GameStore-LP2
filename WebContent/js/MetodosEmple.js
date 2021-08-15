/**
 * Script GameStore Copyright 2021
 */
/**************************************************/
// script para validar el crud de empleado
var ValFormEmpleado=function(){
	if(ValCampoVacio('codEmple')){
		codEmple=$('#codEmple').val();
		if(ValCampoVacio('NombreEmple')){
			NomEmple=$('#NombreEmple').val();
			if(ValCampoVacio('apaternoEmple')){
				APaternoEmple=$('#apaternoEmple').val();
				if(ValCampoVacio('amaternoEmple')){
					AMaternoEmple=$('#amaternoEmple').val();
					if(ValCampoVacio('emailEmple')){
						emailEmple=$('#emailEmple').val();
						if(ValCampoVacio('telefonoEmple')){
							TelfEmple=$('#telefonoEmple').val();
							if(ValCampoVacio('supervisor')){
								supervEmple=$('#supervisor').val();
								if(ValCampoVacio('cargo')){
									CargoEmple=$('#cargo').val();
									if(ValCampoVacio('UserEmple')){
										UserEmple=$('#UserEmple').val();
										if(expreEmail.test(emailEmple)){
											if(expreTelf.test(TelfEmple)){
												return true;
											}else{SweetAlertMessage("Advertencia","Ingrese un Teléfono Válido, debe empezar con 9 y tener 9 digitos","warning","Continuar",5000);return false;}
										}else{SweetAlertMessage("Advertencia","El email ingresado no es valido","warning","Continuar",2000);return false;}
									}else{SweetAlertMessage("Error","Ingrese un Usuario","error","Continuar",2000);return false;}
								}else{SweetAlertMessage("Error","Seleccione un Cargo","error","Continuar",2000);return false;}
							}else{SweetAlertMessage("Error","Seleccione un Supervisor","error","Continuar",2000);return false;}
						}else{SweetAlertMessage("Error","Ingrese un Teléfono","error","Continuar",2000);return false;}
					}else{SweetAlertMessage("Error","Ingrese un email","error","Continuar",2000);return false;}
				}else{SweetAlertMessage("Error","Ingrese un Apellido Materno","error","Continuar",2000);return false;}
			}else{SweetAlertMessage("Error","Ingrese un Apellido Paterno","error","Continuar",2000);return false;}
		}else{SweetAlertMessage("Error","Ingrese un Nombre","error","Continuar",2000);return false;}
	}else{SweetAlertMessage("Error","Ingrese Un Código Para Empleado","error","Continuar",2000);return false;}
}
/**************************************************/
// script para pintar el dropdown Cargo
var LoadComboCargo=function(){
	$.ajax({
		url: 'Empleado', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {'option':'licag'},
		success: function(data){
			if(data!=null){
				crearComboCargo(data);
			}else{console.log("error al llenar le comboBox de Cargos");}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
var crearComboCargo=function(a){
	var bodyCbo="";
	$.each(a,function(item,e){bodyCbo+='<option value="'+e.cod_cargo+'">'+e.nombre+'</option>';});
	$("#cargo").empty();
    $("#cargo").append(bodyCbo);
}
/**************************************************/
// script para pintar el dropdown Supervisor
var LoadComboSuper=function(){
	$.ajax({
		url: 'Empleado', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {'option':'lisup'},
		success: function(a){
			if(a!=null){
				crearComboSuper(a);
			}else{console.log("error al llenar le comboBox de Supervisor");}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
var crearComboSuper=function(a){
	var bodyCbo="";
	$.each(a,function(item,e){bodyCbo+='<option value="'+e.cod_empleado+'">'+e.nom_empleado+'</option>';});
	$("#supervisor").empty();
    $("#supervisor").append(bodyCbo);
}
/**************************************************/
// script para generar un usuario
var GenerarUser=function(){
	var user='g'+$('#apaternoEmple').val().substring(0,2)+$('#amaternoEmple').val().substring(0,2)+$('#telefonoEmple').val().substring($('#telefonoEmple').val().length-2,$('#telefonoEmple').val().length);
	return user;
}
/**************************************************/
// script para pintar la tabla de empleados
var LoadTablaEmple=function(){
	$.ajax({
		url: 'Empleado', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {'option':'ldtab'},
		success: function(data){
			if(data!=null){
				if(data.length!=0){crearTablaEmple(data);}
				/*dataTEP=data;
				dataTT=data.length;
				nPag=Math.ceil(dataTT / xPag);
				offset=(pag-1)*xPag;
				hasta=pag*xPag;
				crearTablaEmple(offset,hasta);*/
				/*mostrarBotones(nPag);
				Paginator();*/
			}else{console.log("error al cargar la tabla de empleados");}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
var crearTablaEmple=function(data){
	var bodyTab="";
	$.each(data,function(item,e){
		bodyTab+='<tr>';
		bodyTab+='<td>'+e.nom_empleado+'</td>';
		bodyTab+='<td>'+e.apaterno+'</td>';
		bodyTab+='<td>'+e.amaterno+'</td>';
		bodyTab+='<td>'+e.email+'</td>';
		bodyTab+='<td>'+e.telefono+'</td>';
		bodyTab+='<td>'+e.cod_emple_superv+'</td>';
		bodyTab+='<td>'+e.nombre+'</td>';
		bodyTab+='<td><button class="btn" style="background: #fd7e14;" onclick="SelectTabEmple('+e.cod_empleado+')"><i class="bi bi-pencil"></i></button></td>';
		bodyTab+='</tr>';
	});
	$("#bodyTabEmple").empty();
    $("#bodyTabEmple").append(bodyTab);
}
/*var crearTablaEmple=function(desde,hasta){
	var bodyTab="";
	for(var i = desde; i < hasta; i++){
		bodyTab+='<tr>';
		bodyTab+='<td>'+dataTEP[i].nom_empleado+'</td>';
		bodyTab+='<td>'+dataTEP[i].apaterno+'</td>';
		bodyTab+='<td>'+dataTEP[i].amaterno+'</td>';
		bodyTab+='<td>'+dataTEP[i].email+'</td>';
		bodyTab+='<td>'+dataTEP[i].telefono+'</td>';
		bodyTab+='<td>'+dataTEP[i].cod_emple_superv+'</td>';
		bodyTab+='<td>'+dataTEP[i].nombre+'</td>';
		bodyTab+='<td><button class="btn" style="background: #fd7e14;" onclick="SelectTabEmple('+dataTEP[i].cod_empleado+')"><i class="bi bi-pencil"></i></button></td>';
		bodyTab+='</tr>';
	}
	$("#bodyTabEmple").empty();
    $("#bodyTabEmple").append(bodyTab);
}*/
/*var mostrarBotones=function(t){
    let botones = '';
    for(var i = 0; i < t; i++){
        botones+= "<button type='button' "+
            "class='btn btn-info'>"+(i+1)+"</button>";
    }
    $('#botones').append(botones);
} 
var quitarActivo=function(){
    var losBotones = document.querySelectorAll('#botones button');
    for(var i = 0; i < losBotones.length; i++){
        $(losBotones[i]).removeClass('active');
    }
}*/
/**************************************************/
// script para Enviar un empleado
var sendDataEmple=function(){
	$.ajax({
		url: 'Empleado', 
		type: 'POST',
		async: true,
		data: {
			'option':'r',
			'codE':codEmple,
			'NombE':NomEmple,
			'ApePat':APaternoEmple,
			'ApeMat':AMaternoEmple,
			'Email':emailEmple,
			'Telf':TelfEmple,
			'CodSuper':supervEmple,
			'CodCrg':CargoEmple,
			'User':UserEmple
			},
		success: function(d){
			if(d=='1'){
				SweetAlertMessage('Registro Exitoso !','','success',true,1500);
				emptyForm();
				$('#codEmple').val(0);
			}else{SweetAlertMessage('Registro Fallido !','','error',true,1500);}
		},
		error:function(e){console.log(e.getMessage);}
	});
}
/**************************************************/
// script para limpiar el Form Empleado
var emptyForm=function(){
	$('#codEmple').val('');
	$('#NombreEmple').val('');
	$('#apaternoEmple').val('');
	$('#amaternoEmple').val('');
	$('#emailEmple').val('');
	$('#telefonoEmple').val('');
	$('#UserEmple').val('');
	LoadComboSuper();
	LoadTablaEmple();
}
/**************************************************/
// script para Enviar un empleado
var sendDataUpdateEmple=function(){
	$.ajax({
		url: 'Empleado', 
		type: 'POST',
		async: true,
		data: {
			'option':'e',
			'codE':codEmple,
			'NombE':NomEmple,
			'ApePat':APaternoEmple,
			'ApeMat':AMaternoEmple,
			'Email':emailEmple,
			'Telf':TelfEmple,
			'CodSuper':supervEmple,
			'CodCrg':CargoEmple
			},
		success: function(d){
			if(d=='1'){
				SweetAlertMessage('Actualización Exitosa !','','success',true,1500);
				emptyForm();	
			}else{SweetAlertMessage('Actualización Fallida !','','error',true,1500);}
		},
		error:function(e){console.log(e.getMessage);}
	});
}
/**************************************************/
// script para Seleccionar un empleado de la tabla
var SelectTablaEmple=function(codE){
	$.ajax({
		url: 'Empleado', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {'option':'shem','codE':codE},
		success: function(data){
			if(data!=null){
				$.each(data,function(item,e){
					$('#codEmple').val(e.cod_empleado);
					$('#NombreEmple').val(e.nom_empleado);
					$('#apaternoEmple').val(e.apaterno)
					$('#amaternoEmple').val(e.amaterno);
					$('#emailEmple').val(e.email);
					$('#telefonoEmple').val(e.telefono);
					$('#supervisor option[value="'+ e.cod_emple_superv +'"]').attr('selected',true);
					$('#cargo option[value="'+ e.cod_cargo +'"]').attr('selected',true);
					$('#UserEmple').val(e.usuario);
				});
			}else{console.log("error al seleccionar un empleado de la tabla");}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
/**************************************************/
// script para validar el formulario de Registro cupon
var SendDataNewCupon=function(){
	if(ValCampoVacio('CodCupon')){
		if(ValCampoVacio('CantUsosCupon')){
			if(validateNumEntero('CantUsosCupon')){
				if(ValCampoVacio('importeCupon')){
					if(validateDecimal('importeCupon')){
						$.ajax({
							url: 'Cupon', 
							type: 'POST',
							dataType: 'json',
							async: true,
							data: {
								'option':'GeCup',
								'ID':$('#CodCupon').val(),
								'CantUsos':$('#CantUsosCupon').val(),
								'Import':$('#importeCupon').val()
								},
							success: function(d){
								if(d=='1'){
									SweetAlertMessage('Registro Exitoso !','\n','success',false,1500);
									EmptyFormNewCupon();
								}else{
									SweetAlertMessage('Registro Fallido !','\n','error',false,1500);
									EmptyFormNewCupon();
									return false;
								}
							},
							error:function(e){console.log(e.getMessage);}
						});
					}else{SweetAlertMessage("Error","Ingrese un importe valido, debe tener como máximo 2 decimales","error",null,2500);return false;}
				}else{SweetAlertMessage("Error","Ingrese Importe S/.","error",null,2500);return false;}	
			}else{SweetAlertMessage("Error","La cantidad debe ser un entero","error","Continuar",2500);return false;}
		}else{SweetAlertMessage("Error","Ingrese Cantidad de usos","error",null,2500);return false;}
	}else{SweetAlertMessage("Error","Ingrese Código de Cupón","error",null,2500);return false;}
}
/**************************************************/
// script para Limpiar el Formulario de Generar Cupón
var EmptyFormNewCupon=function(){
	$('#CodCupon').val('');
	$('#CantUsosCupon').val('');
	$('#importeCupon').val('');
}
/**************************************************/
// script para Limpiar el form de Orden de Credito
var EmptyFormNewOrdenCredito=function(){
	$('#codOrdenV').val('');
	$('#dniClientOV').val('');
	$("#bodyTabProdOrdC").empty();
	$('#codProd').val('');
	$('#Cantidad').val(1);
	$('#Precio').val('');
	$('#bodyTabProdOrdDevol').empty();
	$('#importeDevol').val('');
	DeshabiButton('btn-PrcesarOC');
}
/**************************************************/
// script Cargar data de Orden de Credito
var LoadDataGOrdenCredito=function(cod){
	$.ajax({
		url: 'OrdenCredito', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {'option':'ShVCOC','codOV':cod},
		success: function(data){
			if(data!='0'){
				if(data.length!=0){
					$.each(data,function(item,c){
						$('#dniClientOV').val(c.dni_cliente);
						LoadDataGTabOrdenCredito(c.cod_ordenVenta);
					});
				}else{
					SweetAlertMessage('Error !','No existe esta Orden de Venta','error',false,2500);
					$('#codOrdenV').val('');
					$('#dniClientOV').val('');
					$("#bodyTabProdOrdC").empty();
					$('#codProd').val('');
					$('#Cantidad').val(1);
					$('#Precio').val('');
					$('#bodyTabProdOrdDevol').empty();
					$('#importeDevol').val('');
					DeshabiButton('btn-PrcesarOC');
					Prod=[]
					codOCV='';
					TTDOC=0.00;
					return false;
				}
			}else if(data=='0'){SweetAlertMessage('Error !','Esta Orden de Venta ya generó una Orden de Crédito','error',false,2500);}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
/**************************************************/
// script Cargar tabla de detalle de orden
var LoadDataGTabOrdenCredito=function(codV){
	$.ajax({
		url: 'OrdenCredito', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {'option':'ShDVCOC','codOV':codV},
		success: function(data){
			if(data.length!=0){
				var bodyTab="";
				$.each(data,function(item,e){
					bodyTab+='<tr id="L'+e.cod_producto+'">';
					bodyTab+='<td>'+e.cod_producto+'</td>';
					bodyTab+='<td>'+e.cantidad_venta+'</td>';
					bodyTab+='<td>'+e.precio_detalleventa+'</td>';
					bodyTab+='<td>'+new Intl.NumberFormat("es-PE").format(e.cantidad_venta*e.precio_detalleventa)+'</td>';
					bodyTab+='<td><button class="btn btn-warning" title="Seleccionar Producto" onclick="jsBuscar(`'+e.cod_producto+'`)"><i class="bi bi-check2-circle"></i></button></td>';
					bodyTab+='</tr>';
				});
				$("#bodyTabProdOrdC").empty();
			    $("#bodyTabProdOrdC").append(bodyTab);
				$('#codProd').val('');
				$('#Cantidad').val(1);
				$('#Precio').val('');
				$("#bodyTabProdOrdDevol").empty();
				$('#importeDevol').val('');
				Prod=[];
				TTDOC=0.00;
			}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
var LoadTableProdSelect=function(){
	var bodyTab="";
	const prodOC=$('#codProd').val();
	bodyTab+='<tr id="S'+$('#codProd').val()+'">';
	bodyTab+='<td>'+$('#codProd').val()+'</td>';
	bodyTab+='<td>1</td>';
	bodyTab+='<td>'+$('#Precio').val()+'</td>';
	bodyTab+='<td>'+new Intl.NumberFormat().format($('#Cantidad').val()*$('#Precio').val())+'</td>';
	bodyTab+='<td><button class="btn btn-danger" title="Eliminar Producto" onclick="DeleteRowTable2(`'+prodOC+'`)"><i class="bi bi-trash"></i></button></td>';
	bodyTab+='</tr>';
    $("#bodyTabProdOrdDevol").append(bodyTab);
}
function DeleteRowTable2(x){
	buscar=x;
	encontradoResultado=false;
	$("#bodyTabProdOrdDevol tr").find('td:eq(0)').each(function () {
	    codigo = $(this).html();
	   if(codigo==buscar){
	    	trDelResultado=$(this).parent();
			PR=trDelResultado.find("td:eq(0)").html();
			CT=trDelResultado.find("td:eq(1)").html();
			PC=trDelResultado.find("td:eq(2)").html();
			TT=parseFloat(trDelResultado.find("td:eq(3)").html());
	        $('#S'+x).remove();
			SumImporDevlOC();
			$('#L'+x).removeClass('d-none');
	        encontradoResultado=true;
	    }
	})
	if(!encontradoResultado){
		$('#S'+x).remove();
	}
}
function jsBuscar(x){
	buscar=x;
	encontradoResultado=false;
	$("#bodyTabProdOrdC tr").find('td:eq(0)').each(function () {
	    codigo = $(this).html();
	    if(codigo==buscar){
	    	trDelResultado=$(this).parent();
	        $('#codProd').val(trDelResultado.find("td:eq(0)").html());
			$('#Cantidad').val(1);
			$('#Precio').val(trDelResultado.find("td:eq(2)").html());
			HabilitButton('btn-DevolverProd');
	        encontradoResultado=true;
	    }
	})
	if(!encontradoResultado)
		SweetAlertMessage('Error !','No existe un producto seleccionado','error',false,1500); return false;
}
function jsBuscar2(){
	buscar=$('#codProd').val();
	$('#L'+buscar).addClass('d-none');
	encontradoResultado=false;
	$("#bodyTabProdOrdDevol tr").find('td:eq(0)').each(function () {
	    codigo = $(this).html();
	    if(codigo==buscar){
	    	trDelResultado=$(this).parent();
	        encontradoResultado=true;
	    }else{
		}
	});
	if(!encontradoResultado){
		LoadTableProdSelect();
		SumImporDevlOC();
	}
}
function SumImporDevlOC(){
	TTDOC=0.00;
	$("#bodyTabProdOrdDevol tr").find('td:eq(2)').each(function () {
		TTDOC+=parseFloat($(this).html());
	});
	$('#importeDevol').val(TTDOC);
	if(TTDOC>0){
		HabilitButton('btn-PrcesarOC');
	}else{
		DeshabiButton('btn-PrcesarOC');
	}
}
var SendDataOrdenCred=function(GC){
	$.ajax({
		url: 'OrdenCredito', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {
			'option':'rOC',
			'codOV':codOCV,
			'dniCl':$('#dniClientOV').val(),
			'ImptTOC':$('#importeDevol').val(),
			'Cupon':GC
			},
		success: function(data){
			if(data!=null){
				let imptx=TTDOC;
				SendDataOCDetails(data[0]);
				if(GC==1){
					swal({
						title: 'Cupón Generado !',
						text: 'Código: '+data[1]+' \nImporte: '+imptx+' Gracias.',
						icon: 'success',
						button: 'OK',
						closeOnClickOutside: false
					});
					imptx=0.00;
				}
			}else{
				SweetAlertMessage('Error !','Error al registrar la Orden de Crédito. Intentelo de nuevo en unos minutos.','error',false,4000); 
				Prod=[]
				$('#codOrdenV').val('');
				$('#dniClientOV').val('');
				$("#bodyTabProdOrdC").empty();
				$('#codProd').val('');
				$('#Cantidad').val(1);
				$('#Precio').val('');
				$('#bodyTabProdOrdDevol').empty();
				$('#importeDevol').val('');
				DeshabiButton('btn-PrcesarOC');
				codOCV='';
				Prod=[];
				CCopn='';
				TTDOC=0.00;
				return false;				
			}
		},
		error:function(e){console.log("error ajax >> "+e.getMessage);}
	});
}
function SendDataOCDetails(COC){
	LoadArrayProdDOC();
	if(Prod.length!=0){
		var x=0;
		$.each(Prod,function(item,e){
			$.ajax({
				url: 'OrdenCredito', 
				type: 'POST',
				dataType: 'json',
				async: true,
				data: {
					'option':'rOCDT',
					'codOC':COC,
					'Prod':e.codigo,
					'cant':e.cantidad,
					'precio':e.precio
					},
				success: function(){},
				error:function(e){console.log("error ajax >> "+e.getMessage);}
			});
			x=item+1;
		});
		if(x!=0){
			SweetAlertMessage("Orden de Crédito Registrada","\n","success",false,2000);
			Prod=[]
			$('#codOrdenV').val('');
			$('#dniClientOV').val('');
			$("#bodyTabProdOrdC").empty();
			$('#codProd').val('');
			$('#Cantidad').val(1);
			$('#Precio').val('');
			$('#bodyTabProdOrdDevol').empty();
			$('#importeDevol').val('');
			DeshabiButton('btn-PrcesarOC');
			codOCV='';
			Prod=[];
			CCopn='';
			TTDOC=0.00;
		}else{
			SweetAlertMessage("Error! Orden de Crédito no Registrada","\n","error",false,2500);
			Prod=[]
			$('#codOrdenV').val('');
			$('#dniClientOV').val('');
			$("#bodyTabProdOrdC").empty();
			$('#codProd').val('');
			$('#Cantidad').val(1);
			$('#Precio').val('');
			$('#bodyTabProdOrdDevol').empty();
			$('#importeDevol').val('');
			DeshabiButton('btn-PrcesarOC');
			codOCV='';
			Prod=[];
			CCopn='';
			TTDOC=0.00;
		}
	}
}
var LoadArrayProdDOC=function(){
	z=$("#bodyTabProdOrdDevol tr").length;
	if(z!=0){
		for(i=0; i<z;i++){
			$("#bodyTabProdOrdDevol").find('tr:eq('+i+')').each(function () {
				const prodcuto={
					codigo:$("#bodyTabProdOrdDevol tr:eq("+i+")").find('td:eq(0)').html(),
					cantidad:parseInt($("#bodyTabProdOrdDevol tr:eq("+i+")").find('td:eq(1)').html()),
					precio:parseFloat($("#bodyTabProdOrdDevol tr:eq("+i+")").find('td:eq(2)').html()),
					Total:parseFloat($("#bodyTabProdOrdDevol tr:eq("+i+")").find('td:eq(3)').html())
				}
				Prod.push(prodcuto);
			});
		}
	}
}