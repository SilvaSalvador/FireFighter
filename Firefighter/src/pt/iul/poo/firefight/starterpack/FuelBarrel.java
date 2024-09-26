package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Point2D;

public class FuelBarrel extends Vegetation {

	public FuelBarrel(Point2D position) {
		super(position);
		super.setProbability(0.90);
		super.setMaxmoves(3);
	}

	@Override
	public String getName() {
		if (super.isCut()) {
			return "land";
		} else if (super.Isburned()) {
			return "burntfuelbarrel";
		} else
			return "fuelbarrel";
	}
}
