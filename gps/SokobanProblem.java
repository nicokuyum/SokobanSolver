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
		setRules();
	}
	public SokobanProblem(int[][] s, int[][] f){
		st = new SokobanState(s);
		fin = new SokobanState(f);
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
		return state.equals(fin);
	}

	@Override
	public List<GPSRule> getRules() {
		return rules;
	}

	@Override
	public Integer getHValue(GPSState state) {
		SokobanState s = (SokobanState) state;
		for(int i = 0; i<s.board.length;i++){
			for(int j= 0; j<s.board[0].length;j++){
				if((s.board[i][j] & TILE.BOX.getValue()) != 0){
					if(((s.board[i+1][j] & TILE.WALL.getValue()) != 0 || (s.board[i-1][j] & TILE.WALL.getValue()) != 0) 
							&& ((s.board[i][j+1] & TILE.WALL.getValue()) != 0 || (s.board[i][j-1] & TILE.WALL.getValue()) != 0)
							&& ((s.board[i][j] & TILE.TARGET.getValue()) == 0)){
						return Integer.MAX_VALUE; //CASO DONDE UNA CAJA ESTA MUERTA POR ESTAR CONTRA PAREDES
					}
				}
			}
		}
		return null;
	}

}
