package com.company.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GUI {
	static ArrayList<Label> labels;
	static JFrame frame = new JFrame("Minesweeper");
	static JPanel elements;
	static JPanel infos;
	static int columns;
	static int rows;
	static int mines;
	static int activMines;
	static JLabel text;
	static JLabel mine;
	static int time;
	static int difficulty;

	public static void setUp() {
		labels = new ArrayList();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		elements = new JPanel();
		elements.setLayout(null);

		infos = new JPanel();

		final JButton easy = new JButton("Easy");
		easy.setBounds(250, 100, 200, 50);
		easy.addActionListener(new Listener());
		elements.add(easy);

		final JButton medium = new JButton("Medium");
		medium.setBounds(250, 200, 200, 50);
		medium.addActionListener(new Listener());
		elements.add(medium);

		final JButton hard = new JButton("Hard");
		hard.setBounds(250, 300, 200, 50);
		hard.addActionListener(new Listener());
		elements.add(hard);

		text = new JLabel("Choose the difficulty");
		infos.add(text);

		frame.add(BorderLayout.CENTER, elements);
		frame.add(BorderLayout.NORTH, infos);
		frame.setSize(700, 500);
	}

	public static void create() {
		switch (difficulty) {
		case 1: // easy
			columns = 9;
			rows = 9;
			mines = 10;
			break;
		case 2: // middle
			columns = 16;
			rows = 16;
			mines = 40;
			break;
		case 3: // hard
			columns = 16;
			rows = 30;
			mines = 99;
			break;
		default: // hellMode
			columns = 30;
			rows = 30;
			mines = 696;
			break;
		}

		frame.setSize(((25 * rows) + (2 * rows)), ((25 * columns) + (2 * columns)) + infos.getHeight());

		elements.removeAll();
		elements.setLayout(new GridLayout(columns, rows, 2, 2));

		time = 0;

		text.setText(String.valueOf(time));

		infos.removeAll();

		mine = new JLabel(String.valueOf(mines));

		infos.add(mine);

		final JButton button = new JButton("Restart");
		button.addActionListener(new Listener());
		infos.add(button);
		text.setText(String.valueOf(time));
		infos.add(text);

		frame.add(elements);

		labels.clear();

		setFields();
		setMines();

		for (final Label label : labels) {
			if (!label.mine) {
				label.checkNeighbours();
			}
		}

		activMines = mines;
	}

	public static void setFields() {
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				final Label label = new Label(false, false, false, i, j);
				label.setBackground(Color.BLACK);
				label.addMouseListener(new Listener());
				labels.add(label);
				elements.add(label);
			}
		}
	}

	public static void setMines() {
		final ArrayList<Label> list = GameHandler.getMines();

		for (final Label label1 : list) {
			for (final Label label2 : labels) {
				if (label2.positionX == label1.positionX && label2.positionY == label1.positionY) {
					label2.mine = true;
					label2.empty = false;
				}
			}
		}

		System.out.println();
	}
}