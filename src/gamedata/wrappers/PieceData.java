package gamedata.wrappers;

import gamedata.gamecomponents.Piece;
import java.util.LinkedList;
import java.util.List;

/**
 * Data object representing a list of piece data objects.
 * 
 * Used in JSON deserialization
 * @author annamiyajima
 *
 */
public class PieceData {
        private List<PieceDataIndividual> myPieces;

        public PieceData (List<PieceDataIndividual> pieces) {
            myPieces = pieces;
        }

        public List<PieceDataIndividual> getPieces () {
            return myPieces;
        }
        
        public List<Piece> getPiecesFromData() {
            List<Piece> myPiecesFromData = new LinkedList<Piece>();
            for (PieceDataIndividual pdi : myPieces) {
                myPiecesFromData.add(pdi.getPieceFromData());
            }
            return myPiecesFromData;
        }
        
        public String toString(){
            return myPieces.toString();
        }
    
}
