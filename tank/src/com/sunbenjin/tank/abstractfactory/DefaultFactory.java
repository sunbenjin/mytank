package com.sunbenjin.tank.abstractfactory;

import com.sunbenjin.tank.Bullet;
import com.sunbenjin.tank.Dir;
import com.sunbenjin.tank.Explode;
import com.sunbenjin.tank.GameModel;
import com.sunbenjin.tank.Group;
import com.sunbenjin.tank.Tank;
import com.sunbenjin.tank.TankFrame;

public class DefaultFactory extends GameFactory{
	
	@Override
	public BaseBullet createBullet(int x,int y,Dir dir ,Group group,GameModel gm) {
		// TODO Auto-generated method stub
		return new Bullet(x,y,dir,gm,group);
	}

	@Override
	public	BaseTank createTank(int x,int y,GameModel gm,Group group,Dir dir) {
		// TODO Auto-generated method stub
		return new Tank(dir,x,y,gm,group);
	}

	@Override
	public BaseExplode createExplode(int x,int y,GameModel gm) {
		// TODO Auto-generated method stub
		return new Explode(x,y,gm);
	}

	
	
}
