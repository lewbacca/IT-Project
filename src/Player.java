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

	public boolean isWinner() {
		return isWinner;
	}
	public Card nextCard() {
		return deck.poll();
	}

	public Queue<Card> getDeck() {
		return deck;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}
	
	
}