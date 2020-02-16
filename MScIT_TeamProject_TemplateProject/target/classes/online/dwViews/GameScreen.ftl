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
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<style>
img{
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
sup{
  font-size:100%;
  line-height:1;
  vertical-align:top;
  mso-text-raise: 30%;
  color:orange;
  font-weight:bold;
  padding-left:30px;
}
#card-header1,#pile{
font-weight:bold;
}
#pile{
height:132px;
}
.panelmessage {
margin-left:212px;
margin-top:-75px;
}
</style>
	</head>

    <body onload="initialize()"> <!-- Call the initalize method when the page loads -->
    	
		<div class="jumbotron text-center" style="padding-top: 10px; padding-bottom: 10px">
    		<h1>Top Trumps Game</h1>
    		<form style="display: block" id="form">
    			<label for="numberOfPlayers">How many AI players? (Max. 4)</label>
    			<input type="text text-center" name="numberOfPlayers" id="numberOfPlayers" value="">
    			<input type="button" onclick="setNumberOfPlayers(); displayBoardGame();" value="Submit">
    		</form>
    		
    	</div>

    	
    	<!-- <div id="modal" class = "modal fade" role="dialog">
			<div class="modal-dialog">
    			<div class="modal-content">
        			<div class="modal-header">
            			<button type="button" class="close" data-dismiss="modal">&times;</button>
            			<h3 class="modal-title">Enter the total number of players. (Max. 5 players)</h3>
            		</div>
       				<div class="modal-body">
            			<input type="text" class="form-control" name="numberOfPlayers" id="numberOfPlayers">
            			<br>
            			<center>
                			<button type="button" class="btn btn-warning" onclick='setNumberOfPlayers()'>Start Game</button>
            			</center>
        			</div>
    			</div>
			</div>
		</div> -->

    	<div class="container mt-3 mx-3" style="display: none" id="board-game">
    		<div class="row mx-0 mt-0">
        <div class="card-deck">

            <div class="panelcategory">
                <div class="card mb-4" style="min-width: 10rem; max-width:11rem;">
                    <div class="card-header bg-default text-danger text-center" id="card-header">Active Player:<br> You</div>
                    <div class="card-body">
                    	<div class="dropdown">
                        <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" id="btn-dropdown">
                        	Categories
                        </button>
                        <div class="dropdown-menu">
                            <div class="btn-group-vertical">
                                <a href="#" class="dropdown-item" onclick='setChosenCategoryByHuman(0);disableShowWinnerButton(false);
                                disableNextRoundButton(true);'>Size</a>
                                <a href="#" class="dropdown-item" onclick='setChosenCategoryByHuman(1);disableShowWinnerButton(false);disableNextRoundButton(true);'>Speed</a>
                                <a href="#" class="dropdown-item" onclick='setChosenCategoryByHuman(2);disableShowWinnerButton(false);disableNextRoundButton(true);'>Range</a>
                                <a href="#" class="dropdown-item" onclick='setChosenCategoryByHuman(3);disableShowWinnerButton(false);disableNextRoundButton(true);'>Firepower</a>
                                <a href="#" class="dropdown-item" onclick='setChosenCategoryByHuman(4);disableShowWinnerButton(false);disableNextRoundButton(true);'>Cargo</a>
                            </div>
                        </div>
                    	</div>
                    </div>
                    <div class="card-header bg-default text-success text-center" id="pile">
                        Communal Pile:
                    </div>
                </div>
            </div>

            <div class="card1" id="card1" style="visibility: visible; display: block;">
                <div class="card mb-4" style="min-width: 8rem; max-width: 12rem;">
                    <div class="card-header bg-danger text-white text-center" id="card-header1">You<sup id="decksize1">(14)</sup></div>
                    <img class="card-img-top" src="https://static.thenounproject.com/png/19018-200.png" alt="Card image cap" id="img1">
                    <div class="card-body">
                        <h5 id="cardDescription1" class="card-title1">350r</h5>
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
						<td id="sizeValue1">1</td>
							</tr>
							<tr>
						<td>Speed</td>
						<td id="speedValue1">9</td>
						</tr>
						<tr>
						<td>Range</td>
						<td id="rangeValue1">2</td>
						</tr>
						<tr>
						<td>Firepower</td>
						<td id="firepowerValue1">3</td>
						</tr>
						<tr>
						<td>Cargo</td>
						<td id="cargoValue1">0</td>
						</tr>
						</tbody>
						</table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card2" id="card2" style="visibility: hidden; display: block;">
                <div class="card mb-4" style="min-width: 8rem; max-width: 12rem;">
                    <div class="card-header bg-danger text-white text-center" id="card-header2">AI Player1<sup id="decksize2">()</sup></div>

                    <img class="card-img-top" src="https://www.freeiconspng.com/uploads/avengers-icon-19.png" alt="Card image cap" id="img2">
                    <div class="card-body">
                        <h5 id="cardDescription2" class="card-title2">Avenger</h5>
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
						<td id="sizeValue2">2</td>
							</tr>
							<tr>
						<td>Speed</td>
						<td id="speedValue2">7</td>
						</tr>
						<tr>
						<td>Range</td>
						<td id="rangeValue2">2</td>
						</tr>
						<tr>
						<td>Firepower</td>
						<td id="firepowerValue2">3</td>
						</tr>
						<tr>
						<td>Cargo</td>
						<td id="cargoValue2">0</td>
						</tr>
						</tbody>
						</table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card3" id="card3" style="visibility: hidden; display: block;">
                <div class="card mb-4" style="min-width: 8rem; max-width: 12rem;">
                    <div class="card-header bg-danger text-white text-center" id="card-header3">AI Player2<sup id="decksize3">()</sup></div>

                    <img class="card-img-top" src="https://p7.hiclipart.com/preview/676/615/704/caravel-brigantine-15th-century-barque-carrack-ship.jpg" alt="Card image cap" id="img3">
                    <div class="card-body">
                        <h5 id="cardDescription3" class="card-title3">Carrac</h5>
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
						<td id="sizeValue3">6</td>
							</tr>
							<tr>
						<td>Speed</td>
						<td id="speedValue3">1</td>
						</tr>
						<tr>
						<td>Range</td>
						<td id="rangeValue3">4</td>
						</tr>
						<tr>
						<td>Firepower</td>
						<td id="firepowerValue3">3</td>
						</tr>
						<tr>
						<td>Cargo</td>
						<td id="cargoValue3">8</td>
						</tr>
						</tbody>
						</table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card4" id="card4" style="visibility: hidden; display: block;">
                <div class="card mb-4" style="min-width: 8rem; max-width: 12rem;">
                    <div class="card-header bg-danger text-white text-center" id="card-header4">AI Player3<sup id="decksize4">()</sup></div>
                    <img class="card-img-top" src="https://image.flaticon.com/icons/svg/40/40388.svg" alt="Card image cap" id="img4">
                    <div class="card-body">
                        <h5 id="cardDescription4" class="card-title4">Constellation</h5>
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
						<td id="sizeValue4">4</td>
							</tr>
							<tr>
						<td>Speed</td>
						<td id="speedValue4">5</td>
						</tr>
						<tr>
						<td>Range</td>
						<td id="rangeValue4">7</td>
						</tr>
						<tr>
						<td>Firepower</td>
						<td id="firepowerValue4">3</td>
						</tr>
						<tr>
						<td>Cargo</td>
						<td id="cargoValue4">8</td>
						</tr>
						</tbody>
						</table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card5" id="card5" style="visibility: hidden; display: block;">
                <div class="card mb-4" style="min-width: 8rem; max-width: 12rem;">
                    <div class="card-header bg-danger text-white text-center" id="card-header5">AI Player4<sup id="decksize5">()</sup></div>

                    <img class="card-img-top" src="https://image.flaticon.com/icons/svg/47/47294.svg" alt="Card image cap" id="img5">
                    <div class="card-body">
                        <h5 id="cardDescription5" class="card-title5">Hawk</h5>
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
						<td id="sizeValue5">1</td>
							</tr>
							<tr>
						<td>Speed</td>
						<td id="speedValue5">3</td>
						</tr>
						<tr>
						<td>Range</td>
						<td id="rangeValue5">2</td>
						</tr>
						<tr>
						<td>Firepower</td>
						<td id="firepowerValue5">4</td>
						</tr>
						<tr>
						<td>Cargo</td>
						<td id="cargoValue5">0</td>
						</tr>
						</tbody>
						</table>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card-next-round">
                <div class="card mb-4" style="min-width: 8rem; max-width: 12rem;">
                	<button class="btn btn-primary btn-lg" id="button-show-winner" onclick="showWinner()">Show Winner</button>  <br>
                    <button class="btn btn-primary btn-lg" id="button-next-round" onclick="playNextRound()">Next Round</button> 
                </div>
            </div>
        </div>

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

		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
			var deckSizeArray;
			var activePlayer; 
			var selectedCategoryByAI, selectedCategoryByHuman;
			var deckSizeHumanPlayer, deckSizeAI1, deckSizeAI2, deckSizeAI3, deckSizeAI4;
			var messageWithWinner;
			var communalPileSize;

			var chosenNumberOfPlayers;

			function displayBoardGame(){
				var displayElement = document.getElementById("board-game").style.display;
				if(displayElement == "none"){
					document.getElementById("board-game").style.display="block";
				} else {
					document.getElementById("board-game").style.display="none";
				}
			}

			function displayGameOver(){
				var displayElement = document.getElementById("game-over").style.display;
				if(displayElement == "none"){
					document.getElementById("game-over").style.display="block";
				} else {
					document.getElementById("game-over").style.display="none";
				}
			}


			function removeUnusedCards(){
				for(var i=5; i > chosenNumberOfPlayers; i--){
					document.getElementById("card" + i).style.display = "none";
				}
			}

			function setNumberOfPlayers(){
				chosenNumberOfPlayers = 1 + parseInt(document.getElementById("numberOfPlayers").value);
				resetGameWebApp(chosenNumberOfPlayers);
				document.getElementById("form").style.display = "none";
				removeUnusedCards();
			}

			function hideActiveAICards(){
				for(var i=1; i < chosenNumberOfPlayers; i++){
					document.getElementById("card" + (i+1)).style.visibility = "hidden";
				}
			}

			function showActiveAICards(){
				for(var i=1; i < chosenNumberOfPlayers; i++){
					document.getElementById("card" + (i+1)).style.visibility = "visible";
				}
			}

			function showWinner(){
				console.log("------------New Round------------")

				play();

				getAllCards(true);

				showActiveAICards();

				disableNextRoundButton(false);

				disableShowWinnerButton(true);

				disableDropdown(true);
			}

			function playNextRound(){
				
				hideActiveAICards();

				getAllCards(false);

				disableNextRoundButton(true);

				disableShowWinnerButton(false);


			}

			function disableDropdown(doYouWantItDisabled){
				document.getElementById("btn-dropdown").disabled = doYouWantItDisabled;
			}

			function disableShowWinnerButton(doYouWantItDisabled){

				document.getElementById("button-show-winner").disabled = doYouWantItDisabled;
			}

			function disableNextRoundButton(doYouWantItDisabled){
				document.getElementById("button-next-round").disabled = doYouWantItDisabled;
			}


			function resetGameWebApp(numberOfPlayers) {
				
				if(numberOfPlayers < 0 || numberOfPlayers > 5){
					alert("Please introduce a number of AI players between 0 and 4!");
					displayBoardGame();
					location.reload();
				} else {
					// First create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/resetGameWebApp?numberOfPlayers="+numberOfPlayers); // Request type and URL+parameters
					
					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
	  					alert("CORS not supported");
					}

					// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
					// to do when the response arrives 
					xhr.onload = function(e) {
	 					var responseText = xhr.response; // the text of the response
						console.log(responseText); // lets produce an alert
						getActivePlayer();
						playNextRound();
						
					};
					
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();		
				}
			}

			// function askForNumberOfPlayers(){
			// 	var modal = document.getElementById("modal");
			// 	var child = "<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal">&times;</button><h3 class="modal-title">Enter the total number of players. (Max. 5 players)</h3></div><div class="modal-body"><input type="text" class="form-control" name="numberOfPlayers" id="numberOfPlayers"><br><center><button type="button" class="btn btn-warning" onclick='setNumberOfPlayers()'>Start Game</button></center></div></div></div>";
			// 	modal.innerHTML = child;
			// }

			function getCard(playerIndex) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/extractCard?playerIndex=" + playerIndex); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var card = JSON.parse(xhr.response); // the text of the response
					console.log(card); // lets produce an alert
					
					document.getElementById("cardDescription" + (playerIndex+1)).innerHTML = card.name;
					document.getElementById("sizeValue" + (playerIndex+1)).innerHTML = card.description[0];
					document.getElementById("speedValue" + (playerIndex+1)).innerHTML = card.description[1];
					document.getElementById("rangeValue" + (playerIndex+1)).innerHTML = card.description[2];
					document.getElementById("firepowerValue" + (playerIndex+1)).innerHTML = card.description[3];
					document.getElementById("cargoValue" + (playerIndex+1)).innerHTML = card.description[4];
					document.getElementById("img" + (playerIndex+1)).src = "http://dcs.gla.ac.uk/~richardm/TopTrumps/"+ card.name +".jpg"; // Change Image source

					// getActivePlayer();

					// if(activePlayer == 0 && playerIndex != 0){
					// 	document.getElementById("card" + (playerIndex+1)).style.visibility = "hidden";
					// }


				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

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
								console.log("The card of number " + i+ " has this many cards" + deckSizeArray[i]);
								document.getElementById("card" + (i+1)).style.display = "none";
							} else {
								console.log("The card of number " + i+ " has this many cards" + deckSizeArray[i]);
								getCard(i);
								console.log("card number " + i + " has been retrieved from server!");
							}
						}

						if(deckSizeArray[i] == 40){
							checkAndGetWinner();
						}
					}
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}

			function setChosenCategoryByHuman(category){
				// selectedCategoryByHuman = 1 + category;
				selectedCategoryByHuman = category;
				console.log("category in setChosenCategoryByHuman is " + category);
				// playLikeHuman(category);
				// document.getElementById("button-next-round").disabled = false;
				// play(selectedCategoryByHuman);
			}

			function play(){
				if(activePlayer == 0){
					playLikeHuman(selectedCategoryByHuman);
				} else {
					playLikeAI();
				}


			}


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
					// selectedCategoryByAI = parseInt(xhr.response);
					// console.log("the selected category by " + activePlayer + " is " + selectedCategoryByAI);
					// playLikeHuman(selectedCategoryByAI);
					getActivePlayer(); 
					getCommunalPileSize();

					document.getElementById("container-message").innerHTML = messageWithWinner;

					// checkAndGetWinner();
				}


				xhr.send();
			}


			function playLikeHuman(category){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playLikeHuman?selectedCategoryByHuman=" + category); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					messageWithWinner = xhr.response;
					// console.log("The category from the rest api is: " + xhr.response);
					console.log("selectedCategoryByHuman is " + selectedCategoryByHuman);
					console.log("category argument for playLikeHuman is " + category);
					getActivePlayer();
					getCommunalPileSize();

					document.getElementById("container-message").innerHTML = messageWithWinner;

					// checkAndGetWinner();


					// selectedCategoryByHuman = parseInt(xhr.response);
					// play(selectedCategoryByAI);
				}


				xhr.send();
			}

			function showRoundWinner(){
				for(var i=0; i < chosenNumberOfPlayers; i++){
					document.getElementById("card-header" + (i+1)).className = "card-header bg-danger text-white text-center";
				}
				document.getElementById("card-header" + (activePlayer+1)).className = "card-header bg-success text-white text-center";

			}

			function checkAndGetWinner(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getWinner");

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var response = xhr.response;
					var winner = JSON.parse(response);
					// console.log("the winner of the game is " + winner.name);

					if(response != ""){
						displayBoardGame();
						displayGameOver();
						document.getElementById("winner").innerHTML = winner.name;
					}
				}


				xhr.send();
			}


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
 					var activePlayerObject = JSON.parse(xhr.response); // the text of the response
					console.log("Active player is:" + activePlayerObject.name); // lets produce an alert

					if(activePlayerObject.name === "You"){activePlayer = 0;}
					for(var i=1; i < chosenNumberOfPlayers; i++){
						if(activePlayerObject.name === ("AI Player"+i)){activePlayer = i;}
					}

					// else if(activePlayerObject.name === "AI Player1"){activePlayer = 1;}
					// else if(activePlayerObject.name === "AI Player2"){activePlayer = 2;}
					// else if(activePlayerObject.name === "AI Player3"){activePlayer = 3;}
					// else if(activePlayerObject.name === "AI Player4"){activePlayer = 4;}
					// else console.log("Something went wrong with the getActivePlayer function");

					document.getElementById("card-header").innerHTML = "Active Player is: <br> " + activePlayerObject.name;
					console.log("Active player is " + activePlayer);

					// if(activePlayer != 0){
					// 	document.getElementById("btn-dropdown").disabled = true;
					// } else {
					// 	document.getElementById("btn-dropdown").disabled = false;
					// 	document.getElementById("button-next-round").disabled = true;
					// 	document.getElementById("button-show-winner").disabled = true;
					// }

					showRoundWinner();

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


			// Method that is called on page load
			function initialize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				// initializeGame();
				// For example, lets call our sample methods
				// helloJSONList();
				// helloWord("Student");
				//getStatistics();
				// document.getElementById("btn-dropdown").disabled = true;
				// askForNumberOfPlayers();

				// getActivePlayer();

				// for(var i=0; i < chosenNumberOfPlayers; i++){
				// 	getCard(i);
				// }

				// getActivePlayer();

				// hideAICards();

				// getDeckSize();

				// showWinner(activePlayer);

				// playNextRound();		
				
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
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