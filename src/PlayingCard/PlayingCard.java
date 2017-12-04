package PlayingCard;
import javafx.scene.image.ImageView;
public class PlayingCard implements Comparable<PlayingCard> {
    private Suit suit;
    private int value;
    private ImageView image;
    private boolean visible = false;

    /**
     * PlayingCard() - Creates a PlayingCard, sets the suit to the provided Suit, and the value to the provided value
     * @param value - the Value of the card - 2-14 (2 through Ace)
     * @param suit - a Suit enumeration
     */
    public PlayingCard(int value, Suit suit){
        this.suit = suit;
        this.value = value;
    }

    /**
     * PlayingCard() - Creates a PlayingCard, sets the suit to the provided Suit, and the value to the provided value, and an ImageView assigned to the URL
     * @param value - the Value of the card - 2-14 (2 through Ace)
     * @param suit - a Suit enumeration
     * @param inImage - an ImageView of the image assigned to the PlayingCard
     */
    public PlayingCard(int value, Suit suit, ImageView inImage){
        this.suit = suit;
        this.value = value;
        this.image = inImage;
        this.image.setFitHeight(240);
        this.image.setFitWidth((148));
    }

    /**
     * PlayingCard() - Creates a PlayingCard, sets the suit to the provided Suit, and the value to the provided value, and an ImageView assigned to the URL
     * @param value - the Value of the card - 2-14 (2 through Ace)
     * @param suit - a Suit enumeration
     * @param isVisible - a boolean to set the default state of the PlayingCard (face down or face-up
     * @param inImage - an ImageView of the image assigned to the PlayingCard
     */
    public PlayingCard (int value, Suit suit, boolean isVisible, ImageView inImage){
        this.value = value;
        this.suit = suit;
        this.visible = isVisible;
        this.image = inImage;
        this.image.setFitHeight(115);
        this.image.setFitWidth(75);
    }

    /**
     * toString() - returns a String describing the Playing card
     * @return A String description of the Playing Card
     */
    public String toString(){
        String stringBuilder = "";
        String faceCard = "";
        switch(this.value) {
            case (11):
                faceCard = " J";
                break;
            case (12):
                faceCard = " Q";
                break;
            case (13):
                faceCard = " K";
                break;
            case (14):
                faceCard = " A";
                break;
            default:
                faceCard = null;
                break;
        }
        if(faceCard != null){
            stringBuilder = faceCard + suit;
        }else{
            String tempNum = String.format("%02d",value);
            stringBuilder = tempNum + suit;
        }
        if(this.visible == false){
            stringBuilder = "FDC";
        }
        return stringBuilder;
    }

    /**
     * getSuit() Returns the suit of the playing card
     * @return the suit of the PlayingCard Object
     */
    public Suit getSuit(){
        return this.suit;
    }

    /**
     * getValue() returns the value of the PlayingCard
     * @return the value assigned to the PlayingCard Object
     */
    public int getValue(){
        return value;
    }

    /**
     * compareTo() - returns the difference between thisCads value and the provided Cards value
     * @param inCard the card you wish to compareTo
     * @return an int, the difference between the two cards values
     */
    public int compareTo(PlayingCard inCard){
        return this.value - inCard.value;
    }

    /**
     * compareSuit() - returns if this cards suit is equal to the provided cards suit.
     * @param inCard - the provided card
     * @return boolean, true if they are the same, false if they are not
     */
    public boolean compareSuit(PlayingCard inCard){
        return this.suit == inCard.suit;
    }

    /**
     * compareColor() - compares the Color (Generally Red and Black) of two PlayingCard Objects. Spades/Clubs are considered 1 color, Hearts and Diamonds are considered the other. Any combination of those colors, will return true, opposite colors will return false;
     * @param inCard the PlayingCard that you wish to compare the color with
     * @return boolean, true if they are the same color, false if they are not
     */
    public boolean compareColor(PlayingCard inCard){
        if( (this.suit == Suit.spades || this.suit == Suit.clubs) && (inCard.suit == Suit.spades || inCard.suit == Suit.clubs) ){
            return true;
        }else if( (this.suit == Suit.hearts || this.suit == Suit.diamonds) && (inCard.suit == Suit.hearts || inCard.suit == Suit.diamonds) ){
            return true;
        }else{
            return false;
        }

    }

    /**
     * getImage() returns the ImageView assigned to this PlayingCard object
     * @return ImageView, the image assigned to this PlayingCard
     */
    public ImageView getImage(){
        return this.image;
    }

    /**
     * show() sets this cards visibility to true
     */
    public void show() {
        this.visible = true;
    }

    /**
     * flip() sets this cards visibility to the opposite of it's current state
     */
    public void flip(){
        this.visible = !this.visible;
    }

    /**
     * setVisibility() manually sets the visibility
     * @param visibility boolean value, the desired visibility, true is shown, false is face down
     */
    public void setVisibility(boolean visibility){
        this.visible = visibility;
    }
}
