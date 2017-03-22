package gps;

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
	public SokobanProblem(GPSState s, GPSState f){
		st = s;
		fin = f;
	}
	public SokobanProblem(int[][] s, int[][] f){
		st = new SokobanState(s);
		fin = new SokobanState(f);
	}
	private void setRules(){
		rules.add(new SokobanRuleDown());
		rules.add(new SokobanRuleLeft());
		rules.add(new SokobanRuleRight());
		rules.add(new SokobanRuleUp());
	}
	@Override
	public GPSState getInitState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGoal(GPSState state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<GPSRule> getRules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getHValue(GPSState state) {
		// TODO Auto-generated method stub
		return null;
	}

}
