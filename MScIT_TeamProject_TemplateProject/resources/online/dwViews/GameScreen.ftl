<html>
	<!--Head for GameScreen-->
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
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<!-- CSS stles for the GameScreen -->
	<style>
		img {
			height:110px;
			width:50px;
		}
		
		table {
			font-family: arial, sans-serif;
			border-collapse: collapse;
			width: 100%;
		}
		
		td, th {
			border: 1px solid #dddddd;
			text-align: left;
			padding: 8px;
		}
		
		tr:nth-child(even) {
			background-color: #dddddd;
		}
		
		.card5 {
			margin-left:1100px;
			margin-top:-532px;
		}
		
		sup {
			font-size:100%;
			line-height:1;
			vertical-align:top;
			mso-text-raise: 30%;
			color:orange;
			font-weight:bold;
			padding-left:30px;
		}
		
		#card-header1,#pile {
			font-weight:bold;
		}
		
		#pile {
			height:132px;
		}
		.panelmessage {
			margin-left:212px;
			margin-top:-75px;
		}
	</style>
	</head>

<!--Body for GameScreen-->
    <body> 
		<!--Jumbotron: Title Header-->
		<div class="jumbotron text-center" style="padding-top: 10px; padding-bottom: 10px">
    		<h1>Top Trumps Game</h1>
    		<form style="display: block" id="form">
    			<label for="numberOfPlayers">How many AI players? (Max. 4)</label>
    			<input type="text text-center" name="numberOfPlayers" id="numberOfPlayers" value="">
    			<input type="button" onclick="setNumberOfPlayers(); displayBoardGame();" value="Submit">
    		</form>    		
    	</div>

		<!--Container for Active Player, Category buttons & Communal Pile messages-->
    	<div class="container mt-3 mx-3" style="display: none" id="board-game">
			<div class="row mx-0 mt-0">
				<div class="card-deck">
					<div class="panelcategory">
						<div class="card mb-4" style="min-width: 10rem; max-width:11rem;">
							<!--Active player-->
							<div class="card-header bg-default text-danger text-center" id="card-header">Active Player:<br> You</div>
								<div class="card-body">
									<!--Category buttons-->
									<div class="dropdown">
										<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" id="btn-dropdown">Categories</button>
										<div class="dropdown-menu">
										<div class="btn-group-vertical">
											<a href="#" class="dropdown-item" onclick='setChosenCategoryByHuman(0);disableShowWinnerButton(false);disableNextRoundButton(true);'>Size</a>
											<a href="#" class="dropdown-item" onclick='setChosenCategoryByHuman(1);disableShowWinnerButton(false);disableNextRoundButton(true);'>Speed</a>
											<a href="#" class="dropdown-item" onclick='setChosenCategoryByHuman(2);disableShowWinnerButton(false);disableNextRoundButton(true);'>Range</a>
											<a href="#" class="dropdown-item" onclick='setChosenCategoryByHuman(3);disableShowWinnerButton(false);disableNextRoundButton(true);'>Firepower</a>
											<a href="#" class="dropdown-item" onclick='setChosenCategoryByHuman(4);disableShowWinnerButton(false);disableNextRoundButton(true);'>Cargo</a>
										</div>
										</div>
									</div>
								</div>
							<!--Communal Pile message box-->
							<div class="card-header bg-default text-success text-center" id="pile">Communal Pile:</div>
						</div>
                 
						<!--Show Winner & Next Round buttons-->
						<div class="" style="min-width: 8rem; max-width: 12rem; margin-top:-0px;padding-left:15px;">
							<button class="btn btn-primary btn-lg" id="button-show-winner" onclick="showWinner()" disabled="">Show Winner</button>  <br><br>
							<button class="btn btn-primary btn-lg" id="button-next-round" onclick="playNextRound()" disabled="" style="width:175px;">  Next Round</button> 
						</div>     	
					</div>
           
				<!--Player 1 card-->
				<div class="card1" id="card1" style="visibility: visible; display: block;">
					<div class="card mb-4" style="min-width: 8rem; max-width: 12rem;">
						<div class="card-header bg-danger text-white text-center" id="card-header1">You<sup id="decksize1">()</sup></div>
							<img class="card-img-top" src="" alt="Card image cap" id="img1">
								<div class="card-body">
								<h5 id="cardDescription1" class="card-title1"></h5>
								<div class="containerclass">
									<table class="table table-borderless">
										<thead>
											<tr>
												<th>Category</th>
												<th>Value</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Size</td>
												<td id="sizeValue1"></td>
											</tr>
											<tr>
												<td>Speed</td>
												<td id="speedValue1"></td>
											</tr>
											<tr>
												<td>Range</td>
												<td id="rangeValue1"></td>
											</tr>
											<tr>
												<td>Firepower</td>
												<td id="firepowerValue1"></td>
											</tr>
											<tr>
												<td>Cargo</td>
												<td id="cargoValue1"></td>
											</tr>
										</tbody>
									</table>
								</div>
						</div>
					</div>
				</div>
				
				
			<!--Player 2 card-->
            <div class="card2" id="card2" style="visibility: hidden; display: block;">
                <div class="card mb-4" style="min-width: 8rem; max-width: 12rem;">
                    <div class="card-header bg-danger text-white text-center" id="card-header2">AI Player1<sup id="decksize2">()</sup></div>
						<img class="card-img-top" src="" alt="Card image cap" id="img2">
						<div class="card-body">
						<h5 id="cardDescription2" class="card-title2"></h5>
                        <div class="containerclass">
                            <table class="table table-borderless">
								<thead>
								<tr>
									<th>Category</th>
									<th>Value</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<td>Size</td>
									<td id="sizeValue2"></td>
								</tr>
								<tr>
									<td>Speed</td>
									<td id="speedValue2"></td>
								</tr>
								<tr>
									<td>Range</td>
									<td id="rangeValue2"></td>
								</tr>
								<tr>
									<td>Firepower</td>
									<td id="firepowerValue2"></td>
								</tr>
								<tr>
									<td>Cargo</td>
									<td id="cargoValue2"></td>
								</tr>
								</tbody>
							</table>
                        </div>
                    </div>
                </div>
            </div>
			
			<!--Player 3 Card-->
            <div class="card3" id="card3" style="visibility: hidden; display: block;">
                <div class="card mb-4" style="min-width: 8rem; max-width: 12rem;">
                    <div class="card-header bg-danger text-white text-center" id="card-header3">AI Player2<sup id="decksize3">()</sup></div>
						<img class="card-img-top" src="" alt="Card image cap" id="img3">
						<div class="card-body">
                        <h5 id="cardDescription3" class="card-title3"></h5>
                        <div class="containerclass">
                           <table class="table table-borderless">
								<thead>
									<tr>
										<th>Category</th>
										<th>Value</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Size</td>
										<td id="sizeValue3"></td>
									</tr>
									<tr>
										<td>Speed</td>
										<td id="speedValue3"></td>
									</tr>
									<tr>
										<td>Range</td>
										<td id="rangeValue3"></td>
									</tr>
									<tr>
										<td>Firepower</td>
										<td id="firepowerValue3"></td>
									</tr>
									<tr>
										<td>Cargo</td>
										<td id="cargoValue3"></td>
									</tr>
								</tbody>
							</table>
                        </div>
                    </div>
                </div>
            </div>
			
			<!--Player 4 card-->
            <div class="card4" id="card4" style="visibility: hidden; display: block;">
                <div class="card mb-4" style="min-width: 8rem; max-width: 12rem;">
                    <div class="card-header bg-danger text-white text-center" id="card-header4">AI Player3<sup id="decksize4">()</sup></div>
						<img class="card-img-top" src="" alt="Card image cap" id="img4">
						<div class="card-body">
                        <h5 id="cardDescription4" class="card-title4"></h5>
                        <div class="containerclass">
                            <table class="table table-borderless">
								<thead>
								<tr>
									<th>Category</th>
									<th>Value</th>
								</tr>
								</thead>
								<tbody>
									<tr>
										<td>Size</td>
										<td id="sizeValue4"></td>
									</tr>
									<tr>
										<td>Speed</td>
										<td id="speedValue4"></td>
									</tr>
									<tr>
										<td>Range</td>
										<td id="rangeValue4"></td>
									</tr>
									<tr>
										<td>Firepower</td>
										<td id="firepowerValue4"></td>
									</tr>
									<tr>
										<td>Cargo</td>
										<td id="cargoValue4"></td>
									</tr>
								</tbody>
							</table>
                        </div>
                    </div>
                </div>
            </div>
			
			<!--Player Card 5-->
            <div class="card5" id="card5" style="visibility: hidden; display: block;">
                <div class="card mb-4" style="min-width: 8rem; max-width: 12rem;">
                    <div class="card-header bg-danger text-white text-center" id="card-header5">AI Player4<sup id="decksize5">()</sup></div>
					<img class="card-img-top" src="" alt="Card image cap" id="img5">
                    <div class="card-body">
                        <h5 id="cardDescription5" class="card-title5"></h5>
                        <div class="containerclass">
                            <table class="table table-borderless">
								<thead>
									<tr>
										<th>Category</th>
										<th>Value</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Size</td>
										<td id="sizeValue5"></td>
									</tr>
									<tr>
										<td>Speed</td>
										<td id="speedValue5"></td>
									</tr>
									<tr>
										<td>Range</td>
										<td id="rangeValue5"></td>
									</tr>
									<tr>
										<td>Firepower</td>
										<td id="firepowerValue5"></td>
									</tr>
									<tr>
										<td>Cargo</td>
										<td id="cargoValue5"></td>
									</tr>
								</tbody>
							</table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

		<!--Message box-->
        <div class="panelmessage" style="min-width: 35rem; min-height:17rem; max-width: 50rem;">
            <div class="card-body">
                <h5 class="card-title">Message: </h5>
                <div class="containermessage" id="container-message">
                Let's play!
                </div>
            </div>
        </div>
		
    	</div>
	</div>
	
		<!--Game Over-->
		<div class="container" style="display: none" id="game-over">
		    <center>
			<br>
       		<h1 id="gameovertitle">GAME OVER</h1>        
		    <div id="game-over-body">
		    	<h5><br>
		    	<span id="winner"></span> won the game!</h5>
		    	<br>
		    </div>
		    </center>  
			<div class="game-over-footer text-center">
				<a class="btn btn-dark" href="http://localhost:7777/toptrumps">Back to Main Menu</a>
		    </div>       
      	</div>

		<!-- Java Script -->
		<script type="text/javascript">
			var deckSizeArray;
			var activePlayer; 
			var selectedCategoryByAI, selectedCategoryByHuman=0;
			var messageWithWinner;
			var communalPileSize;
			var chosenNumberOfPlayers;

			// Hide/Show game
			function displayBoardGame(){
				var displayElement = document.getElementById("board-game").style.display;
				if(displayElement == "none"){
					document.getElementById("board-game").style.display="block";
				} else {
					document.getElementById("board-game").style.display="none";
				}
			}

			// Display game over at the end of game
			function displayGameOver(){
				var displayElement = document.getElementById("game-over").style.display;
				if(displayElement == "none"){
					document.getElementById("game-over").style.display="block";
				} else {
					document.getElementById("game-over").style.display="none";
				}
			}

			// Remove the extra player cards based on the input number of players
			function removeUnusedCards(){
				for(var i=5; i > chosenNumberOfPlayers; i--){
					document.getElementById("card" + i).style.display = "none";
				}
			}

			// Getting the input number of players from user
			function setNumberOfPlayers(){
				chosenNumberOfPlayers = 1 + parseInt(document.getElementById("numberOfPlayers").value);
				resetGameWebApp(chosenNumberOfPlayers);
				document.getElementById("form").style.display = "none";
				removeUnusedCards(); // Call remove cards function
			}

			// Make invisible the AI player cards 
			function hideActiveAICards(){
				for(var i=1; i < chosenNumberOfPlayers; i++){
					document.getElementById("card" + (i+1)).style.visibility = "hidden";
				}
			}

			// Display the AI cards
			function showActiveAICards(){
				for(var i=1; i < chosenNumberOfPlayers; i++){
					document.getElementById("card" + (i+1)).style.visibility = "visible";
				}
			}

			// Get the category selected by human
			function setChosenCategoryByHuman(category){	
				selectedCategoryByHuman = category;
			}
			
			// Display winner of game
			function showWinner(){
				play();	// Play the game
				getAllCards(true); // Extract cards and only deck size of each player
				showActiveAICards(); // Display AI Cards
				disableNextRoundButton(false);	// Enable next round button
				disableShowWinnerButton(true);	// Disable show winner button
				disableDropdown(true);	// Display dropdown when active player is human
			}
			
			// Play the next round
			function playNextRound(){	
				hideActiveAICards();	// Hide the AI Player Cards
				getAllCards(false);	// Extract cards, deck size & retrive all players next cards
				disableNextRoundButton(true);	// Diable next round button
				disableShowWinnerButton(false);	// Enable show winner button
			}

			// Disable/Enable dropdown
			function disableDropdown(doYouWantItDisabled){
				document.getElementById("btn-dropdown").disabled = doYouWantItDisabled;
			}

			// Disable/Enable show winner button
			function disableShowWinnerButton(doYouWantItDisabled){
				document.getElementById("button-show-winner").disabled = doYouWantItDisabled;
			}

			// Disable/Enable next round button
			function disableNextRoundButton(doYouWantItDisabled){
				document.getElementById("button-next-round").disabled = doYouWantItDisabled;
			}

			// Display round winner
			function showRoundWinner(){
				for(var i=0; i < chosenNumberOfPlayers; i++){
					document.getElementById("card-header" + (i+1)).className = "card-header bg-danger text-white text-center";
				}
				document.getElementById("card-header" + (activePlayer+1)).className = "card-header bg-success text-white text-center";

			}

			// Reset Game 
			function resetGameWebApp(numberOfPlayers) {			
				if(numberOfPlayers < 2 || numberOfPlayers > 5){
					alert("Introduce a number of players between 1 and 4!"); // Alert box, if input is other than 1,2,3 & 4
					displayBoardGame(); // Show/Hide board game
					location.reload();	// Reload the game after alert
				} else {

					// First create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/resetGameWebApp?numberOfPlayers="+numberOfPlayers); 
					
					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
	  					alert("CORS not supported");
					}

					// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
					// to do when the response arrives 
					xhr.onload = function(e) {
	 					var responseText = xhr.response; // the text of the response
						getActivePlayer(); // Getting the active player
						playNextRound(); // Triggers Play next round
						
					};
					
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();	
				}	
			}

			// Get Card for the player
			function getCard(playerIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/extractCard?playerIndex=" + playerIndex);
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var card = JSON.parse(xhr.response); // the text of the response					
					document.getElementById("cardDescription" + (playerIndex+1)).innerHTML = card.name;
					document.getElementById("sizeValue" + (playerIndex+1)).innerHTML = card.description[0];
					document.getElementById("speedValue" + (playerIndex+1)).innerHTML = card.description[1];
					document.getElementById("rangeValue" + (playerIndex+1)).innerHTML = card.description[2];
					document.getElementById("firepowerValue" + (playerIndex+1)).innerHTML = card.description[3];
					document.getElementById("cargoValue" + (playerIndex+1)).innerHTML = card.description[4];
					document.getElementById("img" + (playerIndex+1)).src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/"+ card.name +".jpg";
				};			
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

			// Extract All Cards 
			function getAllCards(getOnlyDeckSize){
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getAllCards"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					 // the text of the response
					deckSizeArray = JSON.parse(xhr.response);

					for(var i =0; i < Object.keys(deckSizeArray).length; i++){
						document.getElementById("decksize" + (i+1)).innerHTML = "(" + deckSizeArray[i] + ")";
						if(getOnlyDeckSize == false){
							if(deckSizeArray[i] == 0){
								document.getElementById("card" + (i+1)).style.display = "none";
							} else {
								getCard(i); // Calls get Card
							}
						}
						if(deckSizeArray[i] == 40){
							checkAndGetWinner(); // Checks the game winner 
						}
					}
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}

			// Plays the game
			function play(){
				if(activePlayer == 0){
					playLikeHuman(selectedCategoryByHuman); // Plays when the user selects the category
				} else {
					playLikeAI(); // Calls play like AI, when the active player is AI
				}
			}

			// This functions sends a CORS request & plays when the active player is AI
			function playLikeAI(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/AIPlay"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					messageWithWinner = xhr.response;
					getActivePlayer(); // Gets the active player
					getCommunalPileSize(); // Get the communal pile size
					document.getElementById("container-message").innerHTML = messageWithWinner;
				}
				xhr.send();
			}

			// This functions sends CORS  request to play on selection of category
			function playLikeHuman(category){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playLikeHuman?selectedCategoryByHuman=" + category); 
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					messageWithWinner = xhr.response;
					getActivePlayer(); // Gets active player
					getCommunalPileSize(); // Get communal pile size
					document.getElementById("container-message").innerHTML = messageWithWinner;
				}
				xhr.send();
			}

			//  This functions sends a CORS request to retrieve the winner of the game
			function checkAndGetWinner(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getWinner");

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the r esponse arrives 
				xhr.onload = function(e) {
					var response = xhr.response; // Create a javascript variable to start a response
					var winner = JSON.parse(response); // Create a JSON object with a game winner
					
					if(response != ""){	// When  response has player in it...
						displayBoardGame(); // Show/Hide the board game
						displayGameOver(); // Display game over panel
						document.getElementById("winner").innerHTML = winner.name; // Show winner
					}
				}
				xhr.send();
			}

			// This functions sends CORS  request to retreive activePlayer(round winner)
			function getActivePlayer() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getActivePlayer"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var activePlayerObject = JSON.parse(xhr.response); // Create a JSON Object with active player
					
					// Assings the player index based on the JSON Object name key
					if(activePlayerObject.name === "You"){activePlayer = 0;}
					for(var i=1; i < chosenNumberOfPlayers; i++){
						if(activePlayerObject.name === ("AI Player"+i)){activePlayer = i;}
					}

					document.getElementById("card-header").innerHTML = "Active Player is: <br> " + activePlayerObject.name; // Updates the active player field
					showRoundWinner(); // Highlight the card of the round winner

					// Disable / Enable dropdown and showwiner button based on who the active player is(Human = 0, AI ! = 0)
					if(activePlayer == 0){
						disableDropdown(false); 
						disableShowWinnerButton(true);
					} else {
						disableDropdown(true);
					}
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

			// This functions sends CORS  request to retreive communal pile size, when the response has arrived, it updates the communal pile value
			function getCommunalPileSize(){
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCommunalPileSize"); // Request type and URL+parametersselectedCategory)
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					document.getElementById("pile").innerHTML = "Communal Pile: <br>" + xhr.response + " cards";
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