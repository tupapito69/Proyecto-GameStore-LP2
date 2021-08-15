/*
 * Script GameStore Copyright 2021
 */
let codEmple,NomEmple,APaternoEmple,AMaternoEmple,emailEmple,TelfEmple,supervEmple,CargoEmple,UserEmple;
//const pag=1; const xPag=3;
//let nPag, offset, hasta;
var dataTT; var dataTEP;
var TTDOC=0.00;
var Prod=[];
var CCopn;
var codOCV;
$(document).ready(function (){
	//sideBar
	$('#menu-toggle').click(function (e) {
	    e.preventDefault();
	    $('#wrapper').toggleClass('toggled');
	});
	$('#UpdatePassword').click(function(){
		$('#myModalUpdatePass').modal('show');
		$('#PassAntigua').val('');
		$('#PassNew').val('');
		$('#PassNewConfirm').val('');
	});
	$('#btn-ActionProduct').click(function(event){
		event.preventDefault();
		LoadContentMant('p');
		LoadTablaProducts();
		$('#codigoProd').val('0');
		$('#buttonModificar').css({'pointer-events':'none'});
		$('#NombreProd').val('');
		$('#PrecioProd').val('')
		$('#StockProd').val('');
		$('#categoriaProd option[value="C01"]').attr('selected',true);
	});
	$('#btn-ActionHistVent').click(function(event){
		event.preventDefault();
		HborrarImputsTables();
		LoadContentMant('h');
	})
	$('#btn-ActionClient').click(function(event){
		event.preventDefault();
		LoadContentMant('m');
		emptyFormClientMant();
	});
	$('#btn-ActionGeneCupon').click(function(event){
		event.preventDefault();
		LoadContentMant('c');
		EmptyFormNewCupon();
	});
	$('#btn-ActionNotaCredt').click(function(event){
		event.preventDefault();
		LoadContentMant('n');
		EmptyFormNewOrdenCredito();
		swal({
			title: 'Información del Sitio Web !',
			text: 'Por políticas de la Institución y configuración del Servicio, Solo se permite generar una '+
				  'orden de crédito por cada orden de venta (Boleta/Factura) La cantidad máxima para hacer una '+
				  'devolución es de 1 por cada producto en la orden de venta (Boleta/Factura). '+
				  '\nNo obstante se pueden elegir diferentes productos.',
			icon: 'info',
			button: 'Continuar',
			closeOnClickOutside: false
		});
	});
	$('#btn-ActionEmple').click(function(event){
		event.preventDefault();
		LoadContentMant('e');
		emptyForm();
		$('#codEmple').val(0);	
		LoadComboSuper();
		LoadComboCargo();
		LoadTablaEmple();
		$('#btn-mdE').css({'pointer-events':'none'});
		$('#botones').html('');
	});
	$('#btn-reE').click(function(){	
		if(ValFormEmpleado()){
			sendDataEmple();
		}
	});
	$('#btn-GnCupon').click(function(){
		SendDataNewCupon();
	});
	$('#btn-Gcod').click(function(){
		if(ValCampoVacio('apaternoEmple')&&ValCampoVacio('amaternoEmple')&&ValCampoVacio('telefonoEmple')!=''){
			if(expreTelf.test($('#telefonoEmple').val())){
				$('#UserEmple').val(GenerarUser());
			}else{
				SweetAlertMessage("Advertencia !","Ingrese un Teléfono Válido, debe empezar con 9 y tener 9 digitos","warning","Continuar",6000);
			}
		}else{
			SweetAlertMessage('Advertencia !','Debe ingresar un sus apellidos y teléfono','warning',false,3000);
		}
	});
	$('#btn-mdE').click(function(){	
		if(ValFormEmpleado()){
			sendDataUpdateEmple();
			$('#btn-reE, #btn-Gcod').css({'pointer-events':'auto'});
			$(this).css({'pointer-events':'none'});
		}
	});
	$('#btn-ShOrdenVenta').click(function(event){
		event.preventDefault();
		if(ValCampoVacio('codOrdenV')){
			if(expreOrV.test($('#codOrdenV').val())){
				codOCV=$('#codOrdenV').val();
				LoadDataGOrdenCredito(codOCV);
			}else{SweetAlertMessage('Advertencia !','El código debe empezar con OV seguido de dígitos','warning',false,1800); return false;}
		}else{SweetAlertMessage('Error !','Ingrese un código de Orden de Venta','error',false,1500); return false;}
	});
	$('#btn-DevolverProd').click(function(event){
		event.preventDefault();
		if(ValCampoVacio('codProd')&&ValCampoVacio('Cantidad')&&ValCampoVacio('Precio')){
			jsBuscar2();
			DeshabiButton('btn-DevolverProd');
			$('#codProd').val('')
			$('#Cantidad').val('')
			$('#Precio').val('')
		}
	});
	$('#btn-PrcesarOC').click(function(){
		if(ValCampoVacio('codOrdenV')){
			if(expreOrV.test($('#codOrdenV').val())){
				if(ValCampoVacio('dniClientOV')){
					if(ValCampoVacio('importeDevol')){
						Swal.fire({
							title: '¿Desea devolver en efectivo?',
							showDenyButton: true,
							confirmButtonText: 'Si',
							denyButtonText: 'Generar Cupón',
							allowOutsideClick: false
						}).then((result) => {
							if (result.isConfirmed) {
								SendDataOrdenCred(0);
							} else if (result.isDenied) {
								SendDataOrdenCred(1);
							}
						});
					}else{SweetAlertMessage('Error !','No existe Un importe para devolver','error',false,1500); return false;}
				}else{SweetAlertMessage('Error !','Debe Existir el DNI del cliente','error',false,1500); return false;}
			}else{SweetAlertMessage('Advertencia !','El código debe empezar con OV seguido de dígitos','warning',false,1800); return false;}
		}else{SweetAlertMessage('Error !','Ingrese un código de Orden de Venta','error',false,1500); return false;}
	});
});
function SelectTabEmple(data){
	SelectTablaEmple(data);
	$('#btn-reE, #btn-Gcod').css({'pointer-events':'none'});
	$('#btn-mdE').css({'pointer-events':'auto'});
}
function InfoApp(){
	swal({
		title: 'GameStore Version 1.0',
		text: 'Integrantes: \n\n '+
			  '─ Mogollon Espinoza, Carlos : i201922675 \n\n '+
			  '─ Barrios Jaramillo, Rodolfo : i201923975 \n\n '+
			  '─ Rios Balbuena, Guillermo : i201920858',
		icon: 'info',
		button: 'OK',
		closeOnClickOutside: false
	});
}
//-----------------------------------------------------------
/*var Paginator=function(){
	// Activar el primer botón
    $('#botones button:first-child').addClass('active');
	// Poner oyentes a cada botón
    var losBotones = document.querySelectorAll('#botones button');
    for(var i = 0; i < losBotones.length; i++){
        losBotones[i].addEventListener('click',function(){
            quitarActivo();
            var indice = parseInt(this.textContent);
            var o = (indice -1) * xPag;
            var h = indice * xPag;
            crearTablaEmple(o,h);
            $(this).addClass('active');
        });

    }
}*/

