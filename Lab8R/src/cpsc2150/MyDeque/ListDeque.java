package cpsc2150.MyDeque;
import java.util.*;

/**
 * Name: Zachary Supina, Abigail Poropatich
 * Date Submitted: 2/24/2023
 * Section: 1
 * Assignment Name: Generic Deques (Lab 6)
 */

public class ListDeque<T> extends AbsDeque<T> implements IDeque<T> {

    // this time store the deque in a list
    // myQ.get(0) is the front of the deque

    /**
     * @invariants myQ.size() >= 0
     * @invariants myQ.size() <= MAX_LENGTH
     */
    private List<T> myQ;

    /**
     * Constructor for ListDeque that takes no parameters
     *
     * @post myQ = #myQ
     * @post myQ is a list
     */
    public ListDeque() {
        myQ = new ArrayList<T>();
    }

    //Enqueue a new integer
    public void enqueue(T x) {
        myQ.add(x);
    }

    //dequeue and return the first element
    public T dequeue() {
        T firstElement = myQ.get(0);
        myQ.remove(0);

        return firstElement;
    }

    //place an integer at index 0
    public void inject(T x) {
        myQ.add(0, x);
    }

    //remove the last element of the deque
    public T removeLast() {
        return myQ.remove(myQ.size() - 1);
    }

    //return the length of the deque
    public int length() {
        return myQ.size();
    }

    //clear the deque of all integers
    public void clear() {
        myQ.clear();
    }
}