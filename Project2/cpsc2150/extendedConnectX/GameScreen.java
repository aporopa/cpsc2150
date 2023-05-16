package cpsc2150.extendedConnectX;
import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.IGameBoard;
import java.util.*;
/**
 * GameScreen JavaDoc and contracts
 *
 * @author Abigail Poropatich
 * @version 2.0
 */

/**
 * @invariant 0 <= token < maxColumn && 0 <= token < maxRow
 * @invariant [ playAgain is 'Y' OR 'N']
 */

public class GameScreen{
    public static void main(String[] args) {
        //local variables
        char p1 = 'X';
        char p2 = 'O';
        int integer = 0;
        int turnNumber = 0;
        boolean anotherRound = true;
        GameBoard viewBoard = new GameBoard();

        //getting inital input
        System.out.println("Let's play ConnectX!\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println(viewBoard);

        while (anotherRound) {
            turnNumber++;

            //establishing tha player symbol based on turn
            if (turnNumber % 2 == 1) {
                System.out.println("Player " + p1 + ", pick a column between 0 and " + ((viewBoard.maxColumn) - 1) + "\n");
            } else if (turnNumber % 2 != 1) {
                System.out.println("Player " + p2 + ", pick a column between 0 and " + ((viewBoard.maxColumn) - 1) + "\n");
            }

            integer = scanner.nextInt();

            //input validation blocks
            while (integer < 0 || integer >= viewBoard.maxColumn) {
                System.out.println("Please enter a valid column number");
                integer = scanner.nextInt();
            }

            while (!viewBoard.checkIfFree(integer)) {
                System.out.println("This column is full. Please choose another.");
                integer = scanner.nextInt();

                while (integer < 0 || integer >= viewBoard.maxColumn) {
                    System.out.println("Please enter a valid column number");
                    integer = scanner.nextInt();
                }
            }

            //Placing the token and viewing the board
            if (turnNumber % 2 == 1) {
                viewBoard.placeToken(p1, integer);
                System.out.println(viewBoard);

                if(viewBoard.checkForWin(integer)){
                    break;
                }
            }
            else if (turnNumber % 2 != 1) {
                viewBoard.placeToken(p2, integer);
                System.out.println(viewBoard);

                if(viewBoard.checkForWin(integer)){
                    break;
                }
            }

            //win conditions and replay
            if(viewBoard.checkForWin(integer)){
                if(turnNumber % 2 == 1){
                    System.out.println("Player " + p1 + " wins!\n");
                    if(ReplayFunction()){
                        anotherRound = true;
                    }
                    else{
                        anotherRound = false;
                    }
                }
                else if(turnNumber % 2 != 1){
                    System.out.println("Player " + p2 + " wins!\n");
                    if(ReplayFunction()){
                        anotherRound = true;
                    }
                    else{
                        anotherRound = false;
                    }
                }
            }
        }
    }

    /**
     * This function implements the users decision to replay a game
     *
     * @pre [ A game must have already been played ]
     * @pre checkForWin == true OR checkForTie == true
     *
     * @post checkForWin = #checkForWin AND checkForTie = #checkForTie
     * @post if returnValue == true then another game will being
     * @post if returnValue == false then the game will terminate
     */
    private static boolean ReplayFunction(){
        char choice = ' ';
        boolean returnValue = false;
        System.out.println("Would you like to play again? Y/N");
        Scanner scanner = new Scanner(System.in);
        char userInput = scanner.next().charAt(0);

        if (userInput == 'y' || userInput == 'Y') {returnValue = true;}
        else if(userInput == 'n' || userInput == 'N') {returnValue = false;}
        else {
            System.out.println("Would you like to play again? Y/N");
            int count = 0;
            while (count == 0) {
                userInput = scanner.next().charAt(0);

                if (userInput == 'y' || userInput == 'Y') {
                    count++;
                    returnValue = true;
                }
                else if (userInput == 'n' || userInput == 'N') {
                    count++;
                    returnValue = false;
                }
                else {System.out.println("Incorrect input. Please try again.");}
            }
        }
        return returnValue;
    }
}