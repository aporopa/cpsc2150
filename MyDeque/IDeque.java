package cpsc2150.MyDeque;

/**
 * Name: Zachary Supina, Abigail Poropatich
 * Date Submitted: 2/17/2023
 * Section: 1
 * Assignment Name: Inheritance and Default Methods (Lab 5)
 */

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
     *
     * @param x = integer to be added to the back of the deque
     *
     * @post |myDeque| = |#myDeque| + 1
     */
    public void enqueue(Integer x);

    /**
     * removes and returns the integer at the front of the deque
     *
     * @pre |myDeque| > 0
     *
     * @return return the Integer at the front of the deque
     *
     * @post |myDeque| = |#myDeque| - 1
     */
    public Integer dequeue();

    /**
     * Adds x to the front of the deque
     *
     * @param x = integer to be added to the front of the deque
     *
     * @post |myDeque| = |#myDeque| + 1
     */
    public void inject(Integer x);

    /**
     * removes and returns the integer at the end of the deque
     *
     * @pre |myDeque| > 0
     *
     * @return returns the integer at the end of the deque
     */
    public Integer removeLast();

    /**
     * returns the number of integers in the deque
     *
     * @pre elements are integers
     *
     * @return number of integers in deque
     *
     * @post number of integers in deque are returned AND myDeque = #myDeque
     */
    public int length();

    /**
     * This function clears the entire deque
     * @post |myDeque| = 0
     */
    public void clear();

    /**
     * This function shows the integer at the front of the deque without
     *  altering the deque
     *
     * @pre |myDeque| != 0
     *
     * @return the integer number at the front of the deque
     *
     * @post |myDeque| = |#myDeque|
     */
    default Integer peek(){
        Integer temp = 0;
        temp = dequeue();
        inject(temp);
        return temp;
    }

    /**
     * This function returns the value of the integer at the end of the deque
     *  without altering the deque
     *
     * @pre |myDeque| != 0
     *
     * @return value at the end of the deque
     *
     * @post |myDeque| = |#myDeque|
     */
    default Integer endOfDeque(){
        Integer temp;
        temp = removeLast();
        enqueue(temp);
        return temp;
    }

    /**
     * This function inserts x at a position within the deque
     * (pos==1)==(myDeque[0])
     *
     * @pre pos <= |myDeque|
     *
     * @param x The object to be inserted in the deque
     * @param pos The position at which x is inserted
     *
     * @post |myDeque| = |myDeque| + 1
     */
    default void insert(Integer x, int pos){
        Integer temp = 0;
        for(int i = 0; i < length(); i++){
            if((i + 1) == pos){
                enqueue(x);
                i++;
            }
            temp = dequeue();
            enqueue(temp);
        }
    }

    /**
     * This function removes x at a position within the deque
     * (pos==1)==(myDeque[0])
     *
     * @pre |myDeque| != 0
     *
     * @param pos the position of the element to be removed
     * @return the value of the integer at pos
     *
     * @post |myDeque| = |myDeque| - 1
     */
    default Integer remove(int pos){
        Integer temp = 0;
        Integer atPos = 0;
        if(pos == length()) {
            atPos = removeLast();
        }
        else {
            for (int i = 0; i < length(); i++) {
                if ((i + 1) == pos) {
                    atPos = dequeue();
                }
                temp = dequeue();
                enqueue(temp);
            }
        }
        return atPos;
    }

    /**
     * This function removes x at a position within the deque
     * (pos==1)==(myDeque[0])
     *
     * @pre |myDeque| != 0
     *
     * @param pos The position of the element to be removed
     * @return The integer value located at pos
     *
     * @post |myDeque| = |#myDeque|
     */
    default Integer get(int pos){
        Integer temp = 0;
        Integer atPos = 0;
        if(pos == 1){
            atPos = dequeue();
            inject(atPos);
        }
        else if(pos == length()){
            atPos = removeLast();
            enqueue(atPos);
        }
        else{
            for(int i = 0; i < length(); i++){
                temp = dequeue();
                enqueue(temp);
                if(i == (pos - 1)){
                    atPos = temp;
                }
            }
        }
        return atPos;
    }
}