package sokoban.rules;

import java.util.Optional;

import gps.api.GPSRule;
import gps.api.GPSState;
import sokoban.MOVE;
import sokoban.SokobanState;

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
		if (!canMove(MOVE.LEFT, s)) {
			return Optional.empty();
		}
		if (nextToBox(MOVE.LEFT, s)) {
			if (moveToDeadlock(MOVE.LEFT, s)) {
				return Optional.empty();
			}
			next = movePlayerWithBox(MOVE.LEFT, s);
		} else {
			next = movePlayer(MOVE.LEFT, s);
		}
		return Optional.of(next);
	}


}
