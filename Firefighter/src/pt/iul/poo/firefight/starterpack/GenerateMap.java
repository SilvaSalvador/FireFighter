package pt.iul.poo.firefight.starterpack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Point2D;

public class GenerateMap {
	public static void readFile(File file) throws FileNotFoundException {
		GameEngine game = GameEngine.getInstance();
		int GRID_HEIGHT = game.GRID_HEIGHT;
		Scanner s = new Scanner(file);
		int count = 0;
		while (s.hasNextLine()) {
			if (count < GRID_HEIGHT) {
				String[] eachTerrain = s.nextLine().split("");
				for (int i = 0; i < eachTerrain.length; i++) {
					ImageTile img = GameElement.create(eachTerrain[i], new Point2D(i, count));

					game.getImageList().add(img);

				}
			} else {
				String[] inputs = s.nextLine().split(" ");
				if (inputs[0].equals("Fireman")) {
					ImageTile a = GameElement.create("Fireman",
							new Point2D(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2])));
					game.setFireman((Fireman) a);
					game.getImageList().add(a);

				} else {
					ImageTile image = GameElement.create(inputs[0],
							new Point2D(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2])));
					game.getImageList().add(image);
				}
			}
			count++;
		}
		s.close();
	}


}
