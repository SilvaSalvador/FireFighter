package pt.iul.poo.firefight.starterpack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.JOptionPane;

import pt.iul.ista.poo.gui.ImageMatrixGUI;

public class Scores {
	private static String username;
	private static int pointsThisLevel = 0;

	public static void getNickname() {
		username = JOptionPane.showInputDialog("Username");
	}

	public static void burnt() {
		if (pointsThisLevel > 0) {
			pointsThisLevel -= 1;
		}
	}

	public static void watersFire() {
		pointsThisLevel += 5;
	}

	public static void waterPlane() {
		pointsThisLevel += 1;
	}

	public static void pointsAndMenu(File f) throws FileNotFoundException {
		savePoints(f);
		ImageMatrixGUI.getInstance().setMessage(Menu(f));
	}

	public static int getPoints() {
		return pointsThisLevel;
	}

	public static void savePoints(File f) throws FileNotFoundException {
		File score = new File("pontuacoes" + f.getName());
		Queue<Player> fila = new PriorityQueue<>((p1, p2) -> (p2.getScore() - p1.getScore()));
		Player curr = new Player(username, pointsThisLevel);
		fila.offer(curr);
		readPreviousScores(score, fila);
		PrintWriter w = new PrintWriter(score);
		writeTop5(w, fila);
		w.close();
	}

	public static void readPreviousScores(File scores, Queue<Player> fila) throws FileNotFoundException {
		if (scores.exists()) {
			Scanner s = new Scanner(scores);
			while (s.hasNextLine()) {
				String[] z = s.nextLine().split("-");
				Player a = new Player(z[1], Integer.parseInt(z[0]));
				fila.offer(a);
			}
		}
	}

	public static void writeTop5(PrintWriter w, Queue<Player> fila) {
		int count = 0;
		while (!fila.isEmpty() && count < 5) {
			Player fran = fila.poll();
			w.println(fran.getScore() + "-" + fran.getUsername());
			count++;
		}
	}

	public static String Menu(File f) throws FileNotFoundException {
		File score = new File("pontuacoes" + f.getName());
		Scanner s = new Scanner(score);
		String menu = "";
		int c = 1;
		while (s.hasNextLine()) {
			menu += c + "place " + s.nextLine() + "\n";
			c++;
		}
		menu += "Your Score " + pointsThisLevel;
		pointsThisLevel = 0;
		s.close();
		return menu;
	}

}
