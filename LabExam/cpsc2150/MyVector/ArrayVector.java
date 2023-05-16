package cpsc2150.MyVector;
import java.util.*;
import java.util.Vector;
/**
 * Name: Abigail Poropatich
 * Date Submitted: 18 April 2023
 * Section: 1
 * Assignment Name: Lab Final Exam
 */

public class ArrayVector<T> extends AbsVector<T> implements IVector<T> {
    private T[] myVec;
    private int size;

    public ArrayVector(){
        myVec = (T[]) new Object[IVector.MAX_LENGTH];
        size = 0;
    }

    public void addElement(T val) {
        myVec[size] = val;
        size++;
    }

    public T removeElement(){
        T firstEl = myVec[0];
        for(int i = 1; i < size; i++){
            myVec[i - 1] = myVec[i];
        }
        size--;
        return firstEl;
    }

    public boolean contains(T val){
        boolean check = true;
        for(int i = 0; i < size; i++){
            check = myVec[i].equals(val);
        }
        return check;
    }

    public T get(int pos){
        return myVec[pos - 1];
    }

    public int length(){
        return size;
    }

    public void clear(){
        for(int i = 0; i < size; i++)
        {
            myVec[i] = null;
        }
        size = 0;
    }
}
