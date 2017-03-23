package rules;

import gps.MOVE;
import gps.SokobanState;
import gps.TILE;
import gps.api.GPSState;

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
		return ((s.getBoard()[(int)s.getPlayerPos().getX() + m.getX()][(int)s.getPlayerPos().getY() + m.getY()] & TILE.BOX.getValue()) != 0);
	}
	
	public GPSState movePlayer(MOVE m, SokobanState s){
		SokobanState next = new SokobanState(s.getBoard(),s.getPlayerPos(),s.getBoxes());
		next.getBoard()[(int)s.getPlayerPos().getX()+m.getX()][(int)s.getPlayerPos().getY()+m.getY()] = next.getBoard()[(int)s.getPlayerPos().getX() + m.getY()][(int)s.getPlayerPos().getY() + m.getY()] | TILE.PLAYER.getValue();
		next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] = next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] & TILE.PLAYER.getValue();
		next.movePlayer(m);
		return next;
	}
	
	public GPSState movePlayerWithBox(MOVE m, SokobanState s){
		SokobanState next = new SokobanState(s.getBoard(),s.getPlayerPos(),s.getBoxes());
		next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()+2] = next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()+2] | TILE.BOX.getValue();
		next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()+1] = (next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()+1] | TILE.PLAYER.getValue()) & TILE.BOX.getValue();
		next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] = next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] & TILE.PLAYER.getValue();
		next.movePlayer(m);
		next.moveBox(m);
		return next;
	}
}
