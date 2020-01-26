import java.util.ArrayList;

public class Game {
		
		private ArrayList<Player> players= new ArrayList<Player>();
		private int numberOfRounds;
		private int numberOfDraws;
		private ArrayList<Card> communalPile = new ArrayList<Card>();// array list communal pile
		private Player roundWinner;
		private int numberOfPlayers;
		
		public Game(int numberOfPlayers) {
			this.numberOfPlayers=numberOfPlayers;
			this.numberOfRounds=0;
			this.players.add(new Player("You", true));
			addPlayers(numberOfPlayers-1);
			deal();
		}

		public Player getroundWinner() {
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

		public void compareCards() { //uses the deck.poll() method on each member of the players array list,
									// compares the cards by category,
									//sets the winner as roundWinner and add the cards to his deck 
			
		}
		public void nextRound() { //checks who the roundWinner is and directs category selection appropriately (human or AI)
			numberOfRounds++;
		}
		public void addPlayers(int amount) { //adds a number of AIs to the players list
			
		}
		public void deal() { //creates a Dealer who splits the cards amongst the players
			Dealer dealer=new Dealer(this);
		}
		
}