package com.sunbenjin.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankFrame extends Frame{
	Tank myTank = new Tank(Dir.DOWN,200,200,this,Group.GOOD);
	//Bullet bullet = new Bullet(300, 300, Dir.DOWN);
	List<Bullet> bullets = new ArrayList<>();
	List<Tank> tanks = new ArrayList<>();
	List<Explose> exploses = new ArrayList<>();
	static int GAME_WIDTH = 1000,GAME_HEIGHT = 1000;
	Explose e = new Explose(100, 100, this);
	public TankFrame() {
		
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setTitle("tank war");
		this.setResizable(false);
		this.setVisible(true);
		//this.setBackground(Color.BLACK);
		this.addKeyListener(new MyKeyListener());
		this.addWindowListener(new WindowAdapter() {
		
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量共有："+bullets.size(), 10, 50);
		g.drawString("敌人的数量共有："+tanks.size(), 10, 80);
		g.drawString("爆炸的数量共有："+exploses.size(), 10, 110);
		g.setColor(c);
		myTank.paint(g);
		/*for(Bullet bullet: bullets) {
			bullet.paint(g);
		}*/
		for(int i = 0; i<bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
	/*	for(Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
			Bullet b = it.next();
			if(!b.live) {
				it.remove();
			}
		}*/
		// TODO Auto-generated method stub
		for(int i =0 ; i<tanks.size(); i++) {
			tanks.get(i).paint(g);
		}
		for(int i =0 ; i<exploses.size(); i++) {
			exploses.get(i).paint(g);
		}
		for(int i=0; i<bullets.size(); i++) {
			for(int j=0; j<tanks.size(); j++) {
				bullets.get(i).collide(tanks.get(j));
			}
		}
		
	}
	/**
	 * 解决抖屏问题，将内存中的图片刷新到屏幕上
	 */
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if(offScreenImage==null) {
			offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
			
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	class MyKeyListener extends KeyAdapter{
			
		boolean bL = false;
		boolean bR = false;
		boolean bU = false;
		boolean bD = false;
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
				
			switch(key) {
				case KeyEvent.VK_UP:
					bU = true;
					break;
				case KeyEvent.VK_DOWN:
					bD = true;
					break;
				case KeyEvent.VK_LEFT:
					bL = true;
					break;
				case KeyEvent.VK_RIGHT:
					bR = true;
					break;
				default:
					break;
			
			}
			setMainTankDir();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			
			switch(key) {
				case KeyEvent.VK_UP:
					bU = false;
					break;
				case KeyEvent.VK_DOWN:
					bD = false;
					break;
				case KeyEvent.VK_LEFT:
					bL = false;
					break;
				case KeyEvent.VK_RIGHT:
					bR = false;
					break;
				case KeyEvent.VK_CONTROL:
					myTank.fire();
					break;
				default:
					break;
			
			}
			setMainTankDir();
		}

		private void setMainTankDir() {
			if(!bL && !bR && !bD && !bU) {
				myTank.setMoving(false);
			}else {
				myTank.setMoving(true);
				if(bL) myTank.setDir(Dir.LEFT);
				if(bR) myTank.setDir(Dir.RIGHT);
				if(bD) myTank.setDir(Dir.DOWN);
				if(bU) myTank.setDir(Dir.UP);
				
			}
		
		}
		
	}
	public Tank getMyTank() {
		return myTank;
	}

	public void setMyTank(Tank myTank) {
		this.myTank = myTank;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(List<Bullet> bullets) {
		this.bullets = bullets;
	}

	public List<Tank> getTanks() {
		return tanks;
	}

	public void setTanks(List<Tank> tanks) {
		this.tanks = tanks;
	}

}
