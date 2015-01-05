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
	
	public static String msg(String msg, Object... objects) {
		if(instance == null){
			return "";
		}
		// TODO: Fix this, it won't work at all
		return instance.translate(String.format(msg, objects));
	}
	
	private String translate(String msg) {
		return resource.getString(msg);
	}

}
