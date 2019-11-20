package com.sunbenjin.tank;

public class FourDirFireStrategy implements FireStrategy {

	@Override
	public void fire(Tank t) {
		int bX = t.x+Tank.WIDTH/2-Bullet.WIDTH/2;
		int bY = t.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
		Dir[] dirs = Dir.values();
		for(Dir dir : dirs) {
			new Bullet(bX,bY,dir,t.tf,t.group);
		}
		//tf.bullets.add(new Bullet(bX,bY,t.dir,tf,t.group)) ;
		
		if(t.group==Group.GOOD)new Thread(()-> {
			new Audio("audio/tank_fire.wav").play();
		} ).start();

	}

}
