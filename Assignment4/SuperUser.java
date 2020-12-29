package com.coderscampus.Assignment4;

public class SuperUser extends User{

	public SuperUser(String username, String password, String name) {
		
		this.role = "super_user";
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);
	}
}
