package IO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import gps.SokobanState;

public class GraphicBoard extends JFrame{
	
	private static final long serialVersionUID = 1L;
	//private ImageIcon[] images = new ImageIcon[24];
	private static JLabel[] labels;
	private ImageIcon player;
	private ImageIcon wall;
	private ImageIcon target;
	private ImageIcon empty;
	private ImageIcon box;
	private ImageIcon boxOnTarget;
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
		for(int i = 0; i<w;i++){
			for(int j=0; j<h;j++){
				if(i*w + j == w*h -1){
					System.out.println("hola");
				}
				labels[i + j*w] = new JLabel();
				//labels[i + j*w].setSize(32,32);
				//labels[i + j*w].setMaximumSize(new Dimension(32,32));
				labels[i + j*w].setLocation(i*32,j*32);
				//labels[i + j*w].setBounds(i*32, j*32, 32, 32);
				//labels[i*15 + j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				add(labels[i + j*w]);
			}
		}
		setSize(w*32 +5,(h+1)*32);
		setResizable(false);
		setVisible(true);
		setLayout(new GridLayout(h,w));
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
		labels = new JLabel[w*h];
	}
	public void setBoard(SokobanState b){
		for(int i = 0; i<w;i++){
			for(int j=0; j<h;j++){
				labels[i + j*w].setLocation(i*32,j*32);
				switch(b.getBoard()[j][i]){
				case 0:
					labels[i + j*w].setIcon(empty);
					break;
				case 1:
					labels[i + j*w].setIcon(wall);
					break;
				case 2:
					labels[i + j*w].setIcon(box);
					break;
				case 4:
					labels[i + j*w].setIcon(target);
					break;
				case 6:
					labels[i + j*w].setIcon(boxOnTarget);
					break;
				case 8:
				case 12:
				case 24:
					labels[i + j*w].setIcon(player);
				break;
				default:
					labels[i + j*w].setIcon(empty);
				
				}
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
