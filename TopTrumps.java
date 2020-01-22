import java.sql.*;

import commandline.TopTrumpsCLIApplication;

import online.TopTrumpsOnlineApplication;



public class TopTrumps {

	/** This is the main class for the TopTrumps Applications */
	public static void main(String[] args) {
		
		System.out.println("--------------------");
		System.out.println("--- Top Trumps   ---");
		System.out.println("--------------------");
		
		// command line switches
		boolean onlineMode = false;
		boolean commandLineMode = false;
		boolean printTestLog = false;
		
		// check the command line for what switches are active
		for (String arg : args) {
			
			if (arg.equalsIgnoreCase("-t")) printTestLog=true;
			if (arg.equalsIgnoreCase("-c")) commandLineMode=true;
			if (arg.equalsIgnoreCase("-o")) onlineMode=true;
			
		}
		
		// We cannot run online and command line mode simultaniously
		if (onlineMode && commandLineMode) {
			System.out.println("ERROR: Both online and command line mode selected, select one or the other!");
			System.exit(0);
		}
		
		// Start the appropriate application
		if (onlineMode) {
			// Start the online application
			String[] commandArgs = {"server", "TopTrumps.json"};
			TopTrumpsOnlineApplication.main(commandArgs);
		} else if (commandLineMode) {
			// Start the command line application
			String[] commandArgs = {String.valueOf(printTestLog)};
			TopTrumpsCLIApplication.main(commandArgs);
		}
		
		//server settings
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
		String IP= "jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/m_19_2471955s";
		String username="m_19_2471955s";
		String password="new";
		
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
					connection.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("Failed to establish connection!");
			}
		}
}
