import java.util.ArrayList;

public class Game {
		
		private ArrayList<Player> players= new ArrayList<Player>();
		private int numberOfRounds;
		private int numberOfDraws;
		private ArrayList<Card> communalPile = new ArrayList<Card>();// array list communal pile
		private Player roundWinner;
		private int numberOfPlayers;
		private int chosenCategory;
		private String filePath;
		
		public Game(int numberOfPlayers,String filePath) {
			this.numberOfPlayers=numberOfPlayers;
			this.filePath=filePath;
			this.numberOfRounds=0;
			Player humanPlayer= new Player("You", true);
			this.players.add(humanPlayer);
			addPlayers(numberOfPlayers-1); //-1 because this should be the total number of players and we're adding AIs
			deal();
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
/*
 * uses the deck.poll() method on each member of the players array list,
 * compares the cards by category, 
 * sets the winner as roundWinner and add the cards to his deck
 */
		public void compareCards() {  
			Card[] roundCards=new Card[players.size()];
			for (int i=0; i<players.size();i++) {  //get the cards from the players' decks
				roundCards[i]=players.get(i).getDeck().poll();
			}
			int max=0;
			int winnersIndex=0;
			for (int i=0; i<roundCards.length;i++) {
				if (roundCards[i].getDescription()[chosenCategory]>max) {
					this.roundWinner=this.players.get(i);
					winnersIndex=i;
				}
			}
			for (int i=0;i<roundCards.length;i++) {
				players.get(winnersIndex).addCardToDeck(roundCards[i]);
			}
			
		}
		public void nextRound() { //checks who the roundWinner is and directs category selection appropriately (human or AI)
			numberOfRounds++;
			
			System.out.println("Round "+numberOfRounds+"\nYou drew :"+players.get(0).getDeck().peek().toString());
			if (!roundWinner.isHuman()) {
				int max=0;
				for (int i = 0; i < roundWinner.getDeck().peek().getDescription().length; i++) 
			        if (roundWinner.getDeck().peek().getDescription()[i] > max ) { 
			             max=roundWinner.getDeck().peek().getDescription()[i];
			             this.chosenCategory=i;
			        }
					
				
			}
		}
		public void addPlayers(int amount) { //adds a number of AIs to the players list
			for(int i=0;i<amount;i++) {

				players.add(new Player(String.format("AI%d", i+1), false));
				
			}
		}
		public void deal() { //creates a Dealer who splits the cards amongst the players
			Dealer dealer=new Dealer(this);
			dealer.createCards(this.filePath);
			dealer.dealCards();
		}
		
}