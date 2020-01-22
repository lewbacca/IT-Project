import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	public void connect() {
	/*import and load*/
		try {
			Class.forName("org.postgresql.Driver");
			}catch (Exception e) {
				System.out.println("Could not load class org.postgresql.Driver");
				e.printStackTrace();
				return;
			}
			System.out.println("PostgreSQL JDBC Driver found!");
			
			/*Setup Connection*/
			String IP= "jdbc:postgresql://localhost:5432/";
			String username="postgres";
			String password="AbliC2!ur";
			
			Connection connection=null;
			try {
				connection=DriverManager.getConnection(IP,username,password);
			}catch(SQLException e) {
				System.out.println("Connection Failed!");
				e.printStackTrace();
			}
			if (connection!=null) {
				try {
						System.out.println("Connecting to database....");
						Statement stat=connection.createStatement();
						String sqlUpdate= "CREATE TABLE IF NOT EXISTS TopTrumps(gameNo int, humanWin boolean, numberOfDraws int,rounds int)";
						stat.executeUpdate(sqlUpdate);
						connection.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("Failed to establish connection!");
			}
	
	}
}
