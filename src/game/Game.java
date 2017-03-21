package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	private Scanner s = new Scanner(System.in);

	private int moves;
	private int boxesMoved;
	private Coordinates initialPos;
	private Coordinates playerPosition;
	private Map map;
	private int boxes;
	private int boxesCompleted;
	
	public Game(ArrayList<ArrayList<Terrain>> map, Coordinates initialPosition){
		moves = 0;
		boxesMoved = 0;
		boxesCompleted = 0;
		this.initialPos = initialPosition;
		playerPosition = initialPos;
		this.map = new Map(map);
		boxes = countBoxes();
	}
	
	public void restartGame(){
		moves = 0;
		boxesMoved = 0;
		boxesCompleted = 0;
		playerPosition = initialPos;
	}
	
	public void runGame(){
		Output.print(map, playerPosition);
		while(!hasWon()){
			int input = getInput();
			boolean change = processInput(input);
			if(change)
				Output.print(map, playerPosition);
		}
		System.out.println("Congratulations you win!!");
	}
	
	// 1-Left | 2-Up | 3-Right | 4-Down
	private boolean processInput(int input) {
		switch(input){
			case 1: return moveLeft();
			case 2: return moveUp();
			case 3: return moveRight();
			case 4: return moveDown();
			default: return false;
		}
	}

	// Assumes int between 1 and 4;
	private int getInput() {
		int n = 0;
		do{
			n = s.nextInt();
		}while(n<1 || n>4);
		return n;
	}

	private boolean hasWon(){
		return boxesCompleted >= boxes;
	}
	
	/**
	 * Issues the command to move in the specified direction
	 * @param movX Amount to move in x-axis
	 * @param movY Amount to move in x-axis
	 * @return If the move could be completed successfully
	 */
	public boolean move(int movX, int movY){
		Terrain destination = map.getPosition(playerPosition.getX()+movX,playerPosition.getY()+movY);
		if(destination==null ||!destination.canMoveThrough()){
			return false;
		}
		if(destination.hasBox()){
			int targetX = playerPosition.getX()+2*movX;
			int targetY = playerPosition.getY()+2*movY;
			Terrain beyondDestination = map.getPosition(targetX, targetY);
			if(beyondDestination==null || !beyondDestination.canMoveThrough() || beyondDestination.hasBox()){
				return false;
			}else{
				moveBox(destination, beyondDestination);
			}
		}
		playerPosition.addX(movX);
		playerPosition.addY(movY);
		moves++;
		return true;
	}
	
	/**
	 * Moves a box between two terrains (assumes origin has a box and destination doesn't and both are empty terrains)
	 * @param origin The terrain losing the box
	 * @param destination The terrain where the box is moved
	 */
	public void moveBox(Terrain origin, Terrain destination){
		boxesMoved++;
		if(origin.isDestination())
			boxesCompleted--;
		origin.setHasBox(false);
		if(destination.isDestination())
			boxesCompleted++;
		destination.setHasBox(true);
	}
	
	public int countBoxes(){
		int count = 0;
		for(int y=0; y<map.getHeight(); y++){
			for(int x=0; x<map.getWidth(); x++){
				if(map.getPosition(x, y).hasBox()){
					count++;
				}
			}
		}
		return count;
	}
	
	public boolean moveUp(){
		return move(0,-1);
	}
	
	public boolean moveDown(){
		return move(0,1);
	}
	
	public boolean moveLeft(){
		return move(-1,0);
	}
	
	public boolean moveRight(){
		return move(1,0);
	}

	public int getBoxesMoved(){
		return boxesMoved;
	}

	public int getMoves(){
		return moves;
	}
	
	
}
