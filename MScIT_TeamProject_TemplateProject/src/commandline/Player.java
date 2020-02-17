package commandline;

import java.util.ArrayList;


public class Player {
	private String name;
	private ArrayList<Card> deck = new ArrayList<Card>();	
	private boolean isWinner = false;
	private boolean isHuman=false; 
	private boolean active = true;
	private int numberOfRoundsWon=0; //used for statistics
	
	public Player(String name,boolean isHuman) { //constructor
		this.name=name;
		this.isHuman=isHuman;
	}
/*
 * getters and setters
 */
	public boolean isHuman() {
		return isHuman;
	}

	public void addCardToDeck(Card card) { 
		deck.add(card);
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumberOfRoundsWon() {
		return numberOfRoundsWon;
	}
	public void incrementNumberOfRoundsWon() {
		numberOfRoundsWon++;
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
		this.active=active;
	}
	
	
}