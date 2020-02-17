package commandline;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class LogFile {
	
	//attributes
	private String fileName; 
	private String dashes;
	
	//constructor
	public LogFile(String fileName) {
		this.fileName=fileName+".txt";
		dashes="\n----------\n";
		try (BufferedWriter out = new BufferedWriter(new FileWriter(this.fileName))) {
			out.write("----------------------------\n");
			out.write("---------  Log File  -------\n");
			out.write("----------------------------\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//initialization of LogFile
	public void writeLogFile(String toWrite) {
		try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true))) {
			out.write(toWrite);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//a method to write the initial deck before the cards are shuffled
	public void writeInitialDeck(ArrayList<Card> deck) {
		StringBuilder toWrite=new StringBuilder();
		toWrite.append(dashes);
		toWrite.append("INITIAL DECK: \n");
		toWrite.append(dashes);
		ArrayList<Card>initialDeck=deck;
		for (Card card:initialDeck) {
			toWrite.append(card.toString()+"\n\n");
		}
		toWrite.append(dashes);
		writeLogFile(toWrite.toString());
	}
	
	//a method to write every player's deck
	public void writePlayersDecks(ArrayList<Player> players) {
		StringBuilder toWrite=new StringBuilder();
		toWrite.append(dashes);
		toWrite.append("PLAYERS DECK: \n");
		toWrite.append(dashes);
		for (Player player : players) {
			if (player.isActive()) {
				toWrite.append("Player: "+player.getName()+"\n");
				for (int i=0;i<player.getDeck().size();i++) {
					toWrite.append(player.getDeck().get(i).getName()+"\n");
				}
				toWrite.append(dashes);
			}
		}
		writeLogFile(toWrite.toString());
	}
	
	//a method to write the cards in the communal pile
	public void writeCommunalPile(ArrayList<Card> communalPile) {
		StringBuilder toWrite=new StringBuilder();
		toWrite.append(dashes);
		toWrite.append("COMMUNAL PILE: \n");
		toWrite.append(dashes);
		for (Card card : communalPile) {
			toWrite.append("\n"+card.getName());
		}
		toWrite.append(dashes);
		writeLogFile(toWrite.toString());
	}
	
	//a method to write the cards that are down for every round
	public void writeHand(Card[] roundCards) {
		StringBuilder toWrite=new StringBuilder();
		toWrite.append(dashes);
		toWrite.append("HAND: \n");
		toWrite.append(dashes);
		for (int i=0;i<roundCards.length;i++) {
			if (roundCards[i] instanceof Card) {
				toWrite.append("\n"+roundCards[i].getName());
			}
		}
		toWrite.append(dashes);
		writeLogFile(toWrite.toString());
	}
	
	//a method to write the category that was chosen
	public void writeCategory(int category, ArrayList<Player> players) {
		StringBuilder toWrite=new StringBuilder();
		toWrite.append(dashes);
		toWrite.append("CATEGORY: \n");
		toWrite.append(dashes);
		toWrite.append("   1: Size\r\n" + 
				"   2: Speed\r\n" + 
				"   3: Range\r\n" + 
				"   4: Firepower\r\n" + 
				"   5: Cargo\r\n");
		toWrite.append("Chosen Category: "+(category+1)+"\n");
		for (Player player : players) {
			toWrite.append("Player: "+player.getName()+"\n");
			if (player.isActive()) {
				toWrite.append("Card Value: "+player.getDeck().get(0).getDescription()[category]);
			}
		toWrite.append(dashes);
		}
		writeLogFile(toWrite.toString());
	}
	
	//a method to write the overall winner of the game
	public void writeWinner (Player winner) {
		StringBuilder toWrite=new StringBuilder();
		toWrite.append(dashes);
		toWrite.append("WINNER: \n");
		toWrite.append(dashes);
		toWrite.append(winner.getName());
		writeLogFile(toWrite.toString());
	}
}
