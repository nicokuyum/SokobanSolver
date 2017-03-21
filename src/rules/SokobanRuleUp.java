package rules;

import java.util.Optional;

import gps.SokobanState;
import gps.api.GPSRule;
import gps.api.GPSState;

public class SokobanRuleUp implements GPSRule {

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
		SokobanState next = new SokobanState(s.getBoard(),s.getPlayerPos());
		if(s.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()-1] == 'x'){
			return Optional.empty();
		}
		if(s.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()-1] == 'b' 
				&& (s.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()-2] == 'x' 
				|| s.getBoard()[(int)s.getPlayerPos().getX() ][(int)s.getPlayerPos().getY()-2] == 'b')){
			return Optional.empty();
		}
		if(s.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()-1] == 'b'){
			next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()-2] = 'b';
			next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()-1] = 'p';
			next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] = 'o';
		}else{
			next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()-1] = 'p';
			next.getBoard()[(int)s.getPlayerPos().getX()][(int)s.getPlayerPos().getY()] = 'o';
		}
		return Optional.of(next);
	}

}
