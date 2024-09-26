package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.gui.ImageTile;

public class NoTerrain extends GameElement {
	private boolean burning;
	public NoTerrain(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "land";
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}

}
