package com.sunbenjin.tank.abstractfactory;

import com.sunbenjin.tank.Dir;
import com.sunbenjin.tank.Group;
import com.sunbenjin.tank.TankFrame;

public abstract  class GameFactory {

	public abstract BaseBullet createBullet(int x,int y,Dir dir,Group group,TankFrame tf);
	
	public abstract BaseTank createTank(int x,int y,TankFrame tf,Group group,Dir dir);
	
	public abstract BaseExplode createExplode(int x,int y,TankFrame tf);
}
