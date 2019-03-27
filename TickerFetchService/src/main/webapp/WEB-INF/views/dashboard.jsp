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


<title>Dashboard | BLK INTERN TEAM CONSUL</title>
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

		<div id="tickerOverlay" class="ticker-overlay-info-wrapper"
			style="display: none;">
			<div class="ticker-overlay">
				<div style="display: block;" id="tickerOverlayLoader"
					class="ticker-overlay-loading-overlay"></div>
				<div class="ticker-overlay-header">
					<span class="ticker-overlay-heading heading-text"
						id="overlaySecurityName">Shubham Chawla</span> <br>
					<span class="ticker-overlay-subheading normal-text">BSE
						Security Code: <span id="overlaySecurityCode">50003</span>
					</span>
				</div>
				<hr>
				<div class="ticker-overlay-inner-wrapper normal-text">
					<div class="ticker-overlay-inner-grid">

						<div class="ticker-overlay-grid-element">
							Close: <strong><span id="tickerClose">100</span></strong>
						</div>
						<div class="ticker-overlay-grid-element">
							Open: <strong><span id="tickerOpen">100</span></strong>
						</div>
						<div class="ticker-overlay-grid-element">
							High: <strong><span id="tickerHigh">100</span></strong>
						</div>
						<div class="ticker-overlay-grid-element">
							Low: <strong><span id="tickerLow">100</span></strong>
						</div>
						<div class="ticker-overlay-grid-element">
							WAP: <strong><span id="tickerWap">100</span></strong>
						</div>
						<div class="ticker-overlay-grid-element">
							Total Turnover: <strong><span id="tickerTurn">100</span></strong>
						</div>
						<div class="ticker-overlay-grid-element">
							No. of Shares: <strong><span id="tickerNoShares">100</span></strong>
						</div>
						<div class="ticker-overlay-grid-element">
							No. of Trades: <strong><span id="tickerNoTrades">100</span></strong>
						</div>
						<div class="ticker-overlay-grid-element">
							Spread H-L: <strong><span id="tickerSpreadHL">100</span></strong>
						</div>
						<div class="ticker-overlay-grid-element">
							Spread C-O: <strong><span id="tickerSpreadCO">100</span></strong>
						</div>
						<div class="ticker-overlay-grid-element">
							Deliverable Quantity: <strong><span
								id="tickerDelQuantity">100</span></strong>
						</div>
						<div class="ticker-overlay-grid-element">
							% Deli. Qty to Traded Qty: <strong><span
								id="tickerPerDelQua">100</span></strong>
						</div>

					</div>
					<hr>
					<div class="normal-text" style="text-align: right;">
						Last updated: <span id="tickerDate" class="ticker-update-date">date</span>
					</div>
				</div>
			</div>
		</div>

		<div class="jumbotron jumbotron-dash">

			<div class="dash-form-wrapper" style="overflow-x: hidden;">
				<span class="dash-form-heading-text heading-text"><strong>BlackRock</strong>
					Ticker Genie</span>
				<form id="dashForm">
					<input id="searchFormInput" type="text" name="stockname"
						placeholder="Security Name or BSE Security Code"> <input
						id="searchFormButton" type="submit" value="Search">
				</form>
			</div>

		</div>

		<div class="ticker-area-wrapper">

			<div class="from-to-form-wrapper">
				<div id="formTogglerBtn" class="filter-toggle-button"></div>
				<form id="formToggleDOM" style="display: none;">
					<span class="normal-text" style="margin-right: 5px;">Showing
						tickers from </span> <input id="fromInput" type="number" name="from"
						value="1" style="width: 50px;"> <span class="normal-text"
						style="margin-left: 5px; margin-right: 5px;"> and number of
						tickers </span> <input id="countInput" type="number" name="from"
						value="12" style="width: 50px;">
				</form>
			</div>

			<div class="ticker-grid" id="tickerHolder">

				<!--  TEMPLATE
				<div class="ticker-element-wrapper">
					<div class="ticker-element">
						<span class="ticker-name-text normal-text">Name</span><br>
						<div class="ticker-code-text normal-text">BSE Security code</div>
					</div>
				</div>		
				-->

			</div>
		</div>
	</div>

	<script type="text/javascript" src="js/dashboard.js"></script>

	<script>
		var navbarWrapper = document.querySelector("#navbarWrapper");
		window.onscroll = (event) => {
			let scrollValue = event.target.documentElement.scrollTop;
			if(scrollValue > 0.3*window.innerHeight){
				navbarWrapper.setAttribute("style", "background-color: rgba(47, 53, 66, 1)");
			}
			else{
				navbarWrapper.setAttribute("style", "background-color: rgba(47, 53, 66, " + scrollValue/(0.3*window.innerHeight) + ")");
			}
		}
	</script>

</body>
</html>