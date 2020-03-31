package ch.fhnw.oop2.tasky.part5.ui.screen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * Diese Klasse teilt den Bildschirm in die zwei Hauptgebiete auf:
 * (a) Lane Group
 * (b) Detail-Ansicht einer ausgewählten Task.
 *
 */
public final class ApplicationUI extends GridPane {
	
	private static final int TASKLANE_PERCENT = 60;
	private static final int DETAILS_PERCENT = 40;
	
	private Lane todo;
	private Lane doing;
	private Lane done;
	private Lane review;

	/**
	 * Erzeugt einen neuen MainScreen.
	 */
	public ApplicationUI() {
		initializeControls();
		layoutControls();
	}
	
	private void initializeControls() {
		todo = new Lane("Todo", createTasks("#2ecc71"));
		doing = new Lane("Doing", createTasks("#3498db"));
		done = new Lane("Done", createTasks("#e74c3c"));
		review = new Lane("Review", createTasks("#9b59b6"));
	}
	
	private void layoutControls() {
		ConstraintHelper.setRowPercentConstraint(this, 100); // Höhe soll generell voll ausgefüllt werden.
		
		ConstraintHelper.setColumnPercentConstraint(this, TASKLANE_PERCENT);
		add(new LaneGroup(todo, doing, done, review), 0, 0);
		
		ConstraintHelper.setColumnPercentConstraint(this, DETAILS_PERCENT);
		add(new Detail(), 1, 0);
	}
	
	private List<Region> createTasks(String color) {
		List<Region> tasks = new ArrayList<>();
		
		Stream.iterate(1, n -> n + 1)
			.limit((int)(1 + Math.random() * 4))
			.forEach(n -> tasks.add(Area.createRegion(color)));
		
		return tasks;
	}
}
