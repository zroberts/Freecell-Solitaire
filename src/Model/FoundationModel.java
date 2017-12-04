package Model;
import java.util.Map;
import java.util.HashMap;
import TableElements.Foundation;
import PlayingCard.Suit;

public class FoundationModel {
    private Map<Suit, Foundation> foundationMap = new HashMap<>();

    /**
     * FoundationModel() - creates a foundation Model, which is responsible for tieing the Suit Enumeration to the Foundation Object
     */
    public FoundationModel(){
        for (Suit suit : Suit.values()){
            foundationMap.put(suit, new Foundation(suit));
        }
    }

    /**
     * getValue() returns the Foundation Object that is tied to the suit
     * @param suit - the suit that is tied to the Foundation in the Foundation map
     * @return returns the Foundation Objects
     */
    public Foundation getValue(Suit suit){
        return foundationMap.get(suit);
    }

}
