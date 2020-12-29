package com.coderscampus.Assignment4;



public class User implements Comparable<User>{

	String username;
	String password;
	String name;
	String role;

	public User() {

	}public User(String user, String pass, String name) {
		this.username = user;
		this.password = pass;
		this.name = name;
	}

	public User(String user, String pass, String name, String jobRole) {
		this.username = user;
		this.password = pass;
		this.name = name;
		this.role = jobRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public int compareTo(User one) {
		int value = one.getRole().compareTo(this.getRole());
		if(value == 0) {
			value = this.getUsername().compareTo(one.getUsername());
		}
		return value;
	}
}
