package GUI;

import Model.GameModel;
import PlayingCard.Suit;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class MainGui extends GridPane {
    private static GridPane main = new GridPane();
    private static FoundationGui[] tableFoundations = new FoundationGui[Suit.values().length];
    private static FreeCellGui[] tableFreeCells = new FreeCellGui[4];
    private static TableStackGui[] tableStacks = new TableStackGui[8];

    /**
     * MainGui - Creates the Main Gui for the FreeCell card game.
     * @param primaryStage requires the primaryStage
     */
    public MainGui(Stage primaryStage){
        //runs the buildTheBoard method
        buildTheBoard(primaryStage, main);

        // Create the event handler for the "Key Press", used to reset the game
        main.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent theEvent){
                // If the "R" key is pressed
                //  Run the reset command on the GameModel
                //  Clear all of the children in the Main Scene
                //  rebuild the main scene with the new items;
                if(theEvent.getCharacter().equals("r")){
                    GameModel.instance().reset();
                    main.getChildren().clear();
                    buildTheBoard(primaryStage, main);
                }
                // If the "Y" key is pressed
                //  Run the reset command, but turning CheatMode on
                //  Clear all of the children in the Main Scene
                //  Rebuild the main scene with the new items;
                if(theEvent.getCharacter().equals("y")){
                    GameModel.instance().reset(true);
                    main.getChildren().clear();
                    buildTheBoard(primaryStage, main);
                }
            }
        });
    }

    /**
     * gameOver() - adds the "VictoryScreenGui" into the scene when the game has been won
     */
    public static void gameOver(){
        main.add(new VictoryScreenGui(), 0, 0);
    }

    /**
     * getTheScene() gets the Main GridPane scene for the MainGui
     * @return GridPane - returns the main GridPane.
     */
    public GridPane getTheScene(){
        return main;
    }

    /**
     * buildTheBoard() - builds the main Board for the FreeCell Solitare game
     * @param primaryStage - the primaryStage for the game
     * @param main - the main GridPane used for the main styling of the scene
     */
    private static void buildTheBoard(Stage primaryStage, GridPane main){
        // Create and set the grid column widths, 8 columns so need to create and add in 8 constraints
        ColumnConstraints column = new ColumnConstraints(75);
        for(int i = 0; i < 8; i++){
            main.getColumnConstraints().add(column);
        }
        // Create the first row height constraint, will make all the items in the top row the same height;
        RowConstraints row = new RowConstraints(5);
        main.getRowConstraints().add(row);
        row = new RowConstraints(115);
        main.getRowConstraints().add(row);
        main.getRowConstraints().add(row);

        // Create some styles for the board itself, set height/width, background image, background-image position, then apply those styles
        String stageStyles = "-fx-background-image: url('/assets/img/bg.jpg'); -fx-background-size: 800 600; -fx-background-position: center center;";
        main.setStyle(stageStyles);
        // set the spacing between the columns/rows
        main.setHgap(20);
        main.setVgap(20);
        //set the padding on the board,
        main.setPadding(new Insets(20,20,20,30));
        Label basicControls = new Label();
        basicControls.setText("Press R to Reset Game, or Y to reset with Cheat Mode enabled.");
        basicControls.setTextFill(Color.web("#ffffff"));
        basicControls.setStyle("-fx-text-alignment: center;");
        basicControls.setMinWidth(600);
        main.add(basicControls, 2,0);
        // Create and add the Free-Cells to the board
        for(GameModel.FreeCellIndex fc : GameModel.FreeCellIndex.values()){
            tableFreeCells[fc.ordinal()] = new FreeCellGui(fc);
            tableFreeCells[fc.ordinal()].resize(75, 115);
            main.add(tableFreeCells[fc.ordinal()],fc.ordinal(), 1);
        }

        // Create and add the Foundations to the board
        for(Suit suit: Suit.values()){
            try {
                tableFoundations[suit.ordinal()] = new FoundationGui(suit);
                main.add(tableFoundations[suit.ordinal()], 4 + suit.ordinal(), 1);
            }catch(IOException ioe){
                System.out.println(ioe);
            }
        }
        // Create and add the table Stacks to the Scene
        for(GameModel.TableStackIndex tsi : GameModel.TableStackIndex.values()){
            tableStacks[tsi.ordinal()] = new TableStackGui(tsi);
            tableStacks[tsi.ordinal()].resize(75, 115);
            main.add(tableStacks[tsi.ordinal()], tsi.ordinal(), 2);
        }

    }
}
