import java.util.ArrayList;
import java.util.Random;

public class Dealer {
	private Game game;
	private ArrayList<Card> deck=new ArrayList<Card>();
	
	public Dealer(Game game) {
		this.game=game;
	}
	
	public void createCards(String filePath) { //reads the txt file and makes every line into a new Card object, stores it in deck
		
	}
	
	public void dealCards(){ //takes deck and distributes the cards randomly to the players, 1 random card at a time for player 1 to 5  
		while (!deck.isEmpty())
		for (int i=0;i<game.getPlayers().size();i++) {
			Random randomNumber= new Random();
			int random=randomNumber.nextInt(deck.size());
			game.getPlayers().get(i).addCardToDeck(deck.get(random));
			this.deck.remove(random);
			if (deck.isEmpty()) {
				break;
			}
		}
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}
}
