package domain;

import java.awt.Image;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Administrator implements Serializable {

	private String name;
	private String username;
	private String password;

	public Administrator(String name, String login, String password) {
		this.name=name;
		this.username=login;
		this.password=password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString(){
		return name;
	}

}