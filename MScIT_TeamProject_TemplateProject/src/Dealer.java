import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
 
public class Dealer
{
		private Game game;
		private String[] description = new String[40];
		private String[] cards = new String[40];
		private String s;
		private int[] size = new int[40];
		private int[] speed = new int[40];
		private int[] range = new int[40];
		private int[] firepower = new int[40];
		private int[] cargo = new int[40];
		private	Card[] cardsObjects = new Card[40];
		private	ArrayList<Card> deck = new ArrayList<Card>();
		
        //Constructor
		public Dealer(Game game) {
			this.game = game;
		}
		
		//String filePath = "StarCitizenDeck.txt";
		public void createCards(String filePath) {
			s = convertString( filePath );
			cards = s.split("\\s");
			cardAttributes();
		}
		
		// Storing card attributes to their respective arrays
		public void cardAttributes() {
			int j=0;
		
			for(int i=6;i<cards.length;i++) {
				description[j] = cards[i];
				size[j] = Integer.parseInt(cards[++i]);
				speed[j] = Integer.parseInt(cards[++i]);
				range[j] = Integer.parseInt(cards[++i]);
				firepower[j] = Integer.parseInt(cards[++i]);
				cargo[j] = Integer.parseInt(cards[++i]);
				j++;
			}
			
			for(int i=0;i<cardsObjects.length;i++) 
				cardsObjects[i] = new Card(description[i],size[i],speed[i],range[i],firepower[i],cargo[i]);
			
			for(int i=0;i<cardsObjects.length;i++)
				deck.add(cardsObjects[i]);
		}
			
		// dealCards(): Distributes Cards
		
	
		//Read file content into string with - Files.readAllBytes(Path path)
		private static String convertString(String filePath) 
		{
			String content = "";
        	try
        	{
            		content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        	} 
        	catch (IOException e) 
        	{
            		e.printStackTrace();
        	}
        		return content;
		}

	
	public void dealCards(){ //takes deck and distributes the cards randomly to the players, 1 random card at a time for player 1 to 5  
		while (!deck.isEmpty())
		for (int i=0;i<game.getPlayers().size();i++) {
			Random randomNumber= new Random();
			int random=randomNumber.nextInt(deck.size());
			game.getPlayers().get(i).addCardToDeck(deck.get(random));
			this.deck.remove(random);
			if (deck.isEmpty()) {
				break;
			}
		}
	}
	

	public ArrayList<Card> getDeck() {
		return deck;
	}
	public int getDeckSize() {
		return deck.size();
	}
}

