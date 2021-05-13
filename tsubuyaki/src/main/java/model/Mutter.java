package model;

import java.io.Serializable;

public class Mutter implements Serializable{

	private String userName;
	private String text;
	private String time;
	
	public Mutter() {}
	
	public Mutter(String userName, String text) {
		this.userName = userName;
		this.text = text;
	}
	public Mutter(String userName, String text, String time) {
		this.userName = userName;
		this.text = text;
		this.time = time;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public String getText() {
		return text;
	}
	public String getTime() {
		return time;
	}
	
}