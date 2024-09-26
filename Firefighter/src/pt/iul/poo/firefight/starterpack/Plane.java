package pt.iul.poo.firefight.starterpack;

import java.awt.event.KeyEvent;

import javax.swing.event.MenuKeyEvent;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Plane extends GameElement implements Updatable, ActiveElement {
	GameEngine game = GameEngine.getInstance();

	public Plane(Point2D position) {
		super(position);

	}

	@Override
	public String getName() {
		return "plane";
	}

	@Override
	public int getLayer() {
		return 3;
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

	public Point2D getPosition() {
		return super.getPosition();
	}
	public void getPlane() {
		int column = 0;
		int fires = 0;
		int maxFires = 0;
		
		for (int i = 0; i < game.GRID_WIDTH; i++) {
			fires = 0;
			for (ImageTile f : game.getFiresList()) {
				if (f.getPosition().getX() == i) {
					fires++;
				}
			}
			if (fires > maxFires) {
				maxFires = fires;
				column = i;
			}
		}
		Point2D p = new Point2D(column, game.GRID_HEIGHT - 1);
		this.setPosition(p);
		removeFireInFirstPos();

	}

	public void deleteFire(ImageTile i) {
		if (i != null) {
			ImageTile imageTile = game.hasImageBurnableAt(i.getPosition());
			((Vegetation) imageTile).putOutFire();
			Scores.waterPlane();
			game.removeImg(i);
		}
	}

	public void removeFireInFirstPos() {
		ImageTile i = game.hasFireAt(getPosition());
		deleteFire(i);
	}

	public boolean removePlaneInLastPos() {
		if (this.getPosition().getY() == 0) {
			game.removeImg(this);
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		boolean hasMoved = false;
		int moves = 0;
		while (moves < 2) {
			if (removePlaneInLastPos()) {
				break;
			}
			Direction d = Direction.UP;
			Point2D newPosition = super.getPosition().plus(d.asVector());
			ImageTile i = game.hasFireAt(newPosition);
			if (canMoveTo(newPosition)) {
				deleteFire(i);
				setPosition(newPosition);
				moves++;
			}
		}
	}
}
