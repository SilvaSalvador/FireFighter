package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Eucaliptus extends Vegetation {
	public Eucaliptus(Point2D position) {
		super(position);
		super.setProbability(0.1);
		super.setMaxmoves(5);
	}

	@Override
	public String getName() {
		if (super.isCut()) {
			return "land";
		}
		else if(super.Isburned()) {
			return "burnteucaliptus";
		} else
			return "eucaliptus";
	}

}
