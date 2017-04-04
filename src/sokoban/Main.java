package sokoban;

import gps.GPSEngine;
import gps.GPSNode;
import gps.SearchStrategy;
import gps.api.GPSProblem;
import gps.api.GPSState;
import sokoban.IO.GameReader;
import sokoban.IO.GraphicManager;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by lucas on 26/03/17.
 */
public class Main {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        boolean visual = false;
        try {		
        			SearchStrategy ss = SearchStrategy.BFS;
        			System.out.println("Starting...");
        			GPSState s = null;
        			if(args.length <= 0){
        				System.err.println("No board file selected");
        				return;
        			}else{
        				s = GameReader.open(args[0]);
        			}
        			if(args.length <= 1){
        				System.out.println("No Search Strategy Selected, default is BFS");
        			}else{
        				if(args[1].equals(SearchStrategy.BFS.toString())){
        					ss = SearchStrategy.BFS;
        				}else if(args[1].equals(SearchStrategy.DFS.toString())){
        					ss = SearchStrategy.DFS;
        				}else if(args[1].equals(SearchStrategy.ASTAR.toString())){
        					ss = SearchStrategy.ASTAR;
        				}else if(args[1].equals(SearchStrategy.IDDFS.toString())){
        					ss = SearchStrategy.IDDFS;
        				}else if(args[1].equals(SearchStrategy.GREEDY.toString())){
        					ss = SearchStrategy.GREEDY;
        				}else{
        					System.out.println("Invalid Search Strategy, default is BFS");
        				}
        			}
        			visual = (args.length >= 3 && args[2].equals("visual"));
        			
	        		//GameReader.printState((SokobanState)s);
	        		
	        		DeadLockFinder dlf = new DeadLockFinder((SokobanState)s);
	        		GPSState processed_state = dlf.getStateWithDeadLocks();
					System.out.println("Tablero Inicial: ");
					GameReader.printState((SokobanState)processed_state);
	        		
	        		if(processed_state == null){
	        			System.out.println("El tablero inicial no puede ser resuelto");
	        			return;
	        		}
	                GPSProblem problem = new SokobanProblem(processed_state);
	                GPSEngine engine = new GPSEngine(problem,ss);
	                engine.findSolution();
	                if(!engine.isFailed()){
						System.out.println("Solucion Encotrada");
						System.out.println("Metodo de busqueda: "+ ss.toString());
						System.out.print("Tiempo: " + (System.currentTimeMillis() - time) + "\n");
						System.out.println("Costo de la solucion: " + engine.getSolutionNode().getCost());
						System.out.println("Profundidad: " + engine.getSolutionNode().getCost());
						System.out.println("Cantidad de nodos explotados: " + engine.getExplosionCounter());
						System.out.println("Cantidad de nodos en frontera: " + engine.getOpen().size());
						GPSNode parent = engine.getSolutionNode();
						LinkedList<GPSNode> solution = new LinkedList<>();
						while(parent.getGenerationRule() != null){
							solution.offerFirst(parent);
							parent = parent.getParent();
						}
						while(!solution.isEmpty()){
							System.out.print(solution.poll().getGenerationRule().getName() + " ");
						}
						System.out.println();
						System.out.println("Tablero Final: ");
						System.out.println(engine.getSolutionNode().toString());
					}else{
	                    System.out.print("No existe solucion para el tablero");
	                }

	                if(visual)
	                	GraphicManager.startAnimation(engine);
	                
        }catch (FileNotFoundException e){
            System.err.println("No such file");
        }
    }
}
