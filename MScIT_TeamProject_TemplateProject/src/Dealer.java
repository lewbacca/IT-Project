import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
 
public class Dealer
{
		private Game game;
		private String[] description = new String[50];
		private String[] cards = new String[50];
		private String s;
		private int[] size = new int[50];
		private int[] speed = new int[50];
		private int[] range = new int[50];
		private int[] firepower = new int[50];
		private int[] cargo = new int[50];
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
			
			for(int i=0;i<cardsObjects.length;i++) {
				cardsObjects[i] = new Card(description[i],size[i],speed[i],range[i],firepower[i],cargo[i]);
			}
			
			for(int i=0;i<cardsObjects.length;i++)
				deck.add(cardsObjects[i]);
		}
			
		// dealCards(): Distributes Cards
		public void dealCards() {
			int deckSize = deck.size();
			
			for(int i=0; i<deckSize; i++) {
				
				Random rand = new Random();
				int cardIndex = rand.nextInt(deck.size());
				int numOfPlayers = game.getPlayers().size();
				
				for(int playerIndex=0; playerIndex < numOfPlayers; playerIndex++) {
					game.getPlayers().get(playerIndex).addCardToDeck(deck.get(cardIndex));
					deck.remove(deck.get(cardIndex));
					deckSize--;
				}				
			}
		}
	
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
}