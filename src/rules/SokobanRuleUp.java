package rules;

import java.util.Optional;

import gps.MOVE;
import gps.SokobanState;
import gps.TILE;
import gps.api.GPSRule;
import gps.api.GPSState;

public class SokobanRuleUp extends Moveable implements GPSRule {

	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "Move Player Up";
	}

	@Override
	public Optional<GPSState> evalRule(GPSState state) {
		SokobanState s = (SokobanState) state;
		GPSState next;
		if(!canMove(MOVE.UP,s)){
			return Optional.empty();
		}
		if(nextToBox(MOVE.UP, s)){
			next = movePlayerWithBox(MOVE.UP, s);
		}else{
			next = movePlayer(MOVE.UP, s);
		}
		return Optional.of(next);
	}
}
