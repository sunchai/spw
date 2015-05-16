
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

	int step = 8;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		//g.setColor(Color.BLACK);
		//g.fillRect(x, y, width, height);
		
	Image img = Toolkit.getDefaultToolkit().getImage("p.png");
			g.drawImage(img, x, y, width, height, null);	
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}
	public void upDown(int direction){
		y += (step * direction);
		if(y < 0)
			y = 0;
		if(y > 600 - height)
			y = 600 - height;
	}

	public void setPosition(){
		x = 180;
		y = 500;

	}

}
