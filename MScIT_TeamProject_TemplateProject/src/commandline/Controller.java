package commandline;
import java.util.Scanner;

public class Controller {
	private Game game;
	private Scanner sc=null;
	private Database database=null;
	private Statistics statistics=null;
	private View view;
	private boolean play;
	private LogFile logFile=null;
	//constructor
	public Controller(Game game){
		this.game=game;
		database=new Database(this.game);
		statistics=new Statistics(database);
		view=new View(game, this);
		logFile=new LogFile("Logger");
		sc=new Scanner(System.in);
		play=true;
	}
	/*
	 * this method runs the main game logic
	 */
	public void play() {
		while (play==true) {
			view.startMenu(); //shows the initial choices of a statistics or a new game
			firstChoice(); //if the user inputs 1, they see statistics, if they enter 2 they continue to a new game
			game.resetGame(game.getNumberOfPlayers()); //game is reset for however many players
			logFile.writeInitialDeck(game.getDealer().getDeck()); 
			game.deal(); //the cards are given to the players
			logFile.writePlayersDecks(game.getPlayers()); 
			while(!game.hasGameEnded()) {
				game.nextRound(); //increments the number of rounds and let's the AI pick a category if it's their turn to do so
				view.roundViewBeforeSelectingCategory(); //shows appropriate view
				if(game.getHumanPlayer().equals(game.getRoundWinner())) { //let's human select category if it is their turn to do so
					view.roundViewWhileSelectingCategory();
					game.setChosenCategory(categoryChoice()); //they choose in a dummy-proof way using categoryChoice
					
				}
				logFile.writeCategory(game.getChosenCategory(),game.getPlayers());
				game.compareCards();
				logFile.writeHand(game.getRoundCards()); //logs the rounds of the card
				logFile.writePlayersDecks(game.getPlayers());
				if(!game.getCommunalPile().isEmpty()) {
					logFile.writeCommunalPile(game.getCommunalPile());
				}
				view.roundViewAfterSelectingCategory();
				game.loserCheck(); //checks if anyone ran out of cards and makes them inactive
			}
			logFile.writeWinner(game.getGameWinner());
			statistics.updateStats();
			statistics.stats(); //retrieves the updated values of the statistics from the database
			view.gameEnd();
			view.finishMenu();
			play=lastChoice(); //allows the player to quit the program altogether 
		}
		
	}
	/*
	 * shows the statistics and shows a menu within the statistics
	 */
	public void showStats() {
		view.showStatistics();
		view.statMenu();
		statsChoice();
	}
	/*
	 * dummy-proof method for category input
	 */
	public int categoryChoice() {
		int category=sc.nextInt()-1;
		sc.nextLine();
		while ((category<0)||(category>5)) {
			view.errorMessage();
			category=sc.nextInt()-1;
			sc.nextLine();
		}
		return category;
	}
	/*
	 * allows the player to choose whether they want to see statistics or start playing. if the user presses 2, they just continue down the logic in play()  
	 */
	public void firstChoice() {
		int input=sc.nextInt();
		sc.nextLine();
		while ((input!=1) &&(input!=2)){ //dummy-proof input
			view.errorMessage();
			view.startMenu();
			input=sc.nextInt();
			sc.nextLine();
		}
		if(input==1) {
			showStats(); 
		}
	}
	/*
	 * allows the player to reset the statistics or start a new game
	 */
	public void statsChoice() {
		int input=sc.nextInt();
		sc.nextLine();
		while ((input!=1) &&(input!=2)){ //dummy-proof input
			view.errorMessage();
			view.statMenu();
			input=sc.nextInt();
			sc.nextLine();
		}
		if(input==1) { //takes the player back to the initial menu after reseting the statistics
			statistics.resetStats();
			view.startMenu();
			firstChoice();
		}
	}
	
	/*
	 * allows the player to quit the game or, again, choose whether they want to see statistics or play a new game
	 */
	public boolean lastChoice() {
		int input=sc.nextInt();
		sc.nextLine();
		while ((input!=1)&&(input!=0)){
			view.errorMessage();
			view.finishMenu();
			input=sc.nextInt();
			sc.nextLine();
		}
		if (input==0) {
			return false;
		}else {
			return true;
		}
	}
	
	public Statistics getStatistics() {
		return statistics;
	}
}
