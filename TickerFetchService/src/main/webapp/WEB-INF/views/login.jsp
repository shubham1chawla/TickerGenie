<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no, user-scalable=no">

<!-- CUSTOM CSS -->
<link rel="stylesheet" type="text/css" href="css/style.css">

<!-- GOOGLE FONTS -->
<link href="https://fonts.googleapis.com/css?family=Aleo|Roboto"
	rel="stylesheet">
	
<title>Login | BLK INTERN TEAM CONSUL</title>

<script>

	function signUp() {
		document.getElementById("loginForm").addEventListener("submit", function(event) {
			event.preventDefault();
		});
		window.location.href = "/signup";
	}

</script>

</head>
<body>

	<div class="main-body-wrapper">

		<div class="form-wrapper">
			<div class="form-heading heading-text">Miniproject Login</div>
			<form action="/login" method="POST" id="loginForm">
				<input type="text" name="username" placeholder="Username"><br>
				<input type="password" name="password" placeholder="Password"><br>
				<div style = "position: relative; width: 100%; margin-bottom: 20px;">
					<input type="submit" name="login" value="Login" style = "width: 69%; float: left; margin-right: 1%;">
					<input onClick='signUp()' type="submit" name="signup" value="Signup" style = "width: 29%; float: left; margin-left: 1%;">
				</div>
				<div style="margin-top: 50px;" class="error-message-div normal-text">${ error_message }</div>
			</form>
		</div>

	</div>

</body>
</html>