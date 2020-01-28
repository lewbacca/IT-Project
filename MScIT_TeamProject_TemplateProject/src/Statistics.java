
public class Statistics {
	private Database gamesDatabase;
	private int totalGames, gamesWonByHuman, gamesWonByCom, longestGame;
	private double averageDraws;
	
	public Statistics(Database gamesDatabase) {
		this.gamesDatabase=gamesDatabase;
	}
	
	public void Stats(){
		gamesDatabase.connectDatabase();
		gamesDatabase.updateDatabase();
		totalGames=gamesDatabase.totalGames();
		gamesWonByHuman=gamesDatabase.humanWins();
		gamesWonByCom=gamesDatabase.comWins();
		longestGame=gamesDatabase.longestGame();
		averageDraws=gamesDatabase.aveDrawsPerGame();
		gamesDatabase.closeDatabase();
	}
	
	public int getTotalGames() {
		return totalGames;
	}
	public int getHumanWins() {
		return gamesWonByHuman;
	}
	public int getComWins() {
		return gamesWonByCom;
	}
	public int getLongetsGame() {
		return longestGame;
	}
	public double getAverageDraws() {
		return averageDraws;
	}
}
