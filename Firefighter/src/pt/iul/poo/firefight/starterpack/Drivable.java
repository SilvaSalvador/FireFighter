package pt.iul.poo.firefight.starterpack;


import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Drivable extends GameElement implements ActiveElement,Movable{
	private Direction dir = null;
	GameEngine game=GameEngine.getInstance();

	public Drivable(Point2D position) {
		super(position);
	}
	@Override
	public int getLayer() {
		return 3;
	}
	public void setPosition(Point2D position) {
		super.setPosition(position);
	}
	public abstract void uniqueFeature(Point2D p);
	public abstract void fireFeature(Point2D p);
	@Override
	public void move(int key) {
		Point2D newPosition;
		if (key == -1) {
			newPosition = game.getFireman().getPosition();
		} else if(key==-2) {
			newPosition = game.getFireman().getPosition();
            fireFeature(newPosition);
		}
		else {
			dir = Direction.directionFor(key);
			newPosition = super.getPosition().plus(dir.asVector());
			 uniqueFeature(newPosition);
		}
		setPosition(newPosition);
	}
	public Direction getDir() {
		return dir;
	}
	
}
