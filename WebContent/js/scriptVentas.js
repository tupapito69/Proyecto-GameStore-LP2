/**
 * Script GameStore Copyright 2021
 */
//variables para registro de cliente
let DNIClient,NomClient,APaternoClient,AMaternoClient,TelfClient;
//variabeles para registro de ventas
let DNIClientV, Categ, Game1, Game, Price, cant, TTBruto, TTNeto, nFilas;
var carrito=[];
$(document).ready(function (){
	$('#btn-ActionVender').click(function(event){
		event.preventDefault();
		emptyFormRVentas();
		LoadContentVentas('rvt');
		getCategories();
		if (localStorage.getItem('carrito')) {
	        carrito = JSON.parse(localStorage.getItem('carrito'))
			LoadTablaVenta(carrito)
	    }
	});
	
	$('#btn-ActionRegistClient').click(function(event){
		event.preventDefault();
		emptyFormClient();
		LoadContentVentas('rcl');
	});
	
	$('#btn-reCl').click(function(){
		if(ValFormCliente()){
			sendDataClient();
		}
	});
	
	$('#btn-ModCliente').click(function(){
		if(ValFormClientMant()){
			sendDataUpdateClient();
		}
	});	
	
	$('#btn-ValCli').click(function(){
		if(ValCampoVacio('DNIClient')){
			if(expreDNI.test($('#DNIClient').val())){
				let DNICli=$('#DNIClient').val();
				ValidaClient(DNICli);
			}else{SweetAlertMessage('¡Advertencia!','El DNI debe tener 8 dígitos','warning',false,2000);return false;}
		}else{SweetAlertMessage("Error","Ingrese Un DNI","error","Continuar",2000);return false;}
		
	});
	/******************************/
	/**** Script para Ventas*******/
	/******************************/
	$('#Cbocategories').change(function () {
        getGames();
		$('#PriceGame').val('');
		DeshabiButton('btn-AddCart');
		$('#CantidadGame').val('');
    });
	$('#CboGames').change(function () {
        getDataGameCatg();
		$('#CantidadGame').val('');
    });
	$('#CantidadGame').change(function () {
		if(ValCampoVacio('CantidadGame')){
        	HabilitButton('btn-AddCart');
		}else{
			DeshabiButton('btn-AddCart');
		}
    });
	$('#btn-AddCart').click(function(){
		if(ValCampoVacio('PriceGame')){
			if(ValCampoVacio('CantidadGame')){
				if(validateNumEntero('CantidadGame')){
					cant=$('#CantidadGame').val();
					$.ajax({
						url: 'Productos', 
						type: 'POST',
						dataType: 'json',
						async: true,
						data: {
							'option':'vlstkp',
							'prod':$("#CboGames").val(),
							'cant':cant
						},
						success: function(a){
							switch(a){
								case 0: SweetAlertMessage("No hay Stock","","error",null,2500); break;
								case 1: 
									SweetAlertMessage("Stock Válido","","success",null,1000); 
									TTBruto=Price*cant;
									const prodcuto={
										codigo:Game,
										cantidad:cant,
										precio:Price,
										Total:(cant*Price)
									}
									carrito.push(prodcuto);
									LoadTablaVenta(carrito);
									localStorage.setItem('carrito', JSON.stringify(carrito));
									break;
							}
						},
						error:function(e){console.log("error ajax "+e.getMessage)}
					});
				}else{SweetAlertMessage("Error","La cantidad debe ser un entero","error","Continuar",2000);return false;}
			}else{SweetAlertMessage("Error !","No se Cuenta con una cantidad","error",null,2000);return false;}
		}else{SweetAlertMessage("Error !","Seleccione un Videojuego","error",null,2500);return false;}
	});
	$('#chkDescuento').click(function(){
		if($(this).prop('checked')) {
			HabilitInput('DesctCupon');
			HabilitButton('DesctCupon');
		}else{
			$('#DesctCupon').val('');
			DeshabiInput('DesctCupon');
			DeshabiButton('DesctCupon');
			DeshabiButton('btn-DescCup');
			$('#DescuentoVenta').val('');
			$('#TotalNeto').val($('#TotalBruto').val());
		}
	});
	$('#DesctCupon').change(function () {
		if(ValCampoVacio('DesctCupon')){
			HabilitButton('btn-DescCup');
		}
		else{
			DeshabiButton('btn-DescCup');
		}
    });
	$('#btn-DescCup').click(function(){
		let Cupon=$('#DesctCupon').val();
		$.ajax({
			url: 'Cupon', 
			type: 'POST',
			dataType: 'json',
			async: true,
			data: {
				'option':'shCup',
				'idCup':Cupon
			},
			success: function(a){
				if(a.length!=0){
					if(ValCampoVacio('TotalBruto')){
						$.each(a,function(item,e){
							$('#DescuentoVenta').val(e.importe);
							let TB=$('#TotalBruto').val();
							let DC=$('#DescuentoVenta').val();
							$('#TotalNeto').val((TB-DC).toFixed(2));
						});
					}else{SweetAlertMessage("Error !","Seleccione algunos Videojuegos","error",false,2000);return false;}
				}else{
					SweetAlertMessage("Cupón Inválido !","No Existe Este Cupón","error",null,2000);
					DeactivateCheckBox('chkDescuento');
					$('#DesctCupon').val('');
					DeshabiInput('DesctCupon');
					DeshabiButton('btn-DescCup');
					return false;
				}
			},
			error:function(e){console.log("error ajax "+e.getMessage)}
		});
	});
	$('#btn-reCp').click(function(){
		if(ValFormVenta()){
			GenerarCompra();
		}
	});
});

//removeItemFromArr=function( arr, item )
/*function jsBuscar(){
	//obtenemos el valor insertado a buscar
	buscar=Game;
	//utilizamos esta variable solo de ayuda y mostrar que se encontro
	encontradoResultado=false;
	//realizamos el recorrido solo por las celdas que contienen el código, que es la primera
	$("#TableProdCom tr").find('td:eq(0)').each(function () {
	     //obtenemos el codigo de la celda
	      codigo = $(this).html();
	       //comparamos para ver si el código es igual a la busqueda
	       if(codigo==buscar){
	            //aqui ya que tenemos el td que contiene el codigo utilizaremos parent para obtener el tr.
	            trDelResultado=$(this).parent();
	            //ya que tenemos el tr seleccionado ahora podemos navegar a las otras celdas con find
	            cantidadT=parseInt(trDelResultado.find("td:eq(1)").html());
				cantidad=cantidadT+parseInt($('#CantidadGame').val());
				trDelResultado.find("td:eq(1)").html(cantidad)
				
				//precio=parseFloat(trDelResultado.find("td:eq(2)"));
	            TotalT=parseFloat(trDelResultado.find("td:eq(3)").html());
				total=parseFloat(trDelResultado.find("td:eq(1)").html())*parseFloat(trDelResultado.find("td:eq(2)").html());
				trDelResultado.find("td:eq(3)").html(total)
				
				//TotalT=precio++;
	            //mostramos el resultado en el div
	            console.log("El cantidad es: "+cantidad+", el total es: "+TotalT)
	            encontradoResultado=true;
	       }
	})
	//si no se encontro resultado mostramos que no existe.
	if(!encontradoResultado)
		LoadTablaVenta(Game,cant,Price,TTBruto);
}*/








