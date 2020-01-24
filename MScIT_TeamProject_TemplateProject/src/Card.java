public class Card {
	public String description;
	public int height,weight,length,ferocity,intelligence;
	public Card(String s,int h,int l,int f,int i) {
	description =  s;
	height = h;
	length = l;
	ferocity = f;
	intelligence = i;
	}
	public String getDescription() {
		return description;
	}
	public int getHeight() {
		return height;
	}
	public int getWeight() {
		return weight;
	}
	public int getLength() {
		return length;
	}
	public int getFerocity() {
		return ferocity;
	}
	public int getIntelligence() {
		return intelligence;
	}
	String filePath = "StarCitizenDeck.txt";
        String s = convertString( filePath );
		String[] cards = s.split("\\s");
		for(int i=0;i<cards.length;i++) {
		System.out.println(cards[i]);
		}
}
