package sokoban.rules;

import java.util.Optional;

import gps.api.GPSRule;
import gps.api.GPSState;
import sokoban.MOVE;
import sokoban.SokobanState;

public class SokobanRuleUp extends Moveable implements GPSRule {

	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "Up";
	}

	@Override
	public Optional<GPSState> evalRule(GPSState state) {
		SokobanState s = (SokobanState) state;
		GPSState next;
		if(!canMove(MOVE.UP,s)){
			return Optional.empty();
		}
		if(nextToBox(MOVE.UP,s)){
//			if(moveToDeadlock(MOVE.UP, s)){
//				return Optional.empty();
//			}
			next = movePlayerWithBox(MOVE.UP,s);
		}else{
			next = movePlayer(MOVE.UP,s);
		}
		return Optional.of(next);
	}
}
