package gps;

import java.util.ArrayList;
import java.util.List;

import IO.GameReader;
import IO.GraphicBoard;
import gps.api.GPSProblem;
import gps.api.GPSState;

/**
 * Created by lucas on 26/03/17.
 */
public class Main {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        List<SokobanState> solve = new ArrayList<>();
        try {
	        		GPSState s = GameReader.open("tablero4.txt");
	        		
	        		//GameReader.printState((SokobanState)s);
	        		
	        		DeadLockFinder dlf = new DeadLockFinder((SokobanState)s);
	        		GPSState processed_state = dlf.getStateWithDeadLocks();
	        		
	        		//GameReader.printState((SokobanState)processed_state);
	        		
	        		if(processed_state == null){
	        			System.out.println("El tablero inicial no puede ser resuelto");
	        			return;
	        		}
	                GPSProblem problem = new SokobanProblem(processed_state);
	                GPSEngine engine = new GPSEngine(problem,SearchStrategy.BFS);
	                engine.findSolution();
	                if(!engine.isFailed()){
	                    GPSNode n = engine.getSolutionNode();
	                    //System.out.println(n.getSolution());
	                    GraphicBoard.setBoardSize(((SokobanState)n.getState()).width,((SokobanState)n.getState()).height);
	                    GraphicBoard.activate();
	                    GPSNode parent = n;
		                while(parent != null){
		                	solve.add((SokobanState)parent.getState());
	                    	parent = parent.getParent();
		                }
	                    
		                
	                    System.out.print(SearchStrategy.BFS.toString() +" " + n.getCost());
	                }else{
	                    System.out.printf("NO TERMINO");
	                }
	                System.out.print(" " + (System.currentTimeMillis() - time) + "\n");
	                time = System.currentTimeMillis();
	                	for(int j = solve.size()-1;j>=0;j--){
                    	GraphicBoard.getInstance().setBoard(solve.get(j));
                    	Thread.sleep(500);
	                	}
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
