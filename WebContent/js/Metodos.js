/**
 * Script GameStore Copyright 2021
 */
/**************************************************/
// script para validar contenido de cajas de texto
var ValCampoVacio=function(camp){
	if($('#'+camp).val()=='')
		return false;
		return true;
}
var ValLengthCampo=function(camp, tam){
	if($('#'+camp).length<=tam)
		return true;
		return false;
}
/**************************************************/
// script para mostrar alertas con SweetAlert
var SweetAlertMessage=function(title='', text='', icon='', textButton=null,time=''){
	swal({title: title,text: text,icon: icon,button: textButton,timer: time});
}
/**************************************************/
// script para habilitar y deshabilitar elementos
var HabilitInput=function(camp){
	$('#'+camp).prop('readonly', false);
}
var DeshabiInput=function(camp){
	$('#'+camp).prop('readonly', true);
}
var HabilitButton=function(camp){
	$('#'+camp).prop('disabled', false);
}
var DeshabiButton=function(camp){
	$('#'+camp).prop('disabled', true);
}
/**************************************************/
// script para activar y desactivar un checkbox
var ActivateCheckBox=function(camp){
	$('#'+camp).prop('checked', true );
}
var DeactivateCheckBox=function(camp){
	$('#'+camp).prop('checked', false );
}
/**************************************************/
// script para convertir una cadena a mayuscula
var ConvertUpercase=function(e) {
    e.value = e.value.toUpperCase();
}
/**************************************************/
// script para validar un numero decimal
var validateDecimal=function (input) {
	var valor=$('#'+input).val();
    if (numbersDecimal.test(valor)) {
        return true;
    } else {
        return false;
    }
}
/**************************************************/
// script para validar un numero entero
var validateNumEntero=function (input) {
	var valor=$('#'+input).val();
    if (numbersInt.test(valor)) {
        return true;
    } else {
        return false;
    }
}


