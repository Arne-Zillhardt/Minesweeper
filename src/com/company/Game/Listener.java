package com.company.Game;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class Listener implements ActionListener, MouseListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

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


	@Override
	public void mouseClicked(MouseEvent e) {
		GameHandler.startClock();

		Label label = (Label) (e.getComponent());

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

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
