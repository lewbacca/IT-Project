import java.util.Scanner;

public class Controller {
	private Game game;
	private Scanner sc=null;
	private Database database=null;
	private Statistics statistics=null;
	private View view;
	
	public Controller(Game game){
		this.game=game;
		database=new Database(this.game);
		statistics=new Statistics(database);
		view=new View(game, this);
		sc=new Scanner(System.in);
		view.startMenu();
		firstChoice(sc.nextInt());
	}
	
	public void play() {
		game.resetGame(game.getNumberOfPlayers());
		while(!game.hasGameEnded()) {
			game.nextRound();
			view.roundViewBeforeSelectingCategory();
			if(game.getHumanPlayer().equals(game.getRoundWinner())) {
				view.roundViewWhileSelectingCategory();
				game.setChosenCategory(sc.nextInt()-1);
				sc.nextLine();
			}
			game.compareCards();
			view.roundViewAfterSelectingCategory();
			game.loserCheck();
		}
		statistics.stats();
		view.gameEnd();
		view.startMenu();
		firstChoice(sc.nextInt());
	}
	
	public void showStats() {
		view.showStatistics();
		view.startMenu();
		firstChoice(sc.nextInt());
	}
	public void firstChoice(int input) {
		sc.nextLine();
		if (input==2) {
			play();
		}else if(input==1) {
			showStats();
		}
	}
	public Statistics getStatistics() {
		return statistics;
	}
}
