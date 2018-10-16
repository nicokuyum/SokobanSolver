package gps;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;
import sokoban.IO.GraphicBoard;
import sokoban.SokobanState;

import java.util.*;

public class GPSEngine {

	Queue<GPSNode> open;
	Map<GPSState, Integer> bestCosts;
	GPSProblem problem;
	long explosionCounter;
	boolean finished;
	boolean failed;
	GPSNode solutionNode;

	// Use this variable in open set order. 
	protected SearchStrategy strategy;

	public GPSEngine(GPSProblem myProblem, SearchStrategy myStrategy) {
		switch (myStrategy) {
			case BFS:
			case DFS:
			case IDDFS:
			case GREEDY:
				open = new LinkedList<>();
				break;
			case ASTAR:
				open = new PriorityQueue<>(new AStarComparator(myProblem));
				break;
		}
		bestCosts = new HashMap<>();
		problem = myProblem;
		strategy = myStrategy;
		explosionCounter = 0;
		finished = false;
		failed = false;
	}

	public void findSolution() {
		GPSNode rootNode = new GPSNode(problem.getInitState(), 0, null);
		open.add(rootNode);
		switch (strategy) {
			case DFS:
			case BFS:
			case ASTAR:
			case GREEDY:
				while (open.size() > 0) {
					GPSNode currentNode = open.remove();
					if (problem.isGoal(currentNode.getState())) {
						finished = true;
						solutionNode = currentNode;
						return;
					} else {
						explode(currentNode);
					}
				}
				failed = true;
				finished = true;
				break;
			case IDDFS:
				int i = 1;
				GPSNode currentNode;
				int lastExploded = -1;
				int exploded = 0;
				while (lastExploded != exploded) {
					lastExploded = exploded;
					exploded = 0;
					while (open.size() > 0) {
						currentNode = open.remove();

						if (problem.isGoal(currentNode.getState())) {
							finished = true;
							solutionNode = currentNode;
							return;
						} else {
							if (currentNode.getCost() <= i) {
								exploded += explode(currentNode);
							}
						}

					}
					bestCosts.clear();
					i += 2;
					open.add(rootNode);

				}
				failed = true;
				finished = true;
				break;

		}
	}

	private int explode(GPSNode node) {
		Collection<GPSNode> newCandidates;
		switch (strategy) {
			case BFS:
				if (bestCosts.containsKey(node.getState())) {
					return 0;
				}
				newCandidates = new ArrayList<>();
				addCandidates(node, newCandidates);
				open.addAll(newCandidates);
				break;
			case DFS:
				if (bestCosts.containsKey(node.getState())) {
					return 0;
				}
				newCandidates = new ArrayList<>();
				addCandidates(node, newCandidates);
				((LinkedList<GPSNode>) open).addAll(0, newCandidates);
				break;
			case IDDFS:
				if (bestCosts.containsKey(node.getState())) {
					return 0;
				}
				newCandidates = new ArrayList<>();
				addCandidates(node, newCandidates);
				((LinkedList<GPSNode>) open).addAll(0, newCandidates);
				break;
			case GREEDY:
				if (bestCosts.containsKey(node.getState())) {
					return 0;
				}
				newCandidates = new PriorityQueue<>(new GreedyComparator(problem));
				addCandidates(node, newCandidates);
				((LinkedList<GPSNode>) open).addAll(0, newCandidates);
				break;
			case ASTAR:
				if (!isBest(node.getState(), node.getCost())) {
					return 0;
				}
				newCandidates = new ArrayList<>();
				addCandidates(node, newCandidates);
				open.addAll(newCandidates);
				break;
		}
		return 1;
	}

	private void addCandidates(GPSNode node, Collection<GPSNode> candidates) {
		explosionCounter++;
		updateBest(node);
		for (GPSRule rule : problem.getRules()) {
			Optional<GPSState> newState = rule.evalRule(node.getState());
			if (newState.isPresent()) {
				GPSNode newNode = new GPSNode(newState.get(), node.getCost() + rule.getCost(), rule);
				newNode.setParent(node);
				candidates.add(newNode);
			}
		}
	}

	private boolean isBest(GPSState state, Integer cost) {
		return !bestCosts.containsKey(state) || cost < bestCosts.get(state);
	}

	private void updateBest(GPSNode node) {
		bestCosts.put(node.getState(), node.getCost());
	}

	// GETTERS FOR THE PEOPLE!

	public Queue<GPSNode> getOpen() {
		return open;
	}

	public Map<GPSState, Integer> getBestCosts() {
		return bestCosts;
	}

	public GPSProblem getProblem() {
		return problem;
	}

	public long getExplosionCounter() {
		return explosionCounter;
	}

	public boolean isFinished() {
		return finished;
	}

	public boolean isFailed() {
		return failed;
	}

	public GPSNode getSolutionNode() {
		return solutionNode;
	}

	public SearchStrategy getStrategy() {
		return strategy;
	}

}
