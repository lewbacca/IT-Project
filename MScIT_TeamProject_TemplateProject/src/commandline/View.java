package commandline;

public class View {
	
	//attributes
	private Game game;				//view needs to know about the game
	private Controller controller;	//view needs to know about the controller
	
	//constructor
	public View(Game game, Controller controller) { //a game and a controller object are passed to the view
		this.game = game;
		this.controller = controller;
	}
	
	//this is the initial menu
	public void startMenu() {
		System.out.print("\nDo you want to see past results or play a game?\r\n" + 
				"   1: Print Game Statistics\r\n" + 
				"   2: Play game\r\n" + 
				"Enter the number for your selection: ");
	}
	
	//this is the menu that it is shown after the user selects to print the game statistics
	public void statMenu() {
		System.out.print("\nDo you want to reset statistics or play a game?\r\n" + 
				"   1: Reset Statistics\r\n" + 
				"   2: Play game\r\n" + 
				"Enter the number for your selection: ");
	}
	
	//this is the menu that shows the number of the round, which player is active, the card the user has drew and how many cards are left in his deck
	public void roundViewBeforeSelectingCategory() {
		System.out.println("\nRound " + game.getNumberOfRounds()); //number of rounds are retrieved from game object
		if(game.getPlayers().get(0).isActive()) {
			//players current card is shown by retrieving the first card of his deck
			System.out.println("Round "+ game.getNumberOfRounds()+": You drew :"+ game.getPlayers().get(0).getDeck().get(0).toString()); 
			//number of cards left in players deck is the same as the size of his deck
			System.out.println("You have " + game.getPlayers().get(0).getDeck().size() + " cards left in your deck!");
		}
	}
	
	//a menu that shows the player the number which is corresponded to each category
	public void roundViewWhileSelectingCategory() {
		System.out.print("\nIt is your turn to select a category, the categories are:\r\n" + 
				"   1: Size\r\n" + 
				"   2: Speed\r\n" + 
				"   3: Range\r\n" + 
				"   4: Firepower\r\n" + 
				"   5: Cargo\r\n" + 
				"Enter the number for your attribute: ");
	}
	
	//the menu after the player selects category
	public void roundViewAfterSelectingCategory() {
		if(!game.isDraw()) { //if the game is not in a state of draw
		//display the number of round and the name of the winner
		System.out.println("\nRound " + game.getNumberOfRounds() + ": " + game.getRoundWinner().getName() +" won this round.");
		//show the winning card with an arrow pointing the winning category
		System.out.println("The winning card was '" + game.getWinningCard().getName()+ "':\n " + addArrowToCategory(game.getChosenCategory()));
		}else  //if there is a draw
			//display the number of cards in the communal pile
			System.out.println("It's a draw. \nThere are " + game.getCommunalPile().size() + " cards in the communal pile");
	}
	
	//a method that is called to create the arrow that is to be used for pointing the winning category
	public String addArrowToCategory(int categoryNumber) {
		Card winningCard = game.getWinningCard();
		String arrow = "  <---";
		String[] stringArray = {"","","","",""};
		stringArray[categoryNumber] = arrow;
		return String.format("> Size: "+ winningCard.getDescription()[0] + "%s" +
				"\n > Speed: "+winningCard.getDescription()[1] + "%s" +
				"\n > Range: "+winningCard.getDescription()[2] + "%s" +
				"\n > Firepower: "+winningCard.getDescription()[3] + "%s" +
				"\n > Cargo: "+ winningCard.getDescription()[4] + "%s", 
				stringArray[0],stringArray[1],stringArray[2],stringArray[3],stringArray[4]);
	}
	
	//the menu shown after a game is ended
	public void gameEnd() {
		String playerScores="";
		//save how many rounds have been won by each player
		for (int i=0; i<game.getNumberOfPlayers();i++) {
			playerScores+="\n"+game.getPlayers().get(i).getName()+": "+game.getPlayers().get(i).getNumberOfRoundsWon();
		}
		//show the overall winner and each players scores
		System.out.print("\n Game End:"
				+ "\n The overall winner was " + game.getRoundWinner().getName()
				+ "\n\nScores:"+playerScores+"\n");
	}
	
	//display the statistics
	public void showStatistics() {
		controller.getStatistics().stats(); //statistics are retrieved from controller
		System.out.println("Game Statistics: "
				+ "\n Number of Games: " +controller.getStatistics().getTotalGames()	
				+ "\n Number of Human Wins: " + controller.getStatistics().getHumanWins()
				+ "\n Number of AI Wins: " + controller.getStatistics().getComWins()
				+ "\n Average Number of Draws: " + controller.getStatistics().getAverageDraws()
				+ "\n Longest Game: " + controller.getStatistics().getLongestGame());
	}
	
	//the final choice after every game
	public void finishMenu() {
		System.out.print("0 - Exit, 1 - Start Menu: ");
	}
	
	//a message for every not suitable choice
	public void errorMessage() {
		System.err.println("Please provide a suitable choice");
	}
}