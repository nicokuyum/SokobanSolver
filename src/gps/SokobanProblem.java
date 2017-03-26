package gps;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;
import rules.SokobanRuleDown;
import rules.SokobanRuleLeft;
import rules.SokobanRuleRight;
import rules.SokobanRuleUp;

public class SokobanProblem implements GPSProblem {
	
	List<GPSRule> rules = new ArrayList<>();
	GPSState st;
	GPSState fin;
	
	public SokobanProblem(GPSState s){
		st = s;
		setRules();
	}

	private void setRules(){
		rules.add(new SokobanRuleDown());
		rules.add(new SokobanRuleLeft());
		rules.add(new SokobanRuleRight());
		rules.add(new SokobanRuleUp());
	}
	@Override
	public GPSState getInitState() {
		return st;
	}

	@Override
	public boolean isGoal(GPSState state) {
		SokobanState s = (SokobanState) state;
		return s.hasWon();
	}

	@Override
	public List<GPSRule> getRules() {
		return rules;
	}

	@Override
	public Integer getHValue(GPSState state) {
		SokobanState s = (SokobanState) state;
		for(Point p : s.boxes){
			if((s.board[p.x][p.y] & TILE.DEADLOCK.getValue()) != 0){
				return Integer.MAX_VALUE; //CASO DONDE UNA CAJA ESTA MUERTA POR ESTAR CONTRA PAREDES
			}
		}
		return null;
	}

}
