package rules;

import java.util.Optional;

import gps.SokobanState;
import gps.api.GPSRule;
import gps.api.GPSState;

public class SokobanRuleRight implements GPSRule{

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
		SokobanState next = new SokobanState(s.getBoard(),s.getPlayerPos());
		if(s.getBoard()[(int)s.getPlayerPos().getX() + 1][(int)s.getPlayerPos().getY()] == 'x'){
			return Optional.empty();
		}
		if(s.getBoard()[(int)s.getPlayerPos().getX() + 1][(int)s.getPlayerPos().getY()] == 'b' 
				&& (s.getBoard()[(int)s.getPlayerPos().getX() + 2][(int)s.getPlayerPos().getY()] == 'x' 
				|| s.getBoard()[(int)s.getPlayerPos().getX() + 2][(int)s.getPlayerPos().getY()] == 'b')){
			return Optional.empty();
		}
		if(s.getBoard()[(int)s.getPlayerPos().getX() + 1][(int)s.getPlayerPos().getY()] == 'b'){
			next.getBoard()[(int)s.getPlayerPos().getX() + 2][(int)s.getPlayerPos().getY()] = 'b';
			next.getBoard()[(int)s.getPlayerPos().getX() + 1][(int)s.getPlayerPos().getY()] = 'p';
			next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] = 'o';
		}else{
			next.getBoard()[(int)s.getPlayerPos().getX() + 1][(int)s.getPlayerPos().getY()] = 'p';
			next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] = 'o';
		}
		return Optional.of(next);
	}

}
