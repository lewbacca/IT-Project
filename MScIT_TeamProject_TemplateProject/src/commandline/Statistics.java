package commandline;


//Statistics Class is an extension of the Database Class
public class Statistics {
	private Database gamesDatabase=null; //Statistics are handling the database
	private int totalGames=0;			 //these are the attributes that are required by the specifications
	private int gamesWonByHuman=0;
	private int gamesWonByCom=0; 
	private int longestGame=0;
	private double averageDraws=0.0;
	
	//constructor
	public Statistics(Database gamesDatabase) {	//a database object is assigned to the class
		this.gamesDatabase=gamesDatabase;
	}
	
	//a method that retrieves the statistics from the database
	public void stats(){
		gamesDatabase.connectDatabase();				//first the database has to be connected
		totalGames=gamesDatabase.totalGames();			//every attribute is assigned to the corresponding value
		gamesWonByHuman=gamesDatabase.humanWins();
		gamesWonByCom=gamesDatabase.comWins();
		longestGame=gamesDatabase.longestGame();
		averageDraws=gamesDatabase.aveDrawsPerGame();
		gamesDatabase.closeDatabase();					//close the database
		
	}
	
	//this method is called after every game is done, in order to update the database
	public void updateStats() {
		gamesDatabase.connectDatabase();	//connect
		gamesDatabase.updateDatabase();		//update
		gamesDatabase.closeDatabase();		//close
	}
	
	//a method to clear the statistics
	public void resetStats() {
		gamesDatabase.connectDatabase();	//connect
		gamesDatabase.clearTable();			//clear
		gamesDatabase.closeDatabase();		//close
	}
	
	//these are the getters of the class so the attributes can be used by other classes
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
