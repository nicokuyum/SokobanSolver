package gps;

import gps.api.GPSProblem;
import gps.api.GPSState;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import IO.GameReader;
import IO.GraphicBoard;

/**
 * Created by lucas on 26/03/17.
 */
public class Main {
	
	public static final SearchStrategy ss = SearchStrategy.ASTAR;

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        List<SokobanState> solve = new ArrayList<>();
        try {		
        			System.out.println("Starting...");
	        		GPSState s = GameReader.open("tablero3.txt");
	        		
	        		GameReader.printState((SokobanState)s);
	        		
	        		DeadLockFinder dlf = new DeadLockFinder((SokobanState)s);
	        		GPSState processed_state = dlf.getStateWithDeadLocks();
                  
	        		//GraphicBoard.getInstance().setBoard((SokobanState)processed_state);
	        		GameReader.printState((SokobanState)processed_state);
	        		
	        		if(processed_state == null){
	        			System.out.println("El tablero inicial no puede ser resuelto");
	        			return;
	        		}
	                GPSProblem problem = new SokobanProblem(processed_state);
	                GPSEngine engine = new GPSEngine(problem,ss);
	                engine.findSolution();
	                if(!engine.isFailed()){
	                    GPSNode n = engine.getSolutionNode();
	                    //System.out.println(n.getSolution());
	                    GraphicBoard.setBoardSize(((SokobanState)n.getState()).width,((SokobanState)n.getState()).height);
	                    GraphicBoard.activate();
	                    GraphicBoard.getInstance().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                    GPSNode parent = n;
		                while(parent != null){
		                	solve.add((SokobanState)parent.getState());
	                    	parent = parent.getParent();
		                }
	                    
		                
	                    System.out.print(ss.toString() +" " + n.getCost());
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
