package gps;

import gps.api.GPSProblem;
import sokoban.SokobanProblem;

import java.util.Comparator;

/**
 * Created by lucas on 26/03/17.
 */
public class GreedyComparator implements Comparator<GPSNode> {
    GPSProblem problem;

    public GreedyComparator(GPSProblem p){
        problem = p;
    }
    @Override
    public int compare(GPSNode o1, GPSNode o2) {
        return problem.getHValue(o1.getState()) - problem.getHValue(o2.getState());
    }
}
