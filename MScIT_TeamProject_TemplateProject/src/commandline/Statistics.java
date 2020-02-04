package commandline;

public class Statistics {
	private Database gamesDatabase=null;
	private int totalGames=0;
	private int gamesWonByHuman=0;
	private int gamesWonByCom=0; 
	private int longestGame=0;
	private double averageDraws=0.0;
	
	public Statistics(Database gamesDatabase) {
		this.gamesDatabase=gamesDatabase;
	}
	
	public void stats(){
		gamesDatabase.connectDatabase();
		totalGames=gamesDatabase.totalGames();
		gamesWonByHuman=gamesDatabase.humanWins();
		gamesWonByCom=gamesDatabase.comWins();
		longestGame=gamesDatabase.longestGame();
		averageDraws=gamesDatabase.aveDrawsPerGame();
		gamesDatabase.closeDatabase();
		
	}
	
	public void updateStats() {
		gamesDatabase.connectDatabase();
		gamesDatabase.updateDatabase();
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
	
	public int getLongestGame() {
		return longestGame;
	}
	
	public double getAverageDraws() {
		return averageDraws;
	}
}
