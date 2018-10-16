package sokoban.rules;

import java.util.Optional;

import gps.api.GPSRule;
import gps.api.GPSState;
import sokoban.MOVE;
import sokoban.SokobanState;

public class SokobanRuleRight extends Moveable implements GPSRule {

	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "Move Player Right";
	}

	@Override
	public Optional<GPSState> evalRule(GPSState state) {
		SokobanState s = (SokobanState) state;
		GPSState next;
		if (!canMove(MOVE.RIGHT, s)) {
			return Optional.empty();
		}
		if (nextToBox(MOVE.RIGHT, s)) {
			if (moveToDeadlock(MOVE.RIGHT, s)) {
				return Optional.empty();
			}
			next = movePlayerWithBox(MOVE.RIGHT, s);
		} else {
			next = movePlayer(MOVE.RIGHT, s);
		}
		return Optional.of(next);
	}

}
