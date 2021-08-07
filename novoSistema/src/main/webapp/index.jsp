<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<title>Insert title here</title>
</head>
<body>
 <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

	<h3>Bem Vindo!</h3>
	
	<style type="text/css">
	
	form{
	position: absolute;
	
	top: 40%;
	left:33%;
	right: 33%
	}
	
	h3{
	position: absolute;
	
	top: 30%;
	left:33%;
	
	}
	.msg{
	position: absolute;
	
	top: 60%;
	left:33%;
	font-size: 15px;
	color: red;
	}
	
	</style>
	


	<form action="ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
	
	<input type="hidden" value="<%= request.getParameter("url")%>" name="url">
	
	<div class="col-md-6">
			<label class="form-label">Login</label>
				<input class="form-control" type="text" id="login" name="login" required="required">
				<div class="valid-feedback">
      Login ok!
    </div>
    <div class="invalid-feedback">
        Informe o Login !
      </div>
	</div>
	
			<div class="col-md-6">
				<label class="form-label">Senha</label>
				<input class="form-control" type="password" id="senha" name="senha" required="required">
							<div class="valid-feedback">
      Senha ok!
    </div>
    <div class="invalid-feedback">
        Informe a Senha!
      </div>
			</div>
			
			
			<div class="col-12">
				<input class="btn btn-primary" type=submit name="Entrar">
		</div>
		
		
	</form>
	<h5 class="msg">${msg}</h5>
	
	<script type="text/javascript">
	
	// Example starter JavaScript for disabling form submissions if there are invalid fields
	(function () {
	  'use strict'

	  // Fetch all the forms we want to apply custom Bootstrap validation styles to
	  var forms = document.querySelectorAll('.needs-validation')

	  // Loop over them and prevent submission
	  Array.prototype.slice.call(forms)
	    .forEach(function (form) {
	      form.addEventListener('submit', function (event) {
	        if (!form.checkValidity()) {
	          event.preventDefault()
	          event.stopPropagation()
	        }

	        form.classList.add('was-validated')
	      }, false)
	    })
	})()
	
	
	
	
	
	</script>
	
</body>
</html>