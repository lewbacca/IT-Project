package commandline;

public class Card {
	private String name;
	private int[] description;

	public Card(String name,int size,int speed, int range,int firepower,int cargo) { //constructor
		this.name=name;
		description=new int[] {size, speed, range, firepower, cargo};
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
	/*
	 * returns a string for the name of the particular category
	 */
	public String getCategoryName(int i) {
		if (i==0) {
			return "Size";
		}else if(i==1) {
			return "Speed";
		}else if (i==2) {
			return "Range";
		}else if (i==3) {
			return "Firepower";
		}else if(i==4){
			return "Cargo";
		}else {
			return "Category Not Found!";
		}
	}
}