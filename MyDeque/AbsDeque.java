package cpsc2150.MyDeque;

/**
 * Name: Zachary Supina, Abigail Poropatich
 * Date Submitted: 2/17/2023
 * Section: 1
 * Assignment Name: Inheritance and Default Methods (Lab 5)
 */

public abstract class AbsDeque implements IDeque{
    @Override

    public String toString(){
        //Getting the current length of the deque
        //and creating a temporary array
        int length = length();
        Integer temp[] = new Integer[MAX_LENGTH];
        String out = "<";

        //iterating through the deque
        for(int i = 0; i < length; i++){
            //removing the front element
            //and adding it to a temporary array
            temp[i] = dequeue();
            if(i == length - 1){
                out += temp[i];
            }
            else{
                out += temp[i] + ", ";
            }

            //add back to the end of the deque
            enqueue(temp[i]);
        }
        out += ">";
        return out;
    }
}