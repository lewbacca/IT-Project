import java.util.ArrayList;

public class Game {
		private Player player;
		private int numberOfPlayers; // Number of players variable
		private int howManyRounds;
		private ArrayList<Card> communalPile = new ArrayList<Card>();// array list communal pile
		// game statistics
		private int howManyGamesArePlayed, howManyGamesHaveBeenWonByHuman, longestGame; 
		private double averageRoundsPerGame;
		
		public Game() {
			
		}
		
		// Method: Split the cards 
		
		public int getHowManyGamesArePlayed() {
			return howManyGamesArePlayed;
		}
		
		public void setHowManyGamesArePlayed(int i) {
			howManyGamesArePlayed+=i;
		}
		
		public int getHowManyGamesHaveBeenWonByHuman() {
			return howManyGamesHaveBeenWonByHuman;
		}
		
		public void setHowManyGamesHaveBeenWonByHuman(int i) {
			howManyGamesHaveBeenWonByHuman+=i;
		}
		
		public int getLongestGame() {
			return longestGame;
		}
		
		public void setLongestGame(int i) {
			
		}
}