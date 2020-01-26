import java.util.ArrayList;

public class Game {
		
		private ArrayList<Player> players= new ArrayList<Player>();
		private int numberOfRounds;
		private ArrayList<Card> communalPile = new ArrayList<Card>();// array list communal pile
		private boolean isHumanWinner;
		
		public Game() {
			this.players.add(new Player("You", true));
			this.players.add(new Player("AI1", false));
			this.players.add(new Player("AI2", false));
			this.players.add(new Player("AI3", false));
			this.players.add(new Player("AI4", false));
			this.numberOfRounds=0;
		}

		public int getNumberOfPlayers() {
			return this.players.size();
		}

		public boolean isHumanWinner() {
			return isHumanWinner;
		}

		public void setHumanWinner(boolean isHumanWinner) {
			this.isHumanWinner = isHumanWinner;
		}

		public int getNumberOfRounds() {
			return numberOfRounds;
		}

		public ArrayList<Card> getCommunalPile() {
			return communalPile;
		}
		
		public void splitCards() { //splits the cards initially
			
		}
		
	
}