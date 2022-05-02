package com.company.Game;

public class GameClock implements Runnable{
	static void clock() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				Thread.sleep(1000);
				GUI.time += 1;
				GUI.text.setText(String.valueOf(GUI.time));
			}
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		clock();
	}
}