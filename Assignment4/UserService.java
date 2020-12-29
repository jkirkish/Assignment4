/**
 * 
 */
package com.coderscampus.Assignment4;

/**
 * @author kirkish
 *
 */
public class UserService {


	public User fetchValidUser(String username, String password) {
		for (User user : Assignment4.users) {
			if (user.getUsername().equalsIgnoreCase(username))
				if (user.getPassword().equals(password)) {
					return user;
				}
		}
		return null;
	}

	public String toString(User user) {

		return user.getUsername() + ", " + user.getPassword() + ", " + user.getName() + ", " + user.getRole() + "\n";
	}

	public User getUserByUsername(String updated) {

		for (User user : Assignment4.users) {
			if (user.getUsername().equalsIgnoreCase(updated)) {
				return user;
			}
		}
		return null;
	}	
}
