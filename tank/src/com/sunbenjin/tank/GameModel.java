package com.sunbenjin.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.sunbenjin.tank.abstractfactory.BaseExplode;
import com.sunbenjin.tank.abstractfactory.DefaultFactory;
import com.sunbenjin.tank.abstractfactory.GameFactory;

public class GameModel {
	Tank myTank = new Tank(Dir.DOWN,200,200,this,Group.GOOD);
	//Bullet bullet = new Bullet(300, 300, Dir.DOWN);
	List<Bullet> bullets = new ArrayList<>();
	List<Tank> tanks = new ArrayList<>();
	List<BaseExplode> exploses = new ArrayList<>();
	
	public GameFactory gf = new DefaultFactory();
	
	public GameModel() {
		for(int i =0 ; i<Integer.parseInt((String)ConfigMgr.get("initTankCount")); i++) {
			tanks.add(new Tank(Dir.DOWN,80*(i+1), 120, this,Group.BAD));
		}
		
	}
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
	public Tank getMainTank() {
		// TODO Auto-generated method stub
		return myTank;
	}

}
