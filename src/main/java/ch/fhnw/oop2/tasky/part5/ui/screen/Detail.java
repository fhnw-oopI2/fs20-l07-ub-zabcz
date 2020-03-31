package ch.fhnw.oop2.tasky.part5.ui.screen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Diese Klasse sorgt dafür, dass alle Controls für die Detailansicht 
 * vorhanden sind und richtig platziert werden.
 * 
 * Das erweiterte GridPane wird auf zwei Spalten aufgeteilt:
 * Linke Spalte: Labels
 * Rechte Spalte: Input Controls.
 *
 */
final class Detail extends GridPane {

	private Label id;
	private Label title;
	private Label description;
	private Label date;
	private Label state;
	
	private TextField idField;
	private TextField titleField;
	private TextArea descriptionField;
	
	private DatePicker datePicker;
	private ComboBox<String> stateDropDown;
	
	private Button save;
	private Button delete;
	
	/**
	 * Erzeugt eine neue Detailansicht.
	 */
	Detail() {
		initializeControls();
		layoutControls();
	}
	
	private void initializeControls() {
		id = new Label("ID");
		title = new Label("Title");
		description = new Label("Desc");
		date = new Label("Date");
		state = new Label("State");
		
		idField = new TextField();
		titleField = new TextField();
		descriptionField = new TextArea();
		
		datePicker = new DatePicker();
		stateDropDown = new ComboBox<>();
		
		save = new Button("Save");
		delete = new Button("Delete");
	}
	
	private void layoutControls() {
		setPadding(new Insets(22, 30, 22, 30));
		
		ConstraintHelper.setColumnPercentConstraint(this, 20);
		ConstraintHelper.setColumnPercentConstraint(this, 80);
		
		add(id, 0, 0);
		add(idField, 1, 0);
		
		add(title, 0, 1);
		add(titleField, 1, 1);
		GridPane.setMargin(titleField, new Insets(10, 0, 0, 0));
		
		descriptionField.setMaxHeight(100);
		add(description, 0, 2);
		add(descriptionField, 1, 2);
		GridPane.setMargin(descriptionField, new Insets(10, 0, 0, 0));
		
		add(date, 0, 3);
		add(datePicker, 1, 3);
		datePicker.setMaxWidth(Double.MAX_VALUE);
		GridPane.setMargin(datePicker, new Insets(10, 0, 0, 0));
		
		add(state, 0, 4);
		add(stateDropDown, 1, 4);
		stateDropDown.setMaxWidth(Double.MAX_VALUE);
		GridPane.setMargin(stateDropDown, new Insets(10, 0, 0, 0));
		
		// Buttons werden als HBox mit Colspan hinzugefügt.
		HBox buttons = new HBox();
		buttons.setSpacing(10);
		buttons.getChildren().addAll(save, delete);
		add(buttons, 0, 5, 2, 1);
		GridPane.setMargin(buttons, new Insets(20, 0, 0, 0));
	}
}
