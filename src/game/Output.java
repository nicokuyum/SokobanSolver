package ar.edu.itba.sia.tp1;

public class Output {
	
	private static final String playerSymbol = "P";
	private static final String empty = "O";
	private static final String wall = "W";
	private static final String box = "b";
	private static final String objective = "X";
	private static final String objectiveCompleted = "B";
	
	public static void print(Map map, Coordinates player){
		
		for(int y=0; y<map.getWidth(); y++){
			for(int x=0; x<map.getHeight(); x++){
				String print = empty;
				Terrain t = map.getPosition(x, y);
				if(x==player.getX() && y==player.getY()){
					print=playerSymbol;
				}else if(!t.canMoveThrough()){
					print=wall;
				}else if(t.hasBox()){
					if(t.isDestination())
						print = objectiveCompleted;
					else
						print = box;
				}else if(t.isDestination()){
					print = objective;
				}
				System.out.print(print);
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("---------------");
		System.out.println("");
	}
}
