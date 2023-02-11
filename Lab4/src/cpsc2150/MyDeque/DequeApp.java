package cpsc2150.MyDeque;
import java.util.*;
import java.util.Scanner;
public class DequeApp {
    public static void main(String[] args) {
        IDeque q;
        Scanner read = new Scanner(System.in);
        String input = "0";

/*
Prompt  the  user  to  pick  an  implementation  using  the  following
message:  "Enter  1  for  array  implementation  or  2  for  List
implementation"
Your code needs to make sure that they only enter either 1 or 2.
Re-print the message to prompt the user to enter it again. Once
you have gotten an answer, use it to initialize q appropriately.
*/
        while (!input.equals("1") && !input.equals("2")){
            System.out.println("Enter 1 for array implementation or 2 for List implementation");
            input = read.nextLine();
    }

        if (input.equals("1"))
            q = new ArrayDeque();

        else
            q = new ListDeque();


        Integer x = 3;
        q.enqueue(x);
        x = -8;
        q.enqueue(x);
        x = 15;
        q.enqueue(x);
        x = 0;
        q.enqueue(x);
        x = -4;
        q.enqueue(x);

        System.out.print("<");

        int options = 0;
        int length = q.length();

        if (input.equals("1")){
            if(q.length() == 0){
                System.out.print("}");
            }
            else{
                for (int i = 0; i < length; i++){
                    if (options == length - 1){
                        System.out.print(q.dequeue() + ">");
                        return;
                    }
                    System.out.print(q.dequeue() + ", ");
                    ++options;
                }
            }
        }
        else{
            while(q.length() != 0){

                if (q.length() == 1){
                    System.out.print(q.dequeue() + ">");
                    return;
                }
                System.out.print(q.dequeue() + ", ");
                ++options;
            }
        }
    }
}