package cpsc2150.MyDeque;

/**
 * A deque containing integers.
 * A deque is a double-ended queue data structure that allows you
 * to insert and remove from both ends.
 * This deque is bounded by MAX_LENGTH
 */
public interface IDeque {

    /**
     * Deque is a double ended array that stores integer values
     *
     * @defines |myDeque| = 0
     *
     * @initialization_ensures Deque contains no values and the length is 0
     *
     * @Constraints 0 <= |myDeque| <= MAX_LENGTH
     */
    public static final int MAX_LENGTH = 100;

    /**
     * Adds x to the end of the deque
     * @param x = integer to be added to the back of the deque
     * @post |myDeque| = |#myDeque| + 1
     */
    public void enqueue(Integer x);

    /**
     * removes and returns the integer at the front of the deque
     * @pre |myDeque| > 0
     * @return return the Integer at the front of the deque
     * @post |myDeque| = |#myDeque| - 1
     */
    public Integer dequeue();

    /**
     * Adds x to the front of the deque
     * @param x = integer to be added to the front of the deque
     * @post |myDeque| = |#myDeque| + 1
     */
    public void inject(Integer x);

    /**
     * removes and returns the integer at the end of the deque
     * @pre |myDeque| > 0
     * @return returns the integer at the end of the deque
     */
    public Integer removeLast();

    /**
     * returns the number of integers in the deque
     * @pre elements are integers
     *
     * @return number of integers in deque
     * @post number of integers in deque are returned AND myDeque = #myDeque
     */
    public int length();

    /**
     * clears the entire deque
     * @post |myDeque| = 0
     */
    public void clear();
}