/**
 * 
 */

// DOM pointers
var tickerHolder = document.querySelector("#tickerHolder");
var fromInput = document.querySelector("#fromInput");
var countInput = document.querySelector("#countInput");
var tickerOverlay = document.querySelector("#tickerOverlay");
var overlaySecurityName = document.querySelector("#overlaySecurityName");
var overlaySecurityCode = document.querySelector("#overlaySecurityCode");
var tickerOverlayLoader = document.querySelector("#tickerOverlayLoader");
var searchFormInput = document.querySelector("#searchFormInput");
var searchFormButton = document.querySelector("#searchFormButton");
var dashForm = document.querySelector("#dashForm");
var formToggleDOM = document.querySelector("#formToggleDOM");
var formTogglerBtn = document.querySelector("#formTogglerBtn");

var tickerClose = document.querySelector("#tickerClose");
var tickerOpen = document.querySelector("#tickerOpen");
var tickerHigh = document.querySelector("#tickerHigh");
var tickerLow = document.querySelector("#tickerLow");
var tickerWap = document.querySelector("#tickerWap");
var tickerTurn = document.querySelector("#tickerTurn");
var tickerNoShares = document.querySelector("#tickerNoShares");
var tickerNoTrades = document.querySelector("#tickerNoTrades");
var tickerSpreadHL = document.querySelector("#tickerSpreadHL");
var tickerSpreadCO = document.querySelector("#tickerSpreadCO");
var tickerDelQuantity = document.querySelector("#tickerDelQuantity");
var tickerPerDelQua = document.querySelector("#tickerPerDelQua");
var tickerDate = document.querySelector("#tickerDate");

// JS variables
var tickers = [];
var totalNumberOfTickers = 0;

var fromTickerNo = 0;
var countTickerNo = 12;

var isOverlay = false;
var formToggle = false;

// JS Methods
let ajaxCall = (url, reqMethod, queryData, callback) => {
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
		  if (this.readyState == 4 && this.status == 200) {
			  callback(this.responseText);
		  }
	  };
	  xhttp.open(reqMethod, url+"?"+queryData, true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send();
}

function tickerOnClickMethod(BSETicker) {
	if(!isOverlay) {
		ajaxCall("http://localhost:8080/api/tickers/"+BSETicker.code, "GET", "", populateTickerOverlay);
		tickerOverlay.setAttribute("style", "display: block;");
		isOverlay = true;
	}
}

let createDateString = (timestamp) => {
	let date = new Date(timestamp);
	return date.toString();
}

let populateTickerOverlay = (res) => {
	tickerInformation = JSON.parse(res);
	console.log(tickerInformation);
	if(tickerInformation.error === null){
		tickerOverlayLoader.setAttribute("style", "display: none");		
		overlaySecurityName.innerHTML = tickerInformation.name;
		overlaySecurityCode.innerHTML = tickerInformation.quandlCode.substring(3);
		tickerClose.innerHTML = tickerInformation.close;
		tickerOpen.innerHTML = tickerInformation.open;
		tickerHigh.innerHTML = tickerInformation.high;
		tickerLow.innerHTML = tickerInformation.low;
		tickerWap.innerHTML = tickerInformation.wap;
		tickerTurn.innerHTML = tickerInformation.totalTurnover;
		tickerNoShares.innerHTML = tickerInformation.noOfShares;
		tickerNoTrades.innerHTML = tickerInformation.noOfTrades;
		tickerSpreadHL.innerHTML = tickerInformation.spreadHighLow;
		tickerSpreadCO.innerHTML = tickerInformation.spreadCloseOpen;
		tickerDelQuantity.innerHTML = tickerInformation.deliverableQuantity;
		tickerPerDelQua.innerHTML = tickerInformation.perDelQuanToTrade;
		tickerDate.innerHTML = createDateString(tickerInformation.date);
	}
	else{
		tickerOverlayLoader.setAttribute("class", "ticker-overlay-offline-overlay");
		tickerOverlayLoader.innerHTML = "<div class='normal-text' style='color:#7f8c8d;position:relative;width:100%;top:70%;left:50%;text-align:center;transform:translate(-50%,-50%);'>"+tickerInformation.error.message+"</div>";
	}
}

