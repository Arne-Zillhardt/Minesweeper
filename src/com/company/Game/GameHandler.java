package com.company.Game;

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
		Listener.count = 0;
	}

	public static ArrayList<JButton> getMines(JButton button) {
		final ArrayList<JButton> ret = new ArrayList();

		while (ret.size() < GUI.mines) {
			int x = (int) (Math.random() * (GUI.columns));
			int y = (int) (Math.random() * (GUI.rows));

			JButton label = new JButton(false, false, false, x, y);

			while (label.positionX == button.positionX && label.positionY == button.positionY) {
				x = (int) (Math.random() * (GUI.columns));
				y = (int) (Math.random() * (GUI.rows));
				label = new JButton(false, false, false, x, y);
			}

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

		return ret;
	}

	public static void end() {
		clock.interrupt();

		for (final JButton label : GUI.labels) {
			if (label.mine) {
				label.setIcon(GUI.mineIcon);
			}

			if (label.defused && label.mine) {
				label.setIcon(GUI.defusedIcon);
			} else if (label.defused && !label.mine) {
				label.setIcon(GUI.falseDefuse);
				label.setText(null);
			}
		}
	}

	public static void win() {
		clock.interrupt();

		for (final JButton label : GUI.labels) {
			if (label.mine) {
				label.setIcon(GUI.defusedIcon);
			}
		}

		try {
			Thread.sleep(10);
		} catch (final Exception e) {

		}

		GUI.EnterHighScore();
	}
}