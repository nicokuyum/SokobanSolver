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
		GPSState next;
		if(!canMove(MOVE.DOWN,s)){
			return Optional.empty();
		}
		if(nextToBox(MOVE.DOWN,s)){
			next = movePlayerWithBox(MOVE.DOWN,s);
		}else{
			next = movePlayer(MOVE.DOWN,s);
		}
		return Optional.of(next);
	}

}
