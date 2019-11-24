package com.sunbenjin.tank.abstractfactory;

import com.sunbenjin.tank.Dir;
import com.sunbenjin.tank.GameModel;
import com.sunbenjin.tank.Group;


public abstract  class GameFactory {

	public abstract BaseBullet createBullet(int x,int y,Dir dir,Group group,GameModel gm);
	
	public abstract BaseTank createTank(int x,int y,GameModel gm,Group group,Dir dir);
	
	public abstract BaseExplode createExplode(int x,int y,GameModel gm);
}
