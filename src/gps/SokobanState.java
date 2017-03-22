package gps;

import java.awt.Point;
import java.util.Arrays;

import gps.api.*;

public class SokobanState implements GPSState{
	int[][] board;
	Point playerPos;

	
	public SokobanState(int[][] board2, Point playerPos2) {
		board = new int[board2.length][board2[0].length];
		for(int i = 0; i<board2.length;i++){
			for(int j = 0; j<board2.length;j++){
				board[i][j] = board2[i][j];
			}
		}
	}
	public SokobanState(int[][] board2) {
		board = new int[board2.length][board2[0].length];
		for(int i = 0; i<board2.length;i++){
			for(int j = 0; j<board2.length;j++){
				board[i][j] = board2[i][j];
				if((board[i][j] & 8) != 0 ){
					playerPos = new Point(i,j);
				}
			}
		}
	}
	
	public SokobanState(int sizeX, int sizeY,Point playerPos) {
		board = new int[sizeX][sizeY];
		this.playerPos = playerPos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SokobanState other = (SokobanState) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}
	
	public int[][] getBoard(){
		return board;
	}
	
	public Point getPlayerPos(){
		return playerPos;
	}
	
	
}
