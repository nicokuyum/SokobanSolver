package rules;

import java.util.Optional;

import gps.SokobanState;
import gps.TILE;
import gps.api.GPSRule;
import gps.api.GPSState;

public class SokobanRuleLeft implements GPSRule {

	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "Move Player Left";
	}

	@Override
	public Optional<GPSState> evalRule(GPSState state) {
		SokobanState s = (SokobanState) state;
		SokobanState next = new SokobanState(s.getBoard(),s.getPlayerPos(),s.getBoxes());
		if((s.getBoard()[(int)s.getPlayerPos().getX() - 1][(int)s.getPlayerPos().getY()] & TILE.WALL.getValue()) != 0){
			return Optional.empty();
		}
		if((s.getBoard()[(int)s.getPlayerPos().getX()-1][(int)s.getPlayerPos().getY()] & TILE.BOX.getValue()) != 0 
				&& ((s.getBoard()[(int)s.getPlayerPos().getX()-2][(int)s.getPlayerPos().getY()] & TILE.WALL.getValue()) != 0  
				|| (s.getBoard()[(int)s.getPlayerPos().getX()-2][(int)s.getPlayerPos().getY()] & TILE.BOX.getValue()) != 0)){
			return Optional.empty();
		}
		if((s.getBoard()[(int)s.getPlayerPos().getX()-1][(int)s.getPlayerPos().getY()] & TILE.BOX.getValue()) != 0){
			next.getBoard()[(int)s.getPlayerPos().getX()-2][(int)s.getPlayerPos().getY()] = next.getBoard()[(int)s.getPlayerPos().getX()-2][(int)s.getPlayerPos().getY()] | TILE.BOX.getValue();
			next.getBoard()[(int)s.getPlayerPos().getX()-1][(int)s.getPlayerPos().getY()] = (next.getBoard()[(int)s.getPlayerPos().getX()-1][(int)s.getPlayerPos().getY()] | TILE.PLAYER.getValue()) & TILE.BOX.getValue();
			next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] = next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] & TILE.PLAYER.getValue();
		}else{
			next.getBoard()[(int)s.getPlayerPos().getX()-1][(int)s.getPlayerPos().getY()] = next.getBoard()[(int)s.getPlayerPos().getX()-1][(int)s.getPlayerPos().getY()] | TILE.PLAYER.getValue();
			next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] = next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] & TILE.PLAYER.getValue();
		}
		return Optional.of(next);
	}
	
	
}
