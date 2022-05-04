package com.company.Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Test {

	public static void start() {
		Connection conn = null;
		 
        try {
 
			final String dbURL = "jdbc:sqlserver://PEGASUS2";
            final String user = "praktikant";
            final String pass = "prak#2022";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
				System.out.println("Connected");

                final DatabaseMetaData dm = conn.getMetaData();

				final String sql = "DELETE FROM Minesweeper_Highscores WHERE Player_ID = '1'";

				final Statement statement = conn.createStatement();

				final ResultSet result = statement.executeQuery(sql);
			} else {
				System.out.println("The connection failed");
            }
 
        } catch (final SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (final SQLException ex) {
                ex.printStackTrace();
            } 
		}
        
		System.out.println("Finished");
	}

	public static void Insert(String name, int score) {
		Connection conn = null;

		try {

			final String dbURL = "jdbc:sqlserver://PEGASUS2";
			final String user = "praktikant";
			final String pass = "prak#2022";
			conn = DriverManager.getConnection(dbURL, user, pass);
			if (conn != null) {
				final String sql = "INSERT INTO Minesweeper_Highscores (Player_Name, Player_Score) VALUES ('" + name + "', '" + score + "')";

				final Statement statement = conn.createStatement();
				statement.executeUpdate(sql);
			} else {
				System.out.println("The connection failed");
			}

		} catch (final SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (final SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static HashMap<Integer, String> Select() {
		Connection conn = null;
		final HashMap<Integer, String> ret = new HashMap();

		try {

			final String dbURL = "jdbc:sqlserver://PEGASUS2";
			final String user = "praktikant";
			final String pass = "prak#2022";
			conn = DriverManager.getConnection(dbURL, user, pass);
			if (conn != null) {
				final String sql = "SELECT * FROM Minesweeper_Highscores ORDER BY Player_Score DESC";

				final Statement statement = conn.createStatement();
				final ResultSet result = statement.executeQuery(sql);

				while (result.next()) {
					final String name = result.getString("Player_Name");
					final int score = result.getInt("Player_Score");

					ret.put(score, name);
				}
			} else {
				System.out.println("The connection failed");
			}

		} catch (final SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (final SQLException ex) {
				ex.printStackTrace();
			}
		}

		return ret;
	}
}
