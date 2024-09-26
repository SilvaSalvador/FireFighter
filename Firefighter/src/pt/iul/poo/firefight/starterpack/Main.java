package pt.iul.poo.firefight.starterpack;


import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		
		GameEngine game = GameEngine.getInstance();
		try {
			game.start();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound");
		}
	}
}
