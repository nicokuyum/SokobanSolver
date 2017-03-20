package ar.edu.itba.sia.tp1;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {

		ArrayList<ArrayList<Terrain>> map = new ArrayList<ArrayList<Terrain>>();
		for(int x=0; x<4; x++){
			ArrayList<Terrain> al = new ArrayList<Terrain>();
			map.add(al);
			for(int y=0; y<4; y++){
				al.add(new EmptyTerrain(y*10+x));
			}
		}
		map.get(0).get(3).setIsDestination(true);
		map.get(1).get(3).setIsDestination(true);
		map.get(2).get(3).setIsDestination(true);
		map.get(3).get(3).setIsDestination(true);
		
		map.get(0).get(1).setHasBox(true);
		map.get(1).get(1).setHasBox(true);
		map.get(2).get(1).setHasBox(true);
		map.get(3).get(1).setHasBox(true);
		
		Game g = new Game(map,new Coordinates(0, 0));
		
		g.runGame();
	}
}
