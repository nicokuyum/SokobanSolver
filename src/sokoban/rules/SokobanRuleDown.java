package sokoban.rules;

import java.util.Optional;

import gps.api.GPSRule;
import gps.api.GPSState;
import sokoban.MOVE;
import sokoban.SokobanState;

public class SokobanRuleDown extends Moveable implements GPSRule {

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
		if (!canMove(MOVE.DOWN, s)) {
			return Optional.empty();
		}
		if (nextToBox(MOVE.DOWN, s)) {
			if (moveToDeadlock(MOVE.DOWN, s)) {
				return Optional.empty();
			}
			next = movePlayerWithBox(MOVE.DOWN, s);
		} else {
			next = movePlayer(MOVE.DOWN, s);
		}
		return Optional.of(next);
	}

}
