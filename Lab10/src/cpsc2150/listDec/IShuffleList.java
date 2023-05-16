/**
 * Name: Zachary Supina, Abigail Poropatich
 * Date Submitted: 3taylotaylll/28/2023
 * Section: 1
 * Assignment Name: Decorator Pattern (Lab 10)
 */

package cpsc2150.listDec;

import java.util.*;

public interface IShuffleList<T> extends List<T> {
    /**
     * randomly picks two positions in the list and swaps them, this is repeated a number of times
     * determined by var swaps
     *
     * @param swaps is an int that determines how many random swaps should be made
     *
     * @pre
     * list.size() >= 2 AND swaps > 0
     *
     * @post
     * list.size() = #list.size() AND there have been swaps number of random swaps in the list
     */
    default void shuffle(int swaps){
        Random rand = new Random();
        int rand1;
        int rand2;
        for(int i = 0; i < swaps; i++){
            rand1 = rand.nextInt(this.size());
            rand2 = rand.nextInt(this.size());
            this.swap(rand1, rand2);
        }
    }

    /**
     * This function exchanges the values at positions i and j in the list
     *
     * @param i is the first element
     * @param j is the second element
     *
     * @pre list.size() >= 2 AND i >= 0 AND i < list.size() AND j >= 0 AND j < list.size()
     *
     * @post list.size() = #list.size() AND list[i] = #list[j] AND list[j] = #list[i]
     */
    default void swap(int i, int j){
        T var = this.get(i);
        set(i, this.get(j));
        set(j, var);
    }
}
