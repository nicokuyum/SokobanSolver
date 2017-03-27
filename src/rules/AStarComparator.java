package rules;

import java.util.Comparator;

import gps.GPSNode;
import gps.api.GPSProblem;

public class AStarComparator implements Comparator<GPSNode>{

	GPSProblem problem;
	public AStarComparator(GPSProblem problem){
		this.problem = problem;
	}
	@Override
	public int compare(GPSNode n1, GPSNode n2) {
		return (n1.getCost() + problem.getHValue(n1.getState())) - (n2.getCost() + problem.getHValue(n2.getState()));
	}
	

}
