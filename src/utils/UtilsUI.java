package utils;

import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * Utilites for the console
 * 
 * @author jfernandezpe
 *
 */
public class UtilsUI {

	private static Scanner reader = new Scanner(System.in);

	/**
	 * Get string to the console
	 * 
	 * @param text
	 * @return
	 */
	public static String getConsole(String text) {
		String svoid = "";
		boolean valid = false;
		String data = null;
		while (!valid) {
			System.out.print(text);
			data = reader.nextLine();
			if (!data.equals((String) svoid)) {
				valid = true;
			}
		}
		return data;
	}
	

	/**
	 * Get a String 
	 * 
	 * @param text
	 * @param options
	 * @return
	 */
	public static String getCololeOption(String text, String[] options, String error){
		boolean valid = false;
		String data = null;
		while(!valid){
			System.out.print(text);
			data = reader.nextLine();
			data = data.toLowerCase();
			for (int i = 0; i < options.length; i++){
				options[i].toLowerCase();
				if (options[i].equals(data)){
					valid = true;
				}
			}
			if (!valid){
				System.out.println(error+" "+data);
			}
		}
		return data;		
	}
	
	public static String getCololeOption(String text, String[] options){
		return getCololeOption(text, options, "Not found");
	}
	
	/**
	 * Get int for the console
	 * 
	 * @param text
	 * @return
	 */
	public static int getConsoleInt(String text) {
		return getConsoleInt(text, false, 0);
	}
	
	/**
	 * Get int for the console
	 * 
	 * @param text
	 * @return
	 */
	public static int getConsoleInt(String text, int vdefault) {
		return getConsoleInt(text, true, vdefault);
	}
	
	/**
	 * Get int for the console
	 * 
	 * @param text
	 * @return
	 */
	public static int getConsoleInt(String text, boolean nulltable) {
		return getConsoleInt(text, nulltable, 0);
	}
	
	/**
	 * Get int for the console
	 * 
	 * @param text
	 * @return
	 */
	public static int getConsoleInt(String text, boolean nulltable, int vdefault) {
		boolean valid = false;
		int data = 0;
		while (!valid) {
			System.out.print(text);
			try {
				data = Integer.parseInt(reader.nextLine());
			} catch (NumberFormatException exception) {
				if (!nulltable){
					System.out.println("Introduce un número entero");
				} else {
					data = vdefault;
				}
			}
			if (data != 0 || (nulltable)) {
				valid = true;
			}
		}
		return data;
	}
	
	/**
	 * Get a filename with console.
	 * 
	 * @param text
	 * @return
	 */
	public static String getConsoleFilename (String text, String extension){
		String svoid = "";
		boolean valid = false;
		String data = null;
		while (!valid) {
			System.out.print(text);
			data = reader.nextLine();
			if (!data.equals((String) svoid)) {
				String expr = ".*(."+extension+")$";
				boolean cumplePatron = Pattern.matches(expr, data);
				if (!cumplePatron) {
					data += "."+extension;
				}
				valid = true;
			}
		}
		return data;
	}

	
	/**
	 * Formate properly double (autoimplement in getConsoleDouble)
	 * 
	 * @param x
	 * @return
	 */
	public static double StringQuotesDouble(String x) {
		x = x.replace(",", ".");
		Double y = Double.parseDouble(x);
		return y;
	}

	
	/**
	 * Get a double for the console
	 * 
	 * @param text
	 * @return
	 */
	public static Double getConsoleDouble(String text) {
		boolean valid = false;
		double data = 0;
		while (!valid) {
			System.out.print(text);
			try {
				data = StringQuotesDouble(reader.nextLine());
			} catch (NumberFormatException exception) {
				System.out
						.println("Introduce un número (recuerda poner los decimales con punto (.)");
				data = 0;
			}
			if (data != 0) {
				valid = true;
			}
		}
		return data;
	}


	/**
	 * Get an option to the menu
	 * 
	 * @param text
	 * @return
	 */
	public static int menu(String text) {
	
		System.out.print(text);
		
		String order = reader.nextLine();
		int option;
		if (order.equals((String) "q")) {
			option = 99;
		} else {
			try {
				option = Integer.parseInt(order);
			} catch (Exception e) {
				option = 0;
			}
		}
		return option;
	}
	
	public static void close(){
		reader.close();
	}
}
