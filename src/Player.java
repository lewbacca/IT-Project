import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Player {
	private String name;
	private ArrayList<Card> deck = new ArrayList<Card>();	// 
	private boolean isWinner = false;
	private boolean isHuman=false;
	private int numberOfRoundsWon=0;
	private boolean active=true;
	
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
	
	public int getNumberOfRoundsWon() {
		return numberOfRoundsWon;
	}

	public void incrementNumberOfRoundsWon() {
		
		numberOfRoundsWon++;
	}

	public String getName() {
		return name;
	}

	public boolean isWinner() {
		return isWinner;
	}
	
	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}