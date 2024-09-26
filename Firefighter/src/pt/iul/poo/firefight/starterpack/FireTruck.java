package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class FireTruck extends Drivable {

	public FireTruck(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		if (super.getDir() != null) {
			switch (this.getDir()) {
			case RIGHT:
				return "firetruck_right";
			case LEFT:
				return "firetruck_left";
			}
		}
		return "firetruck";
	}

	@Override
	public void uniqueFeature(Point2D p) {
	}

	@Override
	public void fireFeature(Point2D p) {
		for (Point2D p2d : p.getFrontRect(getDir(), 3, 2)) {
			ImageTile img = game.hasImageBurnableAt(p2d);
			if (img != null) {
				if (((Vegetation) img).isBurning()) {
					((Vegetation) img).putOutFire();
					game.getFireman().createWater(p2d, getDir());
				}
			}
		}
	}

}
