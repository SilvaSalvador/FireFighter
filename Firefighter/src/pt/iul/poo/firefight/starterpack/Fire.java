package pt.iul.poo.firefight.starterpack;



import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Fire extends GameElement implements ActiveElement {
	static GameEngine game = GameEngine.getInstance();

	public Fire(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "fire";
	}

	@Override
	public int getLayer() {
		return 1;
	}

	public void propagate() {
		for (Point2D v : this.getPosition().getNeighbourhoodPoints()) {
			ImageTile img = game.hasImageBurnableAt(v);
			if (canBurn(img) && ((Vegetation) img).chanceOfBurning()) {
				((Vegetation) img).setOnFire();
			}
		}
	}

	public static void propagateAll() {
		for (ImageTile fire : game.getFiresList()) {
			((Fire) fire).propagate();
		}
		burnTerrain();
	}

	public ImageTile hasWaterInPositon(Point2D p) {
		return game.hasObjectAt(p, g -> g instanceof Water);
	}

	public ImageTile hasPlaneInPosition(Point2D p) {
		return game.hasObjectAt(p, g -> g instanceof Plane);
	}

	public ImageTile hasFiremanInPosition(Point2D p) {
		return game.hasObjectAt(p, g -> g instanceof Fireman);
	}

	public boolean canBurn(ImageTile t) {
		return (t != null && game.hasActiveElement(t.getPosition()) == null && !((Vegetation) t).isCut());
	}

	public static void burnTerrain() {
		ImageTile terrain = null;
		for (ImageTile imageTile : game.getFiresList()) {
			terrain = game.hasImageBurnableAt(imageTile.getPosition());
			((Vegetation) terrain).didItBurn();
		}
	}

}