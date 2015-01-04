package com.quartzdev.replant;

import java.util.ResourceBundle;

public class Messages {
	
	private static final String MESSAGES = "messages";
	private static Messages instance;
	private static ResourceBundle resource;
	
	public Messages() {
		instance = this;
		resource = ResourceBundle.getBundle(MESSAGES);
	}
	
	public static String msg(String msg) {
		if(instance == null){
			return "";
		}
		
		return instance.translate(msg);
	}
	
	private String translate(String msg) {
		return resource.getString(msg);
	}

}
