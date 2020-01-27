import java.util.ArrayList;

public class Dealer {
	private Game game;
	private ArrayList<Card> deck=new ArrayList<Card>();
	
	public Dealer(Game game) {
		this.game=game;
	}
	
	public void createCards(String filePath) { //reads the txt file and makes every line into a new Card object, stores it in deck
		
	}
	
	public void dealCards(){ //takes deck and distributes the cards randomly to the players, 1 random card at a time for player 1 to 5  
		
	}
}
