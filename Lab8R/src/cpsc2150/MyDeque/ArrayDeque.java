package cpsc2150.MyDeque;

/**
 * Name: Zachary Supina, Abigail Poropatich
 * Date Submitted: 03/10/2023
 * Section: 1
 * Assignment Name: JUnit testing (Lab 8)
 */

public class ArrayDeque<T> extends AbsDeque<T> implements IDeque<T> {

    /**
     * @invariant
     * MAX_LENGTH = 100
     * myLength >= 0 AND myLength <= MAX_LENGTH
     */
    // where the data is stored. myQ[0] is the front of the deque
    private T[] myQ;
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
        myQ = (T[])new Object[IDeque.MAX_LENGTH];
        myLength = 0;
    }

    public void enqueue(T x)
    {
        myQ[myLength] = x;
        myLength++;
    }

    public T dequeue(){
        T firstElement = myQ[0];
        for (int i = 1; i < myLength; i++)
        {
            myQ[i - 1] = myQ[i];
        }
        myLength--;
        return firstElement;
    }

    public void inject(T x) {
        if(myLength == 0){
            myQ[0] = x;
            myLength++;
        }
        else{
            for (int i = myLength; i > 0; i--) {
                myQ[i] = myQ[i - 1];
            }
            myQ[0] = x;

            myLength++;
        }
    }

    public T removeLast(){
        T lastElement = myQ[myLength - 1];
        myQ[myLength] = null;
        myLength--;
        return lastElement;
    }

    public int length(){
        return myLength;
    }
    public void clear(){
        for(int i = 0; i < myLength; i++)
        {
            myQ[i] = null;
        }
        myLength = 0;
    }


}