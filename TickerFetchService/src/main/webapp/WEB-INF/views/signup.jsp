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
	
<title>Sign-up | BLK INTERN TEAM CONSUL</title>
</head>
<body>

	<div class="main-body-wrapper">

		<div class="form-wrapper">
			<div class="form-heading heading-text">Miniproject Sign-up</div>
			<form action="/signup" method="POST">
				<input type="text" name="username" placeholder="Enter your Username"><br>
				<input type="text" name="email" placeholder="Enter your Email-address"><br>
				<input type="password" name="password" placeholder="Enter your Password"><br>
				<input type="password" name="confirm_password" placeholder="Confirm your Password"><br>
				<input type="submit" name="signup" value="Sign-up">
				<div class="error-message-div normal-text">${ error_message }</div>
			</form>
		</div>

	</div>

</body>
</html>