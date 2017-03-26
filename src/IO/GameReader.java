package IO;
import gps.SokobanState;
import gps.TILE;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameReader {
	
	private static Scanner in;

	public static SokobanState open(String file) throws FileNotFoundException{

			in = new Scanner(new java.io.FileReader(file));
			
			int rows = in.nextInt();
			int cols = in.nextInt();
			
			int completedBoxes = 0;
			
			List<Point> boxes = new ArrayList<Point>();
			
			Point playerPos = null;
			
			int[][] matrix = new int[rows][cols];
			
			for(int i = 0 ; i < rows ; i++){		
				for(int j = 0 ; j < cols ; j++){
					int read = in.nextInt();
					matrix[i][j] = read;
					if((read & TILE.BOX.getValue()) != 0){
						boxes.add(new Point(i,j));
						if((read & TILE.TARGET.getValue()) != 0)
							completedBoxes++;
					}else if((read & TILE.PLAYER.getValue())!=0){
						if(playerPos != null){
							throw new IllegalArgumentException("There can't be more than 1 player");
						}
						playerPos = new Point(i,j);
					}
				}
			}
			SokobanState s = new SokobanState(matrix, playerPos, boxes, cols, rows, completedBoxes);
			
			printState(s);
			return s;
			
	}
	
	private static void printState(SokobanState s){
		System.out.println(s.toString());
		System.out.println("Height: " + s.getHeight());
		System.out.println("Width: " + s.getWidth());
	}
	

}

