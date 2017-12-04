package TableElements;
import PlayingCard.*;
import Stack.Stack;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;


public class Deck extends Stack {
    private Stack<PlayingCard> deck = new Stack<PlayingCard>();
    int size;

    private final int cardX = 75;
    private final int cardY = 115;
    private URL imgUrl = this.getClass().getResource("/assets/img/playingCards.png");
    private BufferedImage playingCardSprite = ImageIO.read(imgUrl);
    private BufferedImage tempImage;

    /**
     * Deck - creates a new Deck Object
     * @throws IOException - throws IO Exception when the Image cannot be found.
     */
    public Deck() throws IOException{
        int tmpCardY = 0;
        // Loop through the suit enumeration
        for(Suit theSuit : Suit.values()){
            int tmpCardX = 0;
            // loop through the 15 cards
            for(int e = 2; e < 15; e++){
                // pull a sub image, out of the large sprite of images,
                //  CardX and CardY are the physical dimensions of the card being made (75x115),
                //  tmpCardX and tmpCardY are the location, where those deminsions will be pulled from -
                //      first pass through, it will grab a 75x115 image starting at position 0,0 in the sprite,
                //      second pass through, it will grab a 75x115 image starting at position 75,0 in the sprite
                //      etc...
                tempImage = playingCardSprite.getSubimage(tmpCardX,tmpCardY,cardX,cardY);
                // set the created subImage to it's own image view
                ImageView playingCardImage = new ImageView(SwingFXUtils.toFXImage(tempImage, null));
                // push the card to the deck
                deck.push(new PlayingCard(e, theSuit, playingCardImage));
                //increase the size of the deck by 1
                size++;
                // add 75 to the tmpCardX, this will move the next image grabbed, to the right by 75px's
                tmpCardX += cardX;
            }
            //add 115 to the Y value, this will move the next image grabbed, down by 75px's
            tmpCardY += cardY;
        }
    }

    /**
     * deck() - Overloaded Deck constructor, adds in the option to set the default state of the cards (facedown or faceup)
     * @param defaultVisibility - boolean, desired state of the PlayingCards visibility, true for faceup, false for facedown
     * @throws IOException
     */
    public Deck(boolean defaultVisibility) throws IOException{
        int tmpCardY = 0;
        for(Suit theSuit : Suit.values()){
            int tmpCardX = 0;
            for(int e = 2; e < 15; e++){
                tempImage = playingCardSprite.getSubimage(tmpCardX,tmpCardY,cardX,cardY);
                //Image tmpCardImg = SwingFXUtils.toFXImage(tempImage,null);
                ImageView playingCardImage = new ImageView(SwingFXUtils.toFXImage(tempImage, null));
                deck.push(new PlayingCard(e, theSuit, defaultVisibility, playingCardImage));
                size++;
                tmpCardX += cardX;
            }
            tmpCardY += cardY;
        }
    }

    /**
     * shuffle() - randomizes the order of the cards, in the Deck object
     */
    public void shuffle() {
        ArrayList<PlayingCard> tempHolder = new ArrayList<PlayingCard>();
        Iterator<PlayingCard> deckIterator = deck.iterator();
        while(deckIterator.hasNext()) {
            PlayingCard curr = deckIterator.next();
            tempHolder.add(curr);
        }
        deck.deleteStack();
        Collections.shuffle(tempHolder);
        for(int i = 0; i < tempHolder.size(); i++){
            deck.push(tempHolder.get(i));
        }
        tempHolder = null;
     }

    /**
     * Deals the top card from the deck, flipping it from it's default state.
     * @return a PlayingCard Object
     */
    public PlayingCard dealCard(){
        PlayingCard tmpCard = deck.pop();
        tmpCard.flip();
        return tmpCard;
    }

    /**
     * getSize() returns the size of the deck
     * @return the size of the deckObject
     */
    public int getSize(){
        return size;
    }

    /**
     * dealCard() - overloaded method, allows to manually set the cards visibility for more control
     * @param cardVisible - boolean, desired state of the PlayingCards visibility, true for faceup, false for facedown
     * @return
     */
    public PlayingCard dealCard(boolean cardVisible){
        PlayingCard tmpCard = deck.pop();
        tmpCard.setVisibility(cardVisible);
        return tmpCard;

    }

    /**
     * deckToString() - returns all of the content of the deck as a string, with a line break between each individual PlayingCard object
     * @return a String for the Deck of Cards
     */
    public String deckToString(){
        String deckString = "";
        Iterator<PlayingCard> deckIterator = deck.iterator();
        while(deckIterator.hasNext()){
            PlayingCard curr = deckIterator.next();
            deckString += curr.toString() + "\n";
        }
        return deckString;
    }
}
