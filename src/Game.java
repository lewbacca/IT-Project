import java.util.ArrayList;

public class Game {
		
		private ArrayList<Player> players= new ArrayList<Player>();
		private int numberOfRounds;
		private int numberOfDraws;
		private ArrayList<Card> communalPile = new ArrayList<Card>();// array list communal pile
		private Player roundWinner;
		private int numberOfPlayers;
		private Player humanPlayer=null;
		private int chosenCategory;
		private String filePath;
		private Card winningCard=null;
		private boolean gameFinished=false;
		private Dealer dealer;
		
		public Game(int numberOfPlayers,String filePath) {
			this.numberOfPlayers=numberOfPlayers;
			this.filePath=filePath;
			this.numberOfRounds=0;
			this.numberOfDraws=0;
			this.humanPlayer= new Player("You", true);
			this.roundWinner=humanPlayer;
			this.players.add(humanPlayer);
			addPlayers(numberOfPlayers-1); //-1 because this should be the total number of players and we're adding AIs
			deal();
		}

		
/*
 * uses the deck.poll() method on each member of the players array list,
 * compares the cards by category, 
 * sets the winner as roundWinner and add the cards to his deck
 */
		public void compareCards() {  
			Card[] roundCards=new Card[players.size()];
			for (int i=0; i<players.size();i++) {  //get the cards from the players' decks
				roundCards[i]=players.get(i).getDeck().get(0);
				players.get(i).getDeck().remove(0);
			}
			
			int max=0; //used to hold the max value for the particular category that a card has 
			int winnersIndex=0; //the winning player's index number in the list
			int winnerCount=0; //used to differentiate between a draw and a win
			for (int i=0; i<roundCards.length;i++) {	//used to compare the value of the cards for the attribute
				if (roundCards[i].getDescription()[chosenCategory]>max) { //if it's bigger it is assigned as the new max 
					max=roundCards[i].getDescription()[chosenCategory];
					winnersIndex=i; //used to address the winning player later 
					winnerCount=1; //one winner only
				}else if(roundCards[i].getDescription()[chosenCategory]==max) {
					winnerCount++; //two cards have the same value (will indicate a draw)
				}
			}	
			if(winnerCount>1) { //two cards had the same top value - a draw
				this.numberOfDraws++;
				for(int j=0; j<roundCards.length;j++) {
					this.communalPile.add(roundCards[j]); //the cards are added to the communal pile
				}	
			}
			else if (winnerCount==1) { //if there is only 1 max value - a winner
				for (int i=0;i<roundCards.length;i++) { 
					players.get(winnersIndex).addCardToDeck(roundCards[i]); //add the cards to his deck
					}
				this.roundWinner=players.get(winnersIndex);
				this.winningCard=roundCards[winnersIndex];
				if(!this.communalPile.isEmpty()) { //in case of a previous draw
					for(int i=0;i<this.communalPile.size();i++) { // these cards are also added to his deck
					players.get(winnersIndex).addCardToDeck(this.communalPile.get(i));
					}
					this.communalPile.clear(); //empty the communalPile
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
			             this.chosenCategory=i; //sets that as the chosen category for the cards to be compared
			        }
					
				}
			}
		}
		public void addPlayers(int amount) { //adds a number of AIs to the players list
			for(int i=0;i<amount;i++) {

				players.add(new Player(String.format("AI%d", i+1), false));
				
			}
		}
		
		public void deal() { //creates a Dealer who splits the cards amongst the players
			this.dealer=new Dealer(this);
			dealer.createCards(this.filePath);
			dealer.dealCards();
		}
		public boolean hasGameEnded() {
			for(int i=0; i < players.size(); i++) {
				if(players.get(i).getDeck().size() == this.dealer.getDeck().size()) {
					this.gameFinished = true;
				}
			}
			return this.gameFinished;
		}
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

		public int getNumberOfDraws() {
			return numberOfDraws;
		}
		public int getChosenCategory() {
			return chosenCategory;
		}
		
		public boolean isGameFinished() {
			return gameFinished;
		}


		public ArrayList<Card> getCommunalPile() {
			return communalPile;
		}
		public void setChosenCategory(int chosenCategory) {
			this.chosenCategory = chosenCategory;
		}
		
}