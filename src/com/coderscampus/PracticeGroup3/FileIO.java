package com.coderscampus.PracticeGroup3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {

	
	public static void howToReadFileUsingBuffer() throws IOException 
	{
		System.out.println("This is BufferedReader example");
		File file = new File("data.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String st;
		while((st= br.readLine())!= null)
		{
			System.out.println(st);
		}
	}
	public static void howToReadFileReader() throws IOException {
		System.out.println("\nThis is FileReader example");
		// pass the path to the file as a parameter 
	    FileReader fr = new FileReader("data.txt"); 
	  
	    int i; 
	    while ((i=fr.read()) != -1) 
	      System.out.print((char) i); 
	  } 
	public static void howToUseScanner() throws IOException 
	{
		System.out.println("\n\nThis is a Scanner Example");
		File file = new File("data.txt");
		Scanner sc = new Scanner(file);
		
		while(sc.hasNextLine())
			System.out.println(sc.nextLine());
		
		
			sc.close();
		
		
	}
	public static void main(String[] args) throws Exception {
		
		howToReadFileUsingBuffer();
		howToReadFileReader();
		howToUseScanner();

	}

}


