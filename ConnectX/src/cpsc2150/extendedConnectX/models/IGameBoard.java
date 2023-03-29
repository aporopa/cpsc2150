package cpsc2150.extendedConnectX.models;
import java.util.*;

/**
 * IGameBoard JavaDoc and contracts
 *
 * @author Abigail Poropatich
 * @version 3.0
 */

/**
 * This the interface for a GameBoard object it contains functions that manipulate the
 * 2D that is initialized by the constructor. The beginning of the 2D array is classified
 * as (0,0), and places tokens from the bottom up.
 *
 * @defines maxRow: Number of rows in GameBoard
 * @defines maxColumn: Number of rows in GameBoard
 * @defines numToWin: Number of consecutive tokens from one player needed to win
 *
 * @constraints maxRow >= 1 && maxColumn >= 1
 * @constraints 1 <= numToWin <= (maxRow && maxColumn)
 *
 * @initialization_ensures [ Gameboard of size (maxRow * maxColumn) will be created with
 *                          each position starting as ' ' ]
 */
public interface IGameBoard {
    public static final int maxRow = 100;
    public static final int maxColumn = 100;
    public static final int numToWin = 25;
    public static final int minRow = 3;
    public static final int minColumn = 3;
    public static final int minNumToWin = 3;

    /**
     * This function returns the number of rows in a Gameboard object
     *
     * @return number of rows in GameBoard object
     * @post getNumRows = #numRows
     */
    public int getNumRows();

    /**
     * This function returns the number of columns in the Gameboard
     *
     * @return the maximum number of columns
     * @post getNumColumns = maxColumn
     */
    public int getNumColumns();

    /**
     * This function returns the amount of continuous tokens needed to win
     *
     * @return the maximum number of tokens needed horizontally, diagonally
     *          or vertically to win as defined by the user
     * @post getNumToWin = numToWin
     */
    public int getNumToWin();

    /**
     * This function returns true if the column can accept another token and false otherwise
     *
     * @pre c <= c < maxColumn
     * @param c The token position
     *
     * @return true iff the column != FULL
     * @return false iff the column == FULL
     *
     * @post Function returns false iff (c > the column size) || function returns true iff (c <= maxColumn)
     * @post checkIfFree iff myBoard[maxRow] = ' ' AND myBoard = #myBoard
    AND GamedBoard = #Gameboard AND winCondition = #winCondition AND playAgain = #playAgain
     */
    default boolean checkIfFree(int c){
        for (int i = 0; i < getNumRows(); i++){
            if (whatsAtPos(new BoardPosition(i, c)) == ' ') {return true;}
        }
        return false;
    }

    /**
     * This function places the character p in column c. The token will be placed in the lowest available row in column c
     *
     * @pre c <= the column size
     * @pre checkIfFree == true
     *
     * @param p The character to be placed
     * @param c The position in the column
     *
     * @post iff p.c <= maxColumn [ GameBoard will reflect new token placement at the lowest available row ]
     *       AND winCondition = #winCondition AND playAgain = #playAgain AND GameBoard = #GameBoard + 1
     * @post iff p.c >  maxColumn then GameBoard = #GameBoard AND winCondition = #winCondition
     *       AND playAgain = #playAgain
     */
    public void placeToken(char p, int c);

    /**
     * This function checks to see if the last token placed resulted in numToWin in a row horizontally
     *
     * @pre A token has been placed
     *
     * @param pos The position of the token on the board
     * @param p The player
     *
     * @return winCondition == true iff row contains 5 of the same tokens in uninterrupted row
     * @return winCondition == false iff row does not contain 5 of the same tokens in uninterrupted row
     *
     * @post iff winCondition == true if pos is the last to make up the numToWin of consecutive
     *       same markers needed to win horizontally
     *       AND GameBoard = #GameBoard AND playAgain = #playAgain
     * @post iff winCondition == false if pos is not the last to make up the numToWin of consecutive
     *       same markers needed to win horizontally
     *       AND GameBoard = #GameBoard AND playAgain = #playAgain
     */
    default boolean checkHorizWin(BoardPosition pos, char p){
        int count = 1;
        int numToWin = getNumToWin();
        int numCols = getNumColumns();
        BoardPosition thisPos = pos;

        // check tokens to the right
        for (int col = pos.getColumn() + 1; col < numCols; col++) {
            thisPos = new BoardPosition(thisPos.getRow(), (thisPos.getColumn()+1));
            if (whatsAtPos(thisPos) == p) {
                count++;
                if (count == numToWin) {
                    return true;
                }
            } else {
                break;
            }
        }

        // check tokens to the left
        for (int col = pos.getColumn() - 1; col >= 0; col--) {
            thisPos = new BoardPosition(thisPos.getRow(), (thisPos.getColumn()-1));
            if (whatsAtPos(thisPos) == p) {
                count++;
                if (count == numToWin) {
                    return true;
                }
            } else {
                break;
            }
        }

        return false;
    }

    /**
     * This function checks to see if the last token placed resulted in numToWin in a row vertically
     *
     * @pre A token has been placed
     * @pre pos The position on game board of the latest play
     *
     * @param pos The position on game board of the latest play
     * @param p The marker at pos
     *
     * @return winCondition == true iff column contains numToWin of the same tokens in uninterrupted column
     * @return winCondition == false iff column does not contain numToWin of the same tokens in uninterrupted column
     *
     * @post iff winCondition == true if pos is the last to make up the numToWin of consecutive
     *       same markers needed to win vertically
     *       AND GameBoard = #GameBoard AND playAgain = #playAgain
     * @post iff winCondition == false if pos is not the last to make up the numToWin of consecutive
     *       same markers needed to win vertically
     *       AND GameBoard = #GameBoard AND playAgain = #playAgain
     */
    default boolean checkVertWin(BoardPosition pos, char p){
        int count = 0;
        int col = pos.getColumn();

        for(int i = 0; i < getNumRows() && col < getNumColumns(); i++){
            if(isPlayerAtPos(new BoardPosition(i, col), p)) count++;
        }

        if(count >= getNumToWin()) return true;
        else return false;

    }

