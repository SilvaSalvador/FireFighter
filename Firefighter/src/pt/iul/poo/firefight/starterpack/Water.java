package pt.iul.poo.firefight.starterpack;

import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Vector;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Water extends GameElement implements Updatable, ActiveElement {
	GameEngine game = GameEngine.getInstance();
	private Direction d;
	private boolean delete;

	public Water(Point2D position) {
		super(position);
		delete = false;
	}

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public String getName() {
		switch (d) {
		case RIGHT:
			return "water_right";
		case DOWN:
			return "water_down";
		case UP:
			return "water_up";
		case LEFT:
			return "water_left";
		}
		return null;
	}

	public void setWater(Direction d) {
		this.d = d;
	}

	public void delete() {
		this.delete = true;
	}

	@Override
	public void update() {
		for (Updatable up : game.getUpdatelist()) {
			if (up instanceof Water && delete) {
				game.removeImg((ImageTile) up);
			}
		}
	}
}
