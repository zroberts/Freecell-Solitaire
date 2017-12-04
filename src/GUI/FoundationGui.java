/**
 * FoundationGui - an Object used to create and manage the Foundation stacks
 */
package GUI;
import TableElements.*;
import TableElements.TableStack;
import PlayingCard.*;
import Model.GameModel;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.layout.StackPane;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;
import javafx.scene.input.*;


public class FoundationGui extends StackPane {
    private String imgUrlString = "../assets/img/foundation-";
    private Suit suit;

    /**
     * FoundationGui Creates the GUI for the foundation object
     * @param suitValue - the value of the suit that is assigned to this foundation.
     * @throws IOException - Throws IO Exception when the background image for the foundation is unable to be found
     */
    public FoundationGui(Suit suitValue) throws IOException {
        getChildren().clear();
        suit = suitValue;
        imgUrlString += suitValue + ".png";
        URL imgUrl = this.getClass().getResource(imgUrlString);
        BufferedImage tmpFoundationImg = ImageIO.read(imgUrl);
        ImageView foundationImage = new ImageView(SwingFXUtils.toFXImage(tmpFoundationImg, null));
        getChildren().add(foundationImage);

        setOnMouseClicked(mouseClickedHandler(this));
    }

    /**
     * getThisSuit() - returns the suit that is assigned to this foundation Gui
     * @return - the suit assigned to this Foundation.
     */
    public Suit getThisSuit(){ return suit; }

    /**
     * mouseClickedHandler - creates the handler for what happens when a mouse is clicked on a foundation gui.
     * @param providedFoundationGui - the Foundation Gui that was clicked on
     * @return Returns and EventHandler<MouseEvent>
     */
    private EventHandler<MouseEvent> mouseClickedHandler(FoundationGui providedFoundationGui){
        return new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent theEvent){

                //Check to see if the Games clipboard is currently empty
                if(! GameModel.instance().getClipboard().isEmpty()){
                    // if it is not, that means another card is queued up to be moved, set the Object key to a variable
                    Object thisClipboardKey = GameModel.instance().getClipboard().get(GameModel.getClipboardKey());
                    // also, it means that this foundation gui is the desired destination for the card, so retreive the Foundation Stack object that corresponds to this GUI
                    Foundation desiredLocation = GameModel.instance().getFoundation(providedFoundationGui.getThisSuit());

                    // If thisClipBoardKey is a FreeCell Object, this means if the destination is from a FreeCell
                    if(thisClipboardKey instanceof FreeCellGui){
                        // Assign and retireve the FreeCell Gui and the FreeCell Stack object
                        FreeCellGui originGui = (FreeCellGui) thisClipboardKey;
                        FreeCell originStack = GameModel.instance().getFreecell(originGui.getThisIndex());
                        // Check and see if we can push the top card from the origin stack, into this Foundation.
                        if(desiredLocation.canPush((PlayingCard)originStack.peek())){
                            // If we can,
                            //  get the card,
                            //  push it into the stack,
                            //  pull the image from the card,
                            //  set the opacity back to 1,
                            //  add it to the scene children,
                            //  and clear the clipboard
                            PlayingCard theCard = (PlayingCard)originStack.pop();
                            desiredLocation.push(theCard);
                            ImageView image = theCard.getImage();
                            image.setOpacity(1.0);
                            getChildren().add(image);
                            GameModel.instance().getClipboard().clear();
                            // If this card's value is 13, that means a King was just placed in, so add 1 to the game counter
                            if(theCard.getValue() == 13){
                                GameModel.instance().gameCounter++;

                                // If that equals 4 - that means the game is over, and the player has won, call in the game over function
                                if(GameModel.instance().gameCounter == 4){
                                    MainGui.gameOver();
                                }
                            }
                        }
                    }
                    // If thisClipboardKey is a TableStackGui Object, that means the origin is from a Table Stack
                    if(thisClipboardKey instanceof TableStackGui){
                        // Get the TableStackGui and the TableStack Object of the origin
                        TableStackGui originGui = (TableStackGui) thisClipboardKey;
                        TableStack originStack = GameModel.instance().getTableStacks(originGui.getThisIndex());

                        // Test if we canPush the card, into the desired foundation stack
                        if(desiredLocation.canPush((PlayingCard)originStack.peek())){
                            // If it can
                            //  Get the Playing Card
                            //  Push the card into the foundation stack
                            //  pull the image from the card
                            //  set the card images opacity to 1
                            //  set the translate y to 0
                            //  decrment the offset of the table stack by 1
                            //  add the image to the children
                            //  clear the clipboard
                            PlayingCard theCard = (PlayingCard)originStack.pop();
                            desiredLocation.push(theCard);
                            ImageView image = theCard.getImage();
                            image.setOpacity(1.0);
                            image.setTranslateY(0);
                            originGui.decrementOffset();
                            getChildren().add(image);
                            GameModel.instance().getClipboard().clear();
                            // If this card's value is 13, that means a King was just placed in, so add 1 to the game counter
                            if(theCard.getValue() == 13){
                                GameModel.instance().gameCounter++;

                                // If that equals 4 - that means the game is over, and the player has won, call in the game over function
                                if(GameModel.instance().gameCounter == 4){
                                    MainGui.gameOver();
                                }
                            }
                        }
                    }
                }
                // fire the event off.
                theEvent.consume();
            }
        };
    }
}
