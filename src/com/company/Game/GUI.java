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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.company.Database.Test;

public class GUI {

	static ArrayList<JButton> labels;
	static JFrame frame = new JFrame("Minesweeper");
	static JFrame nframe;
	static JPanel elements;
	static JPanel after;
	static JPanel infos;
	static JButton restartButton;
	static JTextField textfield;
	static ImageIcon icon;
	static ImageIcon defusedIcon;
	static ImageIcon mineIcon;
	static ImageIcon mineHit;
	static ImageIcon falseDefuse;
	static ImageIcon dead;
	static ImageIcon win;
	static ImageIcon restart; // = new ImageIcon(((new ImageIcon("./src/com/company/Pictures/Minesweeper_Restart.png")).getImage())
	// .getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
	static int columns;
	static int rows;
	static int mines;
	static int activMines;
	static JLabel text;
	static JLabel mine;
	static int time;
	public static int difficulty;

	public static void setUp() {
		nframe = new JFrame();
		labels = new ArrayList();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		elements = new JPanel();
		elements.setLayout(null);

		after = new JPanel();
		after.setLayout(null);

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

		activMines = mines;
	}

	public static void AfterClick(JButton button) {
		setMines(button);

		for (final JButton label : labels) {
			if (!label.mine) {
				label.checkNeighbours();
			}
		}
	}

	public static void EnterHighScore() {
		after.removeAll();
		after.setLayout(null);

		nframe.setSize(700, 500 + infos.getHeight());

		final JButton button = new JButton("Submit");
		button.setBounds(250, 300, 200, 50);

		textfield = new JTextField();
		textfield.setBounds(250, 200, 200, 50);

		final JLabel text2 = new JLabel("Enter your name");
		text2.setBounds(300, 100, 200, 50);
		text2.setForeground(Color.BLACK);

		after.setBackground(Color.WHITE);
		after.add(button);
		after.add(textfield);
		after.add(text2);
		button.addActionListener(new Listener());

		nframe.add(after);
		nframe.setVisible(true);
	}

	public static void ViewHighScores() {
		after.removeAll();
		nframe.setSize(700, 500);

		final ArrayList<String> dates = Test.Select();

		final String[] data = new String[dates.size()];

		for (int i = 0; i < dates.size(); i += 2) {
			final String name = dates.get(i);
			final String score = dates.get(i + 1);

			data[i] = name + " " + score;
		}

		final JList<String> list = new JList<String>(data);
		// list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		// list.setBounds(300, 100, 200, 50);
		list.setFont(new Font("Arial", Font.BOLD, 20));
		
		final JScrollPane listScroller = new JScrollPane();
		listScroller.setBounds(250, 100, 200, 300);
		listScroller.setViewportView(list);

		after.add(listScroller);
		nframe.add(after);
		nframe.setSize(700, 500 + infos.getHeight());
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

	public static void setMines(JButton button) {
		final ArrayList<JButton> list = GameHandler.getMines(button);

		for (final JButton label1 : list) {
			for (final JButton label2 : labels) {
				if (label2.positionX == label1.positionX && label2.positionY == label1.positionY) {
					label2.mine = true;
					label2.empty = false;
					// label2.setIcon(mineIcon);
				}
			}
		}
	}

	public void Prepare() {
		ImageIcon testIcon = new ImageIcon(
				((new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("Minesweeper_Field.png"))).getImage()).getScaledInstance(50, 50,
						java.awt.Image.SCALE_SMOOTH));
		icon = testIcon;
		
		testIcon = new ImageIcon(
				((new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("Minesweeper_defused.png"))).getImage()).getScaledInstance(50, 50,
						java.awt.Image.SCALE_SMOOTH));
		defusedIcon = testIcon;
		
		testIcon = new ImageIcon(
				((new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("Minesweeper_Mine.png"))).getImage()).getScaledInstance(50, 50,
						java.awt.Image.SCALE_SMOOTH));
		mineIcon = testIcon;
		
		testIcon = new ImageIcon(
				((new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("Minesweeper_MineHit.png"))).getImage()).getScaledInstance(50, 50,
						java.awt.Image.SCALE_SMOOTH));
		mineHit = testIcon;
		
		testIcon = new ImageIcon(
				((new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("Minesweeper_DefusedFalse.png"))).getImage()).getScaledInstance(50, 50,
						java.awt.Image.SCALE_SMOOTH));
		falseDefuse = testIcon;
		
		testIcon = new ImageIcon(
				((new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("Minesweeper_Lost.png"))).getImage()).getScaledInstance(75, 75,
						java.awt.Image.SCALE_SMOOTH));
		dead = testIcon;
		
		testIcon = new ImageIcon(
				((new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("Minesweeper_Won.png"))).getImage()).getScaledInstance(75, 75,
						java.awt.Image.SCALE_SMOOTH));
		win = testIcon;
		
		testIcon = new ImageIcon(
				((new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("Minesweeper_Restart.png"))).getImage()).getScaledInstance(75, 75,
						java.awt.Image.SCALE_SMOOTH));
		restart = testIcon;
	}
}