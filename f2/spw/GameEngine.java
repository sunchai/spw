
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private SpaceShip v;	
	private int combo=0;
	private Timer timer;
	private Timer timercheck;
	private long score = 0;
	private double difficulty = 0.1;
	private int hp = 100;
	private long count = 0;
	private int lp = 0;
	private int nuclear = 0;
	
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timercheck = new Timer(20000, new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent arg0) {
		difficulty += 0.4;
		check();
		count = 0 ;
		
			}
		});
		
		timer = new Timer(50, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
		process();
			}
		});
		timer.setRepeats(true);
		timercheck.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
		timercheck.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
	
	
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
			
			}
			if(e.isHit()){
				combo ++;
				if(combo>10){
					score += 20;
					count += 20;
				}
			else {
				score += 10;
				count += 10;
				}
				
			}
		}
			
		Iterator<Bullet> b_iter = bullets.iterator();
		while(b_iter.hasNext()){
			Bullet b = b_iter.next();
			b.proceed();
			
			if(!b.isAlive()){
				b_iter.remove();
				gp.sprites.remove(b);
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double br;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				e.hited();
				minus();
				combo = 0;
				
				return;
			}
			for(Bullet b : bullets){
				br = b.getRectangle();
				if(br.intersects(er)){
					e.getHit();
					b.getHit();
					return;
				}				
			}
		}
	}
	
	
	
	public void minus(){
		hp -= 10 ;
		if(hp <= 0){
			die();
		}
	
	}
	public void die(){
		if(lp >=1){
			lifePoint();
			lp -=1;
		}
		else {
			
		timer.stop();
		timercheck.stop();
		}
		
	}
	
	public void lifePoint(){
			hp = 100;	
	}
	
	
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
		/*case KeyEvent.VK_D:
			difficulty += 0.1;
			break;*/
		case KeyEvent.VK_SPACE:
			fire();
			break;
		case KeyEvent.VK_S:
			nuclears();
			break;
			
			
	
		}
	}
	
	public int getLp(){
		//if(lp == true)
		
		return lp;
	}

	public int getNc(){
		//if(nuclear == true)
		return nuclear;
	}

	
	public int getHp(){
		return hp;
	}

	public long getScore(){
		return score;
	}
	
	private void fire(){
		Bullet b = new Bullet((v.x) + (v.width/2) - 5, v.y);
		gp.sprites.add(b);
		bullets.add(b);
	}


	public int getCombo(){
		return combo;
	}

	public void check(){
		if(count >= 500){
			nuclear +=1;
			lp += 1;
		}
		if(count < 500 && count >= 250){
			lp +=1;
		}
		if(count < 250 && count >= 100){
			hp = 100;
		}

	}
	 public void nuclears(){
		if(nuclear >=1 ){
		nuclear -=1;
		for(Enemy e : enemies){
			e.getHit();
			}
		}
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
	
	
					
	
}
