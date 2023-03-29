package cpsc2150.extendedConnectX.models;
import java.util.*;
/**
 * AbsGameBoard JavaDoc and contracts
 *
 * @author Abigail Poropatich
 * @version 3.0
 */
public abstract class AbsGameBoard implements IGameBoard {
    /**
     * This method provides a string representation of the contents of the board.
     *
     * @return String representation of the board
     *
     * @post toString = [ Formats a string representing GameBoard ]
     *                  AND GameBoard = #GameBoard AND winCondition = #winCondition
     *                  AND playAgain = #playAgain
     */
    @Override
    public String toString(){
        //this messes up placeToken()
        StringBuilder board = new StringBuilder("|");
        for(int i = 0; i < getNumColumns(); i++){
            board.append(String.format("%2d", i));
            board.append("|");
        }
        board.append("\n");


        for(int row = getNumRows() - 1; row >= 0; row--){
            for(int col = 0; col < getNumColumns(); col++){
                char token = whatsAtPos(new BoardPosition(row,col));
                board.append("|").append(String.format("%c ", token));
                if (col == getNumColumns() - 1) {board.append("|\n");}
            }
        }

        String newString = board.toString();
        return newString;
    }
}