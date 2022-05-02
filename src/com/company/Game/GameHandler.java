package com.company.Game;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;

public class GameHandler {
	static Thread clock;
	static ArrayList<Label> checkedLabels = new ArrayList();

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

	public static ArrayList<Label> getMines() {
		final ArrayList<Label> ret = new ArrayList();

		int x = (int) (Math.random() * (GUI.rows - 1));
		int y = (int) (Math.random() * (GUI.columns - 1));

		Label label = new Label(false, false, false, x, y);

		ret.add(label);
		final ArrayList<String> spaß = new ArrayList();

		while (ret.size() < GUI.mines) {
			x = (int) (Math.random() * (GUI.columns - 1));
			y = (int) (Math.random() * (GUI.rows - 1));
			x = (int) (Math.random() * (GUI.rows - 1));
			spaß.add("PositionX: " + x + ", PositionY: " + y);

			label = new Label(false, false, false, x, y);

			for (final Label label1 : ret) {
				if (label.positionX != label1.positionX && label.positionY != label1.positionY) {
					ret.add(label);
					break;
				}
			}
		}

		final boolean weitereWichtigeMitarbeit = true;
		return ret;
	}

	@SuppressWarnings("deprecation")
	public static void end() {
		for (final Label label : GUI.labels) {
			if (label.mine) {
				label.setBackground(Color.RED);
				clock.interrupt();
			}

			label.disable();
		}

		GUI.text.setText("You lost!");
		GUI.infos.removeAll();
		GUI.text.setText("You lost!");
		GUI.infos.add(GUI.text);

		final JButton button = new JButton("Restart");
		button.addActionListener(new Listener());
		GUI.infos.add(button);
	}

	public static void win() {
		for (final Label label : GUI.labels) {
			if (label.mine) {
				label.setBackground(Color.RED);
				clock.interrupt();
			}

			label.disable();
		}

		GUI.text.setText("You won!");
		GUI.infos.removeAll();
		GUI.text.setText("You won!");
		GUI.infos.add(GUI.text);

		final JButton button = new JButton("Restart");
		button.addActionListener(new Listener());
		GUI.infos.add(button);
	}
}