
public class View {
	private Game game;
	private Controller controller;
	
	public View(Game game, Controller controller) {
		this.game = game;
		this.controller = controller;
	}
	
	public void startMenu() {
		System.out.println("Do you want to see past results or play a game?\r\n" + 
				"   1: Print Game Statistics\r\n" + 
				"   2: Play game\r\n" + 
				"Enter the number for your selection: ");
	}
	
	public void roundViewBeforeSelectingCategory() {
				
		System.out.println("Round " + game.getNumberOfRounds());
		
		System.out.println("Round "+ game.getNumberOfRounds()+": You drew :"+ game.getPlayers().get(0).getDeck().get(0).toString());
		
		System.out.println("You have " + game.getRoundWinner().getDeck().get(0).getName() + " cards left in your deck!");
	}
	
	public void roundViewWhileSelectingCategory() {
		System.out.println("It is your turn to select a category, the categories are:\r\n" + 
				"   1: height\r\n" + 
				"   2: weight\r\n" + 
				"   3: length\r\n" + 
				"   4: ferocity\r\n" + 
				"   5: intelligence\r\n" + 
				"Enter the number for your attribute: ");
	}
	
	public void roundViewAfterSelectingCategory() {
		
		
		System.out.println("Round " + game.getNumberOfRounds() + ": " + game.getRoundWinner() +" won this round.");
		
		System.out.println("The winning card was '" + game.getWinningCard().getName() + "':\n " + addArrowToCategory(game.getChosenCategory()));

	}
	
	public String addArrowToCategory(int categoryNumber) {
		Card winningCard = game.getRoundWinner().getDeck().get(0);
		
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
		System.out.print(" Game End:"
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
