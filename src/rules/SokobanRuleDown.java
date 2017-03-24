package rules;

import java.util.Optional;

import gps.MOVE;
import gps.SokobanState;
import gps.TILE;
import gps.api.GPSRule;
import gps.api.GPSState;

public class SokobanRuleDown extends Moveable implements GPSRule{

	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "Move Player Down";
	}

	@Override
	public Optional<GPSState> evalRule(GPSState state) {
		SokobanState s = (SokobanState) state;
		SokobanState next = new SokobanState(s);
		if(!canMove(MOVE.DOWN,s)){
			return Optional.empty();
		}
		if((s.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()+1] & TILE.BOX.getValue()) != 0){
			next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()+2] = next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()+2] | TILE.BOX.getValue();
			next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()+1] = (next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()+1] | TILE.PLAYER.getValue()) & TILE.BOX.getValue();
			next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] = next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] & TILE.PLAYER.getValue();
		}else{
			next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()+1] = next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()+1] | TILE.PLAYER.getValue();
			next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] = next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] & TILE.PLAYER.getValue();
		}
		return Optional.of(next);
	}

}
