package sokoban.IO;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import sokoban.SokobanState;

public class GraphicBoard extends JFrame{
	
	private static final long serialVersionUID = 1L;
	//private ImageIcon[] images = new ImageIcon[24];
	private static JLabel[][] labels;
	private ImageIcon player;
	private ImageIcon wall;
	private ImageIcon target;
	private ImageIcon empty;
	private ImageIcon box;
	private ImageIcon boxOnTarget;
	private ImageIcon dead;
	private static int w = 10;
	private static int h = 10;
	private static GraphicBoard instance;
	private static boolean activated  = false;
	 
	private GraphicBoard() throws IOException{
		super("Tablero");
			player = new ImageIcon("img/player.png");
			empty = new ImageIcon("img/empty.png");
			box = new ImageIcon("img/boxtarget.png");
			boxOnTarget = new ImageIcon("img/box.png");
			wall = new ImageIcon("img/WALL.png");
			target = new ImageIcon("img/target.png");
			dead = new ImageIcon("img/deadlock.png");
			setLayout(new GridLayout(h,w,0,0));
			for(int j=0; j<h;j++){
				for(int i = 0; i<w;i++){
				labels[j][i] = new JLabel();
				//labels[i + j*w].setSize(32,32);
				//labels[i + j*w].setMaximumSize(new Dimension(32,32));
				//labels[i + j*w].setLocation(i*32,j*32);
				//labels[i + j*w].setBounds(i*32, j*32, 32, 32);
				//labels[i*15 + j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				add(labels[j][i]);
			}
		}
		setSize(w*32 +5,(h+1)*32);
		//setResizable(false);
		setVisible(true);
		
	}
	public static void activate(){
		activated = true;
	}
	public static GraphicBoard getInstance(){
		if(activated){
			if(instance == null){
				try {
					instance = new GraphicBoard();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return instance;
		}
		return null;
	}
	public static boolean isGraphicsActive(){
		return activated;
	}
	public static void setBoardSize(int w,int h){
		GraphicBoard.w = w;
		GraphicBoard.h = h;
		labels = new JLabel[h][w];
	}
	public void setBoard(SokobanState b){
		for(int j=0; j<h;j++){
			for(int i = 0; i<w;i++){
				switch(b.getBoard()[j][i]){
				case 0:
					labels[j][i].setIcon(empty);
					break;
				case 1:
					labels[j][i].setIcon(wall);
					break;
				case 2:
					labels[j][i].setIcon(box);
					break;
				case 4:
					labels[j][i].setIcon(target);
					break;
				case 6:
					labels[j][i].setIcon(boxOnTarget);
					break;
				case 8:
				case 12:
				case 24:
					labels[j][i].setIcon(player);
					break;
				case 16:
					labels[j][i].setIcon(dead);
					break;
				default:
					labels[j][i].setIcon(empty);
				
				}
		//		labels[i + j*w].setLocation(i*32,j*32);
			}
		}
	/*try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//repaint();*/
	}
	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
	}
}
