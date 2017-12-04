package GUI;
import Model.GameModel.FreeCellIndex;
import Model.GameModel;
import TableElements.*;
import PlayingCard.*;

import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;

public class FreeCellGui extends StackPane {
    private static final int PADDING = 5;
    private static final String FREECELL_STYLES = "-fx-border-width: 2px; -fx-border-style: dotted; -fx-border-color: #333; -fx-border-radius: 8px;";
    private FreeCellIndex thisIndex;

    /**
     * FreeCellGui() creates the layout for the freeCell Scenes
     * @param freeCell A freeCellIndex that will be assigned to this object to keep it tied to it's corresponding DataStructure.
     */
    public FreeCellGui(FreeCellIndex freeCell){
        thisIndex = freeCell;
        getChildren().clear();
        setStyle(FREECELL_STYLES);
        setOnMouseClicked(mouseClickedHandler(this));
    }

    /**
     * getThisIndex() - returns the FreeCellIndex assigned to this object
     * @return the FreeCellIndex assigned to this Object
     */
    public FreeCellIndex getThisIndex(){
        return thisIndex;
    }

    /**
     * mouseClickedHandler() creates an event handler for on click
     * @param providedFreecellGui - the FreecellGui that you clicked on
     * @return EventHandler<MouseEvent> - the event handler for what is to happen;
     */
    EventHandler<MouseEvent> mouseClickedHandler(FreeCellGui providedFreecellGui){
        return new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent theEvent){
                // If the clipboard is empty, this is the origin click
                if(GameModel.instance().getClipboard().isEmpty()){
                    //Get this object, this FreeCell just to confirm they aren't clicking on an empty freeCell
                    FreeCell thisCell = (FreeCell)GameModel.instance().getFreecell(providedFreecellGui.getThisIndex());
                    // If it is not empty
                    if(!thisCell.empty()){
                        // Pull in the Key for the clipboard
                        //  put the key and the gui into the clipboard
                        //  set the opacity of the card image to .5;
                        DataFormat tempHolder = GameModel.getClipboardKey();
                        GameModel.instance().getClipboard().put(tempHolder, providedFreecellGui);
                        getChildren().get(0).setOpacity(.5);
                    }
                }else if(GameModel.instance().getClipboard().get(GameModel.getClipboardKey()) == providedFreecellGui){
                    // Else if, the gui clicked on is the same gui in the clipboard
                    //  set the opacity of the card image back to 1
                    //  clear the clipboard;
                    getChildren().get(0).setOpacity(1);
                    GameModel.instance().getClipboard().clear();
                }else{
                    // Otherwise, this freecell is the destination, not the origin
                    // Get the object that is stored in the clipboard
                    // get this freeCell, which would be the destination
                    Object thisClipboardKey = GameModel.instance().getClipboard().get(GameModel.getClipboardKey());
                    FreeCell destinationFreeCell = GameModel.instance().getFreecell(providedFreecellGui.getThisIndex());
                    // If the origin is a table stack
                    if(thisClipboardKey instanceof TableStackGui) {
                        // pull in the TableStackGui, pull in the TableStackObject
                        TableStackGui originGui = (TableStackGui)thisClipboardKey;
                        TableStack originStack = GameModel.instance().getTableStacks(originGui.getThisIndex());
                        // Make sure you canPush into this freeCell
                        if(destinationFreeCell.canPush()){
                            // If you can - set the playing card to the top of the Origin Table Stack
                            // Push the playing card into the free cell
                            // pull the image from the card
                            // set the TranslateX and Translate y (If this is coming from a table stack, chances are the Translate X is set to the offset still)
                            // Set the opacity back to 1
                            // Add the image to scene
                            // decrement the offSet from the Origin Gui (keep spacing accurate)
                            // Clear the clipboard
                            PlayingCard theCard = (PlayingCard)originStack.pop();
                            destinationFreeCell.push(theCard);
                            ImageView image = theCard.getImage();
                            image.setTranslateX(0);
                            image.setTranslateY(0);
                            image.setOpacity(1.0);
                            getChildren().add(image);
                            originGui.decrementOffset();
                            GameModel.instance().getClipboard().clear();
                        }
                    }
                    if(thisClipboardKey instanceof FreeCellGui){
                        // if the clipboardkey is a FreeCellGui, that means this is coming from another clipboard
                        // Set the origin Gui and FreeCEll Object to the information stored in the clipboard
                        FreeCellGui originGui = (FreeCellGui) thisClipboardKey;
                        FreeCell originStack = GameModel.instance().getFreecell(originGui.getThisIndex());
                        // make sure you can push into the free cell
                        if(destinationFreeCell.canPush()){
                            // Pop and store the PlayingCard object from the origin
                            // push the card into the destination
                            // get the ImageVie from the Card
                            // set the image opacity back to 1
                            // add the image to the scene
                            // clear the clipboard for next move.
                            PlayingCard theCard = (PlayingCard)originStack.pop();
                            destinationFreeCell.push(theCard);
                            ImageView image = theCard.getImage();
                            image.setOpacity(1.0);
                            getChildren().add(image);
                            GameModel.instance().getClipboard().clear();
                        }
                    }
                }
                theEvent.consume();
            }
        };
    }
}
