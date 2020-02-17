package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import commandline.*;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	
	ArrayList<Double> statisticsForWeb = new ArrayList<Double>();
	Statistics statistics = null;
	Game game = null;
	Controller controller = null;
	Database database = null;
	int numberOfPlayers;
	Player activePlayer = null;
	
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
		this.game = new Game(5,"StarCitizenDeck.txt");
		this.controller=new Controller(game);
		this.game.resetGame(5);
		this.game.deal();
		this.statistics = controller.getStatistics();
		activePlayer = game.getRoundWinner();
	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello "+Word;
	}
	
	@GET
	@Path("/showStatistics")
	/**
	 * This method is used to retrive the statistics value, add them to an ArrayList object 
	 * and send them back to the Statistics.ftl to be used in the getStatistics() JavaScript 
	 * which creates and displays the statistics in the Web Application. 
	 * @return - An ArrayList with statistics
	 * @throws IOException
	 * */
	public ArrayList<Double> getStatisticsForWeb() throws IOException{
		statisticsForWeb.clear();
		statistics.stats();
		statisticsForWeb.add((double) statistics.getTotalGames());
		statisticsForWeb.add((double) statistics.getHumanWins());
		statisticsForWeb.add((double) statistics.getComWins());
		statisticsForWeb.add((double) statistics.getLongestGame());
		statisticsForWeb.add( statistics.getAverageDraws());
		return statisticsForWeb;
	}
	
	
	@GET
	@Path("/resetGameWebApp")
	/**
	 * This method is used to reset the game to the wanted number of players. It gets the parameter from the 
	 * JavaScript in the web browser and redo the whole initialization of the game to be played. 
	 * @return - A String containing the number of players with which the game was reset.
	 * @throws IOException
	 * */
	public String resetGameWebApp(@QueryParam("numberOfPlayers") int numberOfPlayers) throws IOException{
		this.numberOfPlayers = numberOfPlayers;//sets the number of players in the game
		game = new Game(numberOfPlayers,"StarCitizenDeck.txt");// reinitialize the game
		controller=new Controller(game);//reinitialize the controller
		game.resetGame(numberOfPlayers);//resets the game with the number of players
		game.deal();//the game deals the cards among the players
		activePlayer = game.getRoundWinner();//the active player is set initially
		statistics = controller.getStatistics();//the reference to the statistics object is created
		return "The game was reset with " + numberOfPlayers + " players";//returns a message
	}
	
	
	@GET
	@Path("/extractCard")
	/** 
	 * This method is used to get the top card of one player represented by playerIndex variable 
	 * and run the method to state who's still an active player.
	 * @return - A string containing the top card object of a specific player
	 * @throws IOException
	 * */
	public String getCard(@QueryParam("playerIndex") int playerIndex) throws IOException{
		game.loserCheck();
		return oWriter.writeValueAsString(game.getPlayers().get(playerIndex).getDeck().get(0));
	}
	
	@GET
	@Path("/playLikeHuman")
	/**
	 * This method is used to play a round with a specific category chosen. 
	 * @return - a String message to be displayed in the message box
	 * @throws IOException
	 *  */
	public String playRoundHuman(@QueryParam("selectedCategoryByHuman") int selectedCategoryByHuman) throws IOException{
		String messageWithWinner;//message to be displayed in the message box in the WebApp
		
		//if the round winner is human call next round to increment the number of rounds
		if(game.getRoundWinner().isHuman()) {
			game.nextRound();
		}
		
		game.setChosenCategory(selectedCategoryByHuman);//sets the preferred category to be played with
		
		game.compareCards();//compare the cards in the game and sets the winner
		
		game.loserCheck();//check which players still have cards
		
		//when the game is finished, update the statistics
		if(game.hasGameEnded()) {
			statistics.updateStats();
		}
		
		messageWithWinner = "The selected category was " + game.getRoundWinner().getDeck().get(0).getCategoryName(selectedCategoryByHuman) + "! <br>";
		
		if(!game.isDraw()) {
			messageWithWinner += "Round " + game.getNumberOfRounds() + ": " + game.getRoundWinner().getName() +" won this round.";
		}else 
			messageWithWinner += "Round " + game.getNumberOfRounds() + ": It's a draw. There are " + game.getCommunalPile().size() + " cards in the communal pile";
		
		return messageWithWinner;// return the message with the winner and selected category
	}
	
	@GET
	@Path("/getActivePlayer")
	/** 
	 * This method is used to retrieve the active player (round winner) and send it to the WebApp
	 * @return - a String containing the object of the active player (round winner)
	 * @throws IOException
	 * */
	public String getActivePlayer() throws IOException{
		activePlayer = game.getRoundWinner();
		return oWriter.writeValueAsString(activePlayer);
	}
	
	@GET
	@Path("/AIPlay")
	/**
	 * This method is used when the active player is an AI player to retrieve its chosen category and play with it 
	 * by calling the playRoundHuman method. 
	 * @return - A String containing the message with the winner and category chosen by the AI. 
	 * @throws IOException
	 *  */
	public String playRoundAI() throws IOException {
		game.nextRound();		
		return oWriter.writeValueAsString(playRoundHuman(game.getChosenCategory()));
	}
	
	@GET
	@Path("/getAllCards")
	/** 
	 * This method is used to retrieve the value of each players deck size left.
	 * @return - a String containing the array with the deck sizes values
	 * @throws IOException
	 * */
	public String getDeckSize() throws IOException{
		int[] deckSizes = new int[numberOfPlayers];
		for(int i=0; i < deckSizes.length; i++) {
			deckSizes[i] = game.getPlayers().get(i).getDeck().size();
		}
		return oWriter.writeValueAsString(deckSizes);
	}
	
	@GET
	@Path("/getCommunalPileSize")
	/** 
	 * This method is used to send to the WebApp the size of the communal pile
	 * @return - a String containing the size of the communal pile
	 * @throws IOException 
	 * */
	public String getCommunalPileSize() throws IOException{
		return oWriter.writeValueAsString(game.getCommunalPile().size());
	}
	
	
	@GET
	@Path("/getWinner")
	/** 
	 * This method is used to retrieve the winner of the whole game and send it
	 * to the WebApp.
	 * @return - a String containing the object of the game winner
	 * @throws IOException
	 * */
	public String getWinner() throws IOException{
		return oWriter.writeValueAsString(game.getGameWinner());
	}
	
	@GET
	@Path("/resetStatistics")
	/** 
	 * This method is used in the statistics screen to reset the statistics in the database
	 * @return - a String which contains a message proof that the method was run
	 * @throws IOException
	 * */
	public String resetStatistics() throws IOException{
		statistics.resetStats();
		return "resetStatistics method has been successfully ran!";
	}
}
