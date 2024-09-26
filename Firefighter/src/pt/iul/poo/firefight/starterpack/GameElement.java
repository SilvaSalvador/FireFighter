package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public abstract class GameElement implements ImageTile {

	private Point2D position;

	public GameElement(Point2D position) {
		this.position = position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	public static GameElement create(String s, Point2D point) {
		switch (s) {
		case "p":
			return new Pine(point);
		case "e":
			return new Eucaliptus(point);
		case "m":
			return new Grass(point);
		case "_":
			return new NoTerrain(point);
		case "Fireman":
			return new Fireman(point);
		case "Bulldozer":
			return new Bulldozer(point);
		case "Fire":
			return new Fire(point);
		case "Water":
			return new Water(point);
		case "Plane":
			return new Plane(point);
		case "a":
			return new Abies(point);
		case "b":
			return new FuelBarrel(point);
		case "FireTruck":
			return new FireTruck(point);
		case "FiremanBot":
			return new FiremanBot(point);

		default:
			throw new IllegalArgumentException();
		}
	}
}