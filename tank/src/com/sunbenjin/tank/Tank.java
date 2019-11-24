package com.sunbenjin.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.Random;

import com.sunbenjin.tank.abstractfactory.BaseTank;

public class Tank extends BaseTank{
	Dir dir = Dir.DOWN;
	 int x;
	 int y;
	private boolean moving = true;
	private final static int SPEED = 10;
    //TankFrame tf;
	public static final int WIDTH = ResourceMgr.badTankD.getWidth();
	public static final int  HEIGHT = ResourceMgr.badTankD.getHeight();
	private boolean living = true;
    Group group = Group.GOOD;
	private Random random = new Random();
	public Rectangle rect = new Rectangle();
	//FireStrategy fs = new DefaultFireStrategy();
	FireStrategy fs ;
	GameModel gm;
	public Dir getDir() {
		return dir;
	}
	public void setDir(Dir dir) {
		this.dir = dir;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public Tank(Dir dir, int x, int y, GameModel gm,Group group) {
		super();
		this.dir = dir;
		this.x = x;
		this.y = y;
		this.gm = gm;
		this.group = group;
		rect.x = this.x;
		rect.y = this.y;
		rect.height = HEIGHT;
		rect.width = WIDTH;
		if(this.group==Group.BAD) {
			String badFS = (String)ConfigMgr.get("badFS");
			try {
				fs = (DefaultFireStrategy)Class.forName(badFS).newInstance();
			} catch (InstantiationException e) {
				
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
		
		}else {
			String badFS = (String)ConfigMgr.get("goodFS");
			try {
				fs = (FourDirFireStrategy)Class.forName(badFS).newInstance();
			} catch (InstantiationException e) {
				
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
		
		}
		
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void paint(Graphics g) {
		//Color c = g.getColor();
		/*g.setColor(Color.YELLOW);
		g.fillRect(x, y, 50, 50);
		g.setColor(c);*/
		//if(!living) tf.tanks.remove(this);
		for(Iterator<Tank> it = gm.tanks.iterator(); it.hasNext();) {
			Tank tank = it.next();
			if(!tank.living) {
				it.remove();
			}
		}
		switch(dir) {
			case LEFT:
				g.drawImage(this.group==Group.BAD?ResourceMgr.badTankL:ResourceMgr.goodTankL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(this.group==Group.BAD?ResourceMgr.badTankR:ResourceMgr.goodTankR, x, y, null);
				break;
			case DOWN:
				g.drawImage(this.group==Group.BAD?ResourceMgr.badTankD:ResourceMgr.goodTankD, x, y, null);
				break;
			case UP:
				g.drawImage(this.group==Group.BAD?ResourceMgr.badTankU:ResourceMgr.goodTankU, x, y, null);
				break;
		}
		
		move();
	
		
	}
	private void move() {
		if(!moving) {
			return;
		} else{
			switch(dir) {
			case LEFT:
				x-=SPEED;
				break;
			case RIGHT:
				x+=SPEED;
				break;
			case DOWN:
				y+=SPEED;
				break;
			case UP:
				y-=SPEED;
				break;
			}
		}
		
		if(this.group == Group.BAD && random.nextInt(100)>95) {
			this.fire();
		}
		if(this.group == Group.BAD && random.nextInt(100)>95) {
			randomDir();
		}
		boudsCheck();
		rect.x = this.x;
		rect.y = this.y;
	}
	/**
	 * ±ß½ç¼ì²â
	 */
	private void boudsCheck() {
		if(this.x <2) x= 2;
		if(this.y < 28 ) y =28;
		if (this.x >TankFrame.GAME_WIDTH -Tank.WIDTH-2) x= TankFrame.GAME_WIDTH - Tank.WIDTH-2;
		if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT-2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT-2;
		
	}
	private void randomDir() {
		
		this.dir = Dir.values()[random.nextInt(4)];
		
	}
	public boolean isMoving() {
		return moving;
	}
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	public void fire() {
	
		fs.fire(this);
		
	}
	public boolean isLiving() {
		return living;
	}
	public void setLiving(boolean living) {
		this.living = living;
	}
	public void die() {
		this.living = false;
		
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	

}
