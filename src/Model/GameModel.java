package Model;

import TableElements.*;
import PlayingCard.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;

import java.io.IOException;


public class GameModel {
    private static final GameModel CURR_GAME = new GameModel();
    private TableStackModel tableStack;
    private FreeCellModel freeCells;
    private FoundationModel foundation;
    private ClipboardContent currMove;
    public static int gameCounter = 0;
    private static final DataFormat clipBoardKey = new DataFormat("Free-cell Clipboard");


    /**
     * Creates a new GameModel() object, and runs the reset method.
     */
    public GameModel(){
        reset();
    }

    /**
     * Creates a new GameModel Object, with the option to enable cheatmode
     * @param cheatMode - Boolean, true to enable cheat mode, false to disable it.
     */
    public GameModel(boolean cheatMode){
        reset(cheatMode);
    }

    /**
     * reset() - resets the Game
     */
    public void reset(){
        Deck deck = null;
        tableStack = null;
        freeCells = null;
        foundation = null;
        currMove = null;
        gameCounter = 0;
        try {
            deck = new Deck();
            deck.shuffle();
        }catch(IOException ioe){
            System.out.println(ioe);
            assert true;
        }
        tableStack = new TableStackModel();
        freeCells = new FreeCellModel();
        foundation = new FoundationModel();
        tableStack.buildTableStack(deck);

        currMove = new ClipboardContent();
    }

    /**
     * reset() - resets the game, with optional boolean to enable/disable cheat mode.
     * @param cheatMode - boolean, enables or disables cheatMode. True to enable, false to disable
     */
    public void reset(boolean cheatMode){
        Deck deck = null;
        tableStack = null;
        freeCells = null;
        foundation = null;
        currMove = null;
        gameCounter = 0;
        try {
            deck = new Deck();
            deck.shuffle();
        }catch(IOException ioe){
            System.out.println(ioe);
            assert true;

        }
        tableStack = new TableStackModel();
        freeCells = new FreeCellModel();
        foundation = new FoundationModel();
        System.out.println(cheatMode);
        tableStack.buildTableStack(deck, cheatMode);
        currMove = new ClipboardContent();

    }

    /**
     * Static method that returns this GameModel
     * @return the currently active GameModel
     */
    public static GameModel instance(){
        return CURR_GAME;
    }

    /**
     * getClipboardKey() - returns the Clipboard key for the game, used to keep track of the origin of the cards being moved
     * @return - DataFile - the clipBoardKey
     */
    public static DataFormat getClipboardKey(){return clipBoardKey;}

    /**
     * getClipboard() - returns the value from the clipboard
     * @return - the value currently assigned to the clipboard
     */
    public ClipboardContent getClipboard(){ return currMove; }

    /**
     * An enumeration for the for the FreeCells, used to connect the FreeCell Model, FreeCell GUI, and the FreeCell Table Element together.
     */
    public enum FreeCellIndex{
        fc1, fc2, fc3, fc4;
    }

    /**
     * An enumeation for the TableStacks, used to connect the TableStack Model, TableStack GUI, and TableStack Table Elements together.
     */
    public enum TableStackIndex{
        ts1, ts2, ts3, ts4, ts5, ts6, ts7, ts8;
    }

    /**
     * getTableStacks() - returns the TableStack that is assigned to the corresponding TableStackIndex
     * @param tsi - the TableStackIndex for the TableStack you would like to retrieve
     * @return the TableStack object that corresponds to the TableStackIndex
     */
    public TableStack getTableStacks(TableStackIndex tsi){
        return tableStack.getValue(tsi);
    }

    /**
     * getFreecell() returns the FreeCell that corresponds the provided FreeCell Index
     * @param fci - a FreeCellIndex enumeration.
     * @return returns the FreeCell object that corresponds to the FreeCell Index
     */
    public FreeCell getFreecell(FreeCellIndex fci){
        return freeCells.getValue(fci);
    }

    /**
     * getFoundation() returns the Foundation that corresponds to the supplied Suit
     * @param suit - the Suit enumeration for which you wish to recieve the Foundation
     * @return returns the Foundation Object that corresponds to the FreeCell Index
     */
    public Foundation getFoundation(Suit suit){
        return foundation.getValue(suit);
    }
}
