package cpsc2150.extendedConnectX.models;

/**
 * GameBoard JavaDoc and contracts
 *
 * @author Abigail Poropatich
 * @version 1.0
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
     * @Correspondence row = maxRow
     * @Correspondence column = maxColumn
     * @Correspondence win = maxNumToWin
     */
    private int numToWin = 5;
    private int numColumn = 7;
    private int numRow = 9;
    private char[][] gameboard;

    public GameBoard(){
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
        if (checkIfFree(c)) {
            int i = getNumRows() - 1;
            // find the lowest empty spot in the column
            while (i >= 0 && gameboard[i][c] != ' ') {
                i--;
            }
            // place the token at the lowest empty spot in the column
            if (i >= 0) {
                gameboard[i][c] = p;
            }
        }
    }

    public char whatsAtPos(BoardPosition pos){return gameboard[pos.getRow()][pos.getColumn()];}
}