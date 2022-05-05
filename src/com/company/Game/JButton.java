package com.company.Game;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.Icon;


public class JButton extends javax.swing.JButton {

	boolean locked;
	boolean mine;
	boolean empty;
	boolean defused;
	boolean text;
	int positionX;
	int positionY;

	public JButton() {
		super();
	}

	public JButton(Icon icon) {
		super(icon);
	}

	public JButton(String text) {
		super(text);
	}

	public JButton(Action a) {
		super(a);
	}

	public JButton(String text, Icon icon) {
		super(text, icon);
	}

	public JButton(boolean l, boolean m, boolean e, int x, int y) {
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
			final ArrayList<JButton> neighbours = getNeighboursWithMines(this);

			if (!(neighbours.size() <= 0)) {
				// this.setText(String.valueOf(neighbours.size()));
				locked = true;
				empty = false;
				// this.setBackground(Color.WHITE);
			}
		}
	}

	public ArrayList<JButton> getNeighboursWithMines(JButton label01) {
		final ArrayList<JButton> neighbours = new ArrayList();

		for (final JButton label02 : GUI.labels) {
			if (((label02.positionY == (label01.positionY - 1) || label02.positionY == (label01.positionY + 1)) && label02.positionX == label01.positionX)
					|| ((label02.positionX == (label01.positionX - 1) || label02.positionX == (label01.positionX + 1)) && label02.positionY == label01.positionY
							|| ((label02.positionX == (label01.positionX + 1) || label02.positionX == (label01.positionX - 1))
									&& ((label02.positionY) == (label01.positionY - 1) || label02.positionY == (label01.positionY + 1))))) {
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

		final ArrayList<JButton> marked = new ArrayList();

		final ArrayList<JButton> tmp1 = getNeighboursWithMines(this);

		setBackground(Color.LIGHT_GRAY);

		if (tmp1.size() != 0) {
			setText(String.valueOf(tmp1.size()));
			text = true;

			if (tmp1.size() == 1) {
				setForeground(Color.BLUE);
			}
			if (tmp1.size() == 2) {
				setForeground(Color.GREEN);
			}
			if (tmp1.size() == 3) {
				setForeground(Color.RED);
			}
			if (tmp1.size() == 4) {
				setForeground(new Color(178, 58, 238));
			}
		}

		setIcon(null);

		marked.add(this);

		int max = marked.size();

		if (!locked && !mine && !text && !defused) {
			while (!end) {
				for (int i = 0; i < max; i++) {
					final JButton label01 = marked.get(i);
					final ArrayList<JButton> neighbours = getNeighbours(label01);

					add = marked.size();

					for (final JButton label02 : neighbours) {
						if (!marked.contains(label02) && !label02.locked && !label02.mine) {
							label02.setBackground(Color.LIGHT_GRAY);
							marked.add(label02);
						}

						if (!label02.mine) {
							final ArrayList<JButton> tmp = label02.getNeighboursWithMines(label02);

							if (!label02.defused) {
								if (tmp.size() != 0) {
									label02.setBackground(Color.LIGHT_GRAY);
									label02.setText(String.valueOf(tmp.size()));
									label02.text = true;
									label02.setIcon(null);

									if (tmp.size() == 1) {
										label02.setForeground(Color.BLUE);
									}
									if (tmp.size() == 2) {
										label02.setForeground(Color.GREEN);
									}
									if (tmp.size() == 3) {
										label02.setForeground(Color.RED);
									}
									if (tmp.size() == 4) {
										label02.setForeground(new Color(178, 58, 238));
									}
								} else {
									label02.empty = true;
									label02.setIcon(null);
								}
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

	public ArrayList<JButton> getNeighbours(JButton label01) {
		final ArrayList<JButton> neighbourList = new ArrayList();

		for (final JButton label02 : GUI.labels) {
			if (((label02.positionY == (label01.positionY - 1) || label02.positionY == (label01.positionY + 1)) && label02.positionX == label01.positionX)
					|| ((label02.positionX == (label01.positionX - 1) || label02.positionX == (label01.positionX + 1)) && label02.positionY == label01.positionY
							|| ((label02.positionX == (label01.positionX + 1) || label02.positionX == (label01.positionX - 1))
									&& ((label02.positionY) == (label01.positionY - 1) || label02.positionY == (label01.positionY + 1))))) {
				neighbourList.add(label02);
			}
		}

		return neighbourList;
	}

}
