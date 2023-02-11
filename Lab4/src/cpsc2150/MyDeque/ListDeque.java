package cpsc2150.MyDeque;

import java.util.*;

public class ListDeque implements IDeque {

    // this time store the deque in a list
    // myQ.get(0) is the front of the deque

    /**
     * @invariants myQ.size() >= 0
     * @invariants myQ.size() <= MAX_LENGTH
     */
    private List<Integer> myQ;

    /**
     * Constructor for ListDeque that takes no parameters
     *
     * @post myQ = #myQ
     * @post myQ is a list
     */
    public ListDeque() {
        myQ = new LinkedList<>();
    }

    public void enqueue(Integer x) {
        myQ.add(x);
    }

    public Integer dequeue() {
        int firstElement = myQ.get(0);
        myQ.remove(0);

        return firstElement;
    }

    public void inject(Integer x) {
        myQ.add(0, x);
    }

    public Integer removeLast() {
        return myQ.remove(myQ.size() - 1);
    }

    public int length() {
        return myQ.size();
    }

    public void clear() {
        myQ.clear();
    }
}