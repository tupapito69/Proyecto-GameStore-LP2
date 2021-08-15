/**
 *  Script GameStore Copyright 2021
 */
let codProd, Nomprod, priceProd, stockProd, categProd;
$(document).ready(function (){
	$('#buttonAgregar').click(function(){
		if(ValFormProducts()){
			sendProducto();	
		}
	});
	$('#buttonModificar').click(function(){
		if(ValFormProducts()){
			sendDataUpdateProducts();
		}	
	});
});