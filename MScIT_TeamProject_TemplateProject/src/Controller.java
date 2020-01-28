import java.util.Scanner;

public class Controller {
	private Game game;
	private View view;
	
	public Controller(Game game){
		this.game=game;
		this.view=new View(game, this);
	}
	public void play() {
		Scanner sc=new Scanner(System.in);
		while(!game.hasGameEnded()) {
			game.nextRound();
			view.roundViewBeforeSelectingCategory();
			if(game.getHumanPlayer().equals(game.getRoundWinner())) {
				view.roundViewWhileSelectingCategory();
				int input=sc.nextInt();
				sc.nextLine();
				game.setChosenCategory(input-1);
			}
			game.compareCards();
			view.roundViewAfterSelectingCategory();
		}
		view.closingMenu();
		int input=sc.nextInt();
		
	}
	
}
