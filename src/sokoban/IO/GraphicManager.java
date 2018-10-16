package sokoban.IO;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import gps.GPSEngine;
import gps.GPSNode;
import sokoban.SokobanState;

public class GraphicManager {
	
	public static void startAnimation(GPSEngine p){
		List<SokobanState> solve = new ArrayList<>();
		if(!p.isFailed()) {
			SokobanState s = (SokobanState) p.getSolutionNode().getState();
			GPSNode parent = p.getSolutionNode();
			while (parent != null) {
				solve.add((SokobanState) parent.getState());
				parent = parent.getParent();
			}
			GraphicBoard.setBoardSize(s.getWidth(),s.getHeight());
			GraphicBoard.activate();
			GraphicBoard.getInstance().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			for (int j = solve.size() - 1; j >= 0; j--) {
				GraphicBoard.getInstance().setBoard(solve.get(j));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
