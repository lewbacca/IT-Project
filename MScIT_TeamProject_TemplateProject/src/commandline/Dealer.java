package commandline;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Dealer
{
	private Game game;
	private String[] description = new String[40];
	private String[] cards = new String[40];
	private String s, filePath;
	private int[] size = new int[40];
	private int[] speed = new int[40];
	private int[] range = new int[40];
	private int[] firepower = new int[40];
	private int[] cargo = new int[40];
	private	ArrayList<Card> deck = new ArrayList<Card>();

	//Constructor
	public Dealer(Game game,String filePath) {
		this.game = game;
		this.filePath=filePath;
	}

/*
 * takes the string version of the txt file and turns it into an array of strings, split by line
 */
	public void createCards() {
		s = convertString( filePath );
		cards = s.split("\\s");
		cardAttributes();
	}

/*
 * takes the array of strings corresponding to lines in the txt file and adds the name of the card to a name array and every attribute to an array for that category
 */
	public void cardAttributes() {
		int j=0;

		for(int i=6;i<cards.length;i++) { //starts at six to skip the first line
			description[j] = cards[i];
			size[j] = Integer.parseInt(cards[++i]);
			speed[j] = Integer.parseInt(cards[++i]);
			range[j] = Integer.parseInt(cards[++i]);
			firepower[j] = Integer.parseInt(cards[++i]);
			cargo[j] = Integer.parseInt(cards[++i]);
			j++;
		}

		for(int i=0;i<description.length;i++) //brings the appropriate attributes and names together to create a card object
			deck.add(new Card(description[i],size[i],speed[i],range[i],firepower[i],cargo[i])); //adds to the deck

	


	}

/*
 * takes the txt file and turns it into a string
 */

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
/*
 * takes deck and distributes the cards randomly to the players, 1 random card at a time for player 1 to however many players we have
 */

	public void dealCards(){  
		while (!deck.isEmpty()) 
			for (int i=0;i<game.getPlayers().size();i++) {
				Random randomNumber= new Random(); 
				int random=randomNumber.nextInt(deck.size()); //picks a random card based on the deck size
				game.getPlayers().get(i).addCardToDeck(deck.get(random)); //adds that card to a player's deck
				this.deck.remove(random); //removes it from this deck
				if (deck.isEmpty()) { //if the deck finishes in the middle of a loop, break the loop
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
