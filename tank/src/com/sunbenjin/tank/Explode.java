package com.sunbenjin.tank;

import java.awt.Graphics;

import com.sunbenjin.tank.abstractfactory.BaseExplode;

public class Explode extends BaseExplode{

	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	
	private int x,y;
	
	private boolean living  = true;
	
	TankFrame tf = null;

	private int step = 0;
	
	
	public Explode(int x, int y,TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.tf = tf;
		new Thread(()->{
			new Audio("audio/explode.wav").play();
		}) .start();
		
		//new Audio("").run();
	}
	@Override
	public void paint (Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++],x,y,null);
		if(step>=ResourceMgr.explodes.length) {
			//step = 0;
			tf.exploses.remove(this);
		}
	}
	
			
}
