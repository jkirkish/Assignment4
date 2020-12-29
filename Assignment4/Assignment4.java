/**
 * 
 */
package com.coderscampus.Assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.coderscampus.Assignment4.User;
import com.coderscampus.Assignment4.UserService;

/**
 * @author kirkish
 *
 */
public class Assignment4 {

	public static User[] users = new User[20];
	private static UserService userService = new UserService();
	private static final int MAX_LOGIN_TRIES = 5;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		BufferedReader reader = null;

		try {
			System.out.println("Reading");
			reader = new BufferedReader(new FileReader("users.txt"));
			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null) {
				String[] props = line.split(", ");
				if ("super_user".equals(props[3]))
					users[i++] = new SuperUser(props[0], props[1], props[2]);
				else {
					users[i++] = new NormalUser(props[0], props[1], props[2]);
				}
			}
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception ex) {
				System.out.println("Reader cannot be closed");
				ex.printStackTrace();
			}
		}

		User signedInUser = null;
		int loginTries = 0;
		while (loginTries < MAX_LOGIN_TRIES && signedInUser == null) {

			System.out.print("Enter your email:");
			String username = scanner.nextLine();
			//String username ="a@a.com";
			System.out.print("Enter your password: ");
			String password = scanner.nextLine();
            //String password = "asdfasdf";
			signedInUser = userService.fetchValidUser(username, password);
			if (signedInUser == null) {
				System.out.println("Invalid login, please try again");
				loginTries++;
				System.out.println(loginTries);
				if (loginTries == MAX_LOGIN_TRIES) {
					System.out.println("Too many failed login attempts, you are now locked out.");
					scanner.close();
					System.exit(0);
				}
			}
			if (signedInUser != null) {
				Integer selection = 0;
				while (selection != 4) {
					System.out.println("Welcome: " + signedInUser.getName());
					selection = selectAChoice(signedInUser);
					if (selection == 0 && signedInUser instanceof SuperUser) {
						String updatedUsername = selectTheUserToLoginAs();
						User userUpdated = userService.getUserByUsername(updatedUsername);
						if (userUpdated == null) {
							System.err.println("Invalid username.");
						} else {
							signedInUser = userUpdated;
						}
					} else {
						switch (selection) {
						case 1:
							userUpdateUsername(signedInUser);
							break;
						case 2:
							userUpdatePassword(signedInUser);
							break;
						case 3:
							userUpdateName(signedInUser);
							break;
						case 4:
							System.out.println("System updated! Have a nice day!");
							scanner.close();
							break;
						}
					}
				}
				BufferedWriter writer = null;
				Arrays.sort(users);
				try {
					writer = new BufferedWriter(new FileWriter("users.txt"));
					Arrays.sort(users);
					System.out.println("Writing");
					for (User user : users) {
						writer.write(userService.toString(user));
						System.out.println(userService.toString(user));
					}
				} finally {
					if (writer != null) {
						writer.close();
					}
				}
			}

		}

	}

	private static Integer selectAChoice(User user) {

		String choice;
		Integer number= null;
		

		System.out.println("________________________________");
		System.out.println("Please make your selection from the menu: ");
		if (user instanceof SuperUser) {
			System.out.println("(0) Log in as another user ");
		}
		System.out.println("(1) Update username");
		System.out.println("(2) Update password");
		System.out.println("(3) Update name");
		System.out.println("(4) Update Exit");
		choice = scanner.nextLine();
		boolean condition = true;
		while(condition) {
			System.out.println(!choice.matches("[01234]"));
			if(!choice.matches("[01234]")) {
			System.err.println("Input Error! ");	
			System.out.println("PLEASE ENTER A NUMERICAL VALUE OF 0,1,2,3, or 4");
			choice = scanner.nextLine();
			}
			else {
				condition = false;
			}
		}
		
		number = Integer.parseInt(choice);	
		return number;
	}

	private static String selectTheUserToLoginAs() {
		System.out.println("Which user would you like to login as? (Type in a valid username)");
		String updatedUsername = scanner.nextLine();
		return updatedUsername;
	}

	private static void userUpdateUsername(User signedInUser) {
		System.out.println("Please type in your new username: ");
		String username = scanner.nextLine();
		signedInUser.setUsername(username);
	}

	private static void userUpdatePassword(User signedInUser) {
		System.out.println("Please type in your new password: ");
		String password = scanner.nextLine();
		signedInUser.setPassword(password);

	}

	private static void userUpdateName(User signedInUser) {
		System.out.println("Please type in your new name: ");
		String name = scanner.nextLine();
		signedInUser.setName(name);

	}

}
