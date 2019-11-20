package com.sunbenjin.tank;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		TankFrame t= new TankFrame();
		List<Tank> tanks = new ArrayList<>();
		for(int i =0 ; i<Integer.parseInt((String)ConfigMgr.get("initTankCount")); i++) {
			tanks.add(new Tank(Dir.DOWN,80*(i+1), 120, t,Group.BAD));
		}
		t.setTanks(tanks);
		try {
			while(true) {
				t.repaint();
				Thread.sleep(50);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
