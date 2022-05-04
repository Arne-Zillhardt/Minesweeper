package com.company;

import java.io.FileNotFoundException;
import java.util.HashMap;

import com.company.Database.Test;
import com.company.Game.GameHandler;

public class Main {

	// https://www.youtube.com/watch?v=PUCV76sM-_I
	//

	public static void main(String[] args) throws FileNotFoundException {
		GameHandler.start();
		final HashMap<Integer, String> test = Test.Select();

		for (final int i : test.keySet()) {
			System.out.println(i + " " + test.get(i));
		}
    }
}