    /**
     * This function checks to see if the last token placed resulted in numToWin in a row diagonally
     *
     * @pre A token has been placed
     *
     * @param pos The position of the token on the board
     * @param p The player
     *
     * @return winCondition == true iff diagonal contains numToWin of the same tokens in uninterrupted diagonal
     * @return winCondition == false iff diagonal does not contain numToWin of the same tokens in uninterrupted diagonal
     *
     * @post iff winCondition == true if pos is the last to make up the numToWin of consecutive
     *       same markers needed to win diagonally
     *       AND GameBoard = #GameBoard AND playAgain = #playAgain
     * @post iff winCondition == false if pos is not the last to make up the numToWin of consecutive
     *       same markers needed to win diagonally
     *       AND GameBoard = #GameBoard AND playAgain = #playAgain
     */
    default boolean checkDiagWin(BoardPosition pos, char p){

        //checking bottom right
        int countR = 1;
        int countL = 1;
        BoardPosition current = pos;


        for(int i = 0; current.getRow() + i < getNumRows() && current.getColumn() + i < getNumColumns(); i++){
            if(whatsAtPos(current) == p) {
                current = new BoardPosition(current.getRow() + i, current.getColumn() + i);
                if (i != 0) countR += 1;
            }
            else break;
        }

        //checking top left
        //reset count and current
        for(int i = 0; current.getRow() - i >= 0 && current.getColumn() - i >= 0; i++){
            if(whatsAtPos(current) == p) {
                current = new BoardPosition(current.getRow() - i, current.getColumn() - i);
                if (i != 0) countR += 1;
            }
            else break;
        }

        //check bottom left
        for(int i = 0; current.getRow() - i >= 0 && current.getColumn() + i < getNumColumns(); i++){
            if(whatsAtPos(current) == p) {
                current = new BoardPosition(current.getRow() - i, current.getColumn() + i);
                if (i != 0) countL += 1;
            }
            else break;
        }


        //check top right
        for(int i = 0; current.getRow() + i < getNumRows() && current.getColumn() - i >= 0; i++){
            if(whatsAtPos(current) == p) {
                current = new BoardPosition(current.getRow() + i, current.getColumn() - i);
                if (i != 0) {countR += 1;}
            }
            else break;
        }

        if(countR >= getNumToWin() || countL >= getNumToWin()) return true;

        //no winner found
        return false;
    }

    /**
     * This function checks to see what is in the game board a specific position
     *
     * @pre 0 <= pos.getRow() < maxRow && 0 <= pos.getColumn() < maxColumn
     *
     * @param pos The GameBoard position
     *
     * @return 'X' || 'O' iff player1 token is at pos
     * @return 'X' || 'O' iff player2 token is at pos
     * @return ' ' iff no token is at pos
     *
     * @post The token character is returned AND GameBoard = #GameBoard
     *       AND winCondition = #winCondition AND playAgain = #playAgain
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * This function checks to see if the player is at a certain position
     *
     * @pre 0 <= pos.getRow() < maxRow && 0 <= pos.getColumn() < maxColumn
     *
     * @param pos The GameBoard position
     * @param p The player
     *
     * @return true iff p exists at pos
     * @return false iff p !exists at pos
     *
     * @post true is returned iff p exists at pos OR false is returned iff p does not exist at post
     *       AND GameBoard = #GameBoard AND winCondition = #winCondition AND playAgain = #playAgain
     */
    default boolean isPlayerAtPos(BoardPosition pos, char p){return (whatsAtPos(pos) == p);}

    /**
     * This function checks if a player has won
     *
     * @param c The token's column position
     * @pre 0 <= c < maxCol
     *
     * @return true iff player's tokens == numToWin,
     * @return false iff player's tokens != numToWin
     *
     * @post checkForWin = true iff the player won horizontally, diagonally, or vertically
     *       checkForWin = false iff the player has not won horizontally, diagonally, or vertically
     */
    default boolean checkForWin(int c){

        char defaultVal = ' ';
        int rowMinusMinus = getNumRows() - 1;
        BoardPosition pos = new BoardPosition(rowMinusMinus, c);

        while (whatsAtPos(pos) == defaultVal) {
            rowMinusMinus--;
            pos = new BoardPosition(rowMinusMinus, c);
        }

        if(checkHorizWin(pos, whatsAtPos(pos))){return true;}
        if(checkVertWin(pos, whatsAtPos(pos))){return true;}
        if(checkDiagWin(pos, whatsAtPos(pos))){return true;}

        return false;
    }

    /**
     * This function checks if the game has resulted in a tie
     *
     * @pre winCondition == false
     * @pre All board positions have been filled
     *
     * @return true iff GameBoard is full and has no winner
     * @return false iff GameBoard is not full OR there is a winner
     *
     * @post if true is returned then end game OR if false is returned and GameBoard != full then continue playing
     *       AND GameBoard = #GameBoard AND winCondition = #winCondition AND playAgain = #playAgain
     */
    default boolean checkTie(){
        //looping through the board to check for empty spaces
        for(int i = 0; i < maxRow; i++){
            for(int j = 0; j < maxColumn; j++){
                if(whatsAtPos(new BoardPosition(i,j)) == ' '){return false;}
            }
        }
        return true;
    }
}
