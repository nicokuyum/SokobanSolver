package ar.edu.itba.sia.tp1;

import java.util.ArrayList;

public class Map {
	
	public ArrayList<ArrayList<Terrain>> map;
	private int height;
	private int width;
	
	public Map(ArrayList<ArrayList<Terrain>> map){
		this.map = map;
		this.height = map.size();
		this.width = map.get(0).size();
		if(height <=0 || width <= 0)
			throw new IllegalArgumentException("Illegal map size parameters");
	}
	
	public Terrain getPosition(int x, int y){
		if(x>=width || x<0 || y>=height || y<0)
			return null;
		return map.get(x).get(y);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
}
