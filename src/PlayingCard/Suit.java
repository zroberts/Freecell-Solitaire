package PlayingCard;

/**
 * Creates an enumeration, representing the the Suits of a cards
 */
public enum Suit {
    spades(0), clubs(1), hearts(2), diamonds(3);
    private final int value;
    private Suit(int givenValue){
        this.value = givenValue;
    }
    public int getValue(){
        return value;
    }
}
