

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.Image;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;
	private int hp;
	private Image imgBackground;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	public GamePanel() {
		imgBackground = Toolkit.getDefaultToolkit().getImage("sds.jpg");
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		//big.setBackground(Color.BLACK);
		//big.drawImage(imgBackground, 0, 0, 400, 600,null);
	}

	public void updateGameUI(GameReporter reporter){
		//big.clearRect(0, 0, 400, 600);
		big.drawImage(imgBackground, 0, 0, 400, 600,null);
		big.setColor(Color.WHITE);		
		big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
		big.drawString(String.format("HP SpaceShip %03d", reporter.getHp()), 150, 40);
		big.drawString(String.format("Combo %03d", reporter.getCombo()), 40, 20);
		big.drawString(String.format("Life %03d", reporter.getLp()), 40, 40);
		big.drawString(String.format("Nuclear %03d", reporter.getNc()), 40, 60);

		hp = reporter.getHp();

		System.out.println(""+"/"+hp);
		
		if(hp<=0){
		big.clearRect(0, 0, 400, 600);
		big.setColor(Color.WHITE);	

		big.drawString("Game Over", 100, 250);
		big.drawString(String.format("Your score is :%d",reporter.getScore()), 100, 300);
		big.drawString("Please ENTER to Play again",100,490);
		

		} 

		for(Sprite s : sprites){
			s.draw(big);
		}		
		repaint();




	}

	/*public void gameOver(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		big.setColor(Color.WHITE);	
		big.drawString("Game Over", 100, 250);
		big.drawString(String.format("Your score is :%d",reporter.getScore()), 100, 300);
		big.drawString("Please ENTER to Play again",100,490);
		
		repaint();

	}*/
	
		
		
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

	public void gamehp(int hp){
		this.hp=hp;
	}
	
}
