package cpsc2150.extendedConnectX.models;
import java.util.*;

/**
 * IGameBoard JavaDoc and contracts
 *
 * @author Abigail Poropatich
 * @version 1.0
 */

/**
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
    public static final int maxRow = 9;
    public static final int maxColumn = 7;
    public static final int numToWin = 5;

    /**
     * This function returns the number of rows in the Gameboard
     *
     * @return the maximum number of rows
     * @post getNumRows = maxRow
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
     *          or vertically to win
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

        //checking to the right of the initial position
        for(int i = 1; i < numToWin; i++){
            int colVal = pos.getColumn() + i;
            if(colVal >= maxColumn || whatsAtPos(new BoardPosition(pos.getRow(), colVal)) != p){break;}
            count++;
        }

        //check to the left of the initial position
        for(int i = 1; i < numToWin; i++){
            int colVal = pos.getColumn() - i;
            if(colVal < 0 || whatsAtPos(new BoardPosition(pos.getRow(), colVal)) != p){break;}
            count++;
        }

        //check for winner and return result
        if(count >= numToWin){return true;}
        else{return false;}
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
        int count = 1;

        for(int i = 1; i < numToWin; i++){
            int rowVal = pos.getRow() + i;
            if(rowVal >= maxRow || whatsAtPos(new BoardPosition(rowVal, pos.getColumn())) != p){break;}
            count++;
        }

        for(int i = 1; i < numToWin; i++){
            int rowVal = pos.getRow() - i;
            if(rowVal < 0 || whatsAtPos(new BoardPosition(rowVal, pos.getColumn())) != p){break;}
            count++;
        }

        //check for winner and return result
        if(count >= numToWin){return true;}
        else{return false;}
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
        int count = 1;
        BoardPosition current = new BoardPosition(pos.getRow(), pos.getColumn());
        int row = current.getRow();
        int col = current.getColumn();
        while(count < numToWin && row > 0 && col < maxColumn - 1){
            current = new BoardPosition(row - 1, col + 1);
            if(whatsAtPos(current) == p) {
                count++;
                row--;
                col++;
            }
            else{break;}
        }


        if(count == numToWin){return true;}

        //checking top left
        //reset count and current
//        count = 1;
        current = new BoardPosition(pos.getRow(),pos.getColumn());
        row = current.getRow();
        col = current.getColumn();
        while(count < numToWin && row < maxRow - 1 && col > 0){
            current = new BoardPosition(row + 1, col - 1);
            if(whatsAtPos(current) == p){
                count++;
                row++;
                col--;
            }
            else{break;}
        }
        if(count == numToWin){return true;}

        //check bottom left
        //reset count and current
        count = 1;
        current = new BoardPosition(pos.getRow(), pos.getColumn());
        row = current.getRow();
        col = current.getColumn();
        while(count < numToWin && row > 0 && col > 0){
            current = new BoardPosition(row - 1, col - 1);
            if(whatsAtPos(current) == p){
                count++;
                row--;
                col--;
            }
            else{break;}
        }
        if(count == numToWin){return true;}

        //check top right
        //reset count and current
//        count = 1;
        current = new BoardPosition(pos.getRow(),pos.getColumn());
        row = current.getRow();
        col = current.getColumn();
        while(count < numToWin && row < maxRow - 1 && col < maxColumn - 1){
            current = new BoardPosition(row + 1, col + 1);
            if(whatsAtPos(current) == p){
                count++;
                row++;
                col++;
            }
            else{break;}
        }

        if(count == numToWin){return true;}

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
    default boolean isPlayerAtPos(BoardPosition pos, char p){
        return (whatsAtPos(pos) == p);
    }

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
        int numR;
        for(int i = getNumRows() - 1; i > -1; i--){
            BoardPosition current = new BoardPosition(i,c);

            if(whatsAtPos(current) != ' '){
                numR = i;
                break;

            }
        }

        BoardPosition current = new BoardPosition((getNumRows() - 1),c);
        char token = this.whatsAtPos(current);
        if(checkHorizWin(current, token)){return true;}
        if(checkVertWin(current, token)){return true;}
        if(checkDiagWin(current, token)){return true;}

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
