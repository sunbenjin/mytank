package com.sunbenjin.tank;

import java.io.IOException;
import java.util.Properties;

public class ConfigMgr {
	
	private static  Properties pro = new Properties();
	
	static {
		try {
			pro.load(ConfigMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Object get(String key) {
		if(pro==null) return null;
		return pro.get(key);
	}
	public static void main(String[] args) {
		System.out.println(ConfigMgr.get("initTankCount"));
	}

}
