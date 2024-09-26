package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Point2D;

public class Abies extends Vegetation {

	public Abies(Point2D position) {
		super(position);
		super.setProbability(0.05);
		super.setMaxmoves(20);
	}

	@Override
	public String getName() {
		if (super.isCut()) {
			return "land";
		}
		else if(super.Isburned()) {
			return "burntabies";
		} else
			return "abies";
	}



}
