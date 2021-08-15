/**
 * Script GameStore Copyright 2021
 */
/**************************************************/
// script para validar el Form Registrar Cliente
var ValFormCliente=function(){
	if(ValCampoVacio('txtDNIClient')){
		if(expreDNI.test($('#txtDNIClient').val())){
			DNIClient=$('#txtDNIClient').val()
			if(ValCampoVacio('NombreClient')){
				NomClient=$('#NombreClient').val()
				if(ValCampoVacio('apaternoClient')){
					APaternoClient=$('#apaternoClient').val()
					if(ValCampoVacio('amaternoClient')){
						AMaternoClient=$('#amaternoClient').val()
						if(ValCampoVacio('telefonoClient')){
							if(expreTelf.test($('#telefonoClient').val())){
								TelfClient=$('#telefonoClient').val()
								return true;
							}else{SweetAlertMessage("Advertencia","Ingrese un Teléfono Válido, debe empezar con 9 y tener 9 digitos","warning","Continuar",5000);return false;}
						}else{SweetAlertMessage("Error","Ingrese un Teléfono","error","Continuar",2000);return false;}
					}else{SweetAlertMessage("Error","Ingrese un Apellido Materno","error","Continuar",2000);return false;}
				}else{SweetAlertMessage("Error","Ingrese un Apellido Paterno","error","Continuar",2000);return false;}
			}else{SweetAlertMessage("Error","Ingrese Un Nombre","error","Continuar",2000);return false;}
		}else{SweetAlertMessage('¡Advertencia!','El DNI debe tener 8 dígitos','warning',false,2000);return false;}
	}else{SweetAlertMessage("Error","Ingrese Un DNI","error","Continuar",2000);return false;}
}
/**************************************************/
// script para validar el Form Registrar Cliente Mant
var ValFormClientMant=function(){
	if(ValCampoVacio('dniClient')){
		if(expreDNI.test($('#dniClient').val())){
			if(ValCampoVacio('NombreClient')){
				if(ValCampoVacio('apertenoClient')){
					if(ValCampoVacio('amertenoClient')){
						if(ValCampoVacio('telefonoClient')){
							if(expreTelf.test($('#telefonoClient').val())){
								return true;
							}else{SweetAlertMessage("Advertencia","Ingrese un Teléfono Válido, debe empezar con 9 y tener 9 digitos","warning","Continuar",5000);return false;}
						}else{SweetAlertMessage("Error","Ingrese un Teléfono","error","Continuar",2000);return false;}
					}else{SweetAlertMessage("Error","Ingrese un Apellido Materno","error","Continuar",2000);return false;}
				}else{SweetAlertMessage("Error","Ingrese un Apellido Paterno","error","Continuar",2000);return false;}
			}else{SweetAlertMessage("Error","Ingrese Un Nombre","error","Continuar",2000);return false;}
		}else{SweetAlertMessage('¡Advertencia!','El DNI debe tener 8 dígitos','warning',false,2000);return false;}
	}else{SweetAlertMessage("Error","Ingrese Un DNI","error","Continuar",2000);return false;}
}
/**************************************************/
// script para limpiar el Form Cliente
var emptyFormClient=function(){
	$('#txtDNIClient').val('');
	$('#NombreClient').val('');
	$('#apaternoClient').val('');
	$('#amaternoClient').val('');
	$('#telefonoClient').val('');
}
/**************************************************/
// script para limpiar el Form Cliente Mantenimiento
var emptyFormClientMant=function(){
	$('#dniClient').val('');
	$('#NombreClient').val('');
	$('#apertenoClient').val('');
	$('#amertenoClient').val('');
	$('#telefonoClient').val('');
	LoadTablaClientMant();
	$('#btn-ModCliente').css({'pointer-events':'none'});
}
/**************************************************/
// script para Enviar un Cliente
var sendDataClient=function(){
	$.ajax({
		url: 'Clientes', 
		type: 'POST',
		async: true,
		data: {
			'option':'reCt',
			'DNIC':DNIClient,
			'NombC':NomClient,
			'ApePat':APaternoClient,
			'ApeMat':AMaternoClient,
			'Telf':TelfClient
			},
		success: function(d){
			if(d=='1'){
				SweetAlertMessage('Registro Exitoso !','','success',true,1500);
				emptyFormClient();
			}else{SweetAlertMessage('Registro Fallido !','','error',true,1500);}
		},
		error:function(e){console.log(e.getMessage);}
	});
}
/**************************************************/
// script para Actualizar un Cliente
var sendDataUpdateClient=function(){
	$.ajax({
		url: 'Clientes', 
		type: 'POST',
		async: true,
		data: {
			'option':'upCt',
			'DNIC':$('#dniClient').val(),
			'NombC':$('#NombreClient').val(),
			'ApePat':$('#apertenoClient').val(),
			'ApeMat':$('#amertenoClient').val(),
			'Telf':$('#telefonoClient').val()
			},
		success: function(d){
			if(d=='1'){
				SweetAlertMessage('Actualización Exitosa !','','success',true,1500);
				emptyFormClientMant();	
			}else{SweetAlertMessage('Actualización Fallida !','','error',true,1500);}
		},
		error:function(e){console.log(e.getMessage);}
	});
}
/**************************************************/
// script para pintar la tabla de empleados
var LoadTablaClientMant=function(){
	$.ajax({
		url: 'Clientes', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {'option':'lsCt'},
		success: function(data){
			if(data!=null){
				if(data.length!=0){crearTablaClientMant(data);}
			}else{console.log("error al cargar la tabla de Clientes");}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
var crearTablaClientMant=function(data){
	var bodyTab="";
	$.each(data,function(item,e){
		bodyTab+='<tr><th>'+e.DNI+'</th>';
		bodyTab+='<td>'+e.nom_cliente+'</td>';
		bodyTab+='<td>'+e.apaterno+'</td>';
		bodyTab+='<td>'+e.amaterno+'</td>';
		bodyTab+='<td>'+e.telefono+'</td>';
		bodyTab+='<td><button class="btn" style="background: #fd7e14;" onclick="SelectTablaClientMant(`'+e.DNI+'`)"><i class="bi bi-pencil"></i></button></td>';
		bodyTab+='</tr>';
	});
	$("#bodyTabClient").empty();
    $("#bodyTabClient").append(bodyTab);
}
/**************************************************/
// script para Seleccionar un Cliente de la tabla Mant
var SelectTablaClientMant=function(CodC){
	$.ajax({
		url: 'Clientes', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {'option':'shCt','DNIC':CodC},
		success: function(data){
			if(data!=null){
				$.each(data,function(item,e){
					$('#dniClient').val(e.DNI);
					$('#NombreClient').val(e.nom_cliente);
					$('#apertenoClient').val(e.apaterno);
					$('#amertenoClient').val(e.amaterno);
					$('#telefonoClient').val(e.telefono);
					$('#btn-ModCliente').css({'pointer-events':'auto'});
				});
			}else{console.log("error al seleccionar un empleado de la tabla");}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
/**************************************************/
// script para Validar la existencia de un Cliente
var ValidaClient=function(DNIClient){
	$.ajax({
		url: 'Clientes', 
		type: 'POST',
		async: true,
		data: {'option':'VlCt',	'DNIC':DNIClient},
		success: function(d){
			if(d=='1'){
				SweetAlertMessage('Correcto !','','success',true,1500);
				//HabilitButton('btn-AddCart');
				HabilitButton('btn-reCp');
			}else{
				SweetAlertMessage('Fallido !','El Cliente no esta Registrado','error',true,1500);
				DeshabiButton('btn-AddCart');
				DeshabiButton('btn-reCp');
			}
		},
		error:function(e){console.log(e.getMessage);}
	});
}
/**************************************************/
// script para pintar el dropdown Categoria
var getCategories=function(){
	$.ajax({
		url: 'Productos', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {'option':'licatg'},
		success: function(a){
			if(a!=null){
				createCBOCategories(a);
			}else{console.log("error al llenar le comboBox de Categorias");}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
var createCBOCategories=function(a){
	var bodyCbo="";
	$.each(a,function(item,e){bodyCbo+='<option value="'+e.cod_cat+'">'+e.nom_categoria+'</option>';});
	$("#Cbocategories").empty();
    $("#Cbocategories").append(bodyCbo);
}
/**************************************************/
// script para pintar el dropdown Juegos
var getGames=function(){
	$.ajax({
		url: 'Productos', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {
			'option':'ligame',
			'catg':$("#Cbocategories").val()
		},
		success: function(a){
			if(a!=null){
				createCBOGames(a);
			}else{console.log("error al llenar le comboBox de Juegos segun Categoria");}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
var createCBOGames=function(a){
	var bodyCbo="<option>--Seleccione--</option>";
	$.each(a,function(item,e){bodyCbo+='<option value="'+e.cod_producto+'">'+e.nom_producto+'</option>';});
	$("#CboGames").empty();
    $("#CboGames").append(bodyCbo);
}
/**************************************************/
// script para validar el Form Registrar Venta
// Categ, Game, Price;
var ValFormVenta=function(){
	if(ValCampoVacio('DNIClient')){
		if(expreDNI.test($('#DNIClient').val())){
			DNIClientV=$('#DNIClient').val();
			cant=$('#CantidadGame').val();
			nFilas = $("#TableProdCom tr").length;
			if(nFilas>=1){
				if(ValCampoVacio('TotalNeto')){
					TTNeto=$('#TotalNeto').val();
					return true;
				}else{SweetAlertMessage("Error !","No se registra un Total Neto","error","Continuar",2000);return false;}
			}else{SweetAlertMessage("Error !","No se cuenta con Juegos Seleccionados","error","Continuar",2000);return false;}
		}else{SweetAlertMessage('¡Advertencia!','El DNI debe tener 8 dígitos','warning',false,2000);return false;}
	}else{SweetAlertMessage("Error","Ingrese Un DNI","error","Continuar",2000);return false;}
}
/**************************************************/
// script para limpiar el Form Registrar Venta
var emptyFormRVentas=function(){
	$('#DNIClient').val('');
	getCategories();
	$("#CboGames").empty();
	$("#CboGames").html('<option>--Seleccione--</option>');
	$('#PriceGame').val('');
	DeshabiInput('PriceGame');
	$('#CantidadGame').val('');
	DeshabiButton('btn-AddCart');
	$('#TotalBruto').val('');
	DeshabiInput('TotalBruto');
	DeactivateCheckBox('chkDescuento');
	$('#DesctCupon').val('');
	DeshabiInput('DesctCupon');
	DeshabiButton('btn-DescCup');
	$('#DescuentoVenta').val('');
	DeshabiInput('DescuentoVenta');
	$('#TotalNeto').val('');
	DeshabiInput('TotalNeto');
	DeshabiButton('btn-reCp');
}
/**************************************************/
// script para obtener el precio de un porducto 
// seleccionado desde el ComboBox
var getDataGameCatg=function(){
	$.ajax({
		url: 'Productos', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {
			'option':'lopdcg',
			'catg':$("#Cbocategories").val(),
			'prod':$("#CboGames").val()
		},
		success: function(a){
			if(a.length!=0){
				$.each(a,function(item,e){
					$('#PriceGame').val(e.precio_producto);
					Game=e.cod_producto ;
					Price=e.precio_producto;
				});
			}else{
				console.log("error al llenar le comboBox de Juegos segun Categoria");
				$('#PriceGame').val('');
			}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
/**************************************************/
// script para crear la tabla de producto (carrito)
var LoadTablaVenta=function(data){	
	var bodyTab="";
	TTBruto=0.0;
	$.each(data,function(item,e){
		bodyTab+='<tr>';
		bodyTab+='<td>'+e.codigo+'</td>';
		bodyTab+='<td>'+e.cantidad+'</td>';
		bodyTab+='<td>'+e.precio+'</td>';
		bodyTab+='<td>'+e.Total+'</td>';
		bodyTab+='<td><button class="btn btn-danger" title="Eliminar del Carrito" onclick="removeItemFromArr(`'+item+'`)"><i class="bi bi-cart-x"></i></button></td>';
		bodyTab+='</tr>';
		TTBruto=TTBruto+e.Total
	});
	$("#TableProdCom").empty()
    $("#TableProdCom").append(bodyTab);
	$('#TotalBruto').val(TTBruto);
	$('#TotalNeto').val(TTBruto);
}
/**************************************************/
// script para eleiminar un elemento de la lista (carrito)
var removeItemFromArr=function(item) {
	carrito.splice (item, 1);
	localStorage.setItem('carrito', JSON.stringify(carrito));
	LoadTablaVenta(carrito);
}
/**************************************************/
// script para registrar una COMPRA
var GenerarCompra=function(){
	$.ajax({
		url: 'Ventas', 
		type: 'POST',
		async: true,
		data: {
			'option':'reVt',
			'DNIC':DNIClientV,
			'idCup':$('#DesctCupon').val(),
			'ImtV':TTNeto
		},
		success: function(a){
			sendDataCarrito(carrito, a);
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});	
}
function sendDataCarrito(ca,cod){
	var x=0;
	$.each(ca,function(item,e){
		$.ajax({
			url: 'Ventas', 
			type: 'POST',
			async: true,
			data: {
				'option':'reDV',
				'codV':cod,
				'codP':e.codigo,
				'cantd':e.cantidad,
				'prci':e.precio
			},
			success: function(){},
			error:function(e){
				console.log("error ajax >> "+e.getMessage)
			}
		});
		x=item+1;
	});
		
	if(x!=0){
		SweetAlertMessage("Venta Registrada","\n","success",false,2000);
		localStorage.clear();
		carrito=[]
		LoadTablaVenta(carrito);
	}else{
		SweetAlertMessage("Error! Venta no Registrada","\n","error",false,2500);
	}
	emptyFormRVentas();
}