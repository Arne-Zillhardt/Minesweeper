package com.company.Game;

import java.awt.Color;
import java.util.ArrayList;

public class GameHandler {
	static Thread clock;
	static ArrayList<JButton> checkedLabels = new ArrayList();

	public static void start() {
		clock = new Thread(new GameClock());
		GUI.setUp();
	}

	static void startClock() {
		if (!clock.isAlive()) {
			clock.start();
		}
	}

	public static void restart() {
		clock.interrupt();
		clock = new Thread(new GameClock());
		GUI.create();
	}

	public static ArrayList<JButton> getMines() {
		final ArrayList<JButton> ret = new ArrayList();

		int x = (int) (Math.random() * (GUI.columns - 1));
		int y = (int) (Math.random() * (GUI.rows - 1));

		JButton label = new JButton(false, false, false, x, y);

		ret.add(label);
		while (ret.size() < GUI.mines) {
			x = (int) (Math.random() * (GUI.columns));
			y = (int) (Math.random() * (GUI.rows));

			label = new JButton(false, false, false, x, y);

			boolean tmp = true;
			for (final JButton label1 : ret) {
				if (label1.positionX == label.positionX && label1.positionY == label.positionY) {
					tmp = false;
				}
			}

			if (tmp) {
				ret.add(label);
			}
		}

		System.out.println();
		return ret;
	}

	public static void end() {
		for (final JButton label : GUI.labels) {
			if (label.mine) {
				label.setBackground(Color.RED);
				clock.interrupt();
			}

			if (label.defused && label.mine) {
				label.setBackground(Color.GREEN);
			} else if (label.defused && !label.mine) {
				label.setBackground(Color.BLACK);
			}

			label.disable();
		}

		GUI.mine.setText("You lost!");
	}

	public static void win() {
		for (final JButton label : GUI.labels) {
			if (label.mine) {
				label.setBackground(Color.RED);
				clock.interrupt();
			}

			label.disable();
		}

		GUI.mine.setText("You won!");
	}
}