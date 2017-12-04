package TableElements;
import Stack.Stack;
import PlayingCard.*;
public class Foundation extends Stack {
    private Suit foundationSuit;

    /**
     * foundation() constructor for a Foundation object
     * @param inSuit the Suit assigned to this foundation
     */
    public Foundation(Suit inSuit){
        foundationSuit = inSuit;
    }

    /**
     * push() - adds a PlayingCard object to the top of the Foundation
     * @param inCard the DesiredPlayingCard to be pushed into the Foundation
     * @return boolean true if the card was successfully pushed into the Foundation, otherwise false
     */
    public boolean push(PlayingCard inCard){
        if(inCard.getSuit() == foundationSuit){
            if(this.empty()) {
                if(inCard.getValue() == 14){
                    super.push(inCard);
                    return true;
                }
            }else{
                PlayingCard topOfStack = (PlayingCard)this.peek();
                if(topOfStack.getValue() == 14 && inCard.getValue() == 2){
                    super.push(inCard);
                    return true;
                }else if(inCard.compareTo(topOfStack) == 1){
                    super.push(inCard);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * canPush() determines if provided card is able to be pushed into the stack, but does not push it
     * @param inCard - the desiredCard to be checked
     * @return boolean, true if it could be, false otherwise
     */
    public boolean canPush(PlayingCard inCard){
        if(inCard.getSuit() == foundationSuit){
            if(this.empty()) {
                if(inCard.getValue() == 14){
                    return true;
                }
            }else{
                PlayingCard topOfStack = (PlayingCard)this.peek();
                if(topOfStack.getValue() == 14 && inCard.getValue() == 2){
                    return true;
                }else if(inCard.compareTo(topOfStack) == 1){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * pop() returns null, unable to pop from the Foundations()
     * @return returns null
     */
    public PlayingCard pop(){
        return null;
    }
}
