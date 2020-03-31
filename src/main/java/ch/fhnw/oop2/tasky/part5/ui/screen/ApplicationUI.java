package ch.fhnw.oop2.tasky.part5.ui.screen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ch.fhnw.oop2.tasky.part1.model.Repository;
import ch.fhnw.oop2.tasky.part1.model.Status;
import ch.fhnw.oop2.tasky.part1.model.Task;
import ch.fhnw.oop2.tasky.part1.model.TaskData;
import ch.fhnw.oop2.tasky.part1.model.impl.InMemoryMapRepository;
import javafx.beans.property.LongProperty;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.beans.property.SimpleLongProperty;

/**
 * Diese Klasse teilt den Bildschirm in die zwei Hauptgebiete auf:
 * (a) Lane Group
 * (b) Detail-Ansicht einer ausgewählten Task.
 *
 */
public final class ApplicationUI extends GridPane {
	
	private static final int TASKLANE_PERCENT = 60;
	private static final int DETAILS_PERCENT = 40;
	public final static String[] COLORS = { "#2ecc71", "#3498db", "#e74c3c", "#9b59b6" };

	private Lane todo;
	private Lane doing;
	private Lane done;
	private Lane review;

	private Repository repo;
	private List<Task> all_tasks;
	private List<Task> todo_tasks;
	private List<Task> doing_tasks;
	private List<Task> done_tasks;
	private List<Task> review_tasks;

	private final LongProperty taskSelected;
	private LaneGroup laneGroup;



	/**
	 * Erzeugt einen neuen MainScreen.
	 */
	public ApplicationUI() {
		repo = new InMemoryMapRepository();
		repo.create(new TaskData("Task01", "erster Task von Tasky v5.0", LocalDate.now(), Status.Todo));
		repo.create(new TaskData("Task02", "zweiter Task von Tasky v5.0", LocalDate.now(), Status.Todo));
		taskSelected = new SimpleLongProperty();

		initializeControls();
		layoutControls();
	}

	private void initializeControls() {
		refreshTaskLanes();
	}

	/**
	 * Anzeigen aller Tasks, eingeordnet in die Lanes
	 */
	private void refreshTaskLanes() {
		// create private list of all tasks from repo
		all_tasks = repo.read();

		// create lists for each state
		todo_tasks = Task.reduceList(all_tasks, Status.Todo);
		doing_tasks = Task.reduceList(all_tasks, Status.Doing);
		done_tasks = Task.reduceList(all_tasks, Status.Done);
		review_tasks = Task.reduceList(all_tasks, Status.Review);

		// create lanes with one region for each task
		todo = new Lane (Status.Todo.name(), createRegionsForTasks(todo_tasks, COLORS[Status.Todo.ordinal()]));
		doing = new Lane (Status.Doing.name(), createRegionsForTasks(doing_tasks, COLORS[Status.Doing.ordinal()]));
		done = new Lane (Status.Done.name(), createRegionsForTasks(done_tasks, COLORS[Status.Done.ordinal()]));
		review = new Lane (Status.Review.name(), createRegionsForTasks(review_tasks, COLORS[Status.Review.ordinal()]));

		laneGroup = new LaneGroup(this, todo, doing, done, review);
		add(laneGroup, 0, 0);

	}


	private void layoutControls() {
		ConstraintHelper.setRowPercentConstraint(this, 100); // Höhe soll generell voll ausgefüllt werden.
		
		ConstraintHelper.setColumnPercentConstraint(this, TASKLANE_PERCENT);
		add(new LaneGroup(this, todo, doing, done, review), 0, 0);
		
		ConstraintHelper.setColumnPercentConstraint(this, DETAILS_PERCENT);

		Detail detailView = new Detail(this);
		add(detailView, 1, 0);

		// Sync property of task id
		taskSelected.bindBidirectional(detailView.getTaskIdProperty());
	}

	private List<Region> createRegionsForTasks(List<Task> tasks, String color) {
		List<Region> tasks_as_region = new ArrayList<>();

		for (Task t : tasks) {
			tasks_as_region.add(Task.createRegionWithText(color, t.data.title));
		}
		return tasks_as_region;
	}

	public void newTask() {
		Task task = repo.create(new TaskData("", "", LocalDate.now(), Status.Todo));
		taskSelected.set(task.id);
		System.out.println(taskSelected.toString());
		refreshTaskLanes();
		System.out.println("create");
	}

	public void showTaskInDetail() {
		refreshTaskLanes();
		System.out.println("refresh");
	}



}
