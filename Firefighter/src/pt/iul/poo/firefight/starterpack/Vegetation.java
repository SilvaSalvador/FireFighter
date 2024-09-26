package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Vegetation extends GameElement implements Burnable, Updatable {
	private boolean isBurning;
	private int numberOfMoves;
	private boolean isBurned;
	private double probability;
	private int maxMoves;
	private boolean cut;
	GameEngine game = GameEngine.getInstance();

	public Vegetation(Point2D position) {
		super(position);
		this.isBurning = false;
		this.numberOfMoves = 0;
		isBurned = false;
		cut = false;
	}

	@Override
	public int getLayer() {
		return 0;
	}

	public boolean Isburned() {
		return isBurned;
	}

	public boolean isBurning() {
		return isBurning;
	}

	public void putOutFire() {
		this.isBurning = false;
	}

	public void setOnFire() {
		this.isBurning = true;
	}

	public void burn() {
		this.isBurned = true;
	}

	public void cut() {
		this.cut = true;
	}

	public boolean isCut() {
		return cut;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public void setMaxmoves(int maxmoves) {
		this.maxMoves = maxmoves;
	}

	@Override
	public boolean chanceOfBurning() {
		if (isBurning() || Isburned()) {
			return false;
		}
		return Math.random() <= probability;
	}

	@Override
	public boolean didItBurn() {
		if (this.isBurning) {
			this.numberOfMoves++;
		} else {
			this.numberOfMoves = 0;
		}
		if (this.numberOfMoves >= this.maxMoves) {
			putOutFire();
			burn();
			return true;
		}
		return false;
	}

	public void createFire(Point2D bimg) {
		ImageTile f = GameElement.create("Fire", bimg);
		game.addImg(f);
	}

	@Override
	public void update() {
		if (game.hasFireAt(this.getPosition()) == null && this.isBurning) {
			createFire(getPosition());

		}
		if (game.hasFireAt(getPosition()) != null && this.isBurned) {
			Scores.burnt();
			game.removeImg(game.hasFireAt(getPosition()));

		}
		if (game.hasFireAt(getPosition()) != null && !this.isBurning) {
			Scores.watersFire();
			game.removeImg(game.hasFireAt(getPosition()));
		}

	}
}
