import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Player {
	private String name;
	private Queue<Card> deck = new PriorityQueue<Card>();	// 
	private boolean isWinner = false;
	private boolean isHuman=false;
	
	public Player(String name,boolean isHuman) {
		this.name=name;
		this.isHuman=isHuman;
	}

	public boolean isHuman() {
		return isHuman;
	}

	public void addCardToDeck(Card card) {
		deck.add(card);
	}
	
	public String getName() {
		return name;
	}

	public int deckSize() {
		return deck.size();
	}

	public boolean isWinner() {
		return isWinner;
	}
	
	
}