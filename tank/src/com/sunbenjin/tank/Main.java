package com.sunbenjin.tank;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		TankFrame t= new TankFrame();
		
		
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
