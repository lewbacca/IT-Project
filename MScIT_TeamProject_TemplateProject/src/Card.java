public class Card {
	public String description;
	public int size,speed,range,firepower,cargo;
	public Card(String d,int s,int sp,int r,int f,int c) {
	description =  d;
	size = s;
	speed = sp;
	range = r;
	firepower = f;
	cargo = c;
	}
	public String getDescription() {
		return description;
	}
	public int getSize() {
		return size;
	}
	public int getSpeed() {
		return speed;
	}
	public int getRange() {
		return range;
	}
	public int getFirepower() {
		return firepower;
	}
	public int getCargo() {
		return cargo;
	}
public String toString() 
    { 
        return "\nDescription:" + description + "\nSize:" + size + "\nSpeed:" + speed + "\nRange:" + range + "\nFirepower:" + firepower + "\nCargo:" + cargo; 
    }
}