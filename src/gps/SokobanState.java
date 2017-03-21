package gps;

import java.awt.Point;
import java.util.Arrays;

import gps.api.*;

public class SokobanState implements GPSState{
	char[][] board;
	Point playerPos;

	
	public SokobanState(char[][] board2, Point playerPos2) {
		board = new char[board2.length][board2[0].length];
		for(int i = 0; i<board2.length;i++){
			for(int j = 0; j<board2.length;j++){
				board[i][j] = board2[i][j];
			}
		}
	}
	public SokobanState(int sizeX, int sizeY,Point playerPos) {
		board = new char[sizeX][sizeY];
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
	
	public char[][] getBoard(){
		return board;
	}
	
	public Point getPlayerPos(){
		return playerPos;
	}
	
	
}
