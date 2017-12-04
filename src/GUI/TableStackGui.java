package GUI;
import Stack.Stack;
import TableElements.*;
import PlayingCard.*;
import Model.GameModel.TableStackIndex;
import Model.GameModel;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import java.util.Iterator;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;
import javafx.scene.input.*;

public class TableStackGui extends StackPane{
    private static final int Y_OFFSET = 30;
    private int offset = 0;
    private final String STYLES = "-fx-border-style: dashed; -fx-border-color: #333; -fx-border-width: 2px; -fx-border-radius: 10px;";
    private TableStackIndex thisStackIndex;

    /**
     * TableStackGui() Creates a Table Stack Gui Scene
     * @param tsi the TableStackIndex enumeration that ties the GUI to the TableStackModel
     */
    public TableStackGui(TableStackIndex tsi){
        thisStackIndex = tsi;
        setStyle(STYLES);
        resize(75, 115);
        buildGui();
    }

    /**
     * getThisIndex() - returns the TableStackIndex that is tied to this TableStackGui objects
     * @return the TableStackIndex;
     */
    public TableStackIndex getThisIndex(){
        return thisStackIndex;
    }

    /**
     * decrementOffset() - subtracts one from the OffsetValue, used to help manage the card spacing as you move them between stacks.
     */
    public void decrementOffset(){ offset--; }

    /**
     * buildGui() - Builds out the Gui for the TableStack
     */
    private void buildGui(){
        //make sure the pane is empty when building out a new one
        getChildren().clear();

        // pulls the TableStack Object that corresponds with the index that has been passed in
        TableStack theStack = GameModel.instance().getTableStacks(thisStackIndex);
        // create a reverse iterator, need it reverse to make sure the top of the stack in the GUI, is the top of the stack in the TableStack Object
        Iterator<PlayingCard> stackIterator = theStack.reverseOrder();

        // Loop through the iterator
        while(stackIterator.hasNext()){
            // get the current card
            // pull the image assigned to the current card
            // Set translation Y to the Y_OFFSET, times the offset. This spaces the cards out evenly, depending on how many cards are in the GUI
            // set the images width to 75
            // Set the images height to 115
            // add one to the offset counter
            // add the image to the scene view;
            PlayingCard current = stackIterator.next();
            ImageView image = current.getImage();
            image.setTranslateY(Y_OFFSET * offset);
            image.setFitWidth(75);
            image.setFitHeight(115);
            offset++;
            getChildren().add(image);

            // set the OnClick eventHanlder for on mouse click
            setOnMouseClicked(mouseClickedHandler(this));
        }
    }
    EventHandler<MouseEvent> mouseClickedHandler(TableStackGui providedTableStackGui) {
        return new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent theEvent) {
                // Check if the clipboard is empty
                if (GameModel.instance().getClipboard().isEmpty()) {
                    // If it is, that means this is the card that wants to be moved
                    //  Pull down the data Format, this is key used to keep track of the clipboard content
                    //  Set the clipboard to the key, and the TableStackGui that was clicked on;
                    DataFormat tempHolder = GameModel.getClipboardKey();
                    GameModel.instance().getClipboard().put(tempHolder, providedTableStackGui);

                    // pull the image from the stack that was clicked on, and set it's opacity to .5 to show which one was selected
                    Node thisItem = getChildren().get(getChildren().size() - 1);
                    thisItem.setOpacity(.5);
                } else if(GameModel.instance().getClipboard().get(GameModel.getClipboardKey()) == providedTableStackGui) {
                    // Else if - the TableStackGui that is stored in the clipboard, is equal to the TableStackGui that was clicked on
                    //  This implies, that they want to choose a different card instead, so set the card at the bottom of the stack, back to 1.0 opacity
                    //  Then clear the clipboard;
                    getChildren().get(getChildren().size()-1).setOpacity(1.0);
                    GameModel.instance().getClipboard().clear();
                }else{
                    // Otherwise, this is a destination and not an origin

                    // pull down the clipboard key - this will be a refrence to the origin GUI (FreeCell or TableStack)
                    Object thisClipboardKey = GameModel.instance().getClipboard().get(GameModel.getClipboardKey());
                    // Get this GUI's TableStack object
                    TableStack destinationStack = GameModel.instance().getTableStacks(providedTableStackGui.getThisIndex());

                    // If it's a TableStack -
                    if(thisClipboardKey instanceof TableStackGui) {
                        // pull the Gui and the Stack of the Origin
                        TableStackGui originGui = (TableStackGui) thisClipboardKey;
                        TableStack originStack = GameModel.instance().getTableStacks(originGui.getThisIndex());
                        // If the destination is not empty

                            // Check and make sure that we can push the OriginStacks top card into the destination stack
                            if (destinationStack.empty() || destinationStack.canPush((PlayingCard) originStack.peek()) || destinationStack.empty()) {
                                // If you can, store the playing card
                                //  push the card to the destination
                                //  set get the image from the playing card
                                //  set the images TranslateY to the offset * the Y_offset
                                //  Reset the opacity to 1
                                //  add the image to the scene
                                //  increase the offset by 1 to keep spacing consistent
                                //  decrement the offset of the origin by 1
                                //  Clear the Clipboard;
                                PlayingCard theCard = (PlayingCard) originStack.pop();
                                destinationStack.push(theCard);
                                ImageView image = theCard.getImage();
                                image.setTranslateY(offset * Y_OFFSET);
                                image.setOpacity(1.0);
                                getChildren().add(image);
                                offset++;
                                originGui.decrementOffset();
                                GameModel.instance().getClipboard().clear();
                            }

                    }else if(thisClipboardKey instanceof FreeCellGui){
                        // else, if this is coming from a free cell gui, collect the free cell gui that it's coming from, and the FreeCell object tied to it
                        FreeCellGui originGui = (FreeCellGui) thisClipboardKey;
                        FreeCell originStack = GameModel.instance().getFreecell(originGui.getThisIndex());
                            // If the destination is empty, or if you can push into it
                            if (destinationStack.empty() || destinationStack.canPush((PlayingCard) originStack.peek())) {
                                // Get and store the playingCard from the origin stack
                                // push the card into the Destination Table Stack
                                // get the image of the card
                                // set the translateY for appropriate spacing
                                // make sure it's opacity is reset to 1
                                // add it to the scen;
                                PlayingCard theCard = (PlayingCard) originStack.pop();
                                destinationStack.push(theCard);
                                ImageView image = theCard.getImage();
                                image.setTranslateY(offset * Y_OFFSET);
                                image.setOpacity(1.0);
                                getChildren().add(image);
                                offset++;
                                GameModel.instance().getClipboard().clear();
                            }

                    }
                }
                //consume the click event
                theEvent.consume();
            }
        };
    }

}


