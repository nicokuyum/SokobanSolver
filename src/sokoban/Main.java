package sokoban;

import gps.GPSEngine;
import gps.SearchStrategy;
import gps.api.GPSProblem;
import gps.api.GPSState;
import sokoban.IO.GameReader;
import sokoban.IO.GraphicManager;

import java.io.FileNotFoundException;

/**
 * Created by lucas on 26/03/17.
 */
public class Main {

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		boolean visual;
		try {
			SearchStrategy ss = SearchStrategy.IDDFS;
			System.out.println("Starting...");
			GPSState s = null;
			if (args.length == 0) {
				System.err.println("No board file selected");
			} else {
				s = GameReader.open(args[0]);
			}
			if (args.length == 1) {
				System.out.println("No Search Strategy Selected, default is BFS");
			} else {
				if (args[1].equals(SearchStrategy.BFS.toString())) {
					ss = SearchStrategy.BFS;
				} else if (args[1].equals(SearchStrategy.DFS.toString())) {
					ss = SearchStrategy.DFS;
				} else if (args[1].equals(SearchStrategy.ASTAR.toString())) {
					ss = SearchStrategy.ASTAR;
				} else if (args[1].equals(SearchStrategy.IDDFS.toString())) {
					ss = SearchStrategy.IDDFS;
				} else if (args[1].equals(SearchStrategy.GREEDY.toString())) {
					ss = SearchStrategy.GREEDY;
				} else {
					System.out.println("Invalid Search Strategy, default is BFS");
				}
			}

			visual = (args.length >= 3 && args[2].equals("visual"));

			DeadLockFinder dlf = new DeadLockFinder((SokobanState) s);
			GPSState processed_state = dlf.getStateWithDeadLocks();

			if (processed_state == null) {
				System.out.println("The initial board state is not valid");
				return;
			}
			GPSProblem problem = new SokobanProblem(processed_state);
			GPSEngine engine = new GPSEngine(problem, ss);
			engine.findSolution();
			if (!engine.isFailed()) {
				System.out.println("Explosions: " + engine.getExplosionCounter());
				System.out.println("Strategy " + ss.toString() + " - Steps taken " + engine.getSolutionNode().getCost());
			} else {
				System.out.println("DID NOT FIND SOLUTION");
			}
			System.out.println("Time taken: " + (System.currentTimeMillis() - time) + "ms\n");
			if (visual) {
				GraphicManager.startAnimation(engine);
			}

		} catch (FileNotFoundException e) {
			System.err.println("No such file");
		}
	}
}
