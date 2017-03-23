package rules;

import java.util.Optional;

import gps.MOVE;
import gps.SokobanState;
import gps.TILE;
import gps.api.GPSRule;
import gps.api.GPSState;

public class SokobanRuleLeft extends Moveable implements GPSRule {

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
		GPSState next;
		if(!canMove(MOVE.LEFT,s)){
			return Optional.empty();
		}
		if(nextToBox(MOVE.LEFT, s)){
			next = movePlayerWithBox(MOVE.LEFT, s);
		}else{
			next = movePlayer(MOVE.LEFT, s);
		}
		return Optional.of(next);
	}
	
	
}