let populateTickerList = (tickersArray) => {
	if(tickersArray === null) {
		tickerHolder.setAttribute("class", "ticker-grid-empty");
		tickerHolder.innerHTML = "<div class='normal-text'>No Such Ticker found!</div>";
		return;
	}
	tickerHolder.setAttribute("class", "ticker-grid");
	tickerHolder.innerHTML = "";
	tickersArray.forEach((ticker, i) => {
		let tickerWrapperDom = document.createElement("div");
		tickerWrapperDom.setAttribute("class", "ticker-element-wrapper");
		tickerWrapperDom.setAttribute("id", ticker.code.substring(3));
		tickerWrapperDom.addEventListener("click", () => {
			this.tickerOnClickMethod(ticker);
		});
		
		let tickerElementDom = document.createElement("div");
		tickerElementDom.setAttribute("class", "ticker-element");
		
		let tickerNameDom = document.createElement("div");
		tickerNameDom.setAttribute("class", "ticker-name-text normal-text");
		tickerNameDom.innerHTML = ticker.name;
		
		let tickerCodeDom = document.createElement("div");
		tickerCodeDom.setAttribute("class", "ticker-code-text normal-text");			
		tickerCodeDom.innerHTML = "Security code: "+ticker.code.substring(3);
		
		tickerElementDom.appendChild(tickerNameDom);
		tickerElementDom.appendChild(tickerCodeDom);
		tickerWrapperDom.appendChild(tickerElementDom);
		
		tickerHolder.appendChild(tickerWrapperDom);
	});
}

let getTickerData = (start, count) => {
	let data = (start !== null && count !== null) ? "start="+start+"&&count="+count : (start !== null && count === null) ? "start="+start : (start === null && count !== null) ? "count="+count : "" ;
	ajaxCall("http://localhost:8080/api/tickers", "GET", data, function(res) {
		tickers = JSON.parse(res);
		populateTickerList(tickers);
	});
}

let populateInitialData = () => {
	ajaxCall("http://localhost:8080/api/tickers/count", "GET", "", (res)=>{
		if(totalNumberOfTickers === 0){
			totalNumberOfTickers = parseInt(JSON.parse(res));
		}
		getTickerData(null, null);
		fromInput.value = 1;
		countInput.value = 15;
	});
}

fromInput.addEventListener("change", (event)=>{
	let from = event.target.value;
	if(from > 0 && from <= totalNumberOfTickers) {
		fromInput.value = from;
		countInput.value = (parseInt(countInput.value) > parseInt(totalNumberOfTickers) - parseInt(fromInput.value) + 1) ? parseInt(totalNumberOfTickers) - parseInt(fromInput.value) + 1 : parseInt(countInput.value);		
		getTickerData(fromInput.value-1, countInput.value);
	}
});

countInput.addEventListener("change", (event)=>{
	let count = event.target.value;
	if(count <= totalNumberOfTickers - fromInput.value) {
		countInput.value = count;
		getTickerData(fromInput.value-1, countInput.value);
	}
});

tickerOverlay.addEventListener("click", (event) => {
	if(isOverlay) {
		tickerOverlay.setAttribute("style", "display: none;");
		tickerOverlayLoader.setAttribute("style", "display: block");
		tickerOverlayLoader.setAttribute("class", "ticker-overlay-loading-overlay");
		tickerOverlayLoader.innerHTML = "";
		isOverlay = false;
	}
});

dashForm.addEventListener("submit", (event) => event.preventDefault());

searchFormInput.addEventListener("change", (event) => {
	if(event.target.value.length > 2) {
		let query = event.target.value;
		ajaxCall("http://localhost:8080/api/tickers/search", "GET", "query="+query, (res) => {
			if(res !== "") {
				let tickerData = JSON.parse(res);
				if(tickerData.length !== 1){
					populateTickerList(tickerData);
				}else{
					tickerOnClickMethod(tickerData[0]);
				}
			}
			else{
				populateTickerList(null);
			}
		});
	}
	else if(event.target.value.length === 0) {
		populateInitialData();	
	}
});

formTogglerBtn.addEventListener("click", (event) => {
	//console.log(formToggleDOM);
	if(formToggle){
		formToggleDOM.setAttribute("style", "display: none;");
		formTogglerBtn.setAttribute("style", "transform: translate(-50%, -50%) rotate(0deg);");
		formToggle = false;
	}
	else{
		formToggleDOM.setAttribute("style", "display: block;");
		formTogglerBtn.setAttribute("style", "transform: translate(-50%, -50%) rotate(180deg);");
		formToggle = true;
	}
});

// Main Method
window.onload = function() {
	populateInitialData();
}