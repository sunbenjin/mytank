package com.sunbenjin.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;

import com.sunbenjin.tank.abstractfactory.BaseBullet;
import com.sunbenjin.tank.abstractfactory.GameFactory;

public class Bullet extends BaseBullet{

	private static final int SPEED = 20;
	private int x, y;
	private Dir dir;
	public static final int WIDTH = ResourceMgr.bulletD.getWidth();
	public static final int  HEIGHT = ResourceMgr.bulletD.getHeight();
	
	public Rectangle rect = new Rectangle();
	public boolean living = true;
	private TankFrame tf = null;
	private Group group = Group.GOOD;
	public Bullet(int x, int y, Dir dir, TankFrame tf,Group group) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
		this.group = group;
		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;
		tf.bullets.add(this);
	}

	public void paint(Graphics g) {
	/*	if (!live) {
			tf.bullets.remove(this);
		}*/
		for(Iterator<Bullet> it =tf.bullets.iterator(); it.hasNext();) {
			Bullet b = it.next();
			if(!b.living) {
				it.remove();
			}
		}
	/*	Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);*/
		switch(dir) {
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
			break;
			
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;
	}
		move();

	}

	private void move() {
		switch (dir) {
		case LEFT:
			x -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		}
		if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			// tf.bullets.remove(this);
			living = false;
		}
		rect.x = this.x;
		rect.y = this.y;
		
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

	public void setY(int y) {
		this.y = y;
	}

	public boolean isLiving() {
		return living;
	}

	public void setLive(boolean living) {
		this.living = living;
	}

	/**
	 * Ì¹¿ËÓë×Óµ¯Åö×²Åó
	 */
	public void collide(Tank tank) {
		if(this.group == tank.getGroup()) return;
		/*Rectangle rect1 = new Rectangle(x,y,WIDTH,HEIGHT);
		Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);*/
		if(rect.intersects(tank.rect)) {
			this.die();
			tank.die();
			int eX = tank.getX()+Tank.WIDTH/2-Explode.WIDTH/2;
			int eY = tank.getY()+Tank.HEIGHT/2-Explode.HEIGHT/2;
			
			
			tf.exploses.add(tf.gf.createExplode(eX, eY, tf));
			
		}
		
	}

	private void die() {
		this.living = false;
		
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
