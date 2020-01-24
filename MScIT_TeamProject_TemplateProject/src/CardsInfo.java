import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
 
public class CardsInfo
{
    public static void main(String[] args) 
    {
		String[] description = new String[41];
		int[] height = new int[41];
		int[] weight = new int[41];
		int[] length = new int[41];
		int[] ferocity = new int[41];
		int[] intelligence = new int[41];
        String filePath = "StarCitizenDeck.txt";
        String s = convertString( filePath );
		String[] cards = s.split("\\s");
		int j=0;
		for(int i=0;i<cards.length;i+=6) {
		description[j] = cards[i];
		//System.out.println(description[j]);
		j++;
		}
		j=0;
		for(int i=7;i<cards.length;i+=6) {
		height[j] = Integer.parseInt(cards[i]);
		System.out.println(height[j]);
		j++;
		}
		j=0;
		for(int i=8;i<cards.length;i+=6) {
		weight[j] = Integer.parseInt(cards[i]);
		j++;
		}
		j=0;
		for(int i=9;i<cards.length;i+=6) {
		length[j] = Integer.parseInt(cards[i]);
		j++;
		}
		j=0;
		for(int i=10;i<cards.length;i+=6) {
		ferocity[j] = Integer.parseInt(cards[i]);
		j++;
		}
		j=0;
		for(int i=11;i<cards.length;i+=6) {
		intelligence[j] = Integer.parseInt(cards[i]);
		System.out.println(intelligence[j]);
		j++;
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
