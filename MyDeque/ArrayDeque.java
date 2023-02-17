package cpsc2150.MyDeque;

/**
 * Name: Zachary Supina, Abigail Poropatich
 * Date Submitted: 2/17/2023
 * Section: 1
 * Assignment Name: Inheritance and Default Methods (Lab 5)
 */

public class ArrayDeque extends AbsDeque implements IDeque {

    /**
     * @invariant
     * MAX_LENGTH = 100
     * myLength >= 0 AND myLength <= MAX_LENGTH
     */
    // where the data is stored. myQ[0] is the front of the deque
    private Integer[] myQ;
    private final int maxSize = 100;

    // tracks how many items are in the deque
    // also used to find the end of the deque
    private int myLength;

    /**
     * Constructor, creates a new deque, initializes the length to 0, and sets the maximum length to
     * MAX_LENGTH
     *
     * @post myLength = 0
     */
    public ArrayDeque()
    {
        myQ = new Integer[IDeque.MAX_LENGTH];
        myLength = 0;
    }

    //Enqueue a new integer
    public void enqueue(Integer x)
    {
        myQ[myLength] = x;
        myLength++;
    }

    //dequeue the first element
    public Integer dequeue(){
        if (myLength > 0)
        {
            Integer firstElement = myQ[0];

            for (int i = 1; i < myLength; i++)
            {
                myQ[i - 1] = myQ[i];
                myQ[i] = null;
            }
            myLength--;
            return firstElement;
        }
        return null;
    }

    //Adds integer x to the front of the deque
    public void inject(Integer x) {
        for(int i = myLength; i > 0; i--)
        {
            myQ[i] = myQ[i - 1];
        }
        myQ[0] = x;

        //increases the deque length
        myLength++;
    }

    //removes and returns the last element
    //and decrements the length
    public Integer removeLast(){
        Integer lastElement = myQ[myLength - 1];
        myQ[myLength - 1] = null;
        myLength--;

        return lastElement;
    }

    //Returns the length of the deque
    public int length(){
        return myLength;
    }

    //Wipes the integers in the deque
    public void clear(){
        for(int i = 0; i < myLength; i++)
        {
            myQ[i] = null;
        }
        myLength = 0;
    }


}