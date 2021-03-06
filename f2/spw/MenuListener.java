
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MenuListener implements ActionListener{
    JMenuItem menuExit;
    JMenuItem menuCredit;
    JMenuItem menuRestart;
    JMenuItem menuPause;
    GameEngine engine;
    
    public MenuListener(JMenuItem menuExit,JMenuItem menuCredit,JMenuItem menuRestart,JMenuItem menuPause,GameEngine engine){
        this.menuCredit = menuCredit;
        this.menuExit = menuExit;
        this.menuRestart = menuRestart;
        this.menuPause = menuPause;
        this.engine = engine;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        clickMenu(e);
    }
    
    private void clickMenu(ActionEvent e){
        if(e.getSource() == menuExit){
            System.exit(0);
        }
        if(e.getSource() == menuCredit){
            JOptionPane.showMessageDialog(null,"Mr.Sunchai Sutjarit 5410110535");
        }
        if(e.getSource() == menuRestart){
            engine.playAg();
            
           
        }
        if(e.getSource() == menuPause){
            engine.pause();
        }
    }
}