package ch.fhnw.oop2.tasky.part5.ui.screen;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.LongProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.converter.NumberStringConverter;

import java.lang.invoke.StringConcatFactory;

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

	private Label labelId;
	private Label labelTitle;
	private Label labelDescription;
	private Label labelDate;
	private Label labelState;
	
	private TextField idField;
	private TextField titleField;
	private TextArea descriptionField;
	
	private DatePicker datePicker;
	private ComboBox<String> stateDropDown;
	
	private Button save;
	private Button delete;

	private final LongProperty id;

	
	/**
	 * Erzeugt eine neue Detailansicht.
	 */
	Detail() {
		id = new SimpleLongProperty();
		initializeControls();
		layoutControls();
	}
	
	private void initializeControls() {
		labelId = new Label("ID");
		labelTitle = new Label("Title");
		labelDescription = new Label("Desc");
		labelDate = new Label("Date");
		labelState = new Label("State");

		idField = new TextField();
		idField.setDisable(true);
		idField.textProperty().bindBidirectional(id, new NumberStringConverter()); //bing to AppUI
		idField.textProperty().bindBidirectional(id, new NumberStringConverter());

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
		
		add(labelId, 0, 0);
		add(idField, 1, 0);
		
		add(labelTitle, 0, 1);
		add(titleField, 1, 1);
		GridPane.setMargin(titleField, new Insets(10, 0, 0, 0));
		
		descriptionField.setMaxHeight(100);
		add(labelDescription, 0, 2);
		add(descriptionField, 1, 2);
		GridPane.setMargin(descriptionField, new Insets(10, 0, 0, 0));
		
		add(labelDate, 0, 3);
		add(datePicker, 1, 3);
		datePicker.setMaxWidth(Double.MAX_VALUE);
		GridPane.setMargin(datePicker, new Insets(10, 0, 0, 0));
		
		add(labelState, 0, 4);
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

	public LongProperty getTaskIdProperty() {
		return id;
	}

}
