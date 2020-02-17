package commandline;

import java.util.ArrayList;
import java.util.Random;
//main model class, works with Player, Card and Dealer
public class Game {
	
		//attributes
		private Player roundWinner=null;
		private Player humanPlayer=null;
		private Card winningCard=null;
		private Dealer dealer=null;
		private ArrayList<Player> players= new ArrayList<Player>();
		private ArrayList<Card> communalPile = new ArrayList<Card>();// array list communal pile
		private int numberOfRounds, numberOfDraws, numberOfPlayers, chosenCategory, initialDeckSize;
		private boolean gameFinished, draw;
		private Player gameWinner=null;
		private Card[] roundCards=null;
		//constructor
		public Game(int numberOfPlayers,String filePath) {
			this.numberOfPlayers=numberOfPlayers;
			dealer=new Dealer(this,filePath);
		}
		
/*
 * sets up a new game by reseting some values
 */
		public void resetGame(int numberOfPlayers) {
			numberOfRounds=0; 
			numberOfDraws=0;
			gameFinished=false; //game is now not finished
			draw=false; //there is no draw currently
			gameWinner=null; 
			players.clear(); 
			addPlayers(numberOfPlayers-1); //-1 because this should be the total number of players and we're adding AIs
			dealer.createCards(); //the cards are created 
			
		}
	/*
	 * checks if any player has lost all their cards and sets their active status to false	
	 */
		public void loserCheck() {
			for(int i=0;i<players.size();i++) {
				if(players.get(i).getDeck().size()==0) {
					players.get(i).setActive(false);
				}
			}
		}
/*
 * adds a number of AIs to the players list and a human player, makes the first player random
 */
		
		public void addPlayers(int amount) { 
			humanPlayer= new Player("You", true);
			players.add(humanPlayer);
			for(int i=0;i<amount;i++) {
				players.add(new Player(String.format("AI Player%d", i+1), false));
			}
			Random randomNumber = new Random();
			int random = randomNumber.nextInt(numberOfPlayers);
			roundWinner = players.get(random);
		}
		
		public void deal() { //sets the inital deck size before the cards are dealt and calls dealCards from dealer
			initialDeckSize=dealer.getDeckSize();
			dealer.dealCards();
		}
		/*
		 * takes the players' cards,
		 * compares the cards by category, 
		 * sets the winner as roundWinner and add the cards to their deck, saves cards to communal pile if necessary
		 */		
		public void compareCards() {  
			roundCards=new Card[players.size()];
			for (int i=0; i<players.size();i++) {  //get the cards from the players' decks
				if(players.get(i).isActive()) { //only if they are active
					roundCards[i]=players.get(i).getDeck().get(0); //adds the card to the round cards
					players.get(i).getDeck().remove(0); //remove it from the player's deck (he might or might not win it back, but it's on the table)
				}
			}
			int max=0; //used to hold the max value for the particular category that a card has 
			int winnersIndex=0; //the winning player's index number in the list
			int winnerCount=0; //used to differentiate between a draw and a win
			for (int i=0; i<roundCards.length;i++) {	//used to compare the value of the cards for the attribute
				if(roundCards[i] instanceof Card) { // checks if we have a card for that player (the spots in the array correspond to players)
					if (roundCards[i].getDescription()[chosenCategory]>max) { //if it's bigger it is assigned as the new max
						max=roundCards[i].getDescription()[chosenCategory];
						winnersIndex=i; //used to address the winning player later 
						winnerCount=1; //one winner only
					}else if(roundCards[i].getDescription()[chosenCategory]==max) {
						winnerCount++; //two cards have the same value (will indicate a draw)
					}
				}
			}
			if(winnerCount>1) { //two cards had the same top value - a draw
				numberOfDraws++;
				draw=true;
				for(int j=0; j<roundCards.length;j++) {
					if (roundCards[j] instanceof Card) {
					communalPile.add(roundCards[j]); //the cards are added to the communal pile
					}	
				}
			}
			else if (winnerCount==1) { //if there is only 1 max value - a winner
				for (int i=0;i<roundCards.length;i++) { 
					if (roundCards[i] instanceof Card) { // just checks if there is a card object in that spot in the array
						players.get(winnersIndex).addCardToDeck(roundCards[i]); //add the cards to their deck
						
					}
				}
				players.get(winnersIndex).incrementNumberOfRoundsWon(); // increase the win count for the player
				draw=false;
				roundWinner=players.get(winnersIndex); //sets this player as the round winner
				winningCard=roundCards[winnersIndex]; //sets the winningCard
				if(!this.communalPile.isEmpty()) { //in case of a previous draw
					for(int i=0;i<communalPile.size();i++) { // these cards are also added to his deck
						players.get(winnersIndex).addCardToDeck(communalPile.get(i));
					}
					communalPile.clear(); //empty the communalPile
				}
			}	
		}
		

		/*
		 * iterates the number of rounds and incorporates the AI logic - sets the chosenCategory based on the best choice for the AI's next card
		 */
		public void nextRound() {
			numberOfRounds++;
			if (!roundWinner.isHuman()) { //the roundWinner is not human so the AI sets the chosenCategory
				int max=0;
				for (int i = 0; i < roundWinner.getDeck().get(0).getDescription().length; i++) { 
			        if (roundWinner.getDeck().get(0).getDescription()[i] > max ) { //finds the highest member of the members of the categories on the card of the previous round's winner
			             max=roundWinner.getDeck().get(0).getDescription()[i];
			             chosenCategory=i; //sets that as the chosen category for the cards to be compared
			        }
				}
			}
		}
		
		/*
		 * checks if the game has ended by checking if any player has all the cards, sets that player as a winner
		 */
		public boolean hasGameEnded() {
			for(int i=0; i < players.size(); i++) {
				if(players.get(i).getDeck().size() == initialDeckSize-communalPile.size()) { //this is to address the scenario, where a player gives their last card in a draw
					gameFinished = true;
					players.get(i).setWinner(true);
					gameWinner=players.get(i);
				}
			}
			return gameFinished;
		}
		
		/*
		 * getters and setters 
		 */
		
		public Player getHumanPlayer() {
			return humanPlayer;
		}

		public Player getRoundWinner() {
			return roundWinner;
		}

		public int getNumberOfRounds() {
			return numberOfRounds;
		}
		
		public ArrayList<Player> getPlayers() {
			return players;
		}
		
		public int getNumberOfPlayers() {
			return numberOfPlayers;
		}

		public int getNumberOfDraws() {
			return numberOfDraws;
		}
				
		public boolean isGameFinished() {
			return gameFinished;
		}
		public Card getWinningCard() {
			return winningCard;
		}

		public ArrayList<Card> getCommunalPile() {
			return communalPile;
		}
		
		public void setChosenCategory(int category) {
			chosenCategory = category;
		}
		
		public int getChosenCategory() {
			return chosenCategory;
		}
		public boolean isDraw() {
			return draw;
		}
		
		public Dealer getDealer() {
			return dealer;
		}
		public Card[] getRoundCards() {
			return roundCards;
		}
		public Player getGameWinner() {
			return gameWinner;
		}
				
}