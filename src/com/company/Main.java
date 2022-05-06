package com.company;

import java.io.FileNotFoundException;

import com.company.Game.GUI;
import com.company.Game.GameHandler;

public class Main {

	// https://www.youtube.com/watch?v=PUCV76sM-_I
	// Ressourcen
	// SQL injection verhindern

	public static void main(String[] args) throws FileNotFoundException {
		final GUI gui = new GUI();
		gui.Prepare();
		GameHandler.start();
    }
}