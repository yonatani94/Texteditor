/**
 * @author Grigory Shaulov
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class HW01 extends Application {

	/**
	 * variables
	 */
	private File file;
	private int filePointer = 0;
	private int fileNextIndex = -1;
	private int textAreaPointer = 0;
	private int textAreaNextIndex = -1;
	private String lastSearch = "";
	// top pane
	private Label labelFileName = new Label("File Name:");
	private TextField textFieldFileName = new TextField();
	private Button buttonLoadFile = new Button("Load File");
	// center pane
	private Label labelFind = new Label("Find:");
	private Label labelReplace = new Label("Replace:");
	private Label labelNextIndex = new Label("Next Index: ");
	private Label labelNextIndexValue = new Label("");
	private Label labelCount = new Label("Count: ");
	private TextField textFieldFind = new TextField();
	private TextField textFieldReplace = new TextField();
	private Button buttonFind = new Button("Find");
	private Button buttonReplace = new Button("Replace");
	private Button buttonReplaceAll = new Button("Replace All");
	private Button buttonReset = new Button("Reset");
	// bottom pane
	private TextArea textArea = new TextArea();

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Text Editor");
			primaryStage.setResizable(false);
			BorderPane root = new BorderPane();
			root.setPrefSize(450, 600);
			root.setTop(getTop());
			root.setCenter(getCenter());
			root.setBottom(getBottom());
			initialize();
			Scene scene = new Scene(root, 450, 600);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
		}
	}

	//reset window
	private void initialize() {
		resetIndexes();
		setDisableTop(false);
		setDisableCenter(true);
		setDisableBottom(true);
	}

	//reset indexes
	private void resetIndexes() {
		filePointer = 0;
		fileNextIndex = -1;
		textAreaPointer = 0;
		textAreaNextIndex = -1;
		labelNextIndexValue.setText("");
	}

	/**
	 * 
	 * @return top pane
	 */
	private Node getTop() {
		buttonLoadFile.setOnAction(e -> loadFile());
		HBox topPane = new HBox(10);
		topPane.setPadding(new Insets(10, 10, 10, 10));
		topPane.setAlignment(Pos.CENTER);
		topPane.setStyle("-fx-border-color: black");
		topPane.getChildren().addAll(labelFileName, textFieldFileName, buttonLoadFile);
		return topPane;
	}

	/**
	 * 
	 * @return center pane
	 */
	private Node getCenter() {
		GridPane centerPane = new GridPane();
		centerPane.setMinSize(400, 200);
		centerPane.setPadding(new Insets(10, 10, 10, 10));
		centerPane.setVgap(10);
		centerPane.setHgap(10);
		centerPane.setAlignment(Pos.CENTER);
		centerPane.setStyle("-fx-border-color: black");
		centerPane.add(labelFind, 0, 0);
		centerPane.add(textFieldFind, 1, 0);
		centerPane.add(labelReplace, 0, 1);
		centerPane.add(textFieldReplace, 1, 1);
		centerPane.add(labelCount, 0, 2);
		centerPane.add(labelNextIndex, 0, 3);
		centerPane.add(labelNextIndexValue, 1, 3);
		labelNextIndexValue.setMaxWidth(100);
		HBox hb = new HBox(10);
		buttonFind.setOnAction(e -> find());
		buttonReplaceAll.setOnAction(e -> replaceAll());
		buttonReset.setOnAction(e -> initialize());
		hb.getChildren().addAll(buttonFind, buttonReplace, buttonReplaceAll, buttonReset);
		centerPane.add(hb, 0, 4, 4, 1);
		return centerPane;
	}

	
	/**
	 * 
	 * @return bottom pane
	 */
	private Node getBottom() {
		HBox hbButtons = new HBox(10);
		hbButtons.setPadding(new Insets(10, 10, 10, 10));
		hbButtons.setAlignment(Pos.CENTER);
		hbButtons.setStyle("-fx-border-color: black");
		textArea.setPrefWidth(400);
		textArea.setPrefSize(400, 300);
		textArea.setPrefColumnCount(20);
		textArea.setPrefRowCount(10);
		hbButtons.getChildren().addAll(textArea);
		return hbButtons;
	}

	
	// load file data to window
	private void loadFile() {
		file = new File(textFieldFileName.getText());
		if (file.exists()) {
			setDisableTop(true);
			setDisableCenter(false);
			setDisableBottom(false);
			showFileData();
		} else {

		}
	}

	
	/**
	 * to set disable components in top pane by value
	 * @param value 
	 */
	private void setDisableTop(boolean value) {
		labelFileName.setDisable(value);
		textFieldFileName.setDisable(value);
		buttonLoadFile.setDisable(value);
	}

	/**
	 * to set disable components in center pane by value
	 * @param value 
	 */
	private void setDisableCenter(boolean value) {
		labelFind.setDisable(value);
		labelReplace.setDisable(value);
		labelNextIndex.setDisable(value);
		labelNextIndexValue.setText("");
		labelNextIndexValue.setDisable(value);
		labelCount.setText("");
		labelCount.setDisable(value);
		textFieldFind.setText("");
		textFieldFind.setDisable(value);
		textFieldReplace.setText("");
		textFieldReplace.setDisable(value);
		buttonFind.setDisable(value);
		buttonReplace.setDisable(true);
		buttonReplaceAll.setDisable(true);
		buttonReset.setDisable(value);

	}

	/**
	 * to set disable components in bottom pane by value
	 * @param value 
	 */
	private void setDisableBottom(boolean value) {
		textArea.setText("");
		textArea.setDisable(value);
	}

	//set file data to textarea 
	private void showFileData() {
		byte[] data = readFileData(file);
		textArea.setText(new String(data));
	}

	public static void main(String[] args) {
		launch(args);
	}

	
	/**
	 * 
	 * @param file
	 * @return all file data
	 */
	private byte[] readFileData(File file) {
		byte[] data = new byte[0];
		
		/**
		 * add code here
		 */
				
		return data;
	}

	/**
	 * 
	 * @return true if word found, otherwise false
	 */
	private boolean find() {
		String text = textFieldFind.getText();
		boolean isFound = false;

		/**
		 * add code here
		 */



		if (isFound) {
			labelNextIndexValue.setText("" + fileNextIndex);
			textAreaNextIndex = textArea.getText().indexOf(text, textAreaPointer);
			textAreaPointer = textAreaNextIndex + text.length();
			textArea.selectRange(textAreaNextIndex, textAreaPointer);
			buttonReplace.setDisable(false);
			buttonReplaceAll.setDisable(false);
			buttonReplace.setOnAction(e -> replace());
			buttonReplaceAll.setOnAction(e -> replaceAll());
			return true;
		} else
			return false;
	}

	
	//replace string in file by index
	private void replace() {
		String foundText = textFieldFind.getText();
		String newText = textFieldReplace.getText();
		buttonReplace.setDisable(true);
		buttonReplaceAll.setDisable(true);

		/**
		 * add code here
		 */



		showFileData();
		textArea.selectRange(textAreaNextIndex, textAreaNextIndex + newText.length());
		resetIndexes();
	}

	
	//replace every found string from file
	private void replaceAll() {

		/**
		 * add code here
		 */

	}

}
