package com.company;

import java.io.FileNotFoundException;

import com.company.Game.GameHandler;

public class Main {

	// https://www.youtube.com/watch?v=PUCV76sM-_I

	public static void main(String[] args) throws FileNotFoundException {
		/*
		 * final FileOutputStream fos = new FileOutputStream("./src/com/company/Pictures/Test.txt", true); final PrintWriter pw = new PrintWriter(fos);
		 * 
		 * pw.print("Test"); pw.close();
		 * 
		 */
		GameHandler.start();
    }
}