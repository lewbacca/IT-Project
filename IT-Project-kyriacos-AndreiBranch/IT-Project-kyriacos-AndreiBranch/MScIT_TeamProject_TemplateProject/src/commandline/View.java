package commandline;

public class View {
	private Game game;
	private Controller controller;
	
	public View(Game game, Controller controller) {
		this.game = game;
		this.controller = controller;
	}
	
	public void startMenu() {
		System.out.println("\nDo you want to see past results or play a game?\r\n" + 
				"   1: Print Game Statistics\r\n" + 
				"   2: Play game\r\n" + 
				"Enter the number for your selection: ");
	}
	public void roundViewBeforeSelectingCategory() {
		System.out.println("\nRound " + game.getNumberOfRounds());
		if(game.getPlayers().get(0).isActive()) {
		System.out.println("Round "+ game.getNumberOfRounds()+": You drew :"+ game.getPlayers().get(0).getDeck().get(0).toString());
		System.out.println("You have " + game.getPlayers().get(0).getDeck().size() + " cards left in your deck!");
		}
	}
	public void roundViewWhileSelectingCategory() {
		System.out.println("\nIt is your turn to select a category, the categories are:\r\n" + 
				"   1: Size\r\n" + 
				"   2: Speed\r\n" + 
				"   3: Range\r\n" + 
				"   4: Firepower\r\n" + 
				"   5: Cargo\r\n" + 
				"Enter the number for your attribute: ");
	}
	public void roundViewAfterSelectingCategory() {
		if(!game.isDraw()) {
		System.out.println("\nRound " + game.getNumberOfRounds() + ": " + game.getRoundWinner().getName() +" won this round.");
		System.out.println("The winning card was '" + game.getWinningCard().getName()+ "':\n " + addArrowToCategory(game.getChosenCategory()));
		}else 
			System.out.println("It's a draw. \nThere are " + game.getCommunalPile().size() + " cards in the communal pile");
	}
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
	public void gameEnd() {
		String playerScores="";
		for (int i=0; i<game.getNumberOfPlayers();i++) {
			playerScores+="\n"+game.getPlayers().get(i).getName()+": "+game.getPlayers().get(i).getNumberOfRoundsWon();
		}
		System.out.print("\n Game End:"
				+ "\n The overall winner was " + game.getRoundWinner().getName()
				+ "\nScores:"+playerScores);
	}
	public void showStatistics() {
		System.out.println("Game Statistics: "
				+ "\n Number of Games: " +controller.getStatistics().getTotalGames()
				+ "\n Number of Human Wins: " + controller.getStatistics().getHumanWins()
				+ "\n Number of AI Wins: " + controller.getStatistics().getComWins()
				+ "\n Average Number of Draws: " + controller.getStatistics().getAverageDraws()
				+ "\n Longest Game: " + controller.getStatistics().getLongestGame());
	}
}