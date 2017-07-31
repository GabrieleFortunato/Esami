package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class dbUtility {
	@SuppressWarnings("resource")
	public static String stringaUser() {
		String s = null;
		try {
			String fileName = "pass.txt";
			FileReader file = new FileReader(fileName);
			BufferedReader b = new BufferedReader(file);
			s = b.readLine();
		} catch (IOException e) {
			
		}
		return s;
	}
	
	@SuppressWarnings("resource")
	public static String stringaPass() {
		String s = null;
		try {
			String fileName = "pass.txt";
			FileReader file = new FileReader(fileName);
			BufferedReader b = new BufferedReader(file);
			s = b.readLine();
		} catch (IOException e) {
			
		}
		return s;
	}
	
}
