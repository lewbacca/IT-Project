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
	
//	int[] statisticsForWeb = new int[10];
	ArrayList<Integer> statisticsForWeb = new ArrayList<Integer>();
	Statistics statistics = null;
	Game game = null;
	Controller controller = null;
	Database database = null;
	int numberOfPlayers;
//	TopTrumpsCLIApplication topTrumpsCLI;
	Player activePlayer = null;
	
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
		this.game = new Game(5,"StarCitizenDeck.txt");
		this.controller=new Controller(game);
		this.game.resetGame(5);
		this.game.deal();
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
	public ArrayList<Integer> getStatisticsForWeb() throws IOException{
		statisticsForWeb.add(statistics.getTotalGames());
		statisticsForWeb.add(statistics.getHumanWins());
		statisticsForWeb.add(statistics.getComWins());
		statisticsForWeb.add(statistics.getLongestGame());
		statisticsForWeb.add((int) statistics.getAverageDraws());
		
		return statisticsForWeb;
		
//		StatisticsForWeb.add(commandline.Statistics.getTotalGames());
		
//		statisticsForWeb[1] = statistics.getHumanWins();
//		statisticsForWeb[2] = statistics.getComWins();
//		statisticsForWeb[3] = statistics.getLongestGame();
//		statisticsForWeb[4] = (int) statistics.getAverageDraws();
		
		
	}
	
	
	@GET
	@Path("/resetGameWebApp")
	public String resetGameWebApp(@QueryParam("numberOfPlayers") int numberOfPlayers) throws IOException{
		this.numberOfPlayers = numberOfPlayers;
		game = new Game(numberOfPlayers,"StarCitizenDeck.txt");
		controller=new Controller(game);
		game.resetGame(numberOfPlayers);
		game.deal();
		activePlayer = game.getRoundWinner();
		return "The game was reset with " + numberOfPlayers + " players";
	}
	
	
	
//	@GET
//	@Path("/game")
//	public String initializeGame(@QueryParam("numberOfPlayers") int numberOfPlayers) throws IOException{
//		topTrumpsCLI = new TopTrumpsCLIApplication();
//		this.game = new Game(5,"StarCitizenDeck.txt");
//		this.controller=new Controller(game);
//		this.game.resetGame(5);
//		this.game.deal();
//		controller.play();
//		this.game = new Game(numberOfPlayers,"StarCitizenDeck.txt");
//		this.controller=new Controller(game);
//		this.game.resetGame(numberOfPlayers);
//		this.game.deal();
//		activePlayer = game.getRoundWinner();
//		return "The game has been created with " + numberOfPlayers + " players!";
//	}
//	
	@GET
	@Path("/extractCard")
	public String getCard(@QueryParam("playerIndex") int playerIndex) throws IOException{
		game.loserCheck();
		return oWriter.writeValueAsString(game.getPlayers().get(playerIndex).getDeck().get(0));
	}
	
	@GET
	@Path("/playLikeHuman")
	public String playRoundHuman(@QueryParam("selectedCategoryByHuman") int selectedCategoryByHuman) throws IOException{
		String messageWithWinner;
		
		if(game.getRoundWinner().isHuman()) {
			game.nextRound();
		}
		
		game.setChosenCategory(selectedCategoryByHuman);
		
		game.compareCards();
		
		game.loserCheck();
		
		game.hasGameEnded();
		
		if(!game.isDraw()) {
			messageWithWinner = "\n Round " + game.getNumberOfRounds() + ": " + game.getRoundWinner().getName() +" won this round.";
		}else 
			messageWithWinner = "It's a draw. \nThere are " + game.getCommunalPile().size() + " cards in the communal pile";
		return messageWithWinner;
	}
	
	@GET
	@Path("/getActivePlayer")
	public String getActivePlayer() throws IOException{
		activePlayer = game.getRoundWinner();
		return oWriter.writeValueAsString(activePlayer);
	}
	
	@GET
	@Path("/AIPlay")
	public String playRoundAI() throws IOException {
		game.nextRound();
		
//		playRoundHuman(game.getChosenCategory());
		
		return oWriter.writeValueAsString(playRoundHuman(game.getChosenCategory()));
	}
	
	@GET
	@Path("/getAllCards")
	public String getDeckSize() throws IOException{
		int[] deckSizes = new int[numberOfPlayers];
		for(int i=0; i < deckSizes.length; i++) {
			deckSizes[i] = game.getPlayers().get(i).getDeck().size();
		}
		return oWriter.writeValueAsString(deckSizes);
	}
	
	@GET
	@Path("/getCommunalPileSize")
	public String getCommunalPileSize() throws IOException{
		return oWriter.writeValueAsString(game.getCommunalPile().size());
	}
	
	
	@GET
	@Path("/checkPlayersForCards")
	public String checkPlayesrForCards() throws IOException{
		boolean[] playerHasCardsLeft = new boolean[5];
		for(int i=0; i < playerHasCardsLeft.length; i++) {
			playerHasCardsLeft[i] = game.getPlayers().get(i).isActive();
		}
		return oWriter.writeValueAsString(playerHasCardsLeft);
	}
	
	@GET
	@Path("/getWinner")
	public String getWinner() throws IOException{
		
		
		return oWriter.writeValueAsString(game.getGameWinner());
	}
	
}
