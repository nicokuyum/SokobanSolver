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
            GPSState s = GameReader.open("tablero.txt");
            GPSProblem problem = new SokobanProblem(s);
            GPSEngine engine = new GPSEngine(problem,SearchStrategy.GREEDY);
            engine.findSolution();
            if(!engine.isFailed()){
                GPSNode n = engine.getSolutionNode();
                System.out.println(n.getSolution());
                System.out.println(n.getCost());
            }else{
                System.out.printf("NO TERMINO");
            }
            System.out.println(System.currentTimeMillis() - time);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
