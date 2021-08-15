<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Login</title>
<link href="css/bootstrap-3.3.7.min.css" rel="stylesheet" type="text/css">
<link href="css/StylesLogin.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/SweetAlert.js"></script>
</head>
<body style="overflow: visible;">
	<form action="Intranet" autocomplete="off" id="frm-Login" method="post" role="form">
		<div class="limiter">
        	<div class="container-login100">
            	<div class="login100-more" style="background-image: url('images/img_LogIn.jpg');"></div>
				<div class="wrap-login100 p-l-50 p-r-50 p-t-72 p-b-50">
                	<div class="col-md-12 text-center">
                    	<img class="img-responsive" width="400" src="images/Logotipo_Tienda_GameStore.jpg">
                	</div>

                	<div class="col-md-12 col-sm-12 col-sm-auto" id="1">
                    	<div class="wrap-input100">
                        	<span class="label-input100">Usuario</span>
                        	<input class="input100" id="Usuario" name="txtUser" placeholder="" type="text" value="" required="required">
                    	</div>

                    	<div class="wrap-input100">
                        	<span class="label-input100">Contraseña</span>
                        	<input class="input100" id="text-Clave" name="txtPass" placeholder="" type="password" required="required">
                    	</div>

                    	<div class="wrap-login100-form-btn">
                        	<div class="login100-form-bgbtn"></div>
                        	<button class="login100-form-btn" style="width:100%;" type="submit" name="option" value="login"> Iniciar Sesión </button>
                    	</div>
                	</div>
            	</div>
        	</div>
    	</div>
	</form>
	${msj }
	<%
		if(request.getAttribute("msje")!=null){
			String mensaje=(String)request.getAttribute("msje");%>
			<div><%=mensaje %></div>
		<%}else{
			System.out.println("msj >> "+request.getAttribute("msje"));
		}
	%>
	
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap-3.3.7.min.js"></script>
</body>
</html>