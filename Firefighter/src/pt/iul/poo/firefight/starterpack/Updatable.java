package pt.iul.poo.firefight.starterpack;

import java.util.List;

import pt.iul.ista.poo.gui.ImageTile;

public interface Updatable {
	public void update();

	public static void updateAll(List<Updatable> t) {
		for (Updatable img : t) {
			img.update();
		}
	}
}
