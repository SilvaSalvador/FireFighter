package pt.iul.poo.firefight.starterpack;



import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Bulldozer extends Drivable {

	public Bulldozer(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		if (super.getDir() != null) {
			switch (this.getDir()) {
			case RIGHT:
				return "bulldozer_right";
			case DOWN:
				return "bulldozer_down";
			case UP:
				return "bulldozer_up";
			case LEFT:
				return "bulldozer_left";
			}
		}
		return "bulldozer_up";
	}

	@Override
	public void uniqueFeature(Point2D newPosition) {
		ImageTile b = game.hasImageBurnableAt(newPosition);
		if (b != null) {
			((Vegetation) b).cut();
		}
	}

	@Override
	public void fireFeature(Point2D p) {		
	}
}
