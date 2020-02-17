<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	<!-- Jumbotron with the title of the page -->
    	<div class="jumbotron jumbotron-fluid text-center bg-dark text-white">
    		<h1>Statistics of the Game</h1>		
		</div>

		<!-- Table container to show the table with statistics value -->
		<div class="container pt-3 text-center" id="stats-table">
			
		</div>

		<!-- Container which includes the two buttons in the page -->
		<div class="container-fluid pt-3 text-center">
				<button type="button" class="btn btn-lg btn-dark" onclick="resetStatistics()">Reset Statistics</button>
				<a class="btn btn-lg btn-dark" href="http://localhost:7777/toptrumps/">Back to Selection Screen</a>
		</div>
		
		<script type="text/javascript">
		
				// Method that is called on page load
				function initalize() {
					getStatistics();
				}
				
				// This function is used to parse the statistics sent from the REST API and creates the table to display them
				function getStatistics() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showStatistics"); 
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var statisticsArray = JSON.parse(xhr.response); // a JSON object is created with the statistics ArrayList sent from REST API
                    //variable table content is used to store the html tag for the table creation and to put the information in the specific table cells
					var tableContent = '<table class="table table-dark">';
					tableContent += '<tr><td> Number of Games: </td>	<td>'+ Math.round(statisticsArray[0]*1)/1 +' </td> </tr>';
					tableContent += '<tr><td> Number of Human Wins: </td>	<td>'+ Math.round(statisticsArray[1]*1)/1 +' </td> </tr>';
					tableContent += '<tr><td> Number of AI Wins: </td>	<td>'+ Math.round(statisticsArray[2]*1)/1 +' </td> </tr>';
					tableContent += '<tr><td> Average Number of Draws: </td>	<td>'+ Math.round(statisticsArray[4]*100)/100+' </td> </tr>';
					tableContent += '<tr><td> Longest Game: </td>	<td>'+ Math.round(statisticsArray[3]*1)/1 +' </td> </tr>';

					tableContent += '</table>';

					$('#stats-table').append(tableContent);//the table content just created above is appended to the HTML container with id = stats-table
                    
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

			// This function is used to send the request to reset statistics to the REST API.
			function resetStatistics(){
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/resetStatistics"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					window.location.reload();// when the response from the API is received reload the page to call initialize() and getStatistics() once again to display the reseted statistics
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}

		</script>
		
		</body>
</html>