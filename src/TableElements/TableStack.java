package TableElements;
import PlayingCard.*;
import Stack.*;
import java.util.Iterator;
public class TableStack extends Stack {
    private boolean initialDeal = true;

    /**
     * TableStack() - Creates an empty table Stack
     */
    public TableStack(){
            super();
        }

    /**
     * push() attempst to push the card into the stack. This is only allowed if the card is the opposite color, and one value less then the top card, or if the initial deal is turned on
     * @param inCard - the PlayingCard to be pushed to the TableStack;
     * @return a boolean, true if the card was pushed into the stack, false if it was not.
     */
    public boolean push(PlayingCard inCard){
        if(initialDeal || this.empty()){
            super.push(inCard);
            return true;
        }else{
            PlayingCard topCard = (PlayingCard)this.peek();
            if(!(topCard.compareColor(inCard)) && (topCard.compareTo(inCard) == 1) && topCard.getValue() != 14){
                super.push(inCard);
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * canPush() - Determines if the card can be pushed into the stack, but does not push it into the stack
     * @param inCard - the PlayingCard to be tested
     * @return boolean - true if it can be pushed into the stack, false if it cannot be
     */
    public boolean canPush(PlayingCard inCard){
        if(initialDeal || inCard == null){
            return true;
        }
        PlayingCard topCard = (PlayingCard)this.peek();
        if(!(topCard.compareColor(inCard)) && (topCard.compareTo(inCard) == 1) && topCard.getValue() != 14){
            return true;
        }else{
            return false;
        }

    }

    /**
     * reverseOrder() - provides an iterator, that produces the cards in reverse order i.e bottom of the stack to the top of the stack
     * @return - an Iterator() object
     */
    public Iterator reverseOrder(){
        return this.reverseIterator();
    }

    /**
     * inOrder() - provides an iterator, that produces the cards in order, i.e. top of the stack to the bottom of the stack
     * @return an Iterator() object
     */
    public Iterator inOrder(){
        return this.iterator();
    }

    /**
     * printStackReverse() provides a String of all of the cards in the stack, in reverse order
     * @return String the cards in reverse order
     */
    public String printStackReverse() {
        Iterator<PlayingCard> stackIterator = this.reverseIterator();
        String toReturn = "";
        while(stackIterator.hasNext()){
            toReturn +=  " " + stackIterator.next().toString();
        }
        return toReturn;
    }

    /**
     * printStack() provides a String of all of the cards in the stack, in reverse order
     * @return String the cards in reverse order
     */
    public String printStack(){
        String toReturn = "";
        Iterator<PlayingCard> stackIterator = this.iterator();

        while(stackIterator.hasNext()){
            toReturn += " " + stackIterator.next().toString();
        }
        return toReturn;
    }

    /**
     * doneDealing() - changes the boolean of "initialDeal" to false.
     * This enforces the rules for the stacks, which are not applicable on initial deck deal.
     */
    public void doneDealing(){
    initialDeal = false;
}
}
