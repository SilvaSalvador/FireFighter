package pt.iul.poo.firefight.starterpack;

import java.awt.event.KeyEvent;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Fireman extends GameElement implements Movable, ActiveElement {
	ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	GameEngine game = GameEngine.getInstance();
	private boolean inVeic = false;
	private Drivable veic;
	private Point2D newPosition;

	public Fireman(Point2D position) {
		super(position);
	}

	public boolean canMoveTo(Point2D p) {
		if (p.getX() < 0)
			return false;
		if (p.getY() < 0)
			return false;
		if (p.getX() >= GameEngine.GRID_WIDTH)
			return false;
		if (p.getY() >= GameEngine.GRID_HEIGHT)
			return false;
		return true;
	}

	public void setPosition(Point2D position) {
		super.setPosition(position);
	}

	// ImageTile methods
	@Override
	public String getName() {
		return "fireman";
	}

	@Override
	public int getLayer() {
		return 3;
	}

	@Override
	public void move(int key) {
		if (key == KeyEvent.VK_ENTER) {
			gui.addImage(this);
			veic = null;
		} else {
			Direction d = Direction.directionFor(key);
			newPosition = super.getPosition().plus(d.asVector());
			if (canMoveTo(newPosition)) {
				changeWater();
				ImageTile i = game.hasActiveElement(newPosition);
				if (veic == null) {
					moveFireman(i, d);
				} else {
					if ((i instanceof ActiveElement)) {
						newPosition = super.getPosition();
						key = -1;
						if (i instanceof Fire) {
							key = -2;
						}
					}
					veic.move(key);
				}
				setPosition(newPosition);
			}
		}
	}

	public void delFireAndCreateWater(ImageTile i, Direction d) {
		ImageTile imageTile = game.hasImageBurnableAt(i.getPosition());
		((Vegetation) imageTile).putOutFire();
		createWater(i.getPosition(), d);
	}

	public void moveFireman(ImageTile i, Direction d) {
		if (i instanceof Fire) {
			delFireAndCreateWater(i, d);
			newPosition = super.getPosition();
		} else if (i instanceof FiremanBot) {
			newPosition = super.getPosition();
		} else if (i instanceof Drivable) {
			veic = ((Drivable) i);
			inVeic = true;
			gui.removeImage(this);
		}
	}

	public void createWater(Point2D pos, Direction d) {
		ImageTile a = GameElement.create("Water", pos);
		((Water) a).setWater(d);
		game.addImg(a);
	}

	public void changeWater() {
		for (Updatable updatable : game.getUpdatelist()) {
			if (updatable instanceof Water) {
				((Water) updatable).delete();
			}
		}
	}
}
