package TableElements;
import Stack.Stack;
import PlayingCard.*;
public class FreeCell extends Stack {
    /**
     * FreeCell() default constructor for the FreeCell Object.
     */
    public FreeCell(){
        super();
    }

    /**
     * push() pushes the provided card into the FreeCell, if the FreeCell is currently empty
     * @param theCard the desired PlayingCard object
     * @return boolean, true if the card was successfully pushed, false otherwise
     */
    public boolean push(PlayingCard theCard){
        if(this.empty()){
           super.push(theCard);
           return true;
        }else{
           return false;
        }
    }

    /**
     * canPush() determines and returns if the freeCell can be pushed to
     * @return boolean - true if the FreeCell is currently empty, false if it is not
     */
    public boolean canPush(){
        if(this.empty()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * toString() - returns a string of the FreeCell Object
     * @return a String of the FreeCell Object
     */
    public String toString(){
        return super.peek().toString();
    }
}
