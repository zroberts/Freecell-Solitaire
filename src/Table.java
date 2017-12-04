import GUI.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Table extends Application{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 650;
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Set the title of the primary Stage
		primaryStage.setTitle("Free-cell Solitaire");
		// Make the window non-resizable
		primaryStage.setResizable(false);
		// Add the scene that was just created to the stage
		GridPane main = new MainGui(primaryStage).getTheScene();

		primaryStage.setScene(new Scene(main, WIDTH, HEIGHT));
		// show the stage.
		primaryStage.show();
		main.requestFocus();
	}

}
