package gps;

import IO.GameReader;
import gps.api.GPSProblem;
import gps.api.GPSState;

/**
 * Created by lucas on 26/03/17.
 */
public class Main {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        try {
        	for(SearchStrategy ss : SearchStrategy.values()){
        		if(ss != SearchStrategy.IDDFS){
	        		GPSState s = GameReader.open("tablero.txt");
	        		
	        		//GameReader.printState((SokobanState)s);
	        		
	        		DeadLockFinder dlf = new DeadLockFinder((SokobanState)s);
	        		GPSState processed_state = dlf.getStateWithDeadLocks();
	        		
	        		//GameReader.printState((SokobanState)processed_state);
	        		
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
	                    System.out.print(ss.toString() +" " + n.getCost());
	                }else{
	                    System.out.printf("NO TERMINO");
	                }
	                System.out.print(" " + (System.currentTimeMillis() - time) + "\n");
	                time = System.currentTimeMillis();
        		}
        	}
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
