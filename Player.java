import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Card> deck = new ArrayList<Card>();	// Card Array List: Cards[] deck
	private int n = deck.size(); // Length of deck
	private boolean isWinner = false;
	private boolean isHuman=false; 
	
	public Player(String name, boolean isWinner) {
		this.name = name;
		this.isWinner = isWinner;
	}

	public void addCardToDeck(Card card) {
		deck.add(card);
	}
	
	public String getName() {
		return name;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public int getN() {
		return n;
	}

	public boolean isWinner() {
		return isWinner;
	}
	
	public boolean getIsHuman(){
		return isHuman;
	}
	
	public void setIsHuman() {
		this.isHuman=true;
	}
	
}