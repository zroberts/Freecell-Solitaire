package Model;
import Model.GameModel.TableStackIndex;
import TableElements.*;

import java.util.Map;
import java.util.HashMap;


public class TableStackModel {
    private Map<TableStackIndex, TableStack> tableStackMap = new HashMap<>();

    /**
     * Base Constructor, builds out 4 empty TableStack's and maps them to the TableStackIndex's
     */
    public TableStackModel() {
        for (TableStackIndex tsi : TableStackIndex.values()) {
            tableStackMap.put(tsi, new TableStack());
        }

    }

    /**
     * buildTableStack() - Takes a valid deck of cards, and deals one card, into each stack, repeating until the deck is empty
     * @param inDeck - A valid Deck of Cards
     */
    public void buildTableStack(Deck inDeck){
        assert inDeck != null;
        int deckSize = inDeck.getSize();
        int numOfStacks = TableStackIndex.values().length;
        int e = 0;
        for(int i = 0; i < deckSize; i++){
            tableStackMap.get(TableStackIndex.values()[e]).push(inDeck.dealCard());
            e++;
            if(e == numOfStacks){ e = 0;}
        }
        for (TableStackIndex tsi : TableStackIndex.values()) {
            tableStackMap.get(tsi).doneDealing();
        }
    }

    /**
     * buildTableStack() - Takes a valid deck of cards, and deals one card, into each stack, repeating until the deck is empty. Doesn't disable the boolean that allows any card to be pushed into any TableStack (Which is traditionally used when dealing), which effectively leaves on cheat mode
     * @param inDeck - a Valid card deck
     * @param cheatMode - boolean, true to leave cheat mode enabled, false to disable it
     */
    public void buildTableStack(Deck inDeck, boolean cheatMode){
        assert inDeck != null;
        int deckSize = inDeck.getSize();
        int numOfStacks = TableStackIndex.values().length;
        int e = 0;
        for(int i = 0; i < deckSize; i++){
            tableStackMap.get(TableStackIndex.values()[e]).push(inDeck.dealCard());
            e++;
            if(e == numOfStacks){ e = 0;}
        }
        if(!cheatMode) {
            for (TableStackIndex tsi : TableStackIndex.values()) {
                tableStackMap.get(tsi).doneDealing();
            }
        }

    }

    /**
     * getValue() - gets the TableStack that is mapped to the provided TableStackIndex()
     * @param tsi - the provided TableStackIndex
     * @return - the TableStack that is mapped to the Index
     */
    public TableStack getValue(TableStackIndex tsi){
        return tableStackMap.get(tsi);
    }

}

