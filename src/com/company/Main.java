package com.company;

import javax.swing.JLabel;

import com.company.Game.GameHandler;

public class Main {

    public static void main(String[] args) {
		final JLabel label = new JLabel();
		label.setIcon(null);

        GameHandler.start();
    }
}