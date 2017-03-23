package rules;

import gps.MOVE;
import gps.SokobanState;
import gps.TILE;

public abstract class Moveable {

	public boolean canMove(MOVE m,SokobanState s){
		if((s.getBoard()[(int)s.getPlayerPos().getX() + m.getX()][(int)s.getPlayerPos().getY() + m.getY()] & TILE.WALL.getValue()) != 0){
			return false;
		}
		if((s.getBoard()[(int)s.getPlayerPos().getX() + m.getX()][(int)s.getPlayerPos().getY()+m.getY()] & TILE.BOX.getValue()) != 0 
				&& ((s.getBoard()[(int)s.getPlayerPos().getX()+m.getX()*2][(int)s.getPlayerPos().getY() + m.getY()*2] & TILE.WALL.getValue()) != 0  
				|| (s.getBoard()[(int)s.getPlayerPos().getX()+ m.getX()*2][(int)s.getPlayerPos().getY() + m.getY()*2] & TILE.BOX.getValue()) != 0)){
			return false;
		}
		return true;
	}
	
	public boolean nextToBox(MOVE m,SokobanState s){
		
	}
	
	public GPSState movePlayer(MOVE m, SokobanState s){
		
	}
	
	public GPSState movePlayerWithBox(MOVE m, SokobanState s){
		
	}
}
