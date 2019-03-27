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

<!-- FONTAWESOME -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">

<title>Home | BLK INTERN TEAM CONSUL</title>

</head>
<body>

	<div class="main-body-wrapper">

		<div class="navbar-wrapper" id="navbarWrapper">
			<div class="navbar-inner">
				<a href="/index">
					<div class="nav-title-logo">
						<div class="nav-heading-text heading-text">Team Consul</div>
						<br>
						<div class="nav-subheading-text normal-text">BlackRock
							Intern Miniproject 2019</div>
					</div>
				</a>
				<div class="nav-user-form">
					<a href="${ nav_bar_button_forward_link }"><button
							class="nav-bar-btn">
							<i class="far fa-user"></i> ${ nav_bar_button_text }
						</button></a>
				</div>
			</div>
		</div>

		<div class="jumbotron jumbotron-index">
			<div class="jumbo-text-layout">
				<h1 class="heading-text jumbo-heading-text">BlackRock Intern
					Miniproject</h1>
				<p class="jumbo-sub-text normal-text">Team Consul</p>
			</div>

			<a href="/login"><div class="get-started-btn normal-text">GET
					STARTED</div></a>
		</div>

		<div class="main-body">

			<div class="body-element">
				<span class="heading-text body-element-heading">Problem
					Statement</span>
				<div class="inside-body">

					<div class="body-element-text normal-text">
						Provide a UI where the user can choose a stock ticker. The list of
						tickers available can be populated from the BSE or NSE end of day
						feed. <br> Build and run two RESTful services exposing
						different endpoints and register them with Consul server. One
						service connects to the Google finance to pull stock quotes while
						the other connects to Yahoo finance or anything alike. Start a
						client which performs service instance lookup using Consul server
						and then communicate with the REST endpoint of the service to
						retrieve the quotes. Display the quotes from both providers on the
						UI.
					</div>

				</div>
			</div>

			<div class="body-element">
				<span class="heading-text body-element-heading">Technologies
					Used</span>
				<div class="inside-body">

					<div class="body-element-text normal-text">

						<div class="tech-columns">


							<div class="column-item">
								<div class="item spring-item"></div>
								<div class="item-info normal-text">
									<a href="http://spring.io/projects/spring-boot">Spring Boot</a>
								</div>
							</div>

							<div class="column-item">
								<div class="item quandl-item"></div>
								<div class="item-info normal-text">
									<a href="https://www.quandl.com/">Quandl API</a>
								</div>
							</div>


							<div class="column-item">
								<div class="item consul-item"></div>
								<div class="item-info normal-text">
									<a href="https://www.consul.io/">Consul.io</a>
								</div>
							</div>


						</div>

					</div>

				</div>
			</div>

		</div>

	</div>

	<script>
		var navbarWrapper = document.querySelector("#navbarWrapper");
		window.onscroll = (event) => {
			let scrollValue = event.target.documentElement.scrollTop;
			if(scrollValue > 0.4*window.innerHeight){
				navbarWrapper.setAttribute("style", "background-color: rgba(47, 53, 66, 1)");
			}
			else{
				navbarWrapper.setAttribute("style", "background-color: rgba(47, 53, 66, " + scrollValue/(0.4*window.innerHeight) + ")");
			}
		}
	</script>

</body>
</html>