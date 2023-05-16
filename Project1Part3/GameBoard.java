/**
 * GameBoard JavaDoc and contracts
 *
 * @author Abigail Poropatich
 * @version 1.0
 */
public class GameBoard {
    /**
     * @invariant A token cannot be placed out of bounds
     * @invariant A token cannot be added to a full column
     * @invariant [ Board has no vertical gaps between game pieces ]
     * @invariant [ playAgain is 'Y'/'y' OR 'N'/'n']
     * @invariant [ Position will remain blank (' ') until a token is place ]
     * @invariant The GameBoard size is rowSize * columnSize
     */
    private char[][] GameBoard;
    private boolean winCondition;
    private char playAgain;

    /**
     *This constructor creates a new instance of the GameBoard class
     */
    GameBoard(){}

    /**
     * This function returns true if the column can accept another token and false otherwise
     *
     * @pre c The column must be able to accept tokens within the predefined bounds
     *
     * @param c The token position
     * @return true iff the column != FULL
     * @return false iff the column == FULL
     *
     * @post Function returns false iff (c > the column size) OR function returns true iff (c <= the column size)
     *       AND GamedBoard = #Gameboard AND winCondition = #winCondition AND playAgain = #playAgain
     */
    public boolean checkIfFree(int c){}

    /**
     * This function places the character p in column c. The token will be placed in the lowest available row in column c
     *
     * @pre c <= the column size
     *
     * @param p The character to be placed
     * @param c The position in the column
     *
     * @post iff p.c <= the column max then GameBoard will reflect new token placement
     *       AND winCondition = #winCondition AND playAgain = #playAgain
     * @post iff p.c > the column max then GameBoard = #GameBoard AND winCondition = #winCondition
     *       AND playAgain = #playAgain
     */
    public void placeToken(char p, int c){}

    /**
     * This function checks to see if the last token placed resulted in 5 in a row horizontally
     *
     * @pre A token has been placed
     *
     * @param pos The position of the token on the board
     * @param p The player
     *
     * @return winCondition == true iff row contains 5 of the same tokens in uninterrupted row
     * @return winCondition == false iff row does not contain 5 of the same tokens in uninterrupted row
     *
     * @post iff winCondition == true then there will be a winner
     *       AND GameBoard = #GameBoard AND playAgain = #playAgain
     * @post iff winCondition == false then there will not be a winner
     *       AND GameBoard = #GameBoard AND playAgain = #playAgain
     */
    public boolean checkHorizWin(BoardPosition pos, char p){}

    /**
     * This function checks to see if the last token placed resulted in 5 in a row vertically
     *
     * @pre A token has been placed
     *
     * @param pos The position of the token on the board
     * @param p The player
     *
     * @return winCondition == true iff column contains 5 of the same tokens in uninterrupted column
     * @return winCondition == false iff column does not contain 5 of the same tokens in uninterrupted column
     *
     * @post iff winCondition == true then there will be a winner
     *       AND GameBoard = #GameBoard AND playAgain = #playAgain
     * @post iff winCondition == false then there will not be a winner
     *       AND GameBoard = #GameBoard AND playAgain = #playAgain
     */
    public boolean checkVertWin(BoardPosition pos, char p){}

    /**
     * This function checks to see if the last token placed resulted in 5 in a row diagonally
     *
     * @pre A token has been placed
     *
     * @param pos The position of the token on the board
     * @param p The player
     *
     * @return winCondition == true iff diagonal contains 5 of the same tokens in uninterrupted diagonal
     * @return winCondition == false iff diagonal does not contain 5 of the same tokens in uninterrupted diagonal
     *
     * @post iff winCondition == true then there will be a winner
     *       AND GameBoard = #GameBoard AND playAgain = #playAgain
     * @post iff winCondition == false then there will not be a winner
     *       AND GameBoard = #GameBoard AND playAgain = #playAgain
     */
    public boolean checkDiagWin(BoardPosition pos, char p){}

    /**
     * This function checks to see what is in the game board a specific position
     *
     * @pre The BoardPosition exists
     *
     * @param pos The GameBoard position
     *
     * @return 'token1' iff player1 token is at pos
     * @return 'token2' iff player2 token is at pos
     * @return ' ' iff no token is at pos
     *
     * @post The token character is returned AND GameBoard = #GameBoard
     *       AND winCondition = #winCondition AND playAgain = #playAgain
     */
    public char whatsAtPos(BoardPosition pos){}

    /**
     * This function checks to see if the player is at a certain position
     *
     * @pre The BoardPosition exists
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
    public boolean isPlayerAtPos(BoardPosition pos, char p){}

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
    public String toString(){}


    /**
     * This function checks if the game has resulted in a tie
     *
     * @pre No winner has been established
     * @pre All board positions have been filled
     *
     * @return true iff GameBoard is full and has no winner
     * @return false iff GameBoard is not full OR there is a winner
     *
     * @post if true is returned then end game OR if false is returned and GameBoard != full then continue playing
     *       AND GameBoard = #GameBoard AND winCondition = #winCondition AND playAgain = #playAgain
     */
    public boolean checkTie(){}

}