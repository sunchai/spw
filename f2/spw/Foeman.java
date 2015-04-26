
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;

public class Foeman extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	
	private int step = 8;
	private boolean alive = true;
	private boolean hit = false;
	


	public Foeman(int x, int y) {
		super(x, y, 60, 90);
		
	}

	@Override
	public void draw(Graphics2D g) {
			


		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		//g.setColor(Color.RED);
		//g.fillRect(x, y, width, height);
		Image img = Toolkit.getDefaultToolkit().getImage("oo.png");
			g.drawImage(img, x, y, width, height, null);
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void hited(){
		this.alive = false;
	}

	public boolean isHit() {
	 return hit ;
	}
	
	public void getHit(){
		this.hit = true;
		this.alive = false;
	}
	
}
