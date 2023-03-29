package cpsc2150.extendedConnectX.models;

/**
 * GameBoard JavaDoc and contracts
 *
 * @author Abigail Poropatich
 * @version 3.0
 */
public class GameBoard extends AbsGameBoard implements IGameBoard {
    /**
     * @invariant A token cannot be placed out of bounds
     * @invariant A token cannot be added to a full column
     * @invariant [ Board has no vertical gaps between game pieces ]
     * @invariant [ playAgain is 'Y'/'y' OR 'N'/'n']
     * @invariant [ Position will remain blank (' ') until a token is place ]
     * @invariant The GameBoard size is rowSize * columnSize
     *
     * @Correspondence self.row = maxRow
     * @Correspondence self.column = maxColumn
     * @Correspondence self.win = maxNumToWin
     * @Correspondence self = board[MAX_ROW_NUM-1...0][0...MAX_COLUMN_NUM-1]
     */
    private int numToWin;
    private int numColumn;
    private int numRow;
    private char[][] gameboard;

    public GameBoard(int numRow, int numColumn, int numToWin){
        this.numRow = numRow;
        this.numColumn = numColumn;
        this.numToWin = numToWin;

        gameboard = new char[numRow][numColumn];
        for(int i = 0; i < numRow; i++){
            for(int j = 0; j < numColumn; j++){
                gameboard[i][j] = ' ';
            }
        }
    }

    public int getNumRows(){return numRow;}

    public int getNumColumns(){return numColumn;}

    public int getNumToWin(){return numToWin;}

    public void placeToken(char p, int c) {
        // check if the spot is free
        for(int i = 0; i < getNumRows(); i++) {
            if(gameboard[i][c] == ' ') {
                gameboard[i][c] = p;
                return;
            }
        }
    }

    public char whatsAtPos(BoardPosition pos){
        return gameboard[pos.getRow()][pos.getColumn()];
    }


}