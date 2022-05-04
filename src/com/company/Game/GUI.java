package com.company.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GUI {

	static ArrayList<JButton> labels;
	static JFrame frame = new JFrame("Minesweeper");
	static JPanel elements;
	static JPanel infos;
	static JButton restartButton;
	static ImageIcon icon = new ImageIcon(((new ImageIcon("C:\\\\Users\\\\Praktikant\\\\Pictures\\\\Screenshot 2022-05-04 094228.png")).getImage())
			.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
	static ImageIcon defusedIcon = new ImageIcon(((new ImageIcon("C:\\\\Users\\\\Praktikant\\\\Pictures\\\\Screenshot 2022-05-04 100509.png")).getImage())
			.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
	static ImageIcon mineIcon = new ImageIcon(((new ImageIcon("C:\\\\Users\\\\Praktikant\\\\Pictures\\\\Screenshot 2022-05-04 101038.png")).getImage())
			.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
	static ImageIcon mineHit = new ImageIcon(((new ImageIcon("C:\\\\Users\\\\Praktikant\\\\Pictures\\\\Screenshot 2022-05-04 101457.png")).getImage())
			.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
	static ImageIcon falseDefuse = new ImageIcon(((new ImageIcon("C:\\\\Users\\\\Praktikant\\\\Pictures\\\\Screenshot 2022-05-04 103745.png")).getImage())
			.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
	static ImageIcon dead = new ImageIcon(((new ImageIcon("C:\\\\Users\\\\Praktikant\\\\Pictures\\\\Screenshot 2022-05-04 101729.png")).getImage())
			.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
	static ImageIcon win = new ImageIcon(((new ImageIcon("C:\\\\Users\\\\Praktikant\\\\Pictures\\\\Screenshot 2022-05-04 101904.png")).getImage())
			.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
	static ImageIcon restart = new ImageIcon(((new ImageIcon("C:\\\\Users\\\\Praktikant\\\\Pictures\\\\Screenshot 2022-05-04 101817.png")).getImage())
			.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
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

		frame.setSize(((50 * rows) + (2 * rows)), ((50 * columns) + (2 * columns)) + infos.getHeight() + 25);

		elements.removeAll();
		elements.setLayout(new GridLayout(columns, rows, 2, 2));
		elements.setBackground(Color.GRAY);

		time = 0;

		text.setText(String.valueOf(time));
		text.setFont(new Font("Arial", Font.BOLD, 20));

		infos.removeAll();

		mine = new JLabel(String.valueOf(mines));
		mine.setFont(new Font("Arial", Font.BOLD, 20));

		infos.add(mine);

		restartButton = new JButton();
		restartButton.addActionListener(new Listener());
		restartButton.setIcon(restart);
		restartButton.setPreferredSize(new Dimension(75, 75));

		infos.add(restartButton);
		text.setText(String.valueOf(time));
		infos.add(text);

		frame.add(elements);

		labels.clear();

		setFields();
		setMines();

		for (final JButton label : labels) {
			if (!label.mine) {
				label.checkNeighbours();
			}
		}

		activMines = mines;
	}

	public static void setFields() {
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				// final JButton label = new JButton(false, false, false, i, j);

				final JButton label = new JButton(false, false, false, i, j);
				label.setIcon(icon);
				label.setFont(new Font("Arial", Font.BOLD, 20));

				label.addMouseListener(new Listener());
				labels.add(label);
				elements.add(label);
			}
		}
	}

	public static void setMines() {
		final ArrayList<JButton> list = GameHandler.getMines();

		for (final JButton label1 : list) {
			for (final JButton label2 : labels) {
				if (label2.positionX == label1.positionX && label2.positionY == label1.positionY) {
					label2.mine = true;
					label2.empty = false;
				}
			}
		}
	}
}