public class Card {
	private String description;
	private int height,weight,length,ferocity,intelligence;
	
	public Card(String s,int h,int l,int f,int i) {
	this.description =  s;
	this.height = h;
	this.length = l;
	this.ferocity = f;
	this.intelligence = i;
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

}