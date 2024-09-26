package pt.iul.poo.firefight.starterpack;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Point2D;

public class GameEngine implements Observer {

	public static final int GRID_HEIGHT = 10;
	public static final int GRID_WIDTH = 10;

	private ImageMatrixGUI gui;
	private List<ImageTile> tileList; // Image List
	private Fireman fireman;
	private static GameEngine INSTANCE;
	private int level = 0;
	private File[] maps = new File("levels").listFiles();

	public static GameEngine getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GameEngine();
		return INSTANCE;
	}

	public List<ImageTile> getImageList() {
		return this.tileList;
	}

	public Fireman getFireman() {
		return this.fireman;
	}

	public void removeImg(ImageTile i) {
		tileList.remove(i);
		gui.removeImage(i);
	}

	public void addImg(ImageTile i) {
		tileList.add(i);
		gui.addImage(i);
	}

	public List<ImageTile> getFiresList() {
		List<ImageTile> fires = new ArrayList<>();
		for (ImageTile img : this.tileList) {
			if (img instanceof Fire) {
				fires.add(img);
			}
		}
		return fires;
	}

	public List<Updatable> getUpdatelist() {
		List<Updatable> up = new ArrayList<>();
		for (ImageTile t : tileList) {
			if (t instanceof Updatable) {
				up.add(((Updatable) t));
			}
		}
		return up;
	}

	public ImageTile hasActiveElement(Point2D pos) {
		return hasObjectAt(pos, g -> g instanceof ActiveElement);
	}

	public ImageTile hasFireAt(Point2D p) {
		return hasObjectAt(p, g -> g instanceof Fire);
	}

	public ImageTile hasImageBurnableAt(Point2D p) {
		return hasObjectAt(p, g -> g instanceof Burnable);
	}

	public <T> T hasObjectAt(Point2D p, Predicate<GameElement> pred) {
		for (ImageTile g : tileList) {
			if (pred.test((GameElement) g) && g.getPosition().equals(p)) {
				return (T) g;
			}
		}
		return null;
	}

	public void updateInicialFires() {
		ImageTile terrain = null;
		for (ImageTile img : getFiresList()) {
			terrain = hasImageBurnableAt(img.getPosition());
			if (terrain != null) {
				((Vegetation) terrain).setOnFire();
			}
		}
	}

	public void setFireman(Fireman a) {
		this.fireman = a;
	}

	public void isTheEnd() throws FileNotFoundException {
		if (getFiresList().size() == 0) {
			Scores.pointsAndMenu(maps[level]);
			if (level == maps.length - 1) {
				gui.dispose();
			}
			if (level < maps.length - 1) {
				level++;
				gui.clearImages();
				tileList.removeAll(tileList);
				start();
			}
		}
	}

	private GameEngine() {
		Scores.getNickname();
		gui = ImageMatrixGUI.getInstance();
		gui.setSize(GRID_HEIGHT, GRID_WIDTH);
		gui.registerObserver(this);
		gui.go();
		tileList = new ArrayList<>();
	}

	@Override
	public void update(Observed source) {
		int key = gui.keyPressed(); 
		if (key == KeyEvent.VK_P) {
			ImageTile plane = GameElement.create("Plane", null);
			((Plane) plane).getPlane();
			addImg(plane);
		} else {
			((Movable) fireman).move(key);
			Fire.propagateAll();
			Updatable.updateAll(getUpdatelist());
		}
		gui.setStatusMessage("Score: " + Integer.toString(Scores.getPoints()));
		gui.update();
		try {
			isTheEnd();
		} catch (FileNotFoundException e) {
		}

	}

	public void start() throws FileNotFoundException {
		GenerateMap.readFile(maps[level]);
		updateInicialFires();
		sendImagesToGUI();
		gui.update();
	}

	private void sendImagesToGUI() {
		gui.addImages(tileList);
	}
}
