package com.sunbenjin.tank;

import java.awt.Color;
import java.awt.Graphics;

import com.sunbenjin.tank.abstractfactory.BaseExplode;

public class RectExplode extends BaseExplode{

	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	
	private int x,y;
	
	private boolean living  = true;
	
	GameModel gm = null;

	private int step = 0;
	
	
	public RectExplode(int x, int y,GameModel gm) {
		super();
		this.x = x;
		this.y = y;
		this.gm = gm;
		new Thread(()->{
			new Audio("audio/explode.wav").play();
		}) .start();
		
		//new Audio("").run();
	}
	@Override
	public void paint (Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(x, y, 10*step, 10*step);
		step++;
		if(step>=10) {
			gm.exploses.remove(this);
		}
		g.setColor(c);
	}

}
