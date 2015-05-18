
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.WindowEvent;

import javax.swing.Timer;

import javax.swing.JOptionPane;



public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Foeman> foemans = new ArrayList<Foeman>();
	private ArrayList<Foeman2HP> foemans2 = new ArrayList<Foeman2HP>();
	private ArrayList<Foeman3Nuclear> foemans3 = new ArrayList<Foeman3Nuclear>();
	private ArrayList<Foeman4Boss> foemans4 = new ArrayList<Foeman4Boss>();
	private SpaceShip v;	
	private int combo=0;   
	private Timer timer;
	private Timer timercheck;
	private long score = 0;
	private double difficulty = 0.1; 
	private int hp = 100;
	private int count = 0;
	private int c = 0;
	private int lp = 0;
	private int nuclear = 0;
	private boolean G_over=false;
	private boolean G_Pause=false;
	
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);


		
		timercheck = new Timer(20000, new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent arg0) {
		difficulty += 0.1;
		check();
		count = 0 ;
		
			}
		});
		
		timer = new Timer(50, new ActionListener() { //speed
		@Override
		public void actionPerformed(ActionEvent arg0) {
		process();
		process2();
		process3();
		process4();
		process5();
			}
		});
		timer.setRepeats(true);
		timercheck.setRepeats(true);
		
	}
	




	
	public void playAg(){
		G_over = false;
		G_Pause = false;
		lp = 0;
		score = 0;

		foemans4.clear();
		foemans3.clear();
		foemans2.clear();
		foemans.clear();
		enemies.clear();
		gp.sprites.clear();

		v.setPosition();
		gp.sprites.add(v);
		start();
        setHP(100);
        setCombo(0);
        setNc(0);
		timer.restart();
		timercheck.restart();
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


	private void generateFoeman(){
		Foeman es = new Foeman((int)(Math.random()*390), 30);
		gp.sprites.add(es);
		foemans.add(es);
	}

	private void generateFoeman2HP(){
		Foeman2HP es2 = new Foeman2HP((int)(Math.random()*390), 30);
		gp.sprites.add(es2);
		foemans2.add(es2);
	}
	
	private void generateFoeman3Nuclear(){
		Foeman3Nuclear es3 = new Foeman3Nuclear((int)(Math.random()*390), 30);
		gp.sprites.add(es3);
		foemans3.add(es3);
	}

	private void generateFoeman4Boss(){
		Foeman4Boss es4 = new Foeman4Boss((int)(Math.random()*390), 30);
		gp.sprites.add(es4);
		foemans4.add(es4);
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
	
	
	private void process2(){
		if(Math.random() < difficulty/10){
			generateFoeman();
		}
	
		
		Iterator<Foeman> es_iter = foemans.iterator();
		while(es_iter.hasNext()){
			Foeman eq = es_iter.next();
			eq.proceed();
			
			if(!eq.isAlive()){
				es_iter.remove();
				gp.sprites.remove(eq);
			
			}
			if(eq.isHit()){
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
		for(Foeman eqs : foemans){
			er = eqs.getRectangle();
			if(er.intersects(vr)){
				eqs.hited();
				minus();
				combo = 0;
				
				return;
			}

			


			for(Bullet b : bullets){
				br = b.getRectangle();
				if(br.intersects(er)){
					eqs.getHit();
					b.getHit();
					return;
				}				
			}
		}
	}




private void process3(){
		if(Math.random() < difficulty/80){
			generateFoeman2HP();
		}
	
		
		Iterator<Foeman2HP> es_iter2 = foemans2.iterator();
		while(es_iter2.hasNext()){
			Foeman2HP eq2 = es_iter2.next();
			eq2.proceed();
			
			if(!eq2.isAlive()){
				es_iter2.remove();
				gp.sprites.remove(eq2);
			
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
		for(Foeman2HP eqs : foemans2){
			er = eqs.getRectangle();
			if(er.intersects(vr)){
				eqs.hited();
				hp += 30;
				combo = 0;
				
				return;
			}


			
		}
	}


private void process4(){
		if(Math.random() < difficulty/50){
			generateFoeman3Nuclear();
		}
	
		
		Iterator<Foeman3Nuclear> es_iter3 = foemans3.iterator();
		while(es_iter3.hasNext()){
			Foeman3Nuclear eq3 = es_iter3.next();
			eq3.proceed();
			
			if(!eq3.isAlive()){
				es_iter3.remove();
				gp.sprites.remove(eq3);
			
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
		for(Foeman3Nuclear eqs : foemans3){
			er = eqs.getRectangle();
			if(er.intersects(vr)){
				eqs.hited();
				nuclear+= 1;
				combo = 0;
				
				return;
			}

		}
	}


//
private void process5(){
		if(Math.random() < difficulty/10){
			generateFoeman4Boss();
		}
	
		
		Iterator<Foeman4Boss> es_iter4 = foemans4.iterator();
		while(es_iter4.hasNext()){
			Foeman4Boss eq4 = es_iter4.next();
			eq4.proceed();
			
			if(!eq4.isAlive()){
				es_iter4.remove();
				gp.sprites.remove(eq4);
			
			}
			if(eq4.isHit()){
				combo ++;
				if(combo>10){
					
					score += 1000;
					count += 100;
				}
			else {
				
				score += 10000;
				count += 100;
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
		for(Foeman4Boss eqs : foemans4){
			er = eqs.getRectangle();
			if(er.intersects(vr)){
				eqs.hited();
				bossAtHP();
				//v.width = 100; //drop size when intersects
				//v.height = 20;
				combo = 0;
				
				return;
			}


			for(Bullet b : bullets){
				br = b.getRectangle();
				if(br.intersects(er)){
					eqs.getHit();
					b.getHit();
					return;
				}				
			}
		}

	}

	private void bossAtHP(){
            hp -= 40;
            if(hp ==0){         	
                die();
            }else if(hp<0){
            	hp=0;
            	die();
            }
            gp.updateGameUI(this);
	}
	public void minus(){
		hp -= 10 ;
		if(hp <= 0){
			
			die();
		}
	
	}

	public void setHP(int hp){
                this.hp = hp;
        }

	public void die(){
		if(lp >=1){	
			lifePoint();
			lp -=1;
		}
		else {

		G_over = true;
		
		
		foemans4.clear();
		foemans3.clear();
		foemans2.clear();
		foemans.clear();
		enemies.clear();
		gp.sprites.clear();
		timercheck.stop();
		timer.stop();
		

		}
		
	}

	public void pause(){
		
		
		G_Pause = true;
		timercheck.stop();
		timer.stop();
		JOptionPane.showMessageDialog(null,"Score: " +score +" Points" );
		JOptionPane.showMessageDialog(null,"Please Press R to return" );
		
		
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
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_SPACE:
			fire();
			break;
		case KeyEvent.VK_C:
			nuclears();
			break;
		case KeyEvent.VK_R:
			if(G_Pause){
				start();
			}
			break;	
		case KeyEvent.VK_UP:
			v.upDown(-1);
			break;
		case KeyEvent.VK_DOWN:
			v.upDown(+1);
			break;		
		case KeyEvent.VK_ENTER:
			if(G_over){
				playAg();
			}

		
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

	public void setNc(int nuclear){
		//if(nuclear == true)
		this.nuclear = nuclear;
	}

	

	public int getHp(){
		return hp;
	}

	public long getScore(){
		return score;
	}
	
 	public void setScore(long score){
		this.score = score;
	}

	private void fire(){
		Bullet b = new Bullet((v.x) + (v.width/2) - 5, v.y);
		gp.sprites.add(b);
		bullets.add(b);
	}


	public int getCombo(){
		return combo;
	}

	public void setCombo(int combo){
		this.combo = combo;
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
		for(Foeman e2 : foemans){
			e2.getHit();
			}	
		for(Foeman2HP e3 : foemans2){
			e3.getHit();
			}
		for(Foeman3Nuclear e4 : foemans3){
			e4.getHit();
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
