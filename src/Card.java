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
		String s="'"+ name + "':+/n"; //needs finished, same names as the ints in the constructor, same order too, see CLI demo for reference
		
		return s;
	}
}