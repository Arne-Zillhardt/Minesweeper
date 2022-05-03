package com.company.Game;

import java.awt.Color;
import java.util.ArrayList;

public class Label extends java.awt.Label {
	boolean locked;
	boolean mine;
	boolean empty;
	boolean defused;
	boolean text;
	int positionX;
	int positionY;

	public Label(boolean l, boolean m, boolean e, int x, int y) {
		locked = l;
		mine = m;
		empty = e;
		defused = false;
		text = false;
		positionX = x;
		positionY = y;
	}

	public void checkNeighbours() {
		if (!mine) {
			final ArrayList<Label> neighbours = getNeighboursWithMines(this);

			if (!(neighbours.size() <= 0)) {
				//this.setText(String.valueOf(neighbours.size()));
				locked = true;
				empty = false;
				//this.setBackground(Color.WHITE);
			}
		}
	}

	public ArrayList<Label> getNeighboursWithMines (Label label01){
		final ArrayList<Label> neighbours = new ArrayList();

		for (final Label label02 : GUI.labels) {
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

		final ArrayList<Label> marked = new ArrayList();

		final ArrayList<Label> tmp1 = getNeighboursWithMines(this);

		setBackground(Color.WHITE);

		if (tmp1.size() != 0) {
			setText(String.valueOf(tmp1.size()));
			text = true;
		}

		marked.add(this);

		int max = marked.size();

		if (!locked && !mine) {
			while (!end) {
				for (int i = 0; i < max; i++) {
					final Label label01 = marked.get(i);
					final ArrayList<Label> neighbours = getNeighbours(label01);

					add = marked.size();

					for (final Label label02 : neighbours) {
						if (!marked.contains(label02) && !label02.locked) {
							label02.setBackground(Color.WHITE);
							marked.add(label02);
						}

						if (!label02.mine) {
							final ArrayList<Label> tmp = label02.getNeighboursWithMines(label02);

							if (tmp.size() != 0) {
								label02.setBackground(Color.WHITE);
								label02.setText(String.valueOf(tmp.size()));
								label02.text = true;
							} else {
								label02.empty = true;
							}
						}
					}

					if (!(add < marked.size())) {
						end = true;
					}

					max = marked.size();
				}
			}
		}
	}

	public ArrayList<Label> getNeighbours (Label label01){
		final ArrayList<Label> neighbourList = new ArrayList();

		for (final Label label02 : GUI.labels) {
			if (((label02.positionY == (label01.positionY - 1) || label02.positionY == (label01.positionY + 1)) && label02.positionX == label01.positionX) || ((label02.positionX == (label01.positionX - 1) || label02.positionX == (label01.positionX + 1)) && label02.positionY == label01.positionY || ((label02.positionX == (label01.positionX + 1) || label02.positionX == (label01.positionX - 1)) && ((label02.positionY) == (label01.positionY - 1)|| label02.positionY == (label01.positionY + 1))))) {
				neighbourList.add(label02);
			}
		}

		return neighbourList;
	}
}
