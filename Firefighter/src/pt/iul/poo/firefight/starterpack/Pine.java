package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Pine extends Vegetation {
	public Pine(Point2D position) {
		super(position);
		super.setProbability(0.05);
		super.setMaxmoves(10);
	}

	@Override
	public String getName() {
		if (super.isCut()) {
			return "land";
		}
		else if(super.Isburned()) {
			return "burntpine";
		} else
			return "pine";
	}
}
