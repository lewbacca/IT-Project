package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String IP= "jdbc:postgresql://52.24.215.108/EasternHemisandSemis";
	private static final String username="EasternHemisandSemis";
	private static final String password="EasternHemisandSemis";
	private Connection connection=null;
	private Statement stat=null;
	private ResultSet rs=null;
	
	private int gameNumber=0;
	private int numberOfDraws,rounds;
	private boolean humanWin;
	private Game game;
	
	public Database(Game game) {
		this.game=game;
	}
	
	public void connectDatabase() {
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
		createTable();
	}
	
	public void createTable() {
		try {
			stat=connection.createStatement();
			String sqlCreate= "CREATE TABLE IF NOT EXISTS TopTrumps(gameNo int, humanWin boolean, numberOfDraws int,rounds int)";
			stat.executeUpdate(sqlCreate);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateDatabase() {
		gameNumber++;
		humanWin=game.getHumanPlayer().isWinner();
		numberOfDraws=game.getNumberOfDraws();
		rounds=game.getNumberOfRounds();
		if (connection!=null) {
			try {
				String sqlUpdate="INSERT INTO TopTrumps(gameNo,humanWin,numberOfDraws,rounds) VALUES ("+gameNumber+","+humanWin+","+numberOfDraws+","+rounds+")";
				stat.executeUpdate(sqlUpdate);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Error! Can not update Database");
		}
	}
	
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