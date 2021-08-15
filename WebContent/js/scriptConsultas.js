/**
 * Script GameStore Copyright 2021
 */
$(document).ready(function (){
	$('#consultasV').click(function(event){
			event.preventDefault();
			LoadContentConsul('cv');
			$('#DNIv').val('');
			EmptyFormConsultVen();
		});
	$('#consultasC').click(function(event){
			event.preventDefault();
			$('#DNIc').val('');
			LoadContentConsul('cc');
			$("#bodyTabConsultasC").empty();
	});
	$('#btn-ConsultarV').click(function(){	
		if(ValCampoVacio('DNIv')){
			if(expreDNI.test($('#DNIv').val())){
				let x=$('#DNIv').val();
				LoadTablaConsultasV(x);
			}else{SweetAlertMessage('¡Advertencia!','El DNI debe tener 8 dígitos','warning',false,2500);return false;}
		}else{SweetAlertMessage('¡Error!','Debe ingresar un DNI','error',false,2500); return false;}
	});
	$('#btn-ConsultarC').click(function(){	
		if (ValCampoVacio('DNIc')){
			if(expreDNI.test($('#DNIc').val())){
				let varDNI=$('#DNIc').val();
				LoadTablaConsultasC(varDNI);
			}else{SweetAlertMessage('¡Advertencia!','El DNI debe tener 8 dígitos.','warning',null,2500);return false;}
		}else {SweetAlertMessage('¡Error!','Ingrese un DNI','error',null,2500);return false;}
	});
});

