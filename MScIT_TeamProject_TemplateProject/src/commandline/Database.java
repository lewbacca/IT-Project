package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {
	 //settings of the database
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String IP= "jdbc:postgresql://52.24.215.108/EasternHemisandSemis";
	private static final String username="EasternHemisandSemis";
	private static final String password="EasternHemisandSemis";
	
	//variables used to connect to Database and execute the queries
	private Connection connection=null;
	private Statement stat=null;
	private ResultSet rs=null;
	
	//variables that are stored in the database
	private int gameNumber=0;
	private int numberOfDraws,rounds;
	private boolean humanWin;
	
	//attributes of the class
	private Game game;
	
	
	//constructor of the class
	public Database(Game game) {
		this.game=game; //we pass a game object to the database
	}
	
	//Database connection
	public void connectDatabase() {

		//grant access to the database
		try {
			Class.forName(JDBC_DRIVER);
			}catch (Exception e) {
				System.out.println("Could not load class org.postgresql.Driver");
				e.printStackTrace();
				return;
			}
		try {
			connection=DriverManager.getConnection(IP,username,password);
		}catch(SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}
		
		createTable(); //calling of createTable()
	}
	
	//a method that creates the Table that will be used by the database.
	public void createTable() {
		try {
			stat=connection.createStatement();
			String sqlCreate= "CREATE TABLE IF NOT EXISTS TopTrumps(gameNo int, humanWin boolean, numberOfDraws int,rounds int)"; //the table will be created only if it not exists(only the first time)
			stat.executeUpdate(sqlCreate);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//a method that clears all the registrations in the table
	public void clearTable() {
		try {
			stat=connection.createStatement();
			String sqlClear="Truncate Table TopTrumps;";
			stat.executeUpdate(sqlClear);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//a method that updates the database. It is called after every finished game
	public void updateDatabase() {
		gameNumber++;								//increment of the games that have been played
		humanWin=game.getHumanPlayer().isWinner();	//if the winner is human a true value is registered in the database. if not, this value is false
		numberOfDraws=game.getNumberOfDraws();		//number of draws of the game
		rounds=game.getNumberOfRounds();			//number of round of the game
		if (connection!=null) {
			try {
				String sqlUpdate="INSERT INTO TopTrumps(gameNo,humanWin,numberOfDraws,rounds) VALUES ("+gameNumber+","+humanWin+","+numberOfDraws+","+rounds+")"; //register the values to the corresponding column of the table
				stat.executeUpdate(sqlUpdate);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Error! Can not update Database");
		}
	}
	
	//a method that retrieves the total games that have been played
	public int totalGames() {
		String Query="SELECT COUNT (*) FROM TopTrumps;";
		int total=0;
		try {
			stat=connection.createStatement();
			rs = stat.executeQuery(Query);
			while (rs.next()) {
				total=rs.getInt("COUNT");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	//a method that retrieves the number of human wins of the game
	public int humanWins() {
		String Query="SELECT COUNT (*) FROM TopTrumps WHERE humanWin=TRUE;";
		int wins=0;
		try {
			stat=connection.createStatement();
			rs = stat.executeQuery(Query);
			while (rs.next()) {
				wins=rs.getInt("COUNT");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return wins;
	}
	
	//a method that retrieves the number of computer wins of the game
	public int comWins() {
		String Query="SELECT COUNT (*) FROM TopTrumps WHERE humanWin=FALSE;";
		int wins=0;
		try {
			stat=connection.createStatement();
			rs = stat.executeQuery(Query);
			while (rs.next()) {
				wins=rs.getInt("COUNT");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return wins;
	}
	
	//a method that returns the average draws per game
	public double aveDrawsPerGame() {
		String Query="SELECT AVG (numberOfDraws) AS average FROM TopTrumps;";
		double ave=0.0;
		try {
			stat=connection.createStatement();
			rs=stat.executeQuery(Query);
			while (rs.next()) {
				ave=rs.getDouble("average");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ave;
	}
	
	//a method that returns the number of rounds of the longest game
	public int longestGame() {
		String Query="SELECT MAX (rounds) AS longest FROM TopTrumps;";
		int longest=0;
		try {
			stat=connection.createStatement();
			rs = stat.executeQuery(Query);
			while (rs.next()) {
				longest=rs.getInt("longest");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return longest;
	}
	
	//a method used to close the database
	public void closeDatabase() {
		try {
			if (!stat.isClosed()){
				stat.close();
			}
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
