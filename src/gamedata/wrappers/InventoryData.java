package gamedata.wrappers;

import gamedata.gamecomponents.Piece;
import java.util.LinkedList;
import java.util.List;

/**
 * Wrapper for inventorydata
 * @author Rica
 *
 */
public class InventoryData {
    private List<PieceData> myInventory;
    
    public InventoryData () {
        myInventory = new LinkedList<PieceData>();
    }
    
    public List<PieceData> getInventory() {
        return myInventory;
    }
}
