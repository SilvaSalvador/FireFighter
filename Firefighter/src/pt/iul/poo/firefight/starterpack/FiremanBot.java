package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class FiremanBot extends Fireman implements Updatable {
	GameEngine game = GameEngine.getInstance();

	public FiremanBot(Point2D position) {
		super(position);

	}

	@Override
	public String getName() {
		return "firemanbot";
	}

	@Override
	public void update() {
		Direction d = Direction.random();
		Point2D newPosition = this.getPosition().plus(d.asVector());
		if (this.canMoveTo(newPosition)) {
			ImageTile i = game.hasActiveElement(newPosition); //ta a dar null nao sei porque
			if (i instanceof Fire) {
				delFireAndCreateWater(i, d);
				newPosition = this.getPosition();
			}
			else if(i instanceof Movable) {
				newPosition = this.getPosition();
			}
			super.setPosition(newPosition);	  	
		}
	}
	
}