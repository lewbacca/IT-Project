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
	public int geteight() {
		return height;
	}
	public int weight() {
		return weight;
	}
	public int length() {
		return length;
	}
	public int ferocity() {
		return ferocity;
	}
	public int intelligence() {
		return intelligence;
	}
	
}