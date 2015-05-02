import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("SpaceShip Wagon");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);
		frame.getContentPane().setLayout(new BorderLayout());
                
               
                
                JMenuBar menubar = new JMenuBar();
                JMenu menuMain = new JMenu("Setting");
                JMenu menuAbout = new JMenu("About");
                JMenuItem menuRestart =new JMenuItem("Restart");
                JMenuItem menuExit = new JMenuItem("Exit");
                JMenuItem menuStop = new JMenuItem("Pause");
                JMenuItem menuCredit = new JMenuItem("Credit");      
               
                menuMain.add(menuRestart);
                menuMain.add(menuStop);
                menuMain.add(menuExit);
                menuAbout.add(menuCredit);
                menubar.add(menuMain);
                menubar.add(menuAbout);
                frame.setJMenuBar(menubar);
                
               
               
		SpaceShip v = new SpaceShip(180, 500, 30, 100);
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp, v);
                         
              
                MenuListener menuListener = new MenuListener(menuExit,menuCredit,menuRestart,menuStop,engine);
                menuExit.addActionListener(menuListener);
                menuCredit.addActionListener(menuListener);
                menuRestart.addActionListener(menuListener);
                menuStop.addActionListener(menuListener);
              
                
		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		
		engine.start();
	}
       
    
        
}