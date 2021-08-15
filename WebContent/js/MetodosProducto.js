/**
 * Script GameStore Copyright 2021
 */
/**************************************************/
// script para validar el crud de empleado
var ValFormProducts=function(){
	if(ValCampoVacio('codigoProd')){
		codProd=$('#codigoProd').val();
		if(ValCampoVacio('NombreProd')){
			Nomprod=$('#NombreProd').val();
			if(ValCampoVacio('PrecioProd')){
				if(validateDecimal('PrecioProd')){
					priceProd=$('#PrecioProd').val();
					if(ValCampoVacio('StockProd')){
						if(validateNumEntero('StockProd')){
							stockProd=$('#StockProd').val();
							if(ValCampoVacio('categoriaProd')){
								categProd=$('#categoriaProd').val();
								return true;
							}else{SweetAlertMessage("Error","Seleccione una Categoría","error","Continuar",2000);return false;}
						}else{SweetAlertMessage("Error","El stock debe ser entero","error","Continuar",2000);return false;}
					}else{SweetAlertMessage("Error","Ingrese Stock del Producto","error","Continuar",2000);return false;}
				}else{SweetAlertMessage("Error","Ingrese el Precio Valido, debe tener Máximo 2 decimales.","error","Continuar",3000);return false;}
			}else{SweetAlertMessage("Error","Ingrese el Precio S/.","error","Continuar",2000);return false;}
		}else{SweetAlertMessage("Error","Ingrese el nombre del Producto","error","Continuar",2000);return false;}
	}else{SweetAlertMessage("Error","Ingrese Un Código Para Producto","error","Continuar",2000);return false;}
}
/**************************************************/
// script para pintar la tabla de empleados
var LoadTablaProducts=function(){
	$.ajax({
		url: 'Productos', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {'option':'lallpd'},
		success: function(data){
			if(data!=null){
				if(data.length!=0){crearTablaProducts(data);}
			}else{SweetAlertMessage('No existen Registros','','error',true,1500);}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
var crearTablaProducts=function(data){
	var bodyTab="";
	$.each(data,function(item,e){
		bodyTab+='<tr>';
		bodyTab+='<td>'+e.nom_producto+'</td>';
		bodyTab+='<td>'+e.precio_producto+'</td>';
		bodyTab+='<td>'+e.stock_producto+'</td>';
		bodyTab+='<td>'+e.nom_categoria+'</td>';
		bodyTab+='<td><button class="btn" style="background: #fd7e14;" title="Editar Producto" onclick="SelectTablaProducts(`'+e.cod_producto+'`)"><i class="bi bi-pencil"></i></button></td>';
		bodyTab+='<td><button class="btn btn-danger" title="Eliminar Producto" onclick="DeleteProducto(`'+e.cod_producto+'`)"><i class="bi bi-trash-fill"></i></button></td>';
		bodyTab+='</tr>';
	});
	$("#BodyTabProductos").empty();
    $("#BodyTabProductos").append(bodyTab);
}
var SelectTablaProducts=function(CodProd){
	$('#buttonAgregar').css({'pointer-events':'none'});
	$('#buttonModificar').css({'pointer-events':'auto'});
	$.ajax({
		url: 'Productos', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {'option':'pp','codPro':CodProd},
		success: function(data){
			if(data!=null){
				if(data.length!=0){
					$.each(data,function(item,e){
						$('#codigoProd').val(e.cod_producto);
						$('#NombreProd').val(e.nom_producto);
						$('#PrecioProd').val(e.precio_producto)
						$('#StockProd').val(e.stock_producto);
						$('#categoriaProd option[value="'+ e.cod_cat +'"]').attr('selected',true);
					});
				}
			}else{console.log("error al seleccionar un empleado de la tabla");}
		},
		error:function(e){console.log("error ajax "+e.getMessage)}
	});
}
/**************************************************/
var sendProducto = function()
{
	$.ajax({
		url: 'Productos', 
		type: 'POST',
		dataType: 'json',
		async: true,
		data: {	
			'option':'am',
			'nomProducto':Nomprod,
			'precioProducto':priceProd,
			'canStock':stockProd,
			'codCategoria':categProd
			},
		success: function(data){
			if(data=='1'){
				SweetAlertMessage('Registro Exitoso !','','success',true,1500);
				$('#codigoProd').val('0');
				$('#buttonAgregar').css({'pointer-events':'auto'});
				$('#buttonModificar').css({'pointer-events':'none'});
				$('#NombreProd').val('');
				$('#PrecioProd').val('')
				$('#StockProd').val('');
				$('#categoriaProd option[value="C01"]').attr('selected',true);
				LoadTablaProducts();
			}else{SweetAlertMessage('Registro Fallido !','','error',true,1500);}
		},
		error:function(e){
			console.log('errorajax: '+ e.getMessage);
		}
	});
}
var sendDataUpdateProducts=function(){
	$.ajax({
		url: 'Productos', 
		type: 'POST',
		async: true,
		data: {
			'option':'pm',
			'codProducto':codProd,
			'nomProducto':Nomprod,
			'precioProducto':priceProd,
			'canStock':stockProd,
			'codCategoria':categProd
			},
		success: function(d){
			if(d=='1'){
				SweetAlertMessage('Actualización Exitosa !','','success',true,1500);	
				$('#codigoProd').val('0');
				$('#buttonAgregar').css({'pointer-events':'auto'});
				$('#buttonModificar').css({'pointer-events':'none'});
				$('#NombreProd').val('');
				$('#PrecioProd').val('')
				$('#StockProd').val('');
				$('#categoriaProd option[value="C01"]').attr('selected',true);
				LoadTablaProducts();
			}else{SweetAlertMessage('Actualización Fallida !','','error',true,1500);}
		},
		error:function(e){console.log(e.getMessage);}
	});
} 
var DeleteProducto = function(CodProd){
	swal({
        title: "¿Deseas eliminar el Producto?",
        text: "Si es eliminado ya no se podrá recuperar la información",
        icon: "warning",
        buttons: true,
        dangerMode: true
    }).then((willDelete) => {
        if (willDelete) {
            $.ajax({
				url: 'Productos', 
				type: 'POST',
				dataType: 'json',
				async: true,
				data: {'option':'em','codProducto':CodProd},
				success: function(data){
					if(data=='1'){
						SweetAlertMessage('¡Eliminado Correctamente!','','success',true,1500);
						LoadTablaProducts();
					}else{SweetAlertMessage('¡No se pudo Eliminar !','','error',true,1500);}
				},
				error:function(e){
					console.log('errorajax: '+ e.getMessage);
				}
			});
        }
    });
} 



