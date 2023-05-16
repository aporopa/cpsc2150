package cpsc2150.extendedConnectX.controllers;

import cpsc2150.extendedConnectX.models.*;
import cpsc2150.extendedConnectX.views.*;

/**
 * The controller class will handle communication between our View and our Model ({@link IGameBoard})
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your your {@link BoardPosition} class, {@link IGameBoard} interface
 * and both of the {@link IGameBoard} implementations from Project 4.
 * If your code was correct you will not need to make any changes to your {@link IGameBoard} implementation class.
 *
 * @version 2.0
 */
public class ConnectXController {

    /**
     * <p>
     * The current game that is being played
     * </p>
     */
    private IGameBoard curGame;

    /**
     * <p>
     * The screen that provides our view
     * </p>
     */
    private ConnectXView screen;

    /**
     * <p>
     * Constant for the maximum number of players.
     * </p>
     */
    public static final int MAX_PLAYERS = 10;

    /**
     * <p>
     * The number of players for this game. Note that our player tokens are hard coded.
     * </p>
     */
    private int numPlayers;

    /**
     * <p>
     * This creates a controller for running the Extended ConnectX game
     * </p>
     *
     * @param model
     *      The board implementation
     * @param view
     *      The screen that is shown
     * @param np
     *      The number of players for this game.
     *
     * @post [ the controller will respond to actions on the view using the model. ]
     */

    private boolean gameCondition = false;
    private int count = 0;
    private char charTracker = 'X';
    private char[] tokens = new char[] {'X', 'O', 'H', 'L', 'W', 'C', 'V', 'E', 'I', 'Z'};

    public ConnectXController(IGameBoard model, ConnectXView view, int np) {
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;
    }

    /**
     * <p>
     * This processes a button click from the view.
     * </p>
     *
     * @param col
     *      The column of the activated button
     *
     * @post [ will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button ]
     */
    public void processButtonClick(int col) {

        //starting the game
        if(gameCondition){
            gameCondition = false;
            this.newGame();
        }


        //if the column is free
        if(curGame.checkIfFree(col)){
            int row = 0;

            //place the token and track the row
            curGame.placeToken(charTracker, col);
            do {
                row++;
            } while (row < curGame.getNumRows() && curGame.whatsAtPos(new BoardPosition(row, col)) != ' ');

            row--;

            //set token on screen
            screen.setMarker(row, col, charTracker);

            //if the game has been won, trigger new game
            if(curGame.checkForWin(col)) {
                screen.setMessage("Player " + charTracker + " wins! Click any button to start a new game.");
                gameCondition = true;
            }

            //if the game has been tied, trigger new game
            else if(curGame.checkTie()) {
                screen.setMessage("The game is tied! Click a button to begin a new game!");
                gameCondition = true;
            }

            //if there is no win and no tie, switch to the next players turn
            else {
                ++count;
                if (count == numPlayers) count = 0;

                charTracker = tokens[count];
                screen.setMessage("Player " + charTracker + "'s turn");
            }
        }

        //if the column is not free, prompt the user
        else if(!curGame.checkIfFree(col)){
            screen.setMessage("Column " + col + " is full!");
        }
    }


    /**
     * <p>
     * This method will start a new game by returning to the setup screen and controller
     * </p>
     *
     * @post [ a new game gets started ]
     */
    private void newGame() {
        //close the current screen
        screen.dispose();

        //start back at the set-up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}