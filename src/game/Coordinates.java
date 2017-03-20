package ar.edu.itba.sia.tp1;

public class Coordinates {
	
	private int x;
	private int y;
	
	public Coordinates(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void addX(int amount){
		x += amount;
	}
	
	public void addY(int amount){
		y += amount;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
