package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Grass extends Vegetation {
	public Grass(Point2D position) {
		super(position);
		super.setProbability(0.15);
		super.setMaxmoves(3);
	}

	@Override
	public String getName() {
		if (super.isCut()) {
			return "land";
		}
		else if(super.Isburned()) {
			return "burntgrass";
		} else
			return "grass";
	}
}
