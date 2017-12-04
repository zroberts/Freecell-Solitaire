package Model;
import Model.GameModel.FreeCellIndex;
import java.util.Map;
import java.util.HashMap;
import TableElements.FreeCell;


public class FreeCellModel {
    private Map<FreeCellIndex, FreeCell> freeCellMap = new HashMap<>();

    /**
     * FreeCellModel() - keeps track of the assignment between FreeCell Object and the FreeCellIndex
     */
    public FreeCellModel(){
        for (FreeCellIndex fci : FreeCellIndex.values()){
            freeCellMap.put(fci, new FreeCell());
        }
    }

    /**
     * Returns the FreeCell Object for a given FreeCell Index
     * @param fci - A valid free-cell index
     * @return A FreeCell Object
     */
    public FreeCell getValue(FreeCellIndex fci){
        return freeCellMap.get(fci);
    }
}
