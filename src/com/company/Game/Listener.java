package com.company.Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Listener implements ActionListener, MouseListener {

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

		if (button.getText().equals("Restart")) {
			GameHandler.restart();
		}
	}


	public void mouseClicked(MouseEvent e) {
		GameHandler.startClock();

		final Label label = (Label) (e.getComponent());

		if (e.getButton() == 1) {
			if (label.mine) {
				GameHandler.end();
			} else {
				label.mark();
			}
		}

		if (e.getButton() == 3) {
			if (!label.locked && GUI.mines >= 1) {
				GUI.mines -= 1;
				label.locked = true;
				label.setBackground(Color.WHITE);
			} else if (label.locked) {
				GUI.mines += 1;
				label.locked = false;
				label.setBackground(Color.BLACK);
			}

			GUI.mine.setText(String.valueOf(GUI.mines));
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}
}
