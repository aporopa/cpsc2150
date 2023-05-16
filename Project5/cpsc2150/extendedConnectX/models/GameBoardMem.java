package cpsc2150.extendedConnectX.models;
import java.util.*;

/**
 * GameBoardMem Javadocs and contracts
 *
 * @author Abigail Poropatich
 * @version 5.0
 */

/**
 * @invariant MIN_ROW <= numRows <= MAX_ROWS_NUM
 * @invariant MIN_COLUMN <= numColumns <= MAX_COLUMN_NUM
 * @invariant MIN_TO_WIN <= numToWin <= MAX_NUM_TO_WIN
 *
 * @Correspondence self.row = numRow
 * @Correspondence self.column = numColumn
 * @Correspondence self.win = numToWin
 * @Correspondence self.GameBoard = board[MAX_ROW_NUM-1...0][0...MAX_COLUMN_NUM-1]
 * @Correspondence self.Map = boardMap
 */

public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    private int numRows;
    private int numColumns;
    private int numToWin;

    private HashMap<Character, List<BoardPosition>> boardMap;

    /**
     * Initialized the board to (row * column) blank spaces
     *
     * @param NumRows amount of rows
     * @param NumColumns amount of columns
     * @param NumToWin amount of counts in a row to win
     *
     * @post numRows = NumRows AND numColumns = NumColumns AND numToWin = NumToWin
     * @post board initialized empty
     */
    public GameBoardMem(int NumRows, int NumColumns, int NumToWin){
        numRows = NumRows;
        numColumns = NumColumns;
        numToWin = NumToWin;

        boardMap = new HashMap<>();
    }

    @Override
    public int getNumRows(){return numRows;}

    @Override
    public int getNumColumns(){return numColumns;}

    @Override
    public int getNumToWin(){return numToWin;}

    @Override
    public void placeToken(char p, int c) {
        if(!boardMap.containsKey(p)){
            boardMap.putIfAbsent(p, new ArrayList<>());
        }

        for(int i = 0; i < getNumRows(); i++){
            if(whatsAtPos(new BoardPosition(i,c)) == ' '){
                boardMap.get(p).add(new BoardPosition(i,c));
                break;
            }
        }
    }
    @Override
    public char whatsAtPos(BoardPosition pos){
        for(HashMap.Entry<Character, List<BoardPosition>> userInput : boardMap.entrySet()){
            if((userInput.getValue()).contains(pos)){
                return userInput.getKey();
            }
        }
        return ' ';
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char p){
        if(boardMap.containsKey(p) == false) return false;

        List<BoardPosition> post = boardMap.get(p);
        for(int i = 0; i < post.size(); i++){
            if(post.get(i).equals(pos)) return true;
        }
        return false;
    }

}
