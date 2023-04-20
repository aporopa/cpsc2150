package cpsc2150.extendedConnectX.models;

import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;
import cpsc2150.extendedConnectX.models.IGameBoard;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestGameBoard {
    private IGameBoard TestingGameBoard(int rows, int columns, int numToWin){
        return new GameBoard(rows, columns, numToWin);
    }

    /*-------------------*/
    // Comparison Board //
    /*------------------*/
    private String ComparisonBoard(char[][] twoD){
        StringBuilder board = new StringBuilder();
        board.append("|");
        int col = 0;
        while(col < twoD[0].length){
            board.append(String.format("%2d", col)).append("|");
            col++;
        }
        board.append("\n");

        int row = twoD.length - 1;
        while(row >= 0){
            board.append("|");
            int column = 0;
            while(column < twoD[row].length){
                board.append(String.format("%c |", twoD[row][column]));
                column++;
            }
            board.append("\n");
            row--;
        }

        return board.toString();

    }

    /*---------------------*/
    // TESTING GameBoard() //
    /*---------------------*/
    @Test
    public void testingConstructor_min(){
        int minRow = 100;
        int minColumn = 100;
        int minToWin = 25;
        char board[][] = new char[minRow][minColumn];
        for(int r = 0; r < minRow; r++){
            for(int c = 0; c < minColumn; c++){
                board[r][c] = ' ';
            }
        }
        IGameBoard gb = this.TestingGameBoard(minRow,minColumn,minToWin);
        Assert.assertEquals(gb.getNumRows(),minRow);
        Assert.assertEquals(gb.getNumColumns(),minColumn);
        Assert.assertEquals(gb.getNumToWin(),minToWin);
        Assert.assertEquals(gb.toString(), ComparisonBoard(board));
    }
    @Test
    public void testingConstructor_max(){
        int maxRow = 100;
        int maxColumn = 100;
        int maxToWin = 25;
        char board[][] = new char[maxRow][maxColumn];
        for(int r = 0; r < maxRow; r++){
            for(int c = 0; c < maxColumn; c++){
                board[r][c] = ' ';
            }
        }
        IGameBoard gb = this.TestingGameBoard(maxRow,maxColumn,maxToWin);
        Assert.assertEquals(gb.getNumRows(),maxRow);
        Assert.assertEquals(gb.getNumColumns(),maxColumn);
        Assert.assertEquals(gb.getNumToWin(),maxToWin);
        Assert.assertEquals(gb.toString(), ComparisonBoard(board));
    }

    @Test
    public void testingConstructor_abstract(){
        int Row = 4;
        int Column = 3;
        int ToWin = 3;
        char board[][] = new char[Row][Column];
        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                board[r][c] = ' ';
            }
        }
        IGameBoard gb = this.TestingGameBoard(Row,Column,ToWin);
        Assert.assertEquals(gb.getNumRows(),Row);
        Assert.assertEquals(gb.getNumColumns(),Column);
        Assert.assertEquals(gb.getNumToWin(),ToWin);
        Assert.assertEquals(gb.toString(), ComparisonBoard(board));
    }

    /*----------------------*/
    // TESTING checkIfFree()//
    /*----------------------*/
    @Test
    public void testingCheckIfFree_empty(){
        int Row = 9;
        int Column = 7;
        int ToWin = 3;
        char board[][] = new char[Row][Column];

        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                board[r][c] = ' ';
            }
        }

        IGameBoard gb = this.TestingGameBoard(Row,Column,ToWin);
        Assert.assertTrue(gb.checkIfFree(3));
        Assert.assertEquals(gb.toString(), ComparisonBoard(board));
    }

    @Test
    public void testingCheckIfFree_full(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                if((r + c) % 2 == 0) testingBoard.placeToken('X', c);
                else testingBoard.placeToken('O', c);
            }
        }

        Assert.assertFalse(testingBoard.checkIfFree(1));
    }

    @Test
    public void testingCheckIfFree_some(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('O',2);

        Assert.assertTrue(testingBoard.checkIfFree(2));
    }

    /*-------------------------*/
    // TESTING checkHorizWin() //
    /*------------------------*/
    @Test
    public void testingCheckHorizWin_empty(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;
        char board[][] = new char[Row][Column];

        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                board[r][c] = ' ';
            }
        }

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        Assert.assertEquals(testingBoard.toString(), ComparisonBoard(board));
        Assert.assertFalse(testingBoard.checkHorizWin(new BoardPosition(1,1), 'X'));

    }

    @Test
    public void testingCheckHorizWin_one(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);

        testingBoard.placeToken('X',1);
        Assert.assertFalse(testingBoard.checkHorizWin(new BoardPosition(1,1), 'X'));

    }

    @Test
    public void testingCheckHorizWin_win(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('X',2);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',0);


        Assert.assertTrue(testingBoard.checkHorizWin(new BoardPosition(0,2), 'X'));

    }

    @Test
    public void testingCheckHorizWin_fauxwin(){
        int Row = 4;
        int Column = 4;
        int ToWin = 4;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('O',1);
        testingBoard.placeToken('O',2);
        testingBoard.placeToken('X',3);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('X',2);
        testingBoard.placeToken('X',2);

        Assert.assertFalse(testingBoard.checkHorizWin(new BoardPosition(0,3), 'X'));

    }

    /*-------------------------*/
    // TESTING checkVertWin()  //
    /*------------------------*/
    @Test
    public void testingCheckVertWin_fauxwin(){
        int Row = 4;
        int Column = 4;
        int ToWin = 4;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('O',1);
        testingBoard.placeToken('X',2);
        testingBoard.placeToken('X',3);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',0);

        Assert.assertFalse(testingBoard.checkVertWin(new BoardPosition(3,0), 'O'));

    }

    @Test
    public void testingCheckVertWin_win(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('O',2);
        testingBoard.placeToken('X',3);
        testingBoard.placeToken('X',3);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',0);

        Assert.assertTrue(testingBoard.checkVertWin(new BoardPosition(2,0), 'O'));
    }

    @Test
    public void testingCheckVertWin_winComp(){
        int Row = 4;
        int Column = 4;
        int ToWin = 4;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('O',2);

        Assert.assertTrue(testingBoard.checkVertWin(new BoardPosition(3,1), 'X'));
    }

    @Test
    public void testingCheckVertWin_fauxWin2(){
        int Row = 4;
        int Column = 4;
        int ToWin = 4;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('X',2);
        testingBoard.placeToken('X',3);
        testingBoard.placeToken('O',1);

        Assert.assertFalse(testingBoard.checkVertWin(new BoardPosition(3,1), 'X'));
    }

    /*-------------------------*/
    // TESTING checkDiagWin()  //
    /*------------------------*/
    @Test
    public void testingCheckDiagWin_empty(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;
        char board[][] = new char[Row][Column];

        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                board[r][c] = ' ';
            }
        }

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        Assert.assertEquals(testingBoard.toString(), ComparisonBoard(board));
        Assert.assertFalse(testingBoard.checkDiagWin(new BoardPosition(0,0), 'X'));

    }

    @Test
    public void testingCheckDiagWin_noWin(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('O',1);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('O',2);
        testingBoard.placeToken('X',2);
        testingBoard.placeToken('X',3);

        Assert.assertFalse(testingBoard.checkDiagWin(new BoardPosition(0,2), 'X'));
    }

    @Test
    public void testingCheckDiagWin_win(){
        int Row = 5;
        int Column = 4;
        int ToWin = 4;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('O',1);
        testingBoard.placeToken('X',2);
        testingBoard.placeToken('O',2);
        testingBoard.placeToken('O',2);
        testingBoard.placeToken('X',3);
        testingBoard.placeToken('O',3);
        testingBoard.placeToken('X',3);
        testingBoard.placeToken('O',3);

        Assert.assertTrue(testingBoard.checkDiagWin(new BoardPosition(0,0), 'O'));
    }

    @Test
    public void testingCheckDiagWin_fauxwin(){
        int Row = 5;
        int Column = 4;
        int ToWin = 4;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('X',2);
        testingBoard.placeToken('X',3);

        Assert.assertFalse(testingBoard.checkDiagWin(new BoardPosition(0,0), 'O'));
    }
    @Test
    public void testingCheckDiagWin_winleft(){
        int Row = 5;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('O',1);
        testingBoard.placeToken('O',1);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('O',2);
        testingBoard.placeToken('X',2);
        testingBoard.placeToken('O',2);
        testingBoard.placeToken('X',3);

        Assert.assertTrue(testingBoard.checkDiagWin(new BoardPosition(3,0), 'X'));
    }

    @Test
    public void testingCheckDiagWin_winleft2(){
        int Row = 5;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('X',2);
        testingBoard.placeToken('O',3);

        Assert.assertTrue(testingBoard.checkDiagWin(new BoardPosition(2,0), 'X'));
    }

    @Test
    public void testingCheckDiagWin_nowin2(){
        int Row = 5;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('R',0);
        testingBoard.placeToken('R',0);
        testingBoard.placeToken('O',1);
        testingBoard.placeToken('O',2);
        testingBoard.placeToken('O',2);
        testingBoard.placeToken('O',2);
        testingBoard.placeToken('R',3);
        testingBoard.placeToken('R',3);

        Assert.assertFalse(testingBoard.checkDiagWin(new BoardPosition(0,0), 'X'));
    }

    /*---------------------*/
    // TESTING checkTie() //
    /*--------------------*/
    @Test
    public void testingCheckTie_empty(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        char board[][] = new char[Row][Column];

        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                board[r][c] = ' ';
            }
        }

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        Assert.assertEquals(testingBoard.toString(), ComparisonBoard(board));

        Assert.assertFalse(testingBoard.checkTie());
    }

    @Test
    public void testingCheckTie_tie(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                if((r + c) % 2 == 0) testingBoard.placeToken('X', c);
                else testingBoard.placeToken('O', c);
            }
        }

        Assert.assertTrue(testingBoard.checkTie());
    }

    @Test
    public void testingCheckTie_notenough(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                if((r + c) % 2 == 0) testingBoard.placeToken('X', c);
            }
        }

        Assert.assertFalse(testingBoard.checkTie());
    }

    @Test
    public void testingCheckTie_notie(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('O',1);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('O',1);
        testingBoard.placeToken('X',2);
        testingBoard.placeToken('X',2);
        testingBoard.placeToken('X',2);
        testingBoard.placeToken('O',2);
        testingBoard.placeToken('O',3);
        testingBoard.placeToken('O',3);
        testingBoard.placeToken('O',3);
        testingBoard.placeToken('X',3);

        Assert.assertFalse(testingBoard.checkTie());
    }

    /*----------------------------*/
    // TESTING checkWhatsAtPos() //
    /*---------------------------*/
    @Test
    public void testingWhatsAtPos_empty(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        char board[][] = new char[Row][Column];

        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                board[r][c] = ' ';
            }
        }

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        Assert.assertEquals(testingBoard.toString(), ComparisonBoard(board));

        Assert.assertEquals(' ', testingBoard.whatsAtPos(new BoardPosition(0,1)));
    }

    @Test
    public void testingWhatsAtPos_char1(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',1);
        testingBoard.placeToken('O',2);
        testingBoard.placeToken('X',3);
        testingBoard.placeToken('X',2);
        testingBoard.placeToken('O',3);

        Assert.assertEquals('O', testingBoard.whatsAtPos(new BoardPosition(0,1)));
    }

    @Test
    public void testingWhatsAtPos_charX(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('X',1);

        Assert.assertEquals('X', testingBoard.whatsAtPos(new BoardPosition(0,1)));
    }

    @Test
    public void testingWhatsAtPos_charUnique(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('Z',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('Z',1);
        testingBoard.placeToken('O',2);
        testingBoard.placeToken('Z',2);
        testingBoard.placeToken('Z',3);
        testingBoard.placeToken('O',3);

        Assert.assertEquals('Z', testingBoard.whatsAtPos(new BoardPosition(0,0)));
    }

    @Test
    public void testingWhatsAtPos_higherRow(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('D',0);
        testingBoard.placeToken('R',0);
        testingBoard.placeToken('R',1);
        testingBoard.placeToken('D',2);
        testingBoard.placeToken('D',2);

        Assert.assertEquals('D', testingBoard.whatsAtPos(new BoardPosition(1,2)));
    }

    /*-------------------------*/
    // TESTING isPlayerAtPos() //
    /*------------------------*/
    @Test
    public void testingIsPlayerAtPos_empty(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        char board[][] = new char[Row][Column];

        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                board[r][c] = ' ';
            }
        }

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        Assert.assertEquals(testingBoard.toString(), ComparisonBoard(board));

        Assert.assertFalse(testingBoard.isPlayerAtPos(new BoardPosition(1,2), 'X'));
    }
    @Test
    public void testingIsPlayerAtPos_foundX(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('X',0);

        Assert.assertTrue(testingBoard.isPlayerAtPos(new BoardPosition(0,0), 'X'));
    }

    @Test
    public void testingIsPlayerAtPos_diffPos(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('R',3);

        Assert.assertFalse(testingBoard.isPlayerAtPos(new BoardPosition(1,2), 'R'));
    }

    @Test
    public void testingIsPlayerAtPos_uniqueChar(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('Z',0);
        testingBoard.placeToken('Z',0);
        testingBoard.placeToken('Z',0);
        testingBoard.placeToken('F',1);
        testingBoard.placeToken('Z',1);
        testingBoard.placeToken('F',1);
        testingBoard.placeToken('F',2);
        testingBoard.placeToken('F',2);

        Assert.assertTrue(testingBoard.isPlayerAtPos(new BoardPosition(0,1), 'F'));
    }

    @Test
    public void testingIsPlayerAtPos_border(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('O',1);
        testingBoard.placeToken('O',1);
        testingBoard.placeToken('O',2);
        testingBoard.placeToken('X',2);
        testingBoard.placeToken('O',3);

        Assert.assertTrue(testingBoard.isPlayerAtPos(new BoardPosition(1,1), 'O'));
    }

    /*-----------------------*/
    // TESTING placeToken() //
    /*----------------------*/
    @Test
    public void testingPlaceToken_empty(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        char board[][] = new char[Row][Column];

        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                board[r][c] = ' ';
            }
        }
        board[0][1] = 'X';

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('X',1);

        Assert.assertEquals(testingBoard.toString(), ComparisonBoard(board));
    }

    @Test
    public void testingPlaceToken_modPlace(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        char board[][] = new char[Row][Column];

        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                board[r][c] = ' ';
            }
        }

        board[0][0] = 'O';
        board[1][0] = 'O';
        board[0][1] = 'X';
        board[1][1] = 'X';
        board[0][2] = 'X';
        board[1][2] = 'O';

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('X',1);
        testingBoard.placeToken('X',2);
        testingBoard.placeToken('O',2);

        Assert.assertEquals(testingBoard.toString(), ComparisonBoard(board));
    }

    @Test
    public void testingPlaceToken_fill(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        char board[][] = new char[Row][Column];

        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                board[r][c] = ' ';
            }
        }
        board[0][0] = 'X';
        board[1][0] = 'O';
        board[2][0] = 'X';

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('X',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('X',0);

        Assert.assertEquals(testingBoard.toString(), ComparisonBoard(board));
    }

    @Test
    public void testingPlaceToken_completeBoad(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        char board[][] = new char[Row][Column];

        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                board[r][c] = ' ';
            }
        }
        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                if((r + c) % 2 == 0) board[r][c] = 'X';
                else board[r][c] = 'O';
            }
        }

        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                if((r + c) % 2 == 0) testingBoard.placeToken('X', c);
                else testingBoard.placeToken('O', c);
            }
        }

        Assert.assertEquals(testingBoard.toString(), ComparisonBoard(board));
    }

    @Test
    public void testingPlaceToken_false(){
        int Row = 4;
        int Column = 4;
        int ToWin = 3;

        char board[][] = new char[Row][Column];

        for(int r = 0; r < Row; r++){
            for(int c = 0; c < Column; c++){
                board[r][c] = ' ';
            }
        }
        board[0][0] = 'X';
        board[1][0] = 'X';
        board[2][0] = 'X';


        IGameBoard testingBoard = new GameBoard(Row, Column, ToWin);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',0);
        testingBoard.placeToken('O',0);

        Assert.assertFalse(testingBoard.toString().equals(ComparisonBoard(board)));
    }
}
