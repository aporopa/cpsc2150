package cpsc2150.extendedConnectX.models;
import java.util.*;
/**
 * AbsGameBoard JavaDoc and contracts
 *
 * @author Abigail Poropatich
 * @version 1.0
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
        StringBuilder board = new StringBuilder();
        for(int i = 0; i < getNumColumns(); i++){
            board.append("|");
            board.append(i);
        }
        board.append("|\n");

        for(int row = getNumRows() - 1; row >= 0; row--){
            board.append("|");
            for(int col = 0; col < getNumColumns(); col++){
                if(whatsAtPos(new BoardPosition(row,col)) == ' ') board.append(" |");
                else board.append(whatsAtPos(new BoardPosition(row, col))).append("|");
            }
            board.append("\n");
        }
        String newString = board.toString();
        return newString;
    }
}