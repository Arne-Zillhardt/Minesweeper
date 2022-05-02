package com.company.Game;

import java.awt.Color;
import java.util.ArrayList;

public class Label extends java.awt.Label {
	boolean locked;
	boolean mine;
	boolean empty;
	int positionX;
	int positionY;

	public Label(boolean l, boolean m, boolean e, int x, int y) {
		locked = l;
		mine = m;
		empty = e;
		positionX = x;
		positionY = y;
	}

	public void checkNeighbours() {
		if (!this.mine) {
			ArrayList<Label> neighbours = getNeighboursWithMines(this);

			if (!(neighbours.size() <= 0)) {
				//this.setText(String.valueOf(neighbours.size()));
				this.locked = true;
				this.empty = false;
				//this.setBackground(Color.WHITE);
			}
		}
	}

	public ArrayList<Label> getNeighboursWithMines (Label label01){
		ArrayList<Label> neighbours = new ArrayList<>();

		for (Label label02 : GUI.labels) {
			if (((label02.positionY == (label01.positionY - 1) || label02.positionY == (label01.positionY + 1)) && label02.positionX == label01.positionX) || ((label02.positionX == (label01.positionX - 1) || label02.positionX == (label01.positionX + 1)) && label02.positionY == label01.positionY || ((label02.positionX == (label01.positionX + 1) || label02.positionX == (label01.positionX - 1)) && ((label02.positionY) == (label01.positionY - 1)|| label02.positionY == (label01.positionY + 1))))) {
				if (label02.mine) {
					neighbours.add(label02);
				}
			}
		}

		return neighbours;
	}

	public void mark() {
		int add = 0;
		boolean end = false;

		ArrayList<Label> marked = new ArrayList<>();

		this.setBackground(Color.YELLOW);
		marked.add(this);

		int max = marked.size();

		while (!end) {
			for (int i = 0; i < max; i++) {
				Label label01 = marked.get(i);
				ArrayList<Label> neighbours = getNeighbours(label01);

				add = marked.size();

				for (Label label02 : neighbours) {
					if (!marked.contains(label02) && !label02.locked && label02.empty) {
						label02.setBackground(Color.YELLOW);
						marked.add(label02);
					}

					if (!label02.mine && label02.locked) {
						ArrayList<Label> tmp = label02.getNeighboursWithMines(label02);
						label02.setBackground(Color.WHITE);
						label02.setText(String.valueOf(tmp.size()));
					}
				}

				if (!(add < marked.size())) {
					end = true;
				}

				max = marked.size();
			}
		}
	}

	public ArrayList<Label> getNeighbours (Label label01){
		ArrayList<Label> neighbours = new ArrayList<>();

		for (Label label02 : GUI.labels) {
			if (((label02.positionY == (label01.positionY - 1) || label02.positionY == (label01.positionY + 1)) && label02.positionX == label01.positionX) || ((label02.positionX == (label01.positionX - 1) || label02.positionX == (label01.positionX + 1)) && label02.positionY == label01.positionY || ((label02.positionX == (label01.positionX + 1) || label02.positionX == (label01.positionX - 1)) && ((label02.positionY) == (label01.positionY - 1)|| label02.positionY == (label01.positionY + 1))))) {
				neighbours.add(label02);
			}
		}

		return neighbours;
	}
}
