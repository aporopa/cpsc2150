package cpsc2150.extendedConnectX;
import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;
import cpsc2150.extendedConnectX.models.IGameBoard;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
/**
 * GameScreen JavaDoc and contracts
 *
 * @author Abigail Poropatich
 * @version 3.0
 */

/**
 * @invariant 0 <= token < maxColumn && 0 <= token < maxRow
 * @invariant [ playAgain is 'Y' OR 'N']
 */

public class GameScreen{
    private static final List<Character> Tokens = new ArrayList<>();
    private static  Scanner scanner = new Scanner(System.in);
    private static final int maxPlayer = 10;
    private static final int minPlayer = 2;
    private static final int minRCInput = 3;
    private static final int maxRCInput = 100;
    private static final int maxToWin = 25;
    private static int numOfPlayers;
    private static IGameBoard viewBoard;
    public static void main(String[] args) {
        //local variables
        int integer = 0;
        int turnNumber = 0;
        int row, column, toWin;
        boolean anotherRound = true;
        boolean thisTurn = true;
        char player;

        //driving loop
        while (anotherRound) {

            //ask user how many players they want
            System.out.println("How many players?");
            numOfPlayers = Integer.parseInt(scanner.next());

            //check that the variable is not greater than the maximum amount of players allowed
            while (numOfPlayers > maxPlayer) {
                System.out.println("Must be 10 players or fewer");
                System.out.println("How many players?");
                numOfPlayers = Integer.parseInt(scanner.next());
            }

            //check that the variable is not less than the least amount of players allowed
            while (numOfPlayers < minPlayer) {
                System.out.println("Must be at least 2 players");
                System.out.println("How many players?");
                numOfPlayers = Integer.parseInt(scanner.next());
            }

            //driving loop for the ArrayList
            for (int i = 0; i < numOfPlayers; i++) {
                String character = " ";

                do {
                    System.out.println("Enter the character to represent player " + (i + 1));
                    character = scanner.next().toUpperCase();

                    if (Tokens.contains(character.charAt(0))) {
                        System.out.println(character + " is already taken as a player token!");
                    }
                } while (Tokens.contains(character.charAt(0)));
                Tokens.add(character.charAt(0));
            }

            //ask user for the amount of rows they want
            //and validate the number is in bounds
            System.out.println("How many rows should be on the board?");
            row = Integer.parseInt(scanner.next());
            while (row < minRCInput || row >= maxRCInput) {
                System.out.println("Please enter a number between " + minRCInput + " and " + maxRCInput);
                System.out.println("\nHow many rows should be on the board?");
                row = Integer.parseInt(scanner.next());
            }

            //ask user for the amount of columns they want
            //and validate the number is in bounds
            System.out.println("How many columns should be on the board?");
            column = Integer.parseInt(scanner.next());
            scanner.nextLine();
            while (column < minRCInput || column >= maxRCInput) {
                System.out.println("Please enter a number between " + minRCInput + " and " + maxRCInput + "\n");
                System.out.println("How many columns should be on the board?");
                column = Integer.parseInt(scanner.next());
            }

            //ask user for the amount of tokens in a row they want in order to win
            //and validate the number is in bounds
            System.out.println("How many in a row to win?");
            toWin = Integer.parseInt(scanner.next());
            while (toWin < minRCInput || toWin > maxRCInput || toWin > row || toWin > column) {
                System.out.println("Please enter a number between " + minRCInput + " and " + maxToWin + " and ensure it is less than the amount of rows and columns");
                System.out.println("\nHow many in a row to win?");
                toWin = Integer.parseInt(scanner.next());
            }
            viewBoard = new GameBoard(row, column, toWin);

            //ask user what kind of game they want to play
            //and validate the character is correct
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
            char memChoice = scanner.next().toUpperCase().charAt(0);
            while(memChoice != 'F' && memChoice != 'M'){
                System.out.println("Incorrect input. Please try again.");
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                memChoice = scanner.next().toUpperCase().charAt(0);
            }

            //call the appropriate constructors
            if(memChoice == 'F') {viewBoard = new GameBoard(row, column, toWin);}
            else{viewBoard = new GameBoardMem(row, column, toWin);}

            //driving loop for placing tokens
            thisTurn = true;
            while (thisTurn) {
                System.out.println(viewBoard.toString());
                player = Tokens.get(turnNumber % numOfPlayers);

                System.out.println("Player " + player + ", what column do you want to place your marker in?");
                integer = Integer.parseInt(scanner.next());

                while (integer < 0 || integer >= viewBoard.getNumColumns()) {
                    System.out.println("Please enter a valid column number");
                    integer = Integer.parseInt(scanner.next());
                }

                while (!viewBoard.checkIfFree(integer)) {
                    System.out.println("This column is full. Please choose another.");
                    integer = Integer.parseInt(scanner.next());

                    while (integer < 0 || integer >= viewBoard.getNumColumns()) {
                        System.out.println("Please enter a valid column number");
                        integer = Integer.parseInt(scanner.next());
                    }
                }

                viewBoard.placeToken(player, integer);
                turnNumber++;

                //check for the win condition
                if(viewBoard.checkForWin(integer)){
                    System.out.print(viewBoard.toString() + "\n");
                    System.out.println("Player " + player + " won!");
                    if(ReplayFunction()){
                        anotherRound = true;
                    }
                    else{
                        anotherRound = false;
                    }

                    turnNumber = 0;
                    thisTurn = false;
                    Tokens.clear();

                }

                //check for a tie
                if(viewBoard.checkTie() && turnNumber >= toWin){
                    System.out.print(viewBoard.toString() + "\n");
                    System.out.println("It's a tie!");

                    if(ReplayFunction()){
                        anotherRound = true;
                    }
                    else{
                        anotherRound = false;
                    }
                    thisTurn = false;
                    turnNumber = 0;
                    Tokens.clear();
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
        boolean returnValue = false;
        System.out.println("Would you like to play again? Y/N");
        Scanner scanner = new Scanner(System.in);
        char userInput = scanner.next().charAt(0);

        if (userInput == 'y' || userInput == 'Y') {returnValue = true;}
        else if(userInput == 'n' || userInput == 'N') {return false;}
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
                    return false;
                }
                else {System.out.println("Incorrect input. Please try again.");}
            }
        }
        return returnValue;
    }
}