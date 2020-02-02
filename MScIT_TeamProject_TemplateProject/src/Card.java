public class Card {
	private String name;
	private int[] description;

	public Card(String name,int size,int speed, int range,int firepower,int cargo) {
		this.name=name;
		this.description=new int[] {size, speed, range, firepower, cargo};
	}

	public int[] getDescription() {
		return description;
	}
	public String getName() {
		return name;
	}
	public String toString() {
		String s="'"+ name + "':\n Size: "+description[0]+
				"\n Speed: "+description[1]+
				"\n Range: "+description[2]+
				"\n Firepower: "+description[3]+
				"\n Cargo: "+ description[4];

		return s;
	}
}