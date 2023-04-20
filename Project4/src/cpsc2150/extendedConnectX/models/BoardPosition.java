
package cpsc2150.extendedConnectX.models;
/**
 * BoardPosition JavaDoc and contracts
 *
 * @author Abigail Poropatich
 * @version 4.0
 */



public class BoardPosition {

    private int Row;
    private int Column;

    /**
     * This constructor creates a BoardPosition object
     *
     * @param row The amount of rows
     * @param column The amount of columns
     *
     * @post row = #row AND column = #column
     */

    public BoardPosition(int row, int column){
        Row = row;
        Column = column;
    }

    /**
     * Retrieves the Row position of this BoardPosition object
     *
     * @return the row position
     * @post the row position is an integer
     * @post row = #row
     */
    public int getRow(){
        return Row;
    }

    /**
     * Retrieves the Column position of this BoardPosition object
     *
     * @return the column position
     * @post the column position is an integer
     * @post column = #column
     */
    public int getColumn(){
        return Column;
    }

    /**
     * Compares two members of the BoardPosition object to check for equality
     *
     * @pre obj is instance of BoardPosition
     *
     * @param obj = pair of coordinates for the players move
     *
     * @return true iff Row == obj.Row AND Column == obj.Column
     * @return false iff Column != Column AND Row != obj.Row
     *
     * @post equals iff (Row == obj.Row AND Column == obj.Column)
     *       AND Row = #Row AND Column = #Column
     */

    @Override
    public boolean equals(Object obj){

        if(this == obj) {return true;}
        if(!(obj instanceof BoardPosition) || obj == null){return false;}

        BoardPosition bp = (BoardPosition) obj;
        return (this.Row == bp.Row && this.Column == bp.Column);
    }

    /**
     * This method provides a string representation of the contents of the board.
     *
     * @return String representation of the board
     *
     * @pre NONE
     * @post toString = [ Formats a string containing valid Row and Column arguments separated by a comma ]
     *                  AND Row = #Row AND Column = #Column
     */

    @Override
    public String toString(){
        return (Row + "," + Column);
    }


}