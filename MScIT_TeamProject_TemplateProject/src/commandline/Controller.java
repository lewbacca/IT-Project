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
	
	public Controller(Game game){
		this.game=game;
		database=new Database(this.game);
		statistics=new Statistics(database);
		view=new View(game, this);
		logFile=new LogFile("Logger");
		sc=new Scanner(System.in);
		play=true;
	}
	
	public void play() {
		while (play==true) {
			view.startMenu();
			firstChoice();
			game.resetGame(game.getNumberOfPlayers());
			logFile.writeInitialDeck(game.getDealer().getDeck());
			game.deal();
			logFile.writePlayersDecks(game.getPlayers());
			while(!game.hasGameEnded()) {
				game.nextRound();
				view.roundViewBeforeSelectingCategory();
				if(game.getHumanPlayer().equals(game.getRoundWinner())) {
					view.roundViewWhileSelectingCategory();
					game.setChosenCategory(categoryChoice());
					
				}
				logFile.writeCategory(game.getChosenCategory(),game.getPlayers());
				game.compareCards();
				logFile.writeHand(game.getRoundCards());
				logFile.writePlayersDecks(game.getPlayers());
				if(!game.getCommunalPile().isEmpty()) {
					logFile.writeCommunalPile(game.getCommunalPile());
				}
				view.roundViewAfterSelectingCategory();
				game.loserCheck();
			}
			logFile.writeWinner(game.getGameWinner());
			statistics.updateStats();
			statistics.stats();
			view.gameEnd();
			view.finishMenu();
			play=lastChoice();
		}
		
	}
	
	public void showStats() {
		view.showStatistics();
		view.startMenu();
		firstChoice();
	}
	
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
	
	public void firstChoice() {
		int input=sc.nextInt();
		sc.nextLine();
		while ((input!=1) &&(input!=2)){
			view.errorMessage();
			view.startMenu();
			input=sc.nextInt();
			sc.nextLine();
		}
		if(input==1) {
			showStats();
		}
	}
	
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
