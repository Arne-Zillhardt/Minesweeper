package com.company.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.company.Database.Test;

public class Listener implements ActionListener, MouseListener {
	MouseEvent ev;
	static int count = 0;
	long time;

	public void actionPerformed(ActionEvent e) {
		final JButton button = (JButton) e.getSource();

		if (button.getText().equals("Easy")) {
			GUI.difficulty = 1;
			GUI.create();
		}
		if (button.getText().equals("Medium")) {
			GUI.difficulty = 2;
			GUI.create();
		}
		if (button.getText().equals("Hard")) {
			GUI.difficulty = 3;
			GUI.create();
		}
		if (button.getText().equals("Submit")) {
			Test.Insert(GUI.textfield.getText(), GUI.time);
			GUI.ViewHighScores();
		}

		if (button.equals(GUI.restartButton)) {
			GameHandler.restart();
		}
	}


	public void mouseClicked(MouseEvent e) {
		GameHandler.startClock();
		final JButton label = (JButton) e.getComponent();
		final long nextTime = System.currentTimeMillis();

		if (count == 0 && e.getButton() == 1) {
			count++;
			GUI.AfterClick(label);
		}

		if (count != 0) {
			if (nextTime - time < 110 && ev != null && label.text) {
				if (ev.getButton() == 1 && e.getButton() == 3 || ev.getButton() == 3 && e.getButton() == 1) {
					label.OpenUp();
				}
			} else {
				if (e.getButton() == 1 && !label.defused) {
					if (label.mine) {
						label.mine = false;
						label.setIcon(GUI.mineHit);

						GUI.restartButton.setIcon(GUI.dead);

						GameHandler.end();
					} else {
						label.mark();
					}
				}

				if (e.getButton() == 3 && !label.empty && !label.text) {
					if (GUI.mines >= 1 && !label.defused) {
						GUI.mines -= 1;
						label.locked = true;
						label.defused = true;
						label.setIcon(null);
						label.setIcon(GUI.defusedIcon);

						if (label.mine) {
							GUI.activMines -= 1;
						}
					} else if (label.locked) {
						GUI.mines += 1;
						label.locked = false;
						label.defused = false;
						label.setIcon(GUI.icon);

						if (label.mine) {
							GUI.activMines += 1;
						}
					}

					GUI.mine.setText(String.valueOf(GUI.mines));
				}
			}

			if (GUI.activMines == 0) {
				label.setIcon(GUI.defusedIcon);
				GUI.restartButton.setIcon(GUI.win);
				GameHandler.win();
			}
		}

		ev = e;
		time = System.currentTimeMillis();
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		// final Label label = (Label) e.getComponent();

		// System.out.println(label.getNeighboursWithMines(label).size());
	}

	public void mouseExited(MouseEvent e) {

	}
}